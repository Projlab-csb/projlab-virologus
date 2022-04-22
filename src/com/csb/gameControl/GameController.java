package com.csb.gameControl;

import com.csb.collectables.gencodes.Gencode;
import com.csb.fields.Field;
import com.csb.skeletonTester.UserInputHandler;
import com.csb.utils.Random;
import com.csb.virologist.Virologist;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GameController {

    //Each type of field must have at least MIN_FIELD_COUNT number of fields
    private static final int MIN_FIELD_COUNT = 2;
    private static final int MAX_FIELD_COUNT = 8;
    private static final String MAP_LOCATION = "data/maps/";

    private List<Virologist> allVirologists;
    private List<Virologist> deadVirologists;
    private Map<Virologist, List<Gencode>> virologistGencodesMap;
    private List<Gencode> allGencodes;
    private boolean someoneWon;
    private GameMap map;

    private static GameController _instance;

    public static GameController getInstance() {
        if (_instance == null) {
            _instance = new GameController();
        }
        return _instance;
    }

    private GameController() {
        allVirologists = new ArrayList<>();
        deadVirologists = new ArrayList<>();
        virologistGencodesMap = new java.util.HashMap<Virologist, List<Gencode>>();
        allGencodes = new ArrayList<>();
        someoneWon = false;
    }

    public void loadMap(String mapName) {
        File file = new File(MAP_LOCATION + mapName + ".map");
        map = GameMap.loadMap(file);
    }

    public void saveMap(String mapName) {
        //TODO: Check if the map name is valid
        File file = new File(MAP_LOCATION + mapName + ".map");
        GameMap.saveMap(file, map);
    }

    public List<Virologist> getAllVirologists() {
        return allVirologists;
    }

    /**
     * Initializes the game
     * Sets up the virologists
     * Sets up the fields
     */
    public void initGame() {
        int playerCount = UserInputHandler.getUserInputInt("How many virologists do you want to play with?");
        for (int i = 0; i < playerCount; i++) {
            allVirologists.add(new Virologist());
        }
        map = new GameMap();
        List<Field> fields = map.getFields();
        Random random = new Random();
        for (Virologist virologist : allVirologists) {
            //Pick a random field for each virologist
            fields.get(random.randomBetween(0, fields.size() - 1)).acceptVirologist(virologist);
        }
    }

    public void startGame() {
        while (!someoneWon) {
            for (Virologist v : allVirologists) {
                v.startOfTurn();
                if (someoneWon) {
                    break;
                }
            }
        }
    }

    /**
     * A given virologist can report the collected gencodes, and the game controller will
     * determine if the virologist is a winner or not.
     * @param v The virologist to report the gencodes for
     * @param gencodeList The reported gencodes
     */
    public void reportGencodes(Virologist v, List<Gencode> gencodeList) {
        virologistGencodesMap.put(v, gencodeList);
        someoneWon = isWinner(v);
    }

    public void reportDeath(Virologist v) {
        allVirologists.remove(v);
    }

    /**
     * Save a gencode to the possible gencodes list
     * @param gencode The gencode to save
     */
    public void addPossibleGencode(Gencode gencode) {
        allGencodes.add(gencode);
    }

    private boolean isWinner(Virologist v) {
        //Check if the virologist has all the possible gencodes
        return virologistGencodesMap.get(v).containsAll(allGencodes);
    }
}
