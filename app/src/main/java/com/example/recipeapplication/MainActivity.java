package com.example.recipeapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements RecipesFragment.OnRecipeSelectedListener {

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

        FrameLayout detailContainer = findViewById(R.id.detail_container);

        if (detailContainer != null) {

            // Landscape mode

            IngredientsFragment detailFragment = IngredientsFragment.newInstance(recipeName);

            getSupportFragmentManager().beginTransaction().replace(R.id.detail_container, detailFragment).addToBackStack(null).commit();

        } else {

            // Portrait mode

            Intent intent = new Intent(MainActivity.this, IngredientsActivity.class);

            intent.putExtra("recipeName", recipeName);

            startActivity(intent);
        }
    }
}