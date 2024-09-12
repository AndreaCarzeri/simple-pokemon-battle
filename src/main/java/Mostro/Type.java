package Mostro;

public class Type {
    private String name;
    private Weakness weakness;

    public Type(String nome, Weakness weakness) {
        this.name = nome;
        this.weakness = weakness;
    }
    public Type(String nome) {
        this.name = nome;
        this.weakness = new Weakness();
    }

    public String getName() {
        return name;
    }

    public double returnModifier(String tipo) {
        double risultato = 1.0;
        if (Weakness.containsType(weakness.getWeakness2x(),tipo)){
            System.out.println("Debolezza 2x");
            risultato = 2.0;
        }
        else if (Weakness.containsType(weakness.getWeakness05x(),tipo)){
            System.out.println("Debolezza 0.5x");
            risultato = 0.5;
        }

        else if (Weakness.containsType(weakness.getWeakness0x(),tipo)){
            System.out.println("Debolezza 0x");
            risultato = 0.0;
        }


        return risultato;
    }

    public Weakness getWeakness() {
        return weakness;
    }

    @Override
    public String toString(){
        return name+" "+weakness.toString();
    }
}
