package com.csb.gameControl;

import com.csb.collectables.gencodes.Gencode;
import com.csb.fields.Field;
import com.csb.skeletonTester.UserInputHandler;
import com.csb.utils.Random;
import com.csb.virologist.Virologist;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * The GameController class is the main class of the game. It controls the
 * game flow and the game state.
 */
public class GameController implements Serializable {

    //Each type of field must have at least MIN_FIELD_COUNT number of fields
    private static final int MIN_FIELD_COUNT = 2;
    private static final int MAX_FIELD_COUNT = 8;
    private static final String GAME_SAVE_LOCATION = "data/saves/";

    List<Virologist> allVirologists;
    List<Virologist> deadVirologists;
    Map<Virologist, List<Gencode>> virologistGencodesMap;
    List<Gencode> allGencodes;
    private boolean someoneWon;
    GameMap map;

    //Instance of the singleton class
    private static GameController _instance;

    /**
     * Returns the instance of the GameController class.
     * @return The instance of the GameController class.
     */
    public static GameController getInstance() {
        if (_instance == null) {
            _instance = new GameController();
        }
        return _instance;
    }

    /**
     * GameController constructor.
     * Initializes the game.
     */
    private GameController() {
        allVirologists = new ArrayList<>();
        deadVirologists = new ArrayList<>();
        virologistGencodesMap = new java.util.HashMap<Virologist, List<Gencode>>();
        allGencodes = new ArrayList<>();
        someoneWon = false;
        map = new GameMap();
    }

    /**
     * Loads all the available game saves
     * @return A list of all the available game saves
     */
    public List<String> listGameSaves() {
        ArrayList<String> gameFiles = new ArrayList<>();
        File folder = new File(GAME_SAVE_LOCATION);
        File[] listOfFiles = folder.listFiles();
        if (listOfFiles != null) {
            Arrays
                .stream(listOfFiles)
                .sorted()
                .forEach(file -> {
                    if (file.isFile()) {
                        gameFiles.add(file.getName());
                    }
                });
        }
        return gameFiles;
    }

    /**
     * Saves the current game state.
     * @param fileName The name of the file to save the game state to.
     */
    public void saveGame(String fileName) {
        File directory = new File(GAME_SAVE_LOCATION);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        File file = new File(GAME_SAVE_LOCATION + fileName + ".ser");
        //Loop through the fields and save them to a file
        try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(getInstance());
            oos.close();
            fos.close();
            System.out.println("Game saved");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads a game state from a file.
     * @param fileName
     */
    public void loadGame(String fileName) {
        try {
            FileInputStream fis = new FileInputStream(new File(GAME_SAVE_LOCATION + fileName));
            byte[] data = fis.readAllBytes();
            ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data));
            Object o = ois.readObject();
            ois.close();
            GameController gameController = (GameController) o;
            _instance = gameController;
            System.out.println("Game loaded");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
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
        boolean mapLoaded = UserInputHandler.getUserInputBoolean("Do you want to load a game?");
        if (mapLoaded) {
            List<String> gameSaves = listGameSaves();
            for (int i = 0; i < gameSaves.size(); i++) {
                System.out.println(i + ": " + gameSaves.get(i));
            }
            //Load the save
            if (gameSaves.size() > 0) {
                int mapId = UserInputHandler.getUserInputInt("Which game save you want to load?", 0, gameSaves.size() - 1);
                loadGame(gameSaves.get(mapId));
            } else {
                System.out.println("No game saves found");
                return false;
            }
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
                if (i >= allVirologists.size() || allVirologists.get(i) == null) continue;
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
