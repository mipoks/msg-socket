package client.listener;

import client.logic.Client;
import client.protocol.Message;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class ServerMessageListener implements Runnable {

    private Socket socket;
    private Client client;

    public ServerMessageListener(Client client, Socket socket) {
        this.client = client;
        this.socket = socket;
    }

    public void run() {
        try (InputStream in = socket.getInputStream()) {
            while (true) {
                Message message = Message.readMessage(in);
                client.handleMessage(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
