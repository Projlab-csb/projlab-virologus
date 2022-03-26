package com.csb.collectables.matters;

import com.csb.collectables.Collectable;

public abstract class Matter implements Collectable {
    private int amount;
    public Matter(){
        amount=0;
    };
    public Matter(int n){
        amount=n;
    };

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
