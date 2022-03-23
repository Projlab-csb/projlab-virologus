package com.csb.collectables.equipments;

import com.csb.collectables.Collectable;
import com.csb.virologist.Virologist;

public abstract class Equipment implements Collectable{

    public abstract void applyEffect(Virologist virologist);

    public abstract void removeEffect(Virologist virologist);
}
