package com.apakgroup.recall.setup;


public class Obj2 {

    private final String str;

    public Obj2(final String str) {
        this.str = str;
    }

    public void print() {
        System.out.println("Obj printing! " + str);
    }

    public Obj getObj() {
        return new Obj();
    }

    public String getStr() {
        return str;
    }

}

