package com.example.udlnshndteringsappfortabletter;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

// Hovedaktivitet, som giver brugeren mulighed for at vælge mellem Bruger- og Admin-indgange.
public class MainActivity extends AppCompatActivity {
    private Button buttonUser, buttonAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialiser knapper
        buttonUser = findViewById(R.id.buttonUser);
        buttonAdmin = findViewById(R.id.buttonAdmin);

        // Gå til brugerregistrering, når "Bruger"-knappen trykkes
        buttonUser.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, UserActivity.class);
            startActivity(intent);
        });

        // Gå til admin-login, når "Admin"-knappen trykkes
        buttonAdmin.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AdminLoginActivity.class);
            startActivity(intent);
        });
    }
}
