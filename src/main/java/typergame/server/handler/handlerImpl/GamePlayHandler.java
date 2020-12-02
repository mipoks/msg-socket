package typergame.server.handler.handlerImpl;

import javafx.util.Pair;
import lombok.extern.slf4j.Slf4j;
import typergame.protocol.MExtendedPair;
import typergame.protocol.Message;
import typergame.protocol.Type;
import typergame.server.Server;
import typergame.server.handler.Handler;
import typergame.server.handler.handlerImpl.helper.ObjectDeserializer;
import typergame.server.handler.handlerImpl.helper.ObjectSerializer;
import typergame.server.model.Client;
import typergame.server.model.Game;
import typergame.server.model.Room;

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

            log.info(optionalGame.isPresent() + " ОН СУЩ");
            int ans = 0;
            if (optionalGame.isPresent())
                ans = optionalGame.get().save(client, cchar);
            else
                return;
            Message msg = Message.createMessage(Type.GAME_PLAY,
                    ObjectSerializer.toByteArray(new Pair<Integer, Integer>
                            (client.getId(), ans)));
            room.sendMessage(msg);
            
            if (ans + 1 == optionalGame.get().getGameText().length()) {
                Message msgGameEnd = Message.createMessage(Type.GAME_END, ObjectSerializer.
                        toByteArray(new MExtendedPair<Integer, String>(client.getId(), client.getName(), client.getCpersec())));
                room.sendMessage(msgGameEnd);
                log.info("Игра окончена" );
            }
        }
    }

    @Override
    public int getType() {
        return Type.GAME_PLAY;
    }
}
