package server.handler.handlerImpl;

import javafx.util.Pair;
import lombok.extern.slf4j.Slf4j;
import server.Server;
import server.handler.Handler;
import server.handler.handlerImpl.helper.ObjectDeserializer;
import server.handler.handlerImpl.helper.ObjectSerializer;
import server.model.Client;
import server.model.Game;
import server.model.Room;
import server.protocol.*;

import java.util.Optional;
@Slf4j
public class GamePlayHandler implements Handler {

    private Server server;

    public GamePlayHandler(Server server) {
        this.server = server;
    }

    @Override
    public void handleMessage(Client client, Message message) {
        if (client.getRoom().isPresent()) {
            Room room = client.getRoom().get();
            room.sendMessage(client, message);

            String cchar = (String) ObjectDeserializer.fromByteArray(message.getData());
            log.info(cchar);
            Optional<Game> optionalGame = Game.findByRoom(room);

            int ans = 0;
            if (optionalGame.isPresent())
                ans = optionalGame.get().save(client, cchar);
            else
                return;
            Message msg = Message.createMessage(Type.GAME_PLAY,
                    ObjectSerializer.toByteArray(new Pair<Pair<Integer, String>, Integer>
                            (new Pair<Integer, String>(client.getId(), client.getName()), ans)));
            room.sendMessage(msg);
            if (ans == optionalGame.get().getGameText().length() + 1) {
                Message msgGameEnd = Message.createMessage(Type.GAME_END, ObjectSerializer.
                        toByteArray(new Pair<Integer, String>(client.getId(), client.getName())));
                room.sendMessage(msgGameEnd);
            }
        }
    }

    @Override
    public int getType() {
        return Type.GAME_PLAY;
    }
}
