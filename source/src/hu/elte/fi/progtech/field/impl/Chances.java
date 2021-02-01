package hu.elte.fi.progtech.field.impl;

import hu.elte.fi.progtech.field.Field;
import hu.elte.fi.progtech.player.Player;


public class Chances extends Field {

    private final int money;

    public Chances(int money) {
        this.money = money;
    }

    /**
     * Gives the task for the player what to do on the chances fields
     *
     * @param p -> which player will do the task
     */
    @Override
    public void Task(Player p) {
        p.setMoney(p.getMoney() + money);
    }
}
