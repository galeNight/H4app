package com.example.udlnshndteringsappfortabletter;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

// Aktivitet, der giver admin et overblik over alle udlån.
// Admin kan filtrere, søge og markere udlån som afleveret.
public class AdminActivity extends AppCompatActivity {
    private ListView listViewLoans; // Liste til at vise udlån
    private SearchView searchViewFilter; // Søgefelt til filtrering
    private DatabaseHelper databaseHelper; // Database-hjælper til udlån
    private ArrayAdapter<String> adapter; // Adapter til at forbinde data til listen
    private List<Loan> loanList; // Liste over udlån fra databasen

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        // Initialiser views
        listViewLoans = findViewById(R.id.listViewLoans);
        searchViewFilter = findViewById(R.id.searchViewFilter);
        Button buttonLogout = findViewById(R.id.buttonLogout); // Log ud-knap

        databaseHelper = new DatabaseHelper(this); // Initialiser databasehjælper

        // Hent udlånsdata og vis i listen og konverter Loan-objekt til en string til visning
        loanList = databaseHelper.getAllLoans();
        List<String> loanStrings = new ArrayList<>();
        for (Loan loan : loanList) {
            loanStrings.add(loan.getTabletBrand() + " - " + loan.getBorrowerName() + "\n" +
                    "Kabel: " + loan.getCableType() + "\n" +
                    "Dato: " + loan.getLoanDate());
        }
        // Sæt adapter til listen
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, loanStrings);
        listViewLoans.setAdapter(adapter);

        // Søg og filtrer data
        searchViewFilter.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);  // Filtrér listen
                return false;
            }
        });

        // Klik på et udlån for at markere det som afleveret
        listViewLoans.setOnItemClickListener((parent, view, position, id) -> {
            Loan selectedLoan = loanList.get(position);
            databaseHelper.deleteLoan(selectedLoan.getId());
            Toast.makeText(this, "Udlån markeret som afleveret", Toast.LENGTH_SHORT).show();
            recreate(); // Genindlæs skærmen
        });

        // Log ud og gå tilbage til startskærmen
        buttonLogout.setOnClickListener(v -> {
            Intent intent = new Intent(AdminActivity.this, MainActivity.class);
            startActivity(intent);
            finish(); // Luk admin-skærmen
        });
    }
}

