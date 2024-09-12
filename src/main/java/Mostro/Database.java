package Mostro;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Database {
    private Random random = new Random();
    ArrayList<Type> types;
    ArrayList<Pokemon> pokemons;
    ArrayList<Move> moves;

    public Database() {
        types = new ArrayList<>();
        pokemons = new ArrayList<>();
        //moves = new ArrayList<>();
        //importMoves();
        importTypes();
        //importPokemos();

    }

    public void addPokemon(Pokemon p) {
        pokemons.add(p);
    }

    public void printPokemons(){
        for (Pokemon p :
                pokemons) {
            System.out.println(p.toString());
        }
    }

    private void importTypes() {
        try {
            FileReader fileReader = new FileReader("databaseTypes.txt");
            Scanner scanner = new Scanner(fileReader);


            while (scanner.hasNext()) {
                ArrayList<String> weakness2x = new ArrayList<>();
                ArrayList<String> weakness05x = new ArrayList<>();
                ArrayList<String> weakness0x = new ArrayList<>();
                String riga = scanner.nextLine();
                String[] splitted = riga.split(";");
                /*
                for (int i=0; i!= splitted.length;i++)
                    System.out.println(splitted[i]);

                 */
                String[] x2 = splitted[1].split(",");
                String[] x05 = splitted[2].split(",");
                String[] x0 = splitted[3].split(",");
                for (int i = 0; i != x2.length; i++)
                    weakness2x.add(x2[i]);
                for (int i = 0; i != x05.length; i++)
                    weakness05x.add(x05[i]);
                for (int i = 0; i != x0.length; i++)
                    weakness0x.add(x0[i]);
                Weakness weakness = new Weakness(weakness2x, weakness05x, weakness0x);
                Type type = new Type(splitted[0], weakness);
                types.add(type);
                //System.out.println(type.toString());
            }
        } catch (IOException e) {
            System.err.println("Errore apertura file");
        }
    }

    private void importMoves() {
        try {
            FileReader fileReader = new FileReader("databaseMoves.txt");
            Scanner scanner = new Scanner(fileReader);
            while (scanner.hasNext()) {
                String riga = scanner.nextLine();
                String[] splitted = riga.split(",");
                Move mossa = new Move(splitted[0], Double.parseDouble(splitted[1]), splitted[2],
                        Integer.parseInt(splitted[3]), splitted[4]);
                moves.add(mossa);
            }
        } catch (IOException e) {
            System.err.println("Errore apertura file");
        }
    }

    private void importPokemos() {
        //carico i pokemon
        try {
            FileReader fileReader = new FileReader("databasePokemon.txt");
            Scanner scanner = new Scanner(fileReader);
            while (scanner.hasNext()) {
                ArrayList<Statistic> statistics = new ArrayList<>();
                ArrayList<Move> pkMoves = new ArrayList<>();
                ArrayList<Type> types = new ArrayList<>();
                String riga = scanner.nextLine();
                String[] splitted = riga.split(";");
                String[] tys = splitted[1].split(",");//Types
                //Types
                for (int i = 0; i != tys.length; i++) {
                    types.add(getTypeByName(tys[i]));
                }
                //Statistics
                for (int i = 3; i != 6; i++) {
                    String[] statisticsTemp = splitted[i].split(",");
                    Statistic temp = new Statistic(statisticsTemp[0], Integer.parseInt(statisticsTemp[1]));
                    statistics.add(temp);
                }
                //Moves
                for (int i = 6; i != splitted.length; i++) {
                    Move temp = getMoveByName(splitted[i]);
                    pkMoves.add(temp);
                }
                Pokemon p = new Pokemon(splitted[0], pkMoves, statistics, types);
                //System.out.println(p.toString());
                pokemons.add(p);
            }

        } catch (IOException e) {
            System.err.println("Errore apertura file");
        }

    }

    private Move getMoveByName(String nome) {
        int i;
        boolean condizione = false;
        Move ritorno = new Move("Scontro", 60.0, "Mossa di default", 100, "Normale");
        for (i = 0; i != moves.size() && !condizione; i++) {
            //System.out.println(nome+"   "+moves.get(i).getName());
            if (nome.equals(moves.get(i).getName())) {
                condizione = true;
                ritorno = moves.get(i);
            }

        }
        return ritorno;
    }

    private Type getTypeByName(String nome) {
        int i;
        boolean condizione = false;
        Type ritorno = types.get(0);
        for (i = 0; i != types.size() && !condizione; i++) {
            //System.out.println(nome+"   "+moves.get(i).getName());
            if (nome.equals(types.get(i).getName())) {
                condizione = true;
                ritorno = types.get(i);
            }

        }
        return ritorno;
    }

    public Pokemon getRandomPokemon() {
        int randomNumber = random.nextInt(pokemons.size());
        Pokemon p = new Pokemon(pokemons.get(randomNumber).getName(),
                pokemons.get(randomNumber).getMoves(), pokemons.get(randomNumber).getStatistics(),
                pokemons.get(randomNumber).getTypes());
        return p;
    }

}
