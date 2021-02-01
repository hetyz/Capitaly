package hu.elte.fi.progtech.field.impl;

import hu.elte.fi.progtech.field.Field;
import hu.elte.fi.progtech.player.Player;

public class Penalty extends Field {

    private final int money;

    public Penalty(int money) {
        this.money = money;
    }

    /**
     * Gives the task for the player what to do on the penalty fields
     *
     * @param p -> which player will do the task
     */
    @Override
    public void Task(Player p) {
        if (p.getMoney() < -money) {
            p.setDied(true);
        } else {
            p.setMoney(p.getMoney() + money);
            setLastPurchased(money);
        }
    }
}
