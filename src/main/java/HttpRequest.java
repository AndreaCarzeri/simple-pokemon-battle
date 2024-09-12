import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;

import Mostro.Move;
import Mostro.Pokemon;
import Mostro.Statistic;
import Mostro.Type;
import org.json.JSONObject;
import org.json.JSONArray;


public class HttpRequest {
    URL url;

    public HttpRequest(String url) {
        try {
            this.url = new URL(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Pokemon loadPokemon() {
        Pokemon p = null;
        try {
            JSONObject jsonObject = returnJson(url);
            String nomePk = jsonObject.getString("name");
            ArrayList<Type> types = new ArrayList<>();
            ArrayList<Statistic> statistics = new ArrayList<>();
            //ArrayList<Move> moves = new ArrayList<>();

            for (int i = 0; i != jsonObject.getJSONArray("types").length(); i++) {
                Type t = new Type(jsonObject.getJSONArray("types").getJSONObject(i).getJSONObject("type").getString("name"));
                types.add(t);
            }

            for (int i = 0; i != jsonObject.getJSONArray("stats").length(); i++) {
                int value = jsonObject.getJSONArray("stats").getJSONObject(i).getInt("base_stat");
                Statistic s = new Statistic(jsonObject.getJSONArray("stats").getJSONObject(i).getJSONObject("stat").getString("name"), value);
                statistics.add(s);
            }

            p = new Pokemon(nomePk, loadMoves(), statistics, types);

            /*
            String name = jsonObject.getString("name");
            int age = jsonObject.getInt("age");
            String city = jsonObject.getString("city");

            System.out.println(name+" "+age+" "+city);

             */


            /* Parsare la risposta JSON utilizzando Gson
            Gson gson = new Gson();
            JsonElement jsonElement = gson.fromJson(br, JsonElement.class);
            JsonObject jsonObject = jsonElement.getAsJsonObject();

            // Gestire la risposta JSON
            String value = jsonObject.get("key").getAsString();
            System.out.println("Il valore della chiave 'key' è: " + value);
             */
        } catch (Exception e) {
            e.printStackTrace();
        }
        return p;
    }

    public ArrayList<Move> loadMoves() {
        ArrayList<Move> moves = new ArrayList<>();
        try {
            JSONObject jsonObject = returnJson(url);

            for (int i = 0; i != 4; i++) { //Capire come fare se un pokemon ha meno di 4 mosse
                //String nameMove= jsonObject.getJSONArray("moves").getJSONObject(i).getString("name");
                //Nuova richiesta http
                HttpRequest httpRequest = new HttpRequest(jsonObject.getJSONArray("moves").getJSONObject(i).getJSONObject("move").getString("url"));
                Move m = httpRequest.loadMoveInfo();
                moves.add(m);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return moves;
    }

    private Move loadMoveInfo() {
        Move m = null;

        JSONObject jsonObject = returnJson(url);
        //System.out.println(jsonObject.getInt("id"));
        //System.out.println(jsonObject.toString());
        String name = jsonObject.getString("name");
        //System.out.println(jsonObject.getInt("accuracy"));
        int accuracy = (jsonObject.isNull("accuracy")) ? 100 : jsonObject.getInt("accuracy");
        //System.out.println(accuracy);
        //System.out.println(jsonObject.getInt("accuracy"));
        int damage = (jsonObject.isNull("power")) ? 0 : jsonObject.getInt("power");
        //String desc= jsonObject.getJSONArray("flavor_text_entries").getJSONObject(0).getString("flavor_text");
        //System.out.println(jsonObject.getInt("id"));
        String type = jsonObject.getJSONObject("type").getString("name");
        m = new Move(name, damage, "ciao", accuracy, type);
        return m;
    }

    private JSONObject returnJson(URL url) {
        JSONObject jsonObject = null;
        try {
            HttpURLConnection conn = (HttpURLConnection) this.url.openConnection();

            // Impostare il metodo di richiesta e le proprietà della connessione
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            // Leggere la risposta dal server
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            jsonObject = new JSONObject(br.readLine());
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
}
