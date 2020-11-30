package server.handler.implementation;

import lombok.extern.slf4j.Slf4j;
import server.Server;
import server.exception.ServerException;
import server.handler.Handler;
import server.handler.implementation.helper.GameTexter;
import server.handler.implementation.helper.ObjectSerializer;
import server.protocol.*;
@Slf4j
public class GameStartHandler implements Handler {
    private Server server;

    public GameStartHandler(Server server) {
        this.server = server;
    }

    @Override
    public void handleMessage(Client client, Message message) {
        System.out.println("WEWEWEWEW");
        if (client.getRoom().isPresent()) {
            Room room = client.getRoom().get();
            System.out.println("WEWEWEWEW2");
            if (room.getRoomOwner().isPresent() && room.getRoomOwner().get() == client) {
                System.out.println("WEWEWEWEW3");
                Game game = null;
                if (Game.findByRoom(room).isPresent()) {
                    System.out.println("WEWEWEWEW4");
                    Client winner = Game.findByRoom(room).get().getWinner();
                    if (winner != null) {
                        System.out.println("WEWEWEWEW5");
                        game = new Game(GameTexter.getText(), room);
                    }

                } else {
                    System.out.println("WEWEWEWEW6");
                    game = new Game(GameTexter.getText(), room);
                }
                game.start();

                System.out.println("WEWEWEWEW7");
                Message msg = Message.createMessage(Type.GAME_START_ANSWER,
                        ObjectSerializer.toByteArray(game.getGameText()));
                room.sendMessage(msg);
            }
        }
    }

    @Override
    public int getType() {
        return Type.GAME_START;
    }
}
