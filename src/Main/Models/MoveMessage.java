package Main.Models;

public class MoveMessage extends Message
{
    public int XCoordinate;
    public int YCoordinate;

    public MoveMessage(int xCoordinate, int yCoordinate)
    {
        super("Move");
        XCoordinate = xCoordinate;
        YCoordinate = yCoordinate;
    }
}
