package Main.Models;

import java.io.StringWriter;
import java.util.function.Consumer;

import org.json.JSONWriter;

public class MessageFactory
{
	public static String json(String type, Consumer<JSONWriter> message)
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

	public static String getChatMessage()
	
	{
		String chatMessage = "";
		return new ChatMessage(chatMessage).toString();
	}
	
	public static String getHitMessage()
	{
		boolean hit = false;
		return new HitMessage(hit).toString();
	}
	
	public static String getMoveMessage()
	{
		int xCoordinate = 0;
		int yCoordinate = 0;
		return new MoveMessage(xCoordinate, yCoordinate).toString();
	}
	
	public static String getStartMessage()
	{
		return new StartMessage().toString();
	}
	
	public static String getWinMessage()
	{
		return new WinMessage().toString();
	}
}