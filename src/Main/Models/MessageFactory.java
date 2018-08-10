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
	
	private static String parse(JSONObject object, String key)
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
		String chatMessage = parse(jsonObject, "message");
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
	
	public static String getMoveMessage(int xCoordinate, int yCoordinate)
	{
		MoveMessage moveMessage = new MoveMessage(xCoordinate, yCoordinate);
		return action("hit", writer ->
		{
			writer.key("x").value(moveMessage.XCoordinate);
			writer.key("y").value(moveMessage.YCoordinate);
		});
	}
	
	public static String getStartMessage()
	{
		StartMessage startMessage = new StartMessage();
		return action("start", writer ->
		{
			startMessage.getClass();
		});
	}
	
	public static String getWinMessage()
	{
		WinMessage winMessage = new WinMessage();
		return action("win", writer ->
		{
			winMessage.getClass();
		});
	}
	
	public static Message parse(String json)
	{
		Message message = null;
		JSONTokener tokener = new JSONTokener(json);
		JSONObject object = new JSONObject(tokener);
		String type = parse(object, "type");
		if (type.equals("chat")) {
			message = setChatMessage(object);
		}
		return message;
	}
}
