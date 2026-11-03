package com.example.recipeapplication;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity
        implements RecipesFragment.OnRecipeSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            RecipesFragment recipesFragment = new RecipesFragment();

            getSupportFragmentManager().beginTransaction().replace(R.id.recipe_container, recipesFragment).commit();
        }
    }

    @Override
    public void onRecipeSelected(String recipeName) {

        int orientation = getResources().getConfiguration().orientation;

        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {

            IngredientsFragment detailFragment = IngredientsFragment.newInstance(recipeName);

            getSupportFragmentManager().beginTransaction().replace(R.id.detail_container, detailFragment).addToBackStack(null).commit();

        } else {

            Intent intent = new Intent(MainActivity.this, IngredientsActivity.class);

            intent.putExtra("recipeName", recipeName);

            startActivity(intent);
        }
    }
}