package it.polimi.ingsw.network.messages;

import it.polimi.ingsw.SetTestAdvantages;
import it.polimi.ingsw.network.server.Controller;
import it.polimi.ingsw.network.server.LobbyManager;

public class SetupGameMessage extends Message {

    private int NPlayers;
    private String nickname;

    public SetupGameMessage(int NPlayers, String nickname) {
        this.NPlayers = NPlayers;
        this.nickname = nickname;
    }

    public int getNPlayers() { return NPlayers; }

    public void setNPlayers(int nPlayers) { this.NPlayers = nPlayers; }

    public String getNickname() { return nickname; }

    public void setNickname(String nickname) { this.nickname = nickname; }

    @Override
    public void activeMessage(Controller controller) {
        controller.getPlayer().setName(nickname);
        LobbyManager.getLobbyInstance().addPlayer(controller.getPlayer(), NPlayers);
    }
}