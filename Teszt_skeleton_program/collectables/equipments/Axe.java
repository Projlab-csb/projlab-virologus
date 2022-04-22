import com.csb.skeletonTester.Tester;
import com.csb.virologist.Virologist;

/**
 * Class for the Axe objects. Axe let virologists murder another.
 */
public class Axe extends Equipment {



    /**
     * Add plus space to the owners inventory
     * @param virologist - his inventory will be bigger til owns the bag
     */
    public void applyEffect(Virologist virologist) {
        virologist.;
    }

    /**
     * Remove inventory bonus
     * @param virologist - his inventory's size must be reduced
     */
    @Override
    public void removeEffect(Virologist virologist) {
        virologist.setInventorySize(virologist.getInventorySize() - getExtraInventorySize());
    }

    /**
     * Getter for extrainventorysize
     */
    private int getExtraInventorySize() {
        Tester.getInstance().functionStart();
        Tester.getInstance().functionEnd();
        return extraInventorySize;
    }
}
