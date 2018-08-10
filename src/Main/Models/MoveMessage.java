package Main.Models;

public class MoveMessage extends Message
{
	public int xCoordinate;
	public int yCoordinate;
	
	public MoveMessage(int xCoordinate, int yCoordinate)
	{
		super("Move");
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
	}
}
