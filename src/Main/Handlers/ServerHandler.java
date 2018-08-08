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
        try
        {
            Reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Writer = new PrintWriter(socket.getOutputStream());
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void SendChatMessage(String chatMessage)
    {

    }

    public void SendHitMessage(boolean hit)
    {

    }

    public void SendMoveMessage(int xCoordinate, int yCoordinate)
    {

    }

    public void SendStartMessage()
    {

    }

    public void WinMessage()
    {

    }
}
