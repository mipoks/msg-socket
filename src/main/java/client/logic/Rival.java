package client.logic;

import java.io.Serializable;

public class Rival implements Serializable {
    private int progress; //in symbols
    private String name;

    public Rival(String name) {
        this.name = name;
        progress = 0;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
