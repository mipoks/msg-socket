package typergame.client.visualizer;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ThemeContext {
    public volatile String currentTheme ="";
    public final String DARK_THEME ="-fx-base:black";
    public final String DEFAULT_THEME ="";
    public static void checkTheme(Scene scene){
        if (ThemeContext.currentTheme.equals(ThemeContext.DEFAULT_THEME)) {


            scene.getStylesheets().remove("css/DarkTheme.css");
        }else  if (ThemeContext.currentTheme.equals(ThemeContext.DARK_THEME)) {

            scene.getStylesheets().add("css/DarkTheme.css");
        }
    }
}
