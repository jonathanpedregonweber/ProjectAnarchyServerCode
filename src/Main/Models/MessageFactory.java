package Main.Models;

public class MessageFactory
{
	public static ChatMessage ChatMessage()
	{
		String chatMessage = "";
		return new ChatMessage(chatMessage);
	}
	
	public static HitMessage HitMessage()
	{
		boolean hit = false;
		return new HitMessage(hit);
	}
	
	public static MoveMessage MoveMessage()
	{
		int xCoordinate = 0;
		int yCoordinate = 0;
		return new MoveMessage(xCoordinate, yCoordinate);
	}
	
	public static StartMessage StartMessage()
	{
		return new StartMessage();
	}
	
	public static WinMessage WinMessage()
	{
		return new WinMessage();
	}
}