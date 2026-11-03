package com.example.recipeapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class IngredientsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ingredients);

        if (savedInstanceState == null) {

            String recipeName = getIntent().getStringExtra("recipeName");

            IngredientsFragment ingredientsFragment = IngredientsFragment.newInstance(recipeName);

            getSupportFragmentManager().beginTransaction().replace(R.id.ingredients_container, ingredientsFragment).commit();
        }
    }
}
