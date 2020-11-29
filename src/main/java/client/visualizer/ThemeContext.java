package client.visualizer;

import javafx.scene.layout.VBox;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ThemeContext {
    public volatile String currentTheme ="";
    public final String DARK_THEME ="-fx-base:black";
    public final String DEFAULT_THEME ="";
    public static void checkTheme(VBox body){
        if (ThemeContext.currentTheme.equals(ThemeContext.DEFAULT_THEME)) {


            body.setStyle(ThemeContext.DEFAULT_THEME);
        }else  if (ThemeContext.currentTheme.equals(ThemeContext.DARK_THEME)) {

            body.setStyle(ThemeContext.DARK_THEME);
        }
    }
}
