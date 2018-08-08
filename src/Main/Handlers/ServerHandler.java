package Main.Handlers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerHandler
{
	public PrintWriter Writer;
	public BufferedReader Reader;
	
	public ServerHandler(Socket socket)
	{
		try {
			this.Reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			this.Writer = new PrintWriter(socket.getOutputStream());
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void SendChatMessage(@SuppressWarnings("unused") String chatMessage)
	{
		//
	}
	
	public void SendHitMessage(@SuppressWarnings("unused") boolean hit)
	{
		//
	}
	
	public void SendMoveMessage(@SuppressWarnings("unused") int xCoordinate, @SuppressWarnings("unused") int yCoordinate)
	{
		//
	}
	
	public void SendStartMessage()
	{
		//
	}
	
	public void WinMessage()
	{
		//
	}
}
