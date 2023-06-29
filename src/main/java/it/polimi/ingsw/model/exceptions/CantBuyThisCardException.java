package it.polimi.ingsw.model.exceptions;

public class CantBuyThisCardException extends Exception {

    /**
     * invoked when there is not enough resource to buy a card or when the requirements does not match
     */
    public CantBuyThisCardException(){ System.out.println("Can't buy this card!"); }
}
