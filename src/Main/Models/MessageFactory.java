package Main.Models;

import java.io.StringWriter;
import java.util.function.Consumer;

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
	
	private static ChatMessage setChatMessage(Json json)
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
	
	private static HitMessage setHitMessage(Json json)
	{
		boolean Hit = json.getBoolean("hit");
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
	
	private static MoveMessage setMoveMessage(Json json)
	{
		int x = json.getInt("x");
		int y = json.getInt("y");
		return new MoveMessage(x, y);
	}
	
	public static String getStartMessage()
	{
		StartMessage startMessage = new StartMessage();
		return action("start", writer ->
		{
			startMessage.getClass();
		});
	}
	
	private static StartMessage setStartMessage(Json json)
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
	
	private static WinMessage setWinMessage(Json json)
	{
		json.getClass();
		return new WinMessage();
	}
	
	@SuppressWarnings("null")
	public static Message parse(String jsonString)
	{
		Message jsonMessage = null;
		Json json = new Json(jsonString);
		String type = json.getString("type");
		if (type.equals("application")) {
			Json message = json.getObject("message");
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
						jsonMessage = null;
						break;
				}
			}
			else {
				module = null;
			}
		}
		else {
			jsonMessage = setChatMessage(json);
		}
		return jsonMessage;
	}
}
