package client.message;

import client.model.Gamer;
import javafx.util.Pair;

import java.io.Serializable;
import java.util.Collection;

public class State implements Serializable {
    private Collection<Pair<Gamer, Integer>> stateInfo;

    public State(Collection<Pair<Gamer, Integer>> stateInfo) {
        this.stateInfo = stateInfo;
    }

    public Collection<Pair<Gamer, Integer>> getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(Collection<Pair<Gamer, Integer>> stateInfo) {
        this.stateInfo = stateInfo;
    }
}
