package server.handler.handlerImpl.helper;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

@Slf4j
public class GameTexter {
    public static String getText() {
        try {
            String Agent="Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36";
            Connection.Response response = Jsoup
                    .connect("http://rooltime.com/service/typingspeed/typing.php/")
                    .data("language", "russian")
                    .userAgent(Agent)
                    .method(Connection.Method.POST)
                    .timeout(5000)
                    .execute();
            Document doc = response.parse();

//            Document doc = Jsoup.connect("http://rooltime.com/service/typingspeed/typing.php").get();
            Element textElem = doc.getElementById("text");
            return textElem.text();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Если получить строку не получилось
        return "Советник верховного лидера Ирана Хосейн Дехган заявил, что Тегеран ответит на убийство физика-ядерщика, " +
                "главы организации исследований и инноваций Минобороны страны Мохсена Фахризаде, передает агентство Mehr.";
    }
}
