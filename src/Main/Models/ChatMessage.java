package Main.Models;

public class ChatMessage extends Message
{
	public String chatMessage;
	public String username;
	
	public ChatMessage(String chatMessage)
	{
		super("Chat");
		this.chatMessage = chatMessage;
		this.username = "";
	}
	public ChatMessage(String chatMessage, String username)
	{
		super("Chat");
		this.chatMessage = chatMessage;
		this.username = username;
	}
}
