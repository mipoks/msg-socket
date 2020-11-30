package server;

import lombok.extern.slf4j.Slf4j;
import server.exception.ServerException;
import server.handler.Handler;
import server.handler.handlerImpl.helper.ObjectSerializer;
import server.protocol.Client;
import server.protocol.Message;
import server.protocol.Room;
import server.protocol.Type;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
@Slf4j
public class Server {
    protected ServerSocket serverSocket;
    protected int port;
    protected boolean started;
    protected List<Handler> listeners;

    protected ExecutorService threadPool = Executors.newFixedThreadPool(50);

    protected Collection<Room> rooms;

    public Server(int port) {
        this.port = port;
        this.started = false;
        this.listeners = new ArrayList<>();
        this.rooms = new ArrayList<>();
    }


    public void registerListener(Handler listener) throws ServerException {
        if(started){
            throw new ServerException("Server has been started already.");
        }
        this.listeners.add(listener);
    }

    public void start() throws ServerException {
        try{
            // Start server
            serverSocket = new ServerSocket(this.port);
            started = true;

            // Proccess connections
            while(true){
                Socket s = serverSocket.accept();
                handleConnection(s);
            }
        }
        catch(IOException ex){
            throw new ServerException("Problem with server starting.", ex);
        }
    }

    protected void handleConnection(Socket socket) throws ServerException{
        Client client = new Client(Client.generateName(), socket);
        Message msgId = Message.createMessage(Type.CLIENT_ID, ObjectSerializer.toByteArray(new Integer(client.getId())));
        sendMessage(client, msgId);
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    Message message = null;
                    try {
                        message = Message.readMessage(socket.getInputStream());

                        log.info("New message: {}",Message.toString(message));


                        for (Handler listener : listeners) {
                            if (message.getType() == listener.getType()) {
                                listener.handleMessage(client, message);
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        });

    }


    public void sendMessage(Client client, Message message) throws ServerException{
        if(!started){
            throw new ServerException("Server hasn't been started yet.");
        }
        //ToDo: check if this socket is from our pull
        try{
            Socket socket = client.getSocket();
            socket.getOutputStream().write(Message.getBytes(message));
            socket.getOutputStream().flush();
        } catch (IOException ex) {
            throw new ServerException("Can't send message.", ex);
        }
    }
}
