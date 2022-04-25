package com.csb.collectables.matters;

import com.csb.skeletonTester.Tester;
import com.csb.skeletonTester.UserInputHandler;
import com.csb.virologist.Virologist;

/**
 * Class, what describes the AminoAcid matter with its amount. AminoAcid is one of the two ingredients of the Agents
 */
public class AminoAcid extends Matter {

    /**
     * Constructors with or without parameter
     * the amount -1 means infinite amount
     */
    public AminoAcid() {
        super();
    }

    public AminoAcid(int n) {
        super(n);
    }

    /**
     *A virologist can collect matter from a storage (an infinite source) and from a finite source (a paralyzed virologist)
     * @param virologist - a virologist who collect the matter
     */
    @Override
    public void collectBy(Virologist virologist) {
        if (virologist.getInventorySize() <= virologist.getAminoAcid() + virologist.getNucleicAcid()) {
            System.out.println("The inventory is full, you won't get more matter");
            return;
        }

        //if the source is a storage, the amount is infinite, the virologist fill his stock to full capacity
        if (this.getAmount() == -1) {
            virologist.setAminoAcid(virologist.getAminoAcid() + virologist.getInventorySize() - virologist.getNucleicAcid());
            System.out.println(virologist.getName() + " has collected AminoAcid to full");
        }
        //if the source is an other virologist, the source is finite, so it is the minimum of his full capacity and the sum of his owned acid and stolen acid
        else {
            virologist.setAminoAcid(
                Math.min(
                    virologist.getAminoAcid() + this.getAmount(),
                    virologist.getAminoAcid() + virologist.getInventorySize() - virologist.getNucleicAcid()
                )
            );
            System.out.println(virologist.getName() + " has collected AminoAcid, now he has" + virologist.getAminoAcid());
        }
    }

    /**
     * This removes the amount of amino acid from the virologist
     * @param virologist - who discard an amount of Aminoacid
     */
    @Override
    public void discard(Virologist virologist) {
        int discarded = this.getAmount();

        //if the player want to discard more acid than the virologist has, he discards all the virologist has
        virologist.setAminoAcid(virologist.getAminoAcid() - discarded);

        System.out.println(virologist.getName() + " has discarded AminoAcid, now he has" + virologist.getAminoAcid());
    }
}
