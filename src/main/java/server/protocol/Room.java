package server.protocol;

import javafx.util.Pair;
import server.exception.ServerException;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;

public class Room {
    public static final int ROOM_UNIQUE_LENGTH = 4;
    public static final int MAX_CLIENT = 1000;

    private static ArrayList<Pair<String, Room>> uniqueString = new ArrayList<>();
    private static final String CHARACTERS = "QWERTYUIOPASDFGHJKLZXCVBNM1234567890";

    public synchronized static String createRoomUniqueString() {
        char[] text = new char[ROOM_UNIQUE_LENGTH];
        do {
            for (int i = 0; i < ROOM_UNIQUE_LENGTH; i++) {
                text[i] = CHARACTERS.charAt((int) (Math.random() * CHARACTERS.length()));
            }
        } while (getRoomByUnique(new String(text)) != null);
        return new String(text);
    }

    public static Room getRoomByUnique(String roomUnique) {
        Room room = null;
        for (Pair<String, Room> pair : uniqueString) {
            if (pair.getKey().equals(roomUnique)) {
                room = pair.getValue();
            }
        }
        return room;
    }

    public static Room getOpenRoom() {
        for (int i = 0; i < uniqueString.size(); i++) {
            Room room = uniqueString.get(i).getValue();
            if (room.isPublicity() == true && room.getClientsCount() < Room.MAX_CLIENT) {
                return room;
            }
        }
        return new Room(Room.createRoomUniqueString());
    }

    public int getClientsCount() {
        return clients.size();
    }

    private String roomUniqueString;
    private Collection<Client> clients;
    private boolean publicity;

    public Room(String roomUniqueString) {
        this.roomUniqueString = roomUniqueString;
        this.clients = new ArrayList<>();
        this.publicity = true;
        uniqueString.add(new Pair<>(roomUniqueString, this));
    }

    public Room(String roomUniqueString, boolean publicity) {
        this.roomUniqueString = roomUniqueString;
        this.clients = new ArrayList<>();
        this.publicity = publicity;
        uniqueString.add(new Pair<>(roomUniqueString, this));
    }

    public boolean isPublicity() {
        return publicity;
    }

    public void setPublicity(boolean publicity) {
        this.publicity = publicity;
    }

    public synchronized boolean addClient(Client client) {
        if (getClientsCount() < Room.MAX_CLIENT) {
            if (!clients.contains(client)) {
                clients.add(client);
                return true;
            }
        }
        return false;
    }

    public void removeClient(Client client) {
        clients.remove(client);
    }

    public void sendMessage(Message message) throws ServerException {

        byte[] rawMessage = Message.getBytes(message);
        for (Client client : clients) {
            try {
                Socket socket = client.getSocket();
                socket.getOutputStream().write(rawMessage);
                socket.getOutputStream().flush();
            } catch (IOException ex) {
                ex.printStackTrace();
//                throw new ServerException("Can't send message. To client: " + client, ex);
            }
        }
        System.out.println("SENDED TO " + clients.size() + " CLIENTS OF ROOM " + roomUniqueString);

    }

    public void sendMessage(Client from, Message message) throws ServerException {
        byte[] rawMessage = Message.getBytes(message);
        if (clients.contains(from)) {
            for (Client client : clients) {
                try {
                    Socket socket = client.getSocket();
                    socket.getOutputStream().write(rawMessage);
                    socket.getOutputStream().flush();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            System.out.println("SENDED TO " + clients.size() + " CLIENTS OF ROOM " + roomUniqueString);
        }
    }

    public String getRoomUniqueString() {
        return roomUniqueString;
    }
}
