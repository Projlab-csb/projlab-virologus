package com.csb.gameControl;

import com.csb.collectables.gencodes.Gencode;
import com.csb.skeletonTester.Tester;
import com.csb.skeletonTester.UserInputHandler;
import com.csb.virologist.Virologist;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GameController {

    private List<Virologist> virologists;
    private Map<Virologist, List<Gencode>> virologistGencodesMap;

    private static GameController _instance = null;

    /**
     * Returns a singleton object of the GameController class
     * @return
     */
    public static GameController getInstance() {
        if (_instance == null) {
            _instance = new GameController();
        }
        return _instance;
    }

    /**
     * The constructor of this class
     */
    private GameController() {
        virologists = new ArrayList<>();
        virologistGencodesMap = new java.util.HashMap<>();
    }

    public List<Virologist> getVirologists() {
        return virologists;
    }

    /**
     * This function sets up the game to its default state and gets the number of players
     */
    public void initGame() {
        Tester.getInstance().functionStart();
        int playerCount = UserInputHandler.getUserInputInt("How many virologists do you want to play with?");
        for (int i = 0; i < playerCount; i++) {
            virologists.add(new Virologist());
        }
        Tester.getInstance().functionEnd();
    }

    /**
     * Maps the virologist with its gencodes
     * @param v the virologist to be mapped
     * @param gencodeList the list of gencodes that belong to virologist v
     */
    public void reportGencodes(Virologist v, List<Gencode> gencodeList) {
        Tester.getInstance().functionStart();
        virologistGencodesMap.put(v, gencodeList);
        Tester.getInstance().functionEnd();
    }
}
