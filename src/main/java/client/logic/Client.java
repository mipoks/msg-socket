package client.logic;

import client.handler.Handler;
import client.listener.ServerMessageListener;
import client.protocol.Message;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;



@Data
@Slf4j
public class Client {

    protected final InetAddress address;
    protected final int port;
    protected Socket socket;
    protected List<Handler> listeners;

    protected Room room;

    public InetAddress getAddress() {
        return address;
    }

    public void registerListener(Handler listener) {
        this.listeners.add(listener);
    }

    public void connect() throws Exception {
        try {
            socket = new Socket(address, port);
        } catch (IOException ex) {
            throw new Exception("Can't connect.", ex);
        }
    }
    public void start() {
        room = new Room();
        Thread thread = new Thread(new ServerMessageListener(this,  socket));
        thread.start();
    }

    public void sendMessage(Message message) throws Exception {
        try {
            socket.getOutputStream().write(Message.getBytes(message));
            socket.getOutputStream().flush();
            //return Message.readMessage(socket.getInputStream());
        } catch (IOException ex) {
            throw new Exception("Can't send message.", ex);
        }
    }

    public void handleMessage(Message message) {
        // Every connection will wait creating and handling of message
//        System.out.println("New message:");
//        System.out.println(Message.toString(message));
        for(Handler listener : listeners){
            if(message.getType() == listener.getType()){
                // One by one! Another left listeners will wait current
                // Another thread could be created here or before for every Listener
                listener.handleMessage(message);
            }
        }
    }

    public Client(InetAddress address, int port) {
        this.address = address;
        this.port = port;
        this.listeners = new ArrayList<>();
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}

