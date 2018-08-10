package Main.Models;

import java.io.StringWriter;
import java.util.function.Consumer;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.json.JSONWriter;

public class MessageFactory
{
	private static String json(String type, Consumer<JSONWriter> message)
	{
		StringWriter stringWriter = new StringWriter();
		JSONWriter writer = new JSONWriter(stringWriter);
		writer.object();
		writer.key("type").value(type);
		writer.key("message");
		message.accept(writer);
		writer.endObject();
		return stringWriter.toString();
	}
	
	private static String action(String action, Consumer<JSONWriter> message)
	{
		return json("application", writer ->
		{
			writer.object();
			writer.key("action").value(action);
			message.accept(writer);
			writer.endObject();
		});
	}
	
	private static String getString(JSONObject object, String key)
	{
		String value = "";
		try {
			value = object.getString(key);
		}
		catch (org.json.JSONException exception) {
			System.out.println(exception.getMessage());
		}
		return value;
	}
	
	private static boolean getBoolean(JSONObject object, String key)
	{
		boolean value = false;
		try {
			value = object.getBoolean(key);
		}
		catch (org.json.JSONException exception) {
			System.out.println(exception.getMessage());
		}
		return value;
	}

	private static int getInt(JSONObject object, String key)
	{
		int value = 0;
		try {
			value = object.getInt(key);
		}
		catch (org.json.JSONException exception) {
			System.out.println(exception.getMessage());
		}
		return value;
	}

	private static JSONObject getObject(JSONObject object, String key)
	{
		JSONObject value = object;
		try {
			value = object.getJSONObject(key);
		}
		catch (org.json.JSONException exception) {
			System.out.println(exception.getMessage());
		}
		return value;
	}
	
	public static String getChatMessage(String messageText)
	{
		ChatMessage chatMessage = new ChatMessage(messageText);
		return json("chat", writer ->
		{
			writer.value(chatMessage.ChatMessage);
		});
	}
	
	private static ChatMessage setChatMessage(JSONObject jsonObject)
	{
		String chatMessage = getString(jsonObject, "message");
		return new ChatMessage(chatMessage);
	}
	
	public static String getHitMessage(boolean hit)
	{
		HitMessage hitMessage = new HitMessage(hit);
		return action("hit", writer ->
		{
			writer.key("hit").value(hitMessage.Hit);
		});
	}
	
	private static HitMessage setHitMessage(JSONObject jsonObject)
	{
		boolean Hit = getBoolean(jsonObject, "hit");
		return new HitMessage(Hit);
	}
	
	public static String getMoveMessage(int xCoordinate, int yCoordinate)
	{
		MoveMessage moveMessage = new MoveMessage(xCoordinate, yCoordinate);
		return action("move", writer ->
		{
			writer.key("x").value(moveMessage.XCoordinate);
			writer.key("y").value(moveMessage.YCoordinate);
		});
	}
	
	private static MoveMessage setMoveMessage(JSONObject jsonObject)
	{
		int x = getInt(jsonObject, "x");
		int y = getInt(jsonObject, "y");
		return new MoveMessage(x,y);
	}
	
	public static String getStartMessage()
	{
		StartMessage startMessage = new StartMessage();
		return action("start", writer ->
		{
			startMessage.getClass();
		});
	}
	
	private static StartMessage setStartMessage(JSONObject jsonObject)
	{
		jsonObject.getClass();
		return new StartMessage();
	}
	
	public static String getWinMessage()
	{
		WinMessage winMessage = new WinMessage();
		return action("win", writer ->
		{
			winMessage.getClass();
		});
	}
	
	private static WinMessage setWinMessage(JSONObject jsonObject)
	{
		jsonObject.getClass();
		return new WinMessage();
	}
	
	public static Message parse(String json)
	{
		Message jsonMessage;
		JSONTokener tokener = new JSONTokener(json);
		JSONObject object = new JSONObject(tokener);
		String type = getString(object, "type");
		if (type.equals("application")) {
			JSONObject message = getObject(object, "message");
			String action = getString(message, "action");
			switch (action)
			{
				case "hit":
					jsonMessage = setHitMessage(message);
					break;
				case "move":
					jsonMessage = setMoveMessage(message);
					break;
				case "start":
					jsonMessage = setStartMessage(message);
					break;
				case "win":
					jsonMessage = setWinMessage(message);
					break;
				default:
					jsonMessage = null;
					break;
			}
		}
		else {
			jsonMessage = setChatMessage(object);
		}
		return jsonMessage;
	}
}
