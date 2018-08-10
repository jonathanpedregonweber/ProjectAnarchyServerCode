package Main.Models;

public class ChatMessage extends Message
{
	public String chatMessage;
	
	public ChatMessage(String chatMessage)
	{
		super("Chat");
		this.chatMessage = chatMessage;
	}
}
