package com.example.recipeapplication;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RecipeData {
    public static JSONArray getRecipes(Context context) {

        JSONArray recipes = new JSONArray();

        try {
            InputStream inputStream = context.getAssets().open("recipes.json");

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            StringBuilder stringBuilder = new StringBuilder();

            String line;

            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }

            reader.close();

            recipes = new JSONArray(stringBuilder.toString());

        } catch (Exception e) {
            Log.e("RecipeData", "Error reading JSON file", e);
        }

        return recipes;
    }

    public static JSONObject getRecipeByName(Context context, String recipeName) {

        JSONArray recipes = getRecipes(context);

        for (int i = 0; i < recipes.length(); i++) {

            try {

                JSONObject recipe = recipes.getJSONObject(i);

                if (recipe.getString("name").equals(recipeName)) {

                    return recipe;

                }

            } catch (Exception e) {

                Log.e("RecipeData", "Error finding recipe", e);

            }
        }

        return null;
    }
}