package client.model;

import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
@Slf4j
public class Gamer implements Serializable {
    private int progress; //in symbols
    private int id;
    private String name;

    public Gamer(int id, String name) {
        this.name = name;
        progress = 0;
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gamer gamer = (Gamer) o;
        return id == gamer.id;
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
