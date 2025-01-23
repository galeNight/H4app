package com.example.udlnshndteringsappfortabletter;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

// Aktivitet for Admin-login. Her kan admin logge ind med en adgangskode for at få adgang til udlånsoverblikket.
public class AdminLoginActivity extends AppCompatActivity {
    private static final String ADMIN_PASSWORD = "1234";
    private EditText editTextPassword;
    private Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        // Initialiser inputfelt og knap samt kliklistner på login-knappen
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);

        buttonLogin.setOnClickListener(v -> {
            String password = editTextPassword.getText().toString(); // Hent kode fra inputfelt
            if (password.equals(ADMIN_PASSWORD)) {
                // Hvis koden er korrekt, gå til AdminActivity
                Intent intent = new Intent(AdminLoginActivity.this, AdminActivity.class);
                startActivity(intent);
                finish(); // Lukker login-skærmen
            } else {
                // Hvis koden er forkert, vis en fejlmeddelelse
                Toast.makeText(this, "Forkert kode!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

