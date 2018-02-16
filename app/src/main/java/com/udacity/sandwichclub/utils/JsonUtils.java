package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Parses String containing a {@link JSONObject} and creates a new {@link Sandwich}.
 */
public class JsonUtils {

    private static final String TAG = "JsonUtils";

    /**
     * Creates a new {@link Sandwich} according to data in input string.
     *
     * @param json String describing a new Sandwich
     * @return Sandwich representing data in the input JSON string
     */
    public static Sandwich parseSandwichJson(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONObject name = jsonObject.getJSONObject("name");
            String mainName = name.getString("mainName");
            JSONArray alsoKnownAsJson = name.getJSONArray("alsoKnownAs");
            List<String> alsoKnownAs = new ArrayList<>();
            for (int i = 0; i < alsoKnownAsJson.length(); i++) {
                String alias = alsoKnownAsJson.getString(i);
                alsoKnownAs.add(alias);
            }
            String placeOfOrigin = jsonObject.getString("placeOfOrigin");
            String description = jsonObject.getString("description");
            String image = jsonObject.getString("image");
            JSONArray ingredientsJson = jsonObject.getJSONArray("ingredients");
            List<String> ingredients = new ArrayList<>();
            for (int i = 0; i < ingredientsJson.length(); i++) {
                String ingredient = ingredientsJson.getString(i);
                ingredients.add(ingredient);
            }
            return new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);
        } catch (JSONException e) {
            Log.e(TAG, "Error while parsing Sandwich JSON: " + e.getMessage());
        }
        return null;
    }
}
