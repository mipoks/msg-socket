package client.logic;

import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
@Slf4j
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
