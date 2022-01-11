package ch.bbw.pg.api;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;

/**
 * @author P. Gatzka
 * @version 04.01.2022
 * Project: Spoon
 */
public class ConnectionManager {

    private static final Properties properties = new Properties();

    private static void loadProperties() {
        try {
            properties.load(new BufferedReader(new FileReader("src/main/resources/META-INF/ApplicationConfig.properties")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Recipe getRandomRecipe() {
        loadProperties();
        final String url = "https://api.spoonacular.com/recipes/random?instructionsRequired=true&fillIngredients=false&addRecipeInformation=false&number=1&apiKey=" + properties.getProperty("apiKey");

        try {
            final HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setRequestMethod("GET");

            System.out.println(conn.getResponseCode() + " " + conn.getResponseMessage());

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            StringBuilder builder = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(builder.toString());
            JSONObject jsonRecipe = (JSONObject) ((JSONArray) jsonObject.get("recipes")).get(0);
            return new Recipe((Long) jsonRecipe.get("id"), (String) jsonRecipe.get("title"), (String) jsonRecipe.get("image"), (String) jsonRecipe.get("imageType"));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return null;

    }

    public static void getRecipeInstructions(Recipe recipe) {

        final String url = "https://api.spoonacular.com/recipes/" + recipe.getId() + "/analyzedInstructions?stepBreakdown=true&apiKey=" + properties.getProperty("apiKey");
        try {
            final HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setRequestMethod("GET");

            System.out.println(conn.getResponseCode() + " " + conn.getResponseMessage());

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            StringBuilder builder = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }

            JSONArray array = (JSONArray) new JSONParser().parse(builder.toString());
            JSONObject object = (JSONObject) array.get(0);
            JSONArray steps = (JSONArray) object.get("steps");

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }


    }


}
