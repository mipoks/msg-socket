package server;


import server.exception.ServerException;
import server.handler.handlerImpl.*;

public class Main {
    public static void main(String[] args) throws ServerException {
        Server server = new Server(4888);
        server.registerListener(new GamePlayHandler(server));
        server.registerListener(new GameStartHandler(server));
        server.registerListener(new NameChangeHandler(server));
        server.registerListener(new RoomCloseHandler(server));
        server.registerListener(new RoomConnectHandler(server));
        server.registerListener(new RoomCreateHandler(server));
        server.registerListener(new RoomOpenHandler(server));
        server.registerListener(new RoomRandomConnectHandler(server));
        server.start();
    }
}
