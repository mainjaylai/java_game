package com.company;

import java.util.Random;

interface Canplay {
    public void attack(Actor ememy);

    public void skill(Actor ememy);
}

class attacknum {
    static public int master[] = {20, 10, 31};//0
    static public int shooter[] = {34, 23, 14};//1克制法师
    static public int worrior[] = {13, 29, 19};//2//克制射手
    static public int defence[] = {17, 12, 20};//防御
    static public int[] blood = {175, 160, 172};
}

public class Actor implements Canplay {
    private int blood;
    private int id;//master("master",{}),shooter("shooter"),warrior("worrior");
    private boolean isdefence = false;
    private int crit_rate;//暴击率
    public int maxblood;

    Actor(int blood, int id, int crit_rate) {
        this.blood = blood;
        maxblood = blood;
        this.id = id;
        this.crit_rate = crit_rate;
    }

    public boolean getdefence() {
        return isdefence;
    }

    public int getblood() {
        return blood;
    }

    public void setdefence(boolean flag) {
        isdefence = flag;
    }

    public void addblood(int add) {
        this.blood += add;
    }

    public int getid() {
        return id;
    }

    @Override
    public void attack(Actor ememy) {
        int temp = 0;
        Random rand = new Random();
        int t = rand.nextInt(100);
        int isdouble = t <= crit_rate ? 2 : 1;
        switch (this.id) {
            case 0:
                temp = attacknum.master[ememy.getid()] * isdouble - (ememy.getdefence() ? 1 : 0) * attacknum.defence[ememy.getid()];
                break;
            case 1:
                temp = attacknum.shooter[ememy.getid()] * isdouble - (ememy.getdefence() ? 1 : 0) * attacknum.defence[ememy.getid()];
                break;
            case 2:
                temp = attacknum.worrior[ememy.getid()] * isdouble - (ememy.getdefence() ? 1 : 0) * attacknum.defence[ememy.getid()];
                break;
        }
        ememy.addblood(temp > 0 ? (-1 * temp) : 0);
    }


    @Override
    public void skill(Actor ememy) {
        switch (this.id) {
            case 0:
                this.addblood(27);
                ememy.addblood(-25);
                break;
            case 1:
                ememy.addblood(-50);
                break;
            case 2:
                ememy.addblood(-40);
                this.crit_rate = 30;
                break;
        }
    }
}

