package it.polimi.ingsw.network.messages;

import it.polimi.ingsw.model.GameModel;
import it.polimi.ingsw.model.exceptions.CantBuyThisCardException;
import it.polimi.ingsw.model.exceptions.NotEnoughResourcesException;
import it.polimi.ingsw.model.commands.BuyDevCardCommand;


public class BuyDevCardMessage extends Message {

    private int level;
    private int colour;
    private int positionDevCardSlot;

    public BuyDevCardMessage(int level, int colour, int positionDevCardSlot) {
        this.level = level;
        this.colour = colour;
        this.positionDevCardSlot = positionDevCardSlot;
    }

    public int getLevel() { return level; }

    public void setLevel(int level) { this.level = level; }

    public int getColour() { return colour; }

    public void setColour(int colour) { this.colour = colour; }

    public int getPositionDevCardSlot() { return positionDevCardSlot; }

    public void setPositionDevCardSlot(int positionDevCardSlot) { this.positionDevCardSlot = positionDevCardSlot; }

    @Override
    public void activeMessage(GameModel gameModel) throws NotEnoughResourcesException, CantBuyThisCardException {
        BuyDevCardCommand command = new BuyDevCardCommand();
        command.executeCommand(this, gameModel);
    }
}
