package typergame.server.handler.handlerImpl;

import javafx.util.Pair;
import lombok.extern.slf4j.Slf4j;
import typergame.server.Server;
import typergame.server.exception.ServerException;
import typergame.server.handler.Handler;
import typergame.server.handler.handlerImpl.helper.ObjectSerializer;
import typergame.server.handler.handlerImpl.helper.ObjectDeserializer;
import typergame.server.model.Client;
import typergame.protocol.Message;
import typergame.server.model.Room;
import typergame.protocol.Type;

import java.util.Collection;
import java.util.Optional;
@Slf4j
public class RoomConnectHandler implements Handler {

    private Server server;

    public RoomConnectHandler(Server server) {
        this.server = server;
    }

    public void handleMessage(Client client, Message message) {
        log.info("BLABALBAL");
        log.info("string res : {}", (String)(ObjectDeserializer.fromByteArray(message.getData())));
        try {
            //ToDo обернуть new String() в try catch

            String roomName = (String)(ObjectDeserializer.fromByteArray(message.getData()));
            System.out.println(roomName + " ROOM ON1");
            Optional<Room> room = Room.getRoomByUnique(roomName);
            if (!room.isPresent()) {
                System.out.println(roomName + " ROOM ON2");
                return;
            }
            System.out.println(roomName + " ROOM ON3");
            if (room.get().getClients().contains(client))
                return;
            room.get().addClient(client);
            client.setRoom(room.get());


            Message msg = Message.createMessage(Type.ROOM_CONNECT, ObjectSerializer.toByteArray(
                    new Pair<Integer, String>(client.getId(), client.getName())));
            System.out.println(roomName + " ROOM ON4");
            room.get().sendMessageExcept(client, msg);

            msg.setType(Type.ROOM_CONNECT_ANSWER);
            msg.setData(ObjectSerializer.toByteArray(new Pair<Integer, String>(client.getId(), client.getName())));
            server.sendMessage(client, msg);
            Collection<Client> clients = room.get().getClients();
            msg.setType(Type.ROOM_CONNECT);
            for (Client client1 : clients) {
                if (!client.equals(client1)) {
                    msg.setData(ObjectSerializer.toByteArray(
                            new Pair<Integer, String>(client1.getId(), client1.getName())));
                    server.sendMessage(client, msg);
                }
            }
        } catch (ServerException ex) {
            //Add some catch implementation
        }
    }

    @Override
    public int getType() {
        return Type.ROOM_CONNECT;
    }
}
