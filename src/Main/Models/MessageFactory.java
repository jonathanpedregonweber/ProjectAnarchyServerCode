package Main.Models;

import java.io.StringWriter;
import java.util.function.Consumer;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.json.JSONWriter;

public class MessageFactory
{
	private static final String MODULE = "ProjectAnarchy";
	
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
	
	public static String getChatMessage(String messageText)
	{
		ChatMessage chatMessage = new ChatMessage(messageText);
		return json("chat", writer ->
		{
			writer.value(chatMessage.ChatMessage);
		});
	}
	
	private static ChatMessage setChatMessage(JSONObject json)
	{
		String chatMessage = json.getString("message");
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
	
	private static HitMessage setHitMessage(JSONObject json)
	{
		boolean hit = json.getBoolean("hit");
		return new HitMessage(hit);
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
	
	private static MoveMessage setMoveMessage(JSONObject json)
	{
		int xCoordinate = json.getInt("x");
		int yCoordinate = json.getInt("y");
		return new MoveMessage(xCoordinate, yCoordinate);
	}
	
	public static String getStartMessage()
	{
		StartMessage startMessage = new StartMessage();
		return action("start", writer ->
		{
			startMessage.getClass();
		});
	}
	
	private static StartMessage setStartMessage(JSONObject json)
	{
		json.getClass();
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
	
	private static WinMessage setWinMessage(JSONObject json)
	{
		json.getClass();
		return new WinMessage();
	}
	
	public static Message parse(String jsonString)
	{
		Message jsonMessage = new IgnoreMessage();
		try {
			JSONTokener tokener = new JSONTokener(jsonString);
			JSONObject json = new JSONObject(tokener);
			String type = json.getString("type");
			if (type.equals("application")) {
				JSONObject message = json.getJSONObject("message");
				String module = message.getString("module");
				if (module.equals(MODULE)) {
					String action = message.getString("action");
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
							break;
					}
				}
			}
			else {
				jsonMessage = setChatMessage(json);
			}
		}
		catch (org.json.JSONException exception) {
			System.out.println(exception.getMessage());
			jsonMessage = new IgnoreMessage();
		}
		return jsonMessage;
	}
}
