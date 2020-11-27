package client.message;

import client.logic.Rival;
import javafx.util.Pair;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class State implements Serializable {
    private Collection<Pair<Rival, Integer>> stateInfo;

    public State(Collection<Pair<Rival, Integer>> stateInfo) {
        this.stateInfo = stateInfo;
    }

    public Collection<Pair<Rival, Integer>> getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(Collection<Pair<Rival, Integer>> stateInfo) {
        this.stateInfo = stateInfo;
    }
}
