package Main.Models;

public class HitMessage extends Message
{
	public boolean hit;
	
	public HitMessage(boolean hit)
	{
		super("Hit");
		this.hit = hit;
	}
}
