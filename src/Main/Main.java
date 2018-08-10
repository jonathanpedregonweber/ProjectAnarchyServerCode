package Main;

import Main.Handlers.ServerHandler;
import Main.Models.HitMessage;
import Main.Models.MessageFactory;

import java.io.IOException;
import java.net.Socket;

public class Main {

    public static void main(String[] args)
    {
        String serverLocation = "ec2-18-207-150-67.compute-1.amazonaws.com";
        int port =  8989;
        try
        {
            Socket socket = new Socket(serverLocation, port);
            ServerHandler handler = new ServerHandler(socket);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
