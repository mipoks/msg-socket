package server.handler.implementation;

import server.Server;
import server.exception.ServerException;
import server.handler.Handler;
import server.handler.implementation.helper.GameTexter;
import server.handler.implementation.helper.ObjectSerializer;
import server.protocol.*;

public class GameStartHandler implements Handler {
    private Server server;

    public GameStartHandler(Server server) {
        this.server = server;
    }

    @Override
    public void handleMessage(Client client, Message message) {
        if (client.getRoom().isPresent()) {
            Room room = client.getRoom().get();
            if (room.getRoomOwner().isPresent() && room.getRoomOwner().get() == client) {
                Game game = null;
                if (Game.findByRoom(room).isPresent()) {
                    Client winner = Game.findByRoom(room).get().getWinner();
                    if (winner != null) {
                        game = new Game(GameTexter.getText(), room);
                    }

                } else {
                    game = new Game(GameTexter.getText(), room);
                }
                game.setStarted(true);
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
