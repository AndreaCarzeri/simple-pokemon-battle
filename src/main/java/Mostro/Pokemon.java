package Mostro;

import java.util.ArrayList;

public class Pokemon {
    private double vita;
    private String name;
    private int level;
    private ArrayList<Move> moves;
    private ArrayList<Statistic> statistics;
    private ArrayList<Type> types;

    public Pokemon(String name, ArrayList<Move> moves, ArrayList<Statistic> statistics, ArrayList<Type> types) {
        this.name = name;
        this.moves = moves;
        this.level = 50;
        this.statistics = statistics;
        this.vita = getHp();
        this.types = types;
    }

    private int getHp(){
        for (Statistic s:
             statistics) {
            if(s.getName().equals("hp"))
                return s.getValue();
        }
        return 1;
    }

    public ArrayList<Type> getTypes() {
        return types;
    }

    public boolean isAlive() {
        if (vita != 0)
            return true;
        else
            return false;
    }

    public double calculateModifierType(Move mossa){
        double risultato=1.0;
        for (Type t :
                types) {
            //System.out.println(t.getName()+"  "+mossa.getType());
            risultato*=t.returnModifier(mossa.getType());
            //System.out.println(risultato);
        }
        return risultato;
    }

    public void damage(double quantity) {
        if (quantity > 0) {
            vita -= quantity;
            if (vita < 0)
                vita = 0;
        }
    }

    public int getLevel() {
        return level;
    }

    public int getAttack() {
        for (Statistic s :
                statistics) {
            if (s.getName().toLowerCase().equals("attack"))
                return s.getValue();
        }
        return 0;

    }

    public boolean isStab(String tipo){
        boolean contiene=false;
        for (Type t :
                types) {
            if (t.getName().equals(tipo))
                contiene=true;
        }
        return contiene;
    }
    public int getDefence(){
        for (Statistic s :
                statistics) {
            if (s.getName().toLowerCase().equals("defense"))
                return s.getValue();
        }
        return 0;
    }

    public double getVitaMax() {
        return getHp();
    }

    public ArrayList<Move> getMoves() {
        return moves;
    }

    public ArrayList<Statistic> getStatistics() {
        return statistics;
    }

    public int getSpeed() {
        for (Statistic s :
                statistics) {
            if (s.getName().equals("Velocita"))
                return s.getValue();
        }
        return 0;
    }

    @Override
    public String toString() {
        return name + " " + vita + " " + getHp() + " " + moves.toString() + " " + statistics.toString() + " " + types.toString();
    }

    public void displayInfo() {
        System.out.println("--------------------");
        displayTypes();
        System.out.println("\n" + name);
        printLife();
        System.out.println(" " + (int) vita + "/" + (int) getHp() + " PS");
        System.out.println("--------------------");
    }


    public void showMoves() {
        int i = 1;
        for (Move m :
                moves) {
            System.out.println(i + ")");
            i++;
            m.displayInfo();
        }
    }

    public String getName() {
        return name;
    }

    public int getLenMoves() {
        return moves.size();
    }

    public Move getMossa(int index) {
        if (index >= 0 && index < moves.size())
            return moves.get(index);
        else
            return moves.get(0);
    }

    private void displayTypes() {
        for (Type t :
                types) {
            System.out.print(t.getName() + " ");
        }
    }

    public void printLife() {
        System.out.print("[");
        for (int i = 0; i != 10; i++) {
            if (i < (vita / getHp()) * 10)
                System.out.print("*");
            else
                System.out.print(" ");
        }
        System.out.print("]");
    }

}
