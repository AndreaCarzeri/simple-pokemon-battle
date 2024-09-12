package Mostro;

import java.util.ArrayList;

public class Weakness {
    private ArrayList<String> weakness2x;
    private ArrayList<String> weakness05x;
    private ArrayList<String> weakness0x;

    public Weakness(ArrayList<String> weakness2x, ArrayList<String> weakness05x, ArrayList<String> weakness0x) {
        this.weakness2x = weakness2x;
        this.weakness05x = weakness05x;
        this.weakness0x = weakness0x;
    }

    public Weakness() {
        weakness0x= new ArrayList<>();
        weakness2x= new ArrayList<>();
        weakness05x= new ArrayList<>();
    }

    static public boolean containsType(ArrayList<String> weakness, String type){
        for (String s:
             weakness) {
            if(s.equals(type))
                return true;
        }
        return false;
    }

    public ArrayList<String> getWeakness2x() {
        return weakness2x;
    }

    public ArrayList<String> getWeakness05x() {
        return weakness05x;
    }

    public ArrayList<String> getWeakness0x() {
        return weakness0x;
    }

    @Override
    public String toString() {
        return weakness2x.toString() + " " + weakness05x.toString() + " " + weakness0x.toString();
    }

}
