package Main.Models;

import java.io.StringWriter;
import java.util.function.Consumer;

import org.json.JSONObject;
import org.json.JSONTokener;
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
			writer.key("module").value(MODULE);
			writer.key("action").value(action);
			message.accept(writer);
			writer.endObject();
		});
	}
	
	public static String getChatMessage(ChatMessage chatMessage)
	{
		return getChatMessage(chatMessage.chatMessage, chatMessage.username);
	}
	
	public static String getChatMessage(String chatMessage)
	{
		return json("chat", writer ->
		{
			writer.value(chatMessage);
		});
	}
	
	public static String getChatMessage(String chatMessage, String username)
	{
		return json("chat", writer ->
		{
			writer.value(chatMessage);
			if (!username.isEmpty()) {
				writer.key("username").value(username);
			}
		});
	}
	
	private static ChatMessage setChatMessage(JSONObject json)
	{
		String chatMessage = json.getString("message");
		return new ChatMessage(chatMessage);
	}
	
	public static String getHitMessage(HitMessage hitMessage)
	{
		return getHitMessage(hitMessage.hit);
	}
	
	public static String getHitMessage(boolean hit)
	{
		return action("hit", writer ->
		{
			writer.key("hit").value(hit);
		});
	}
	
	private static HitMessage setHitMessage(JSONObject json)
	{
		boolean hit = json.getBoolean("hit");
		return new HitMessage(hit);
	}
	
	public static String getLoginMessage(LoginMessage loginMessage)
	{
		return getLoginMessage(loginMessage.username);
	}
	
	public static String getLoginMessage(String username)
	{
		return getLoginMessage(username, "");
	}
	
	public static String getLoginMessage(String username, String password)
	{
		return json("login", writer ->
		{
			writer.object();
			writer.key("username").value(username);
			if (!password.isEmpty()) {
				writer.key("password").value(password);
			}
			writer.endObject();
		});
	}
	
	@SuppressWarnings("unused")
	private static LoginMessage setLoginMessage(JSONObject json)
	{
		String userName = json.getString("message");
		return new LoginMessage(userName);
	}
	
	public static String getMoveMessage(MoveMessage moveMessage)
	{
		return getMoveMessage(moveMessage.xCoordinate, moveMessage.yCoordinate);
	}
	
	public static String getMoveMessage(int xCoordinate, int yCoordinate)
	{
		return action("move", writer ->
		{
			writer.key("x").value(xCoordinate);
			writer.key("y").value(yCoordinate);
		});
	}
	
	private static MoveMessage setMoveMessage(JSONObject json)
	{
		int xCoordinate = json.getInt("x");
		int yCoordinate = json.getInt("y");
		return new MoveMessage(xCoordinate, yCoordinate);
	}
	
	public static String getStartMessage()
	{
		return action("start", writer ->
		{
			//
		});
	}
	
	private static StartMessage setStartMessage(JSONObject json)
	{
		json.getClass();
		return new StartMessage();
	}
	
	public static String getWinMessage()
	{
		return action("win", writer ->
		{
			//
		});
	}
	
	private static WinMessage setWinMessage(JSONObject json)
	{
		json.getClass();
		return new WinMessage();
	}
	
	public static Message parse(String jsonString)
	{
		try
		{
			JSONTokener tokener = new JSONTokener(jsonString);
			JSONObject json = new JSONObject(tokener);
			
			if (json.has("type") && json.getString("type").equals("chat"))
			{
				return setChatMessage(json);
			}
			
			if (json.has("module") && json.getString("module").equals("ProjectAnarchy"))
			{
				
				String action = json.getString("action");
				
				if (action.equals("hit"))
				{
					return setHitMessage(json);
				}
				else if (action.equals("move"))
				{
					return setMoveMessage(json);
				}
				else if (action.equals("start"))
				{
					return setStartMessage(json);
				}
				else if (action.equals("win"))
				{
					return setWinMessage(json);
				}
				else
				{
					return new IgnoreMessage();
				}
			}
		}
		catch (org.json.JSONException exception)
		{
			System.out.println(exception.getMessage());
		}
		
		return new IgnoreMessage();
	}
}
