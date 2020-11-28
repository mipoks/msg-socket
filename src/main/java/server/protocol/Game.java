package server.protocol;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Optional;

public class Game {
    private String gameText;
    private Client winner;

    private HashMap<Client, Integer> gameState;
    private Room room;
    private boolean started;

    public Game(String gameText, Room room) {
        this.gameText = gameText;
        this.room = room;
        games.put(room, this);
        started = false;
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

    public void setWinner(Client winner) {
        this.winner = winner;
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

    public void setStarted(boolean started) {
        this.started = started;
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
                winner = client;
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
}
