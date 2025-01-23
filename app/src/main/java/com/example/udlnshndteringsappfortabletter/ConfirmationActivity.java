package com.example.udlnshndteringsappfortabletter;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

// Aktivitet for at vise bekræftelsesbesked
public class ConfirmationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        // Initialiser views
        TextView textViewMessage = findViewById(R.id.textViewConfirmationMessage);
        Button buttonBackToMain = findViewById(R.id.buttonBackToMain);

        // Indstil brugerdefineret besked, hvis den blev sendt fra en anden aktivitet
        String confirmationMessage = getIntent().getStringExtra("CONFIRMATION_MESSAGE");
        if (confirmationMessage != null && !confirmationMessage.isEmpty()) {
            textViewMessage.setText(confirmationMessage);
        }

        // Klik på knappen for at gå tilbage til hovedskærmen
        buttonBackToMain.setOnClickListener(v -> {
            Intent intent = new Intent(ConfirmationActivity.this, MainActivity.class);
            startActivity(intent);
            finish(); // Luk denne aktivitet
        });
    }
}
