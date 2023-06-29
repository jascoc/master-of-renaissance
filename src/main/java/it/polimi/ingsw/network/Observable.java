package it.polimi.ingsw.network;

import it.polimi.ingsw.changes.Changes;

public interface Observable {

    void notify(Changes changes);
}
