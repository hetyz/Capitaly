package hu.elte.fi.progtech.map;

import hu.elte.fi.progtech.field.Field;
import hu.elte.fi.progtech.field.impl.Estate;
import hu.elte.fi.progtech.io.MapParserException;
import hu.elte.fi.progtech.player.Player;

import java.util.ArrayList;
import java.util.Collections;

public class Map {

    private static int mapLength;
    private final ArrayList<Field> fields;
    private int playerNum;
    private final ArrayList<Player> players;
    private int throwNumber;
    private final ArrayList<ArrayList<Integer>> diceThrows;

    public static int getMapLength() {
        return mapLength;
    }

    public void setMapLength(int mapLength) {
        Map.mapLength = mapLength;
    }

    public void setPlayerNum(int playerNum) {
        this.playerNum = playerNum;
    }

    public void setThrowNumber(int throwNumber) {
        this.throwNumber = throwNumber;
    }

    public int getPlayerNum() {
        return playerNum;
    }

    public int getThrowNumber() {
        return throwNumber;
    }

    public Map() {
        mapLength = 0;
        fields = new ArrayList<>();
        playerNum = 0;
        players = new ArrayList<>();
        throwNumber = 0;
        diceThrows = new ArrayList<>();
    }

    public void addField(Field field) {
        fields.add(field);
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void addDice(ArrayList<Integer> diceThrowArray) {
        diceThrows.addAll(Collections.singleton(diceThrowArray));
    }

    public void startSimulation() throws MapParserException {
        ArrayList<Player> lostOnes = new ArrayList<>();

        for (int i = 0; i < throwNumber; i++) {
            for (int j = 0; j < players.size(); j++) {
                if (!players.get(j).isDied()) {
                    players.get(j).movePlayer(diceThrows.get(j).get(i));
                    Field threwField = returnField(players.get(j).getMapPos() - 1);
                    players.get(j).AI(threwField);

                    System.out.println(i + ". kör " + players.get(j).getMoney());


                    if (players.get(j).isDied()) {
                        processPlayerDeath(players.get(j));
                        System.out.println(players.get(j).getName() + " lost!");
                        players.get(j).setMoney(-1);
                        lostOnes.add(players.get(j));
                    }
                }
            }
        }

        if (players.size() == 1) {
            System.out.println("\nNot enough players to complete the task!");
        } else if (lostOnes.size() >= 2) {
            System.out.println("\nSecond loser: " + lostOnes.get(1).getName());
        } else {
            System.out.println("\nNot enough losers!");
        }
    }

    private Field returnField(int pos) throws MapParserException {
        try {
            return fields.get(pos);
        } catch (Exception ex) {
            throw new MapParserException("Something wrong with the return field!", ex);
        }
    }

    /**
     * It deletes the property of the death player
     *
     * @param p -> player
     */
    private void processPlayerDeath(Player p) {
        for (Field field : fields) {
            if (field instanceof Estate) {
                Estate estate = (Estate) field;
                if (estate.getOwner() == p) {
                    deleteOwn(estate);
                }
            }
        }
    }

    /**
     * Deletes the player properties of the estate fields
     */
    private void deleteOwn(Estate estate) {
        estate.setHouses(false);
        estate.setOwner(null);
    }
}
