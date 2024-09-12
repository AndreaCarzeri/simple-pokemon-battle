package Mostro;

public class Statistic {
    private String name;
    private int value;

    public Statistic(String name, int value){
        this.name=name;
        this.value=value;
    }

    @Override
    public String toString(){
        return name+" "+value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
