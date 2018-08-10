package Main.Handlers;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import Main.Models.*;

public class ServerHandler
{
	private static PrintWriter Writer;
	
	public ServerHandler(Socket socket)
	{
		try {
			ServerHandler.Writer = new PrintWriter(socket.getOutputStream());
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void SendChatMessage(String chatMessage)
	{
		Writer.println(MessageFactory.getChatMessage(chatMessage));
	}
	
	public void SendHitMessage(boolean hit)
	{
		Writer.println(MessageFactory.getHitMessage(hit));
	}
	
	public void SendMoveMessage(int xCoordinate, int yCoordinate)
	{
		Writer.println(MessageFactory.getMoveMessage(xCoordinate, yCoordinate));
	}
	
	public void SendStartMessage()
	{
		Writer.println(MessageFactory.getStartMessage());
	}
	
	public void WinMessage()
	{
		Writer.println(MessageFactory.getWinMessage());
	}
}
