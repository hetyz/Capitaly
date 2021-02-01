package hu.elte.fi.progtech.start;

import hu.elte.fi.progtech.Map.Map;
import hu.elte.fi.progtech.io.MapParser;
import hu.elte.fi.progtech.io.MapParserException;

public class StartGame {

    public StartGame() {
        readAndSimulation();
    }

    private void readAndSimulation() {
        try {
            Map map = new MapParser("tests/teszt9.txt").parser();
            map.startSimulation();
        } catch (MapParserException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
