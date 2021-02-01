package hu.elte.fi.progtech.player.impl;

import hu.elte.fi.progtech.field.Field;
import hu.elte.fi.progtech.player.Player;

public class Greedy extends Player {

    public Greedy(String name) {
        super(name);
    }

    /**
     * Logical movements
     *
     * @param field -> give the tasks what to do where the player moved
     */
    public void AI(Field field) {
        if (!isDied()) {
            field.Task(this);
        }
    }
}
