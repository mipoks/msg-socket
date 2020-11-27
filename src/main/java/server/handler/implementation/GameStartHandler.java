package server.handler.implementation;

import server.Server;
import server.exception.ServerException;
import server.handler.Handler;
import server.protocol.Client;
import server.protocol.Message;
import server.protocol.Room;
import server.protocol.Type;

public class GameStartHandler implements Handler {
    private Server server;

    public GameStartHandler(Server server) {
        this.server = server;
    }

    @Override
    public void handleMessage(Client client, Message message) {
        if (client.getRoom().isPresent()) {
            Room room = client.getRoom().get();
            if (room.getRoomOwner().isPresent()) {
                if (room.getRoomOwner().get() == client) {

                    //ToDo начать игру
                    room.sendMessage(message);
//                    message.setType(Type.GAME_START_ANSWER);
                }
            }
        }
    }

    @Override
    public int getType() {
        return Type.GAME_START;
    }
}
