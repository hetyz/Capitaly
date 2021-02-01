package hu.elte.fi.progtech.field.impl;

import hu.elte.fi.progtech.field.Field;
import hu.elte.fi.progtech.player.Player;

public class Estate extends Field {

    private Player owner;
    private boolean houses;

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public Player getOwner() {
        return owner;
    }

    public boolean isHouses() {
        return houses;
    }

    public void setHouses(boolean houses) {
        this.houses = houses;
    }


    public Estate() {
        owner = null;
        houses = false;
    }

    /**
     * Gives the task for the player what to do on the estate fields
     *
     * @param p -> which player will do the task
     */
    @Override
    public void Task(Player p) {
        if (owner == null) {
            purchaseField(p);
        } else if (owner == p) {
            expansionField(p);
        } else {
            paymentField(p);
        }
    }

    /**
     * It buys the field for the player
     *
     * @param p -> which player will do the task
     */
    private void purchaseField(Player p) {
        if (p.getMoney() >= ESTATE_PRICE) {
            p.setMoney(p.getMoney() - ESTATE_PRICE);
            owner = p;
            setLastPurchased(ESTATE_PRICE);
        }
    }

    /**
     * It expansions the field with houses
     *
     * @param p -> which player will do the task
     */
    private void expansionField(Player p) {
        if (!houses && p.getMoney() >= ESTATE_HOUSES_PRICE) {
            p.setMoney(p.getMoney() - ESTATE_HOUSES_PRICE);
            houses = true;
            setLastPurchased(ESTATE_HOUSES_PRICE);
        }
    }

    /**
     * Here will the player pay
     *
     * @param p -> which player will do the task
     */
    private void paymentField(Player p) {
        if (houses) {
            if (p.getMoney() < ESTATE_PAYMENT_WITH_HOUSES) {
                p.setDied(true);
            } else {
                p.setMoney(p.getMoney() - ESTATE_PAYMENT_WITH_HOUSES);
                owner.setMoney(owner.getMoney() + ESTATE_PAYMENT_WITH_HOUSES);
                setLastPurchased(ESTATE_PAYMENT_WITH_HOUSES);
            }
        } else {
            if (p.getMoney() < ESTATE_PAYMENT_WITHOUT_HOUSES) {
                p.setDied(true);
            } else {
                p.setMoney(p.getMoney() - ESTATE_PAYMENT_WITHOUT_HOUSES);
                owner.setMoney(owner.getMoney() + ESTATE_PAYMENT_WITHOUT_HOUSES);
                setLastPurchased(ESTATE_PAYMENT_WITHOUT_HOUSES);
            }
        }
    }
}
