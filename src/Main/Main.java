package Main;

import Main.Handlers.ServerHandler;
import Main.Models.ChatMessage;
import Main.Models.HitMessage;
import Main.Models.MessageFactory;
import Main.Models.MoveMessage;
import Main.Models.StartMessage;
import Main.Models.WinMessage;

import java.io.IOException;
import java.net.Socket;

public class Main
{
	public static void main(String[] args)
	{
		testMessageFactory();
		String serverLocation = "ec2-18-207-150-67.compute-1.amazonaws.com";
		int port = 8989;
		try (Socket socket = new Socket(serverLocation, port)) {
			ServerHandler handler = new ServerHandler(socket);
			handler.getClass();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void testMessageFactory()
	{
		String chatString = MessageFactory.getChatMessage("This is the chat text");
		String hitString = MessageFactory.getHitMessage(true);
		String moveString = MessageFactory.getMoveMessage(0, 0);
		String startString = MessageFactory.getStartMessage();
		String winString = MessageFactory.getWinMessage();
		System.out.println(chatString);
		System.out.println(hitString);
		System.out.println(moveString);
		System.out.println(startString);
		System.out.println(winString);
		@SuppressWarnings("unused")
		ChatMessage chatMessage = (ChatMessage) MessageFactory.parse(chatString);
		@SuppressWarnings("unused")
		HitMessage hitMessage = (HitMessage) MessageFactory.parse(hitString);
		@SuppressWarnings("unused")
		MoveMessage moveMessage = (MoveMessage) MessageFactory.parse(moveString);
		@SuppressWarnings("unused")
		StartMessage startMessage = (StartMessage) MessageFactory.parse(startString);
		@SuppressWarnings("unused")
		WinMessage winMessage = (WinMessage) MessageFactory.parse(winString);
	}
}
