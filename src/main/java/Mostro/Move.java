package Mostro;

import java.util.Random;

public class Move {
    private String name;
    private Double damage;
    private String desc;
    private int accuracy;
    private String type;
    private Random random= new Random();

    public Move(String name, double damage, String desc, int accuracy, String type) {
        this.name = name;
        this.damage = damage;
        this.desc = desc;
        this.accuracy = accuracy;
        this.type=type;
    }

    public void displayInfo(){
        System.out.println(name);
        System.out.print("Type: "+type);
        System.out.println("\t"+damage+" Power");
        System.out.println("Accuracy: "+accuracy);
        System.out.println(desc+"\n");
    }

    @Override
    public String toString(){
        return name+" "+damage+" "+accuracy+" "+desc+" "+type;
    }

    public String getName() {
        return name;
    }

    public double getDamage() {
        return damage;
    }

    public String getDesc() {
        return desc;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public String getType() {
        return type;
    }

    public boolean hit(){
        int hit=random.nextInt(100)+1;
        if(hit>accuracy)
            return false;
        else
            return true;
    }
}
