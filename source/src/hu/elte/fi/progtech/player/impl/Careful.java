package hu.elte.fi.progtech.player.impl;

import hu.elte.fi.progtech.field.Field;
import hu.elte.fi.progtech.field.impl.Chances;
import hu.elte.fi.progtech.field.impl.Estate;
import hu.elte.fi.progtech.field.impl.Penalty;
import hu.elte.fi.progtech.player.Player;

public class Careful extends Player {

    private int purchased;
    private int halfOfTheMoney;

    public Careful(String name) {
        super(name);
        halfOfTheMoney = getMoney() / 2;
    }

    /**
     * Logical movements
     *
     * @param field -> give the tasks what to do where the player moved
     */
    @Override
    public void AI(Field field) {
        calculateBehaviour();
        if (!isDied()) {
            if (field instanceof Chances || field instanceof Penalty) {
                field.Task(this);
            } else if (field instanceof Estate) {
                Estate estate = (Estate) field;
                if (estate.getOwner() != null && estate.getOwner() != this) {
                    estate.Task(this);
                } else {
                    if (purchased < halfOfTheMoney) {
                        estate.Task(this);
                        purchased = purchased + field.getLastPurchased();
                    }
                }
            }
        }
    }

    /**
     * The logic behind the careful player
     */
    private void calculateBehaviour() {
        if (isNewRound()) {
            purchased = 0;
            halfOfTheMoney = getMoney() / 2;
            setNewRound(false);
        }
    }
}
