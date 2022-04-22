package com.csb.gameControl;

import com.csb.agents.*;
import com.csb.collectables.Collectable;
import com.csb.collectables.equipments.Bag;
import com.csb.collectables.equipments.Cloak;
import com.csb.collectables.equipments.Equipment;
import com.csb.collectables.equipments.Gloves;
import com.csb.collectables.gencodes.Gencode;
import com.csb.collectables.matters.AminoAcid;
import com.csb.collectables.matters.NucleicAcid;
import com.csb.fields.Field;
import com.csb.fields.Lab;
import com.csb.fields.Shelter;
import com.csb.fields.Storage;
import com.csb.skeletonTester.UserInputHandler;
import com.csb.utils.Random;
import com.csb.virologist.Virologist;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GameController {

    //Each type of field must have at least MIN_FIELD_COUNT number of fields
    private static final int MIN_FIELD_COUNT = 2;
    private static final int MAX_FIELD_COUNT = 8;

    private List<Virologist> virologists;
    private Map<Virologist, List<Gencode>> virologistGencodesMap;
    private List<Gencode> possibleGencodes;
    private List<Field> allFields;

    private static GameController _instance;

    public static GameController getInstance() {
        if (_instance == null) {
            _instance = new GameController();
        }
        return _instance;
    }

    private GameController() {
        virologists = new ArrayList<>();
        virologistGencodesMap = new java.util.HashMap<Virologist, List<Gencode>>();
    }

    public List<Virologist> getVirologists() {
        return virologists;
    }

    /**
     * Initializes the game
     * Sets up the virologists
     * Sets up the fields
     */
    public void initGame() {
        int playerCount = UserInputHandler.getUserInputInt("How many virologists do you want to play with?");
        for (int i = 0; i < playerCount; i++) {
            virologists.add(new Virologist());
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
    }

    private void generateMap() {
        //Generate regular fields
        generateField(MIN_FIELD_COUNT, MAX_FIELD_COUNT);
        //Generate labs
        generateLab(MIN_FIELD_COUNT, MAX_FIELD_COUNT);
        //Generate shelters
        generateShelter(MIN_FIELD_COUNT, MAX_FIELD_COUNT);
        //Generate storage
        generateStorage(MIN_FIELD_COUNT, MAX_FIELD_COUNT);
    }

    private void generateField(int minCount, int maxCount) {
        Random rand = new Random();
        int regularFieldCount = rand.randomBetween(minCount, maxCount);
        for (int i = 0; i < regularFieldCount; i++) {
            storeField(new Field());
        }
    }

    private void generateLab(int minCount, int maxCount) {
        Random rand = new Random();
        int labCount = rand.randomBetween(minCount, maxCount);
        for (int i = 0; i < labCount; i++) {
            storeField(new Lab(new ArrayList<Collectable>(List.of(generateRandomGencode()))));
        }
    }

    private void generateShelter(int minCount, int maxCount) {
        Random rand = new Random();
        int shelterCount = rand.randomBetween(minCount, maxCount);
        for (int i = 0; i < shelterCount; i++) {
            ArrayList<Collectable> collectables = new ArrayList<>();
            int collectableCount = rand.randomBetween(1, 3);
            for (int j = 0; j < collectableCount; j++) {
                collectables.add(generateRandomEquipment());
            }
            storeField(new Shelter(collectables));
        }
    }

    private void generateStorage(int minCount, int maxCount) {
        Random rand = new Random();
        int storageCount = rand.randomBetween(minCount, maxCount);
        for (int i = 0; i < storageCount; i++) {
            //Infinity storage of amino acids, and or nucleic acids
            ArrayList<Collectable> collectables = new ArrayList<>();
            if (rand.nextBoolean()) collectables.add(new AminoAcid());
            if (rand.nextBoolean()) collectables.add(new NucleicAcid());

            storeField(new Storage(collectables));
        }
    }

    /**
     * Save a given field to the list of all fields, set the field's neighbours
     * @param field The field to save
     */
    private void storeField(Field field) {
        Random rand = new Random();
        Field randomPreviousField = allFields.get(rand.nextInt(allFields.size()));
        if (randomPreviousField != null) {
            randomPreviousField.addNeighbor(field);
        }
        allFields.add(field);
    }

    /**
     * Generates a random gencode, and stores it in the possible gencodes list
     * @return The random generated gencode
     */
    private Gencode generateRandomGencode() {
        Random rand = new Random();
        //Get random gencode Forget, Paralyzed, Protection, VitusDance
        Agent agent;
        switch (rand.nextInt(4)) {
            case 1:
                agent = new Paralyzed();
                break;
            case 2:
                agent = new Protection();
                break;
            case 3:
                agent = new VitusDance();
                break;
            default:
                agent = new Forget();
                break;
        }
        Gencode gencode = new Gencode(
            agent,
            new AminoAcid(rand.randomBetween(Gencode.MIN_AMINO_ACID, Gencode.MAX_AMINO_ACID)),
            new NucleicAcid(rand.randomBetween(Gencode.MIN_NUCLEIC_ACID, Gencode.MAX_NUCLEIC_ACID))
        );
        this.possibleGencodes.add(gencode);
        return gencode;
    }

    private Equipment generateRandomEquipment() {
        Random rand = new Random();
        //Get random equipment, Bag, Cloak, Gloves
        Equipment equipment;
        switch (rand.nextInt(3)) {
            case 1:
                equipment = new Cloak();
                break;
            case 2:
                equipment = new Gloves();
                break;
            default:
                equipment = new Bag();
                break;
        }
        return equipment;
    }

    private boolean isWinner(Virologist v) {
        //Check if the virologist has all the possible gencodes
        return virologistGencodesMap.get(v).containsAll(possibleGencodes);
    }
}
