package com.example.recipeapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import org.json.JSONArray;
import org.json.JSONObject;

public class IngredientsFragment extends Fragment {

    public static IngredientsFragment newInstance(String recipeName) {

        IngredientsFragment fragment = new IngredientsFragment();

        Bundle bundle = new Bundle();

        bundle.putString("recipeName", recipeName);

        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_ingredients, container, false);

        TextView recipeNameText = view.findViewById(R.id.recipeName);

        TextView ingredientsText = view.findViewById(R.id.ingredientsText);

        TextView methodText = view.findViewById(R.id.methodText);

        String recipeName = getArguments().getString("recipeName");

        recipeNameText.setText(recipeName);

        JSONObject recipe = RecipeData.getRecipeByName(requireContext(), recipeName);

        if (recipe != null) {

            try {

                JSONArray ingredients = recipe.getJSONArray("ingredients");

                StringBuilder ingredientList = new StringBuilder();

                for (int i = 0; i < ingredients.length(); i++) {

                    ingredientList.append("- ");

                    ingredientList.append(ingredients.getString(i));

                    ingredientList.append("\n");
                }

                ingredientsText.setText(ingredientList.toString());

                String method = recipe.getString("method");

                methodText.setText(method);

            } catch (Exception e) {
                e.printStackTrace();

            }
        }

        return view;
    }
}