
import client.message.MessageCreater;
import client.message.Text;
import client.protocol.Message;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, IllegalAccessException {
        Message msg = MessageCreater.createTextMsg(new Text("Hello world"));
        System.out.println();
    }
}
