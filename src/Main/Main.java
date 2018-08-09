package Main;

import Main.Models.*;

public class Main {

    public static void main(String[] args) {
	// write your code here

        System.out.println(MessageFactory.getHitMessage(new HitMessage(true)));
        System.out.println(MessageFactory.getChatMessage(new ChatMessage("This is the chat text")));
        System.out.println(MessageFactory.getMoveMessage(new MoveMessage(0, 0)));
        System.out.println(MessageFactory.getStartMessage(new StartMessage()));
        System.out.println(MessageFactory.getWinMessage(new WinMessage()));

    }
}
