package client.visualizer;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ThemeContext {
    public volatile String currentTheme ="";
    public final String DARK_THEME ="-fx-base:black";
    public final String DEFAULT_THEME ="";
}
