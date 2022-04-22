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
import com.csb.utils.Random;
import java.io.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class GameMap implements Serializable {

    private List<Field> fields;

    /**
     * Generate a random gencode
     * @param minFieldCount The minimum number of fields from each type to generate
     * @param maxFieldCount The maximum number of fields from each type to generate
     */
    public GameMap(int minFieldCount, int maxFieldCount) {
        fields = new ArrayList<>();
        generateMap(minFieldCount, maxFieldCount);
    }

    public GameMap() {
        fields = new ArrayList<>();
    }

    public List<Field> getFields() {
        return fields;
    }

    public static void saveMap(File mapFile, GameMap map) {
        //Loop through the fields and save them to a file
        try {
            if (mapFile.canWrite()) {
                FileOutputStream fos = new FileOutputStream(mapFile);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(map);
                oos.close();
                fos.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static GameMap loadMap(File mapFile) {
        try {
            FileInputStream fis = new FileInputStream(mapFile);
            byte[] data = fis.readAllBytes();
            ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data));
            Object o = ois.readObject();
            ois.close();
            GameMap map = (GameMap) o;
            return map;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void generateMap(int minFieldCount, int maxFieldCount) {
        //Generate regular fields
        generateField(minFieldCount, maxFieldCount);
        //Generate labs
        generateLab(minFieldCount, maxFieldCount);
        //Generate shelters
        generateShelter(minFieldCount, maxFieldCount);
        //Generate storage
        generateStorage(minFieldCount, maxFieldCount);
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

        Field randomPreviousField = fields.get(rand.nextInt(fields.size()));
        if (randomPreviousField != null) {
            randomPreviousField.addNeighbor(field);
        }
        fields.add(field);
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
        GameController.getInstance().addPossibleGencode(gencode);
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

    /** Read the object from Base64 string. */
    private static Object fromString(String s) throws IOException, ClassNotFoundException {
        byte[] data = Base64.getDecoder().decode(s);
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data));
        Object o = ois.readObject();
        ois.close();
        return o;
    }
}
