package typergame.client.util;


import lombok.Getter;
import lombok.Setter;
import typergame.client.model.Gamer;

import java.util.HashMap;

public class ColorMixer {
    @Setter
    @Getter
    private static HashMap<Gamer,String> libraryColor = new HashMap<>();

}
