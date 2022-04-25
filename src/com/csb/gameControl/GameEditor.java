package com.csb.gameControl;

import com.csb.agents.*;
import com.csb.collectables.Collectable;
import com.csb.collectables.equipments.*;
import com.csb.collectables.gencodes.Gencode;
import com.csb.collectables.matters.AminoAcid;
import com.csb.collectables.matters.NucleicAcid;
import com.csb.fields.*;
import com.csb.skeletonTester.UserInputHandler;
import com.csb.virologist.Virologist;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GameEditor {

    private static GameEditor _instance;

    private GameEditor() {}

    public static GameEditor getInstance() {
        if (_instance == null) {
            _instance = new GameEditor();
        }
        return _instance;
    }

    /**
     * Editor mode, edit all the game data
     */
    public void enterEditorMode() {
        create();
    }

    private void create() {
        createField();
        GameController.getInstance().allGencodes.addAll(getGencodes());
        int virologistCount = UserInputHandler.getUserInputInt("How many virologists do you want to create?", 1, 10);
        for (int i = 0; i < virologistCount; i++) {
            createVirologist();
        }
    }

    /**
     * Create field, every field is connected to all the other fields
     */
    private void createField() {
        int fieldCount = UserInputHandler.getUserInputInt("How many fields do you want to create?", 1, 100);
        for (int i = 0; i < fieldCount; i++) {
            String name = UserInputHandler.getUserInputString(
                "What is the type of the field?",
                new String[] { "Field", "Storage", "Lab", "Cursed Lab", "Shelter" }
            );
            switch (name) {
                case "Field":
                    GameController.getInstance().map.fields.add(new Field());
                    break;
                case "Storage":
                    GameController.getInstance().map.fields.add(createStorageField());
                    break;
                case "Lab":
                    GameController.getInstance().map.fields.add(createLab());
                    break;
                case "Cursed Lab":
                    GameController.getInstance().map.fields.add(createCursedLab());
                    break;
                case "Shelter":
                    GameController.getInstance().map.fields.add(createShelter());
                    break;
            }
        }
        //Connect the fields
        for (int i = 0; i < GameController.getInstance().map.fields.size(); i++) {
            for (int j = 0; j < GameController.getInstance().map.fields.size(); j++) {
                if (i != j) {
                    GameController.getInstance().map.fields.get(i).addNeighbor(GameController.getInstance().map.fields.get(j));
                }
            }
        }
    }

    /**
     * Storage field has both acid
     */
    private Storage createStorageField() {
        ArrayList<Collectable> contents = new ArrayList<>();
        contents.add(new AminoAcid());
        contents.add(new NucleicAcid());
        return new Storage(contents);
    }

    /**
     * Every shelter has 3 of each type of stuff
     */
    private Shelter createShelter() {
        ArrayList<Collectable> contents = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            contents.add(new Axe());
            contents.add(new Cloak());
            contents.add(new Bag());
            contents.add(new Gloves());
        }
        return new Shelter(contents);
    }

    private Lab createLab() {
        ArrayList<Collectable> contents = getCollectables();
        return new Lab(contents);
    }

    private Lab createCursedLab() {
        ArrayList<Collectable> contents = getCollectables();
        return new CursedLab(contents);
    }

    private ArrayList<Collectable> getCollectables() {
        ArrayList<Collectable> contents = new ArrayList<>();
        contents.add(new Gencode(new Forget(), new AminoAcid(1), new NucleicAcid(1)));
        contents.add(new Gencode(new Paralyzed(), new AminoAcid(1), new NucleicAcid(1)));
        contents.add(new Gencode(new VitusDance(), new AminoAcid(1), new NucleicAcid(1)));
        contents.add(new Gencode(new Protection(), new AminoAcid(1), new NucleicAcid(1)));
        return contents;
    }

    private ArrayList<Gencode> getGencodes() {
        ArrayList<Gencode> contents = new ArrayList<>();
        contents.add(new Gencode(new Forget(), new AminoAcid(1), new NucleicAcid(1)));
        contents.add(new Gencode(new Paralyzed(), new AminoAcid(1), new NucleicAcid(1)));
        contents.add(new Gencode(new VitusDance(), new AminoAcid(1), new NucleicAcid(1)));
        contents.add(new Gencode(new Protection(), new AminoAcid(1), new NucleicAcid(1)));
        return contents;
    }

    private void createVirologist() {
        String name = UserInputHandler.getUserInputString("What is the name of the virologist?");
        Virologist virologist = new Virologist(name);
        GameController.getInstance().allVirologists.add(virologist);

        //Put the virologist on a field
        List<Field> fields = GameController.getInstance().map.getFields();

        for (int i = 0; i < fields.size(); i++) {
            Field field = fields.get(i);
            System.out.println(i + ". " + field.getClass().getSimpleName());
        }
        int fieldId = UserInputHandler.getUserInputInt("What field to put the virologist on?", 0, fields.size() - 1);
        fields.get(fieldId).acceptVirologist(virologist);

        //Put stuff on the virologist
        String command = "";
        do {
            command =
                UserInputHandler.getUserInputString(
                    "What do you want to put on the virologist? ",
                    new String[] { "Equipment", "Gencode", "Nucleic Acid", "Amino Acid", "Agent", "Exit" }
                );
            switch (command) {
                case "Equipment":
                    equipmentSelect().collectBy(virologist);
                    break;
                case "Gencode":
                    Gencode gencode = gencodeSelect();
                    virologist.addGencode(gencode);
                    break;
                case "Nucleic Acid":
                    virologist.setNucleicAcid(UserInputHandler.getUserInputInt("How much nucleic acid?", 0, 5));
                    break;
                case "Amino Acid":
                    virologist.setAminoAcid(UserInputHandler.getUserInputInt("How much amino acid?", 0, 5));
                    break;
                case "Agent":
                    applyAgent(virologist);
                    break;
            }
        } while (!command.equals("Exit"));
    }

    private void applyAgent(Virologist virologist) {
        String command = "";
        command =
            UserInputHandler.getUserInputString(
                "What agent do you want to apply? ",
                new String[] { "Forget", "Paralyzed", "Vitus Dance", "Protection" }
            );
        switch (command) {
            case "Forget":
                new Forget().applyEffect(virologist);
                break;
            case "Paralyzed":
                new Paralyzed().applyEffect(virologist);
                break;
            case "Vitus Dance":
                new VitusDance().applyEffect(virologist);
                break;
            case "Protection":
                new Protection().applyEffect(virologist);
                break;
            case "Bear Dance":
                new BearDance().applyEffect(virologist);
                break;
        }
    }

    private Equipment equipmentSelect() {
        String command = "";
        command =
            UserInputHandler.getUserInputString(
                "What equipment to put on the virologist? ",
                new String[] { "Axe", "Bag", "Cloak", "Gloves" }
            );
        switch (command) {
            case "Axe":
                return new Axe();
            case "Bag":
                return new Bag();
            case "Cloak":
                return new Cloak();
            case "Gloves":
                return new Gloves();
        }
        return null;
    }

    private Gencode gencodeSelect() {
        String command = "";
        command =
            UserInputHandler.getUserInputString(
                "What gencode to put on the virologist? ",
                new String[] { "Forget", "Paralyzed", "VitusDance", "Protection" }
            );
        switch (command) {
            case "Forget":
                return new Gencode(new Forget(), new AminoAcid(1), new NucleicAcid(1));
            case "Paralyzed":
                return new Gencode(new Paralyzed(), new AminoAcid(1), new NucleicAcid(1));
            case "VitusDance":
                return new Gencode(new VitusDance(), new AminoAcid(1), new NucleicAcid(1));
            case "Protection":
                return new Gencode(new Protection(), new AminoAcid(1), new NucleicAcid(1));
        }
        return null;
    }
}
