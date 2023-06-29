package it.polimi.ingsw.model.leaderCards;

import it.polimi.ingsw.model.DevelopmentCard;
import it.polimi.ingsw.model.DevelopmentCardString;
import it.polimi.ingsw.model.ProductionDevelopmentCard;
import it.polimi.ingsw.model.resources.Coin;
import it.polimi.ingsw.model.resources.Servant;
import it.polimi.ingsw.model.resources.Shield;
import it.polimi.ingsw.model.resources.Stone;

import java.util.ArrayList;

/**
 * Leader Card parameters
 */
public class LeaderCardParameters extends LeaderCard {

    ArrayList<DevelopmentCardString> requirements;
    ArrayList<DevelopmentCard> devCardReq;

    /**
     * Constructor
     * @param name card name
     * @param victoryPoints victory points
     * @param requirements requirements to active the card
     * @param ability ability name
     * @param desc ability description
     * @param resource resource
     */
    public LeaderCardParameters(String name, int victoryPoints, ArrayList<DevelopmentCardString> requirements, String ability, String desc, String resource){
        super(name, victoryPoints, ability, desc, resource);
        this.requirements = requirements;
    }

    /**
     * Converts the requirements from String to DevelopmentCard Class object
     */
    public void setRequirementsFromStringToDevCards() {
        ArrayList<String> devCardPrice = null;
        ArrayList<DevelopmentCard> development = new ArrayList<>(2);

        for(DevelopmentCardString dev : requirements){
            ArrayList<DevelopmentCard> devCardList = new ArrayList<>(2);

            if(dev.getType().equals("devCard")) {
                if(dev.getAmount() == 1){
                    DevelopmentCard devCard = new ProductionDevelopmentCard(dev.getLevel(), dev.getColour(), devCardPrice, 0, devCardPrice);
                    development.add(devCard);
                }
                else if(dev.getAmount() == 2) {
                    DevelopmentCard devCard = new ProductionDevelopmentCard(dev.getLevel(), dev.getColour(), devCardPrice, 0, devCardPrice);
                    development.add(devCard);
                    devCard = new ProductionDevelopmentCard(dev.getLevel(), dev.getColour(), devCardPrice, 0, devCardPrice);
                    development.add(devCard);
                }
                devCardReq = development;
            }
            else if(dev.getType().equals("resource")){
                switch(dev.getColour()){
                    case ("Coin"):
                        setRequiredResource(new Coin());
                        break;
                    case ("Servant"):
                        setRequiredResource(new Servant());
                        break;
                    case ("Shield"):
                        setRequiredResource(new Shield());
                        break;
                    case ("Stone"):
                        setRequiredResource(new Stone());
                        break;
                }
            }
        }
    }

    @Override
    public String toString() { return super.toString() + ". RequiredDevCard: " + getRequiredDevCards() + ". RequiredResource: " + getRequiredResource() + "\n"; }

    /**
     * getter
     */
    public ArrayList<DevelopmentCard> getDevCardRequired() { return devCardReq; }
}
