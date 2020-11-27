package server;


import server.exception.ServerException;
import server.handler.implementation.NameChangeHandler;
import server.handler.implementation.RoomConnectHandler;
import server.handler.implementation.RoomCreateHandler;

public class Main {
    public static void main(String[] args) throws ServerException {
        Server server = new Server(4888);
        server.registerListener(new RoomConnectHandler(server));
        server.registerListener(new RoomCreateHandler(server));
        server.registerListener(new NameChangeHandler(server));
        server.start();
    }
}
