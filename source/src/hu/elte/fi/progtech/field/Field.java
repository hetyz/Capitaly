package hu.elte.fi.progtech.field;

import hu.elte.fi.progtech.player.Player;

public abstract class Field {

    protected static final int ESTATE_HOUSES_PRICE = 4000;
    protected static final int ESTATE_PRICE = 1000;
    protected static final int ESTATE_PAYMENT_WITHOUT_HOUSES = 500;
    protected static final int ESTATE_PAYMENT_WITH_HOUSES = 2000;

    private int lastPurchased;

    public int getLastPurchased() {
        return lastPurchased;
    }

    public void setLastPurchased(int lastPurchased) {
        this.lastPurchased = lastPurchased;
    }

    /**
     * Gives the task for the player what to do
     *
     * @param p -> which player will do the task
     */
    public abstract void Task(Player p);
}
