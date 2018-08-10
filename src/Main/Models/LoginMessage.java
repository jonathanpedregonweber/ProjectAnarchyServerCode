package Main.Models;

public class LoginMessage extends Message
{
	public String userName;
	
	public LoginMessage(String userName)
	{
		super("Login");
		this.userName = userName;
	}
}
