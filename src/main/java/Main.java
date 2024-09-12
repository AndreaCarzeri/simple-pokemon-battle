import Mostro.Database;
import Mostro.Move;
import Mostro.Pokemon;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    static Random random = new Random();

    public static void main(String[] args) {
        Database database = new Database();
        ArrayList<Pokemon> sUser = new ArrayList<>();
        ArrayList<Pokemon> sEnemy = new ArrayList<>();
        /*
        for (int i=1; i!=100; i++){
            HttpRequest httpRequest = new HttpRequest("https://pokeapi.co/api/v2/pokemon/"+i);
            httpRequest.ProvaHttp();
        }

         */
        System.out.println("Scaricando i pokemon...");
        for (int i = 0; i != 6; i++) {
            int n = random.nextInt(800) + 1;
            HttpRequest httpRequest = new HttpRequest("https://pokeapi.co/api/v2/pokemon/" + n);
            Pokemon p = httpRequest.loadPokemon();
            database.addPokemon(p);
            if (i >= 3)
                sUser.add(p);
            else
                sEnemy.add(p);
        }
        //database.printPokemons();


        //Prendo i primi
        Pokemon enemyPk = sEnemy.get(sEnemy.size() - 1);
        Pokemon myPk = sUser.get(sUser.size() - 1);

        boolean condizioneBot;
        if (enemyPk.getSpeed() > myPk.getSpeed())
            condizioneBot = true;
        else
            condizioneBot = false;

        while (sUser.size() != 0 && sEnemy.size() != 0) {

            if (condizioneBot) {
                battleBot(enemyPk, myPk);
                condizioneBot = false;
            } else {
                battle(enemyPk, myPk);
                condizioneBot = true;
            }

            enemyPk=swapPokemonTeam(sEnemy, enemyPk);
            myPk=swapPokemonTeam(sUser, myPk);
        }
        showInfo(enemyPk, myPk);
    }

    private static Pokemon swapPokemonTeam(ArrayList<Pokemon> squad, Pokemon pk) {
        if (!pk.isAlive()) {
            if(squad.size() != 1) {
                System.out.print(pk.getName() + " viene sostituito con ");
                squad.remove(squad.size() - 1);
                pk = squad.get(squad.size() - 1);
                System.out.println(pk.getName());
            }
            else
                squad.remove(squad.size() - 1);
        }
        return pk;
    }

    private static double calculateDamage(Pokemon source, Pokemon target, Move mossa) {
        //Algoritmo
        double stab = (source.isStab(mossa.getType())) ? 1.5 : 1;
        double dr = target.calculateModifierType(mossa); //Debolezze
        //System.out.println("MODIFICATORE TIPO: "+dr);
        int rn = random.nextInt(15) + 85;
        //System.out.println(source.toString());
        double risultato = ((((2 * source.getLevel()) / (7.0)) * source.getAttack() * mossa.getDamage()) / target.getDefence()) / 50;
        //System.out.println(risultato);
        risultato = ((risultato + 2) * stab * dr * rn) / 100;
        System.out.println("Risulato calcolo danni: " + risultato);
        return risultato;
    }

    private static void battleBot(Pokemon enemyPk, Pokemon myPk) {
        showInfo(enemyPk, myPk);
        Move mossa = enemyPk.getMossa(random.nextInt(enemyPk.getLenMoves()));
        if (mossa.hit()) {
            myPk.damage(calculateDamage(enemyPk, myPk, mossa));
            System.out.println(enemyPk.getName() + " AVVERSARIO USA " + mossa.getName() + "\n");
        } else
            System.out.println(enemyPk.getName() + " AVVERSARIO SBAGLIA AD ATTACCARE!\n");
    }

    /**
     * mostra le informazioni dei pokemon in battaglia
     *
     * @param enemyPk pokemon avversario
     * @param myPk    pokemon personale
     */
    private static void showInfo(Pokemon enemyPk, Pokemon myPk) {
        System.out.println("ENEMY");
        enemyPk.displayInfo();
        System.out.println("\nVS\n");
        myPk.displayInfo();
        System.out.println("\n");
    }

    private static void battle(Pokemon enemyPk, Pokemon myPk) {
        showInfo(enemyPk, myPk);
        Move mossa = sceltaMossa(myPk);
        //accuracy
        if (mossa.hit()) {
            enemyPk.damage(calculateDamage(myPk, enemyPk, mossa));
            System.out.println(myPk.getName() + " USA " + mossa.getName() + "\n");
        } else
            System.out.println("\nAVVERSARIO MANCATO!\n");

    }


    private static Move sceltaMossa(Pokemon myPk) {
        myPk.showMoves();
        int scelta = -1;
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Inserisci il numero della mossa da fare!");
            scelta = scanner.nextInt();
            if (scelta < 1 || scelta > myPk.getLenMoves())
                System.out.println("Errore, mossa non esistente");
        } while (scelta < 1 || scelta > myPk.getLenMoves());
        return myPk.getMossa(scelta - 1);
    }


}