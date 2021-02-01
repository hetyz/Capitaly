package hu.elte.fi.progtech.player;

import hu.elte.fi.progtech.field.Field;
import hu.elte.fi.progtech.Map.Map;

public abstract class Player {

    private static final int START_MONEY = 10000;

    private final String name;
    private int money;
    private int mapPos;
    private boolean died;
    private boolean newRound;

    public String getName() {
        return name;
    }

    public void setNewRound(boolean newRound) {
        this.newRound = newRound;
    }

    public void setDied(boolean died) {
        this.died = died;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    public boolean isDied() {
        return died;
    }

    public boolean isNewRound() {
        return newRound;
    }

    public int getMapPos() {
        return mapPos;
    }

    public Player(String name) {
        this.name = name;
        died = false;
        newRound = false;
        money = START_MONEY;
        mapPos = 0;
    }

    /**
     * Player moving
     *
     * @param moveNum -> the number how far to go
     */
    public void movePlayer(int moveNum) {
        mapPos = mapPos + moveNum;

        if(Map.getMapLength() > mapPos)
        {
            newRound = false;
        }

        while (mapPos > Map.getMapLength()) {
            mapPos = mapPos - Map.getMapLength();
            newRound = true;
        }

    }

    /**
     * Logical movements
     *
     * @param field -> give the tasks what to do
     */
    public abstract void AI(Field field);
}
