package com.example.recipeapplication;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class RecipesFragment extends Fragment {

    ListView recipeList;

    OnRecipeSelectedListener listener;

    public interface OnRecipeSelectedListener {

        void onRecipeSelected(String recipeName);

    }

    @Override
    public void onAttach(Context context) {

        super.onAttach(context);

        if (context instanceof OnRecipeSelectedListener) {
            listener = (OnRecipeSelectedListener) context;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_recipes, container, false);

        recipeList = view.findViewById(R.id.recipeList);

        ArrayList<String> recipeNames = new ArrayList<>();

        JSONArray recipes = RecipeData.getRecipes(requireContext());

        for (int i = 0; i < recipes.length(); i++) {

            try {

                JSONObject recipe = recipes.getJSONObject(i);

                String name = recipe.getString("name");

                recipeNames.add(name);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, recipeNames);

        recipeList.setAdapter(adapter);

        recipeList.setOnItemClickListener((parent, view1, position, id) -> {

            String selectedRecipe = recipeNames.get(position);

            if (listener != null) {
                listener.onRecipeSelected(selectedRecipe);
            }
        });

        return view;
    }
}
