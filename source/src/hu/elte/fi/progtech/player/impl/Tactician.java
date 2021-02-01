package hu.elte.fi.progtech.player.impl;

import hu.elte.fi.progtech.field.Field;
import hu.elte.fi.progtech.field.impl.Chances;
import hu.elte.fi.progtech.field.impl.Estate;
import hu.elte.fi.progtech.field.impl.Penalty;
import hu.elte.fi.progtech.player.Player;

public class Tactician extends Player {

    private boolean bought;

    public Tactician(String name) {
        super(name);
        bought = false;
    }

    /**
     * Logical movements
     *
     * @param field -> give the tasks what to do where the player moved
     */
    @Override
    public void AI(Field field) {
        if (!isDied()) {
            if(isNewRound())
            {
                bought = false;
            }

            if (field instanceof Chances || field instanceof Penalty) {
                field.Task(this);
            } else if (field instanceof Estate) {
                Estate estate = (Estate) field;
                if (estate.getOwner() == null && !bought) {
                    estate.Task(this);
                    bought = true;
                } else if (estate.getOwner() == this && !estate.isHouses()) {
                    if (!bought) {
                        estate.Task(this);
                        bought = true;
                    }
                } else if (estate.getOwner() != this && estate.getOwner() != null) {
                    estate.Task(this);
                }
            }
        }
    }
}
