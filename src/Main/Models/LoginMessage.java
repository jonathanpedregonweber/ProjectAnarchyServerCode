package Main.Models;

public class LoginMessage extends Message
{
	public String username;
	public String password;
	
	public LoginMessage(String username)
	{
		super("Login");
		this.username = username;
		this.password = "";
	}

	public LoginMessage(String username, String password)
	{
		super("Login");
		this.username = username;
		this.password = password;
	}
}
