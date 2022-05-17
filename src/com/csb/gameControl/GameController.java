package com.csb.gameControl;

import com.csb.collectables.gencodes.Gencode;
import com.csb.controller.VirologistController;
import com.csb.fields.Field;
import com.csb.skeletonTester.UserInputHandler;
import com.csb.utils.Random;
import com.csb.view.GameView;
import com.csb.view.PopUpView;
import com.csb.virologist.Virologist;
import java.io.*;
import java.lang.reflect.Type;
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
    private static final String GAME_SAVE_LOCATION = "../projlab-virologus/data/saves/";

    List<Virologist> allVirologists;
    List<Virologist> deadVirologists;
    Map<Virologist, List<Gencode>> virologistGencodesMap;
    List<Gencode> allGencodes;
    private boolean someoneWon;
    int currentVirologistIndex;
    GameMap map = null;

    GameView gameView = null;
    public VirologistController virologistController;

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
        gameView = new GameView();
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
    public void saveGame(String fileName) throws IOException {
        File directory = new File(GAME_SAVE_LOCATION);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        File file = new File(GAME_SAVE_LOCATION + fileName + ".ser");
        //Loop through the fields and save them to a file
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(getInstance());
        oos.close();
        fos.close();
        System.out.println("Game saved");
    }

    /**
     * Loads a game state from a file.
     * @param fileName
     */
    public void loadGame(String fileName) {
        try {
            //FileInputStream fis = new FileInputStream(new File(GAME_SAVE_LOCATION + fileName));
            FileInputStream fis = new FileInputStream(new File(fileName));
            byte[] data = fis.readAllBytes();
            ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data));
            Object o = ois.readObject();
            ois.close();
            GameController gameController = (GameController) o;
            _instance.gameView.setVisible(false);
            _instance = gameController;
            _instance.gameView.init();

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

    public void initGame(List<String> virologistNames) {
        virologistController = new VirologistController(null, gameView.getVirologistView());

        virologistNames.forEach(name -> allVirologists.add(new Virologist(name)));
        map = new GameMap(3, 3);
        List<Field> fields = map.getFields();
        Random random = new Random();
        for (Virologist virologist : allVirologists) {
            //Pick a random field for each virologist
            fields.get(random.randomBetween(0, fields.size() - 1)).acceptVirologist(virologist);
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
        currentVirologistIndex = 0;
        Virologist currentVirologist = allVirologists.get(currentVirologistIndex);
        virologistController.setVirologist(currentVirologist);
        virologistController.updateView();
        currentVirologist.startOfTurn();
    }

    public void nextPlayersTurn() {
        //Check if the last virologist has won
        if (currentVirologistIndex <= 0) {
            someoneWon = isWinner(allVirologists.get(allVirologists.size() - 1));
        } else {
            someoneWon = isWinner(allVirologists.get(currentVirologistIndex - 1));
        }
        if (someoneWon) {
            String[] options = { "Ok!" };
            PopUpView.selectOption(
                "Congratulations! The winner is:" + allVirologists.get(currentVirologistIndex).getName(),
                "Game Over",
                options
            );
            System.exit(0);
        } else {
            currentVirologistIndex++;
            if (currentVirologistIndex >= allVirologists.size()) {
                currentVirologistIndex = 0;
            }
            Virologist currentVirologist = allVirologists.get(currentVirologistIndex);
            virologistController.setVirologist(currentVirologist);
            currentVirologist.startOfTurn(); //Start of turn method MUST return immediately when using UI
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

    /**Returns a boolean whether the given virologist has won or not**/
    private boolean isWinner(Virologist v) {
        //Check if the virologist has all the possible gencodes
        List<Gencode> gencodes = virologistGencodesMap.get(v);
        ArrayList<String> gencodenames = new ArrayList<>();
        ArrayList<String> allgencodenames = new ArrayList<>();
        boolean isEqual = true;
        if (gencodes != null) {
            if (gencodes.isEmpty()) {
                return false;
            } else {
                System.out.println(gencodes);
                System.out.println(allGencodes);

                for (int i = 0; i < gencodes.size(); i++) {
                    gencodenames.add(gencodes.get(i).toString());
                }

                for (int i = 0; i < allGencodes.size(); i++) {
                    allgencodenames.add(allGencodes.get(i).toString());
                }
                for (int i = 0; i < allgencodenames.size(); i++) {
                    if (!gencodenames.contains(allgencodenames.get(i))) {
                        System.out.println(allgencodenames.get(i));
                        return false;
                    }
                }
                return true;
            }
        }
        return false; //No gencodes collected yet!
    }
}
