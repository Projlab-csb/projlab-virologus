package com.csb.gameControl;

import com.csb.collectables.gencodes.Gencode;
import com.csb.fields.Field;
import com.csb.skeletonTester.UserInputHandler;
import com.csb.utils.Random;
import com.csb.virologist.Virologist;
import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GameController {

    //Each type of field must have at least MIN_FIELD_COUNT number of fields
    private static final int MIN_FIELD_COUNT = 2;
    private static final int MAX_FIELD_COUNT = 8;
    private static final String MAP_LOCATION = "data/maps/";

    List<Virologist> allVirologists;
    List<Virologist> deadVirologists;
    Map<Virologist, List<Gencode>> virologistGencodesMap;
    List<Gencode> allGencodes;
    private boolean someoneWon;
    GameMap map;

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
        map = new GameMap();
    }

    public List<String> listMapFiles() {
        ArrayList<String> mapFiles = new ArrayList<>();
        File folder = new File(MAP_LOCATION);
        File[] listOfFiles = folder.listFiles();
        for (File file : listOfFiles) {
            if (file.isFile()) {
                mapFiles.add(file.getName());
            }
        }
        return mapFiles;
    }

    public void loadMap(String mapName) {
        File file = new File(MAP_LOCATION + mapName);
        map = GameMap.loadMap(file);
        System.out.println("Map loaded");
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
        //Prompt for the editor
        boolean isEdited = editorModePrompt();
        boolean mapLoaded = false;
        if (!isEdited) {
            mapLoaded = loadMapPrompt();
        }
        //Only run regular game flow if the game has not been edited
        if (!isEdited && !mapLoaded) {
            int playerCount = UserInputHandler.getUserInputInt("How many virologists do you want to play with?");
            for (int i = 0; i < playerCount; i++) {
                String name = UserInputHandler.getUserInputString("What is the name of player " + (i + 1) + "?");
                allVirologists.add(new Virologist(name));
            }
            map = new GameMap(3, 3);
            List<Field> fields = map.getFields();
            Random random = new Random();
            for (Virologist virologist : allVirologists) {
                //Pick a random field for each virologist
                fields.get(random.randomBetween(0, fields.size() - 1)).acceptVirologist(virologist);
            }
        }
    }

    /**
     * Trys to enter the game editor mode game editor state
     */
    public boolean editorModePrompt() {
        boolean editorMode = UserInputHandler.getUserInputBoolean("Do you want to enter game editor mode?");
        if (editorMode) {
            //Enter the editor
            GameEditor.getInstance().enterEditorMode();
        }
        //Regular game flow continues
        return editorMode;
    }

    private boolean loadMapPrompt() {
        boolean mapLoaded = UserInputHandler.getUserInputBoolean("Do you want to load a map?");
        if (mapLoaded) {
            List<String> maps = listMapFiles();
            for (int i = 0; i < maps.size(); i++) {
                System.out.println(i + ": " + maps.get(i));
            }
            //Load the map
            int mapId = UserInputHandler.getUserInputInt("Which map do you want to load?", 0, maps.size() - 1);
            loadMap(maps.get(mapId));
        }
        //Regular game flow continues
        return mapLoaded;
    }

    /**
     * Start of the game process
     */
    public void startGame() {
        while (!someoneWon) {
            for (int i = 0; i < allVirologists.size(); i++) {
                allVirologists.get(i).startOfTurn();
                if(i >= allVirologists.size() || allVirologists.get(i) == null) continue;
                if (someoneWon) {
                    break;
                }
            }
        }
        System.out.println("The winner is: " + allVirologists.get(0).getName());
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
