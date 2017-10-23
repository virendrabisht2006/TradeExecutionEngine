package com.virendra.trade.execution.engine.model;


public enum Indicator {
    S("Sell"),
    B("Buy");

    private String name;

    Indicator(String name){
       this.name = name;
    }

    public String getName(){
        return this.name;
    }
}
