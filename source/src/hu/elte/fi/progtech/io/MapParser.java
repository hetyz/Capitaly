package hu.elte.fi.progtech.io;

import hu.elte.fi.progtech.map.Map;
import hu.elte.fi.progtech.field.impl.Chances;
import hu.elte.fi.progtech.field.impl.Estate;
import hu.elte.fi.progtech.field.impl.Penalty;
import hu.elte.fi.progtech.player.impl.Careful;
import hu.elte.fi.progtech.player.impl.Greedy;
import hu.elte.fi.progtech.player.impl.Tactician;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class MapParser {

    private final File file;

    private static final String FIELD_TYPE_CHANCES = "CH";
    private static final String FIELD_TYPE_ESTATE = "EST";
    private static final String FIELD_TYPE_PENALTY = "PEN";

    private static final String GREEDY = "greedy";
    private static final String TACTICIAN = "tactician";
    private static final String CAREFUL = "careful";

    public MapParser(String filePath) {
        this.file = new File(filePath);
    }

    public Map parser() throws MapParserException {
        try (final Scanner sc = new Scanner(file)) {
            Map map = new Map();
            sc.useDelimiter("\n");

            map.setMapLength(readNumber(sc));
            readField(sc, map);

            map.setPlayerNum(readNumber(sc));
            readPlayer(sc, map);

            if (sc.hasNext()) {
                map.setThrowNumber(readNumber(sc));
                readDiceNumber(sc, map);
            } else {
                randomDiceThrows(map);
            }
            return map;
        } catch (FileNotFoundException ex) {
            throw new MapParserException("Failed to read file, because the file was not found!", ex, file.getPath());
        }
    }

    private int readNumber(Scanner sc) throws MapParserException {
        int number;
        try {
            number = Integer.parseInt(readNextWithCheck(sc, "index numbers").trim());
        } catch (NumberFormatException ex) {
            throw new MapParserException("Something wrong with the integers!");
        }
        return number;
    }

    /**
     * Reading the fields from the file
     *
     * @param sc -> read file scanner
     */
    private void readField(Scanner sc, Map map) throws MapParserException {
        try {
            for (int i = 0; i < Map.getMapLength(); i++) {
                String line = readNextWithCheck(sc, "Field type");
                String[] splitstr = line.split(" ");
                String fieldType = splitstr[0].trim();
                int money;

                if (FIELD_TYPE_ESTATE.equals(fieldType)) {
                    money = 0;
                } else {
                    try {
                        money = Integer.parseInt(splitstr[1].trim());
                    } catch (NumberFormatException ex) {
                        throw new MapParserException("Something wrong with the field's value!", ex);
                    }
                }

                switchField(fieldType, money, map);
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            throw new MapParserException("Something wrong with the read fields!", ex);
        }
    }

    private void switchField(String fieldType, int money, Map map) throws MapParserException {
        switch (fieldType) {
            case FIELD_TYPE_CHANCES:
                try {
                    map.addField(createChances(money));
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
                    throw new MapParserException("Something wrong with the field's money!");
                }
                break;
            case FIELD_TYPE_PENALTY:
                try {
                    map.addField(createPenalty(money));
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
                    throw new MapParserException("Something wrong with the field's money!");
                }
                break;
            case FIELD_TYPE_ESTATE:
                map.addField(createEstate());
                break;
            default:
                throw new MapParserException("Invalid field type: " + fieldType);
        }
    }

    private Chances createChances(int money) {
        return new Chances(money);
    }

    private Penalty createPenalty(int money) {
        return new Penalty(money);
    }

    private Estate createEstate() {
        return new Estate();
    }

    private void readPlayer(Scanner sc, Map map) throws MapParserException {
        try {
            for (int i = 0; i < map.getPlayerNum(); i++) {
                String playerData = readNextWithCheck(sc, "Player type");
                String[] playerDataArray = playerData.split(" ");
                String name = playerDataArray[0].trim();
                String type = playerDataArray[1].trim();

                switchPlayer(name, type, map);
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            throw new MapParserException("Something wrong with the player numbers!", ex);
        }
    }

    private void switchPlayer(String name, String type, Map map) throws MapParserException {
        switch (type) {
            case GREEDY -> map.addPlayer(createGreedy(name));
            case CAREFUL -> map.addPlayer(createCareful(name));
            case TACTICIAN -> map.addPlayer(createTactician(name));
            default -> throw new MapParserException("Invalid tactic: " + type);
        }
    }

    private Greedy createGreedy(String name) {
        return new Greedy(name);
    }

    private Tactician createTactician(String name) {
        return new Tactician(name);
    }

    private Careful createCareful(String name) {
        return new Careful(name);
    }

    /**
     * Reads the serial numbers of dices
     *
     * @param sc -> read file scanner
     */
    private void readDiceNumber(Scanner sc, Map map) throws MapParserException {
        try {
            for (int i = 0; i < map.getPlayerNum(); i++) {
                String line = readNextWithCheck(sc, "Dice number").trim();
                try {
                    Integer.parseInt(line);
                    ArrayList<Integer> playersThrows = new ArrayList<>();
                    for (int j = 0; j < map.getThrowNumber(); j++) {
                        int diceThrow = Character.getNumericValue(line.charAt(j));
                        playersThrows.add(diceThrow);
                    }
                    map.addDice(playersThrows);
                } catch (NumberFormatException ex) {
                    throw new MapParserException("Dice throws must be integer!");
                }
            }
        } catch (StringIndexOutOfBoundsException ex) {
            throw new MapParserException("Something wrong with the throws!");
        }
    }

    private void randomDiceThrows(Map map) {
        Random random = new Random();
        map.setThrowNumber(random.nextInt(7) + 4);
        for (int i = 0; i < map.getPlayerNum(); i++) {
            ArrayList<Integer> playersThrows = new ArrayList<>();
            for (int j = 0; j < map.getThrowNumber(); j++) {
                int diceThrow = random.nextInt(6) + 1;

                playersThrows.add(diceThrow);
            }
            map.addDice(playersThrows);
        }
    }

    private String readNextWithCheck(Scanner sc, String element) throws MapParserException {
        if (sc.hasNext()) {
            return sc.next();
        } else {
            throw new MapParserException("Element not found!", element);
        }
    }
}
