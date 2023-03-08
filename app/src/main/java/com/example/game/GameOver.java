package com.example.game;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class GameOver extends AppCompatActivity {
    TextView tvScore;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_over);
        int score = getIntent().getExtras().getInt("score");
        tvScore = findViewById(R.id.tvScore) ;
        tvScore.setText(""+score);
    }

    public void restart(View view) {
        Intent intent =new Intent(GameOver.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
