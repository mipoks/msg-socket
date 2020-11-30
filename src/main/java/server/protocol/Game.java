package server.protocol;

import lombok.extern.slf4j.Slf4j;
import server.handler.EventListener;

import java.lang.reflect.Array;
import java.util.*;

@Slf4j
public class Game {
    private String gameText;
    private Client winner;

    private HashMap<Client, Integer> gameState;
    private Room room;
    private boolean started;
    private ArrayList<EventListener> listeners;

    public Game(String gameText, Room room) {
        this.gameText = gameText;
        this.room = room;
        games.put(room, this);
        started = false;
        listeners = new ArrayList<>();
    }

    private static HashMap<Room, Game> games = new HashMap<>();

    public String getGameText() {
        return gameText;
    }

    public void setGameText(String gameText) {
        this.gameText = gameText;
    }

    public final Client getWinner() {
        return winner;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public boolean isStarted() {
        return started;
    }

    public void start() {
        this.started = false;
        Collection<Client> clients = room.getClients();
        for (Client client : clients) {
            client.setStartTime(new Date());
        }
    }

    public int save(Client client, String cchar) {
        if (!started || cchar.length() < 1)
            return 0;
        int num = 0;
        if (gameState.containsKey(client)) {
            num = gameState.get(client);
            if (cchar.charAt(0) == gameText.charAt(num + 1))
                gameState.put(client, ++num);
            if (num == gameText.length()) {
                if (winner == null) {
                    winner = client;
                    onWinnerSetted();
                }
                client.setEndTime(new Date());
            }
        } else {
            if (cchar.charAt(0) == gameText.charAt(0)) {
                gameState.put(client, ++num);
            }
        }
        return num;
    }

    public static Optional<Game> findByRoom(Room room) {
        Game game = null;
        if (games.containsKey(room)) {
            game = games.get(room);
        }
        return Optional.ofNullable(game);
    }


    public void addEventListener(EventListener eventListener) {
        listeners.add(eventListener);
    }

    private void onWinnerSetted() {
        for (EventListener eventListener : listeners)
            eventListener.onEventAction(winner);
    }
}
