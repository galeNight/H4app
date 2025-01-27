package com.example.udlnshndteringsappfortabletter;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class UserActivity extends AppCompatActivity {
    private Spinner spinnerTabletBrand;
    private RadioGroup radioGroupCableType;
    private EditText editTextBorrowerName, editTextContactInfo;
    private Button buttonSubmit;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        spinnerTabletBrand = findViewById(R.id.spinnerTabletBrand);
        radioGroupCableType = findViewById(R.id.radioGroupCableType);
        editTextBorrowerName = findViewById(R.id.editTextBorrowerName);
        editTextContactInfo = findViewById(R.id.editTextContactInfo);
        buttonSubmit = findViewById(R.id.buttonSubmit);

        // Opret en liste over tabletmærker
        String[] tabletBrands = {"Vælg tabletmærke", "Apple iPad", "Samsung Galaxy Tab", "Lenovo Tab", "Microsoft Surface", "Andet"};

        // Opret en adapter til Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                tabletBrands
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTabletBrand.setAdapter(adapter);

        databaseHelper = new DatabaseHelper(this);

        buttonSubmit.setOnClickListener(v -> registerLoan());
    }

    private void registerLoan() {
        // Tjek for null før du kalder toString()
        Object selectedTabletBrand = spinnerTabletBrand.getSelectedItem();
        if (selectedTabletBrand == null) {
            Toast.makeText(this, "Vælg venligst en tabletmærke fra listen!", Toast.LENGTH_SHORT).show();
            return;
        }
        String tabletBrand = selectedTabletBrand.toString();

        int selectedCableId = radioGroupCableType.getCheckedRadioButtonId();
        String cableType = selectedCableId == R.id.radioUsbC ? "USB-C" :
                selectedCableId == R.id.radioMicroUsb ? "Micro-USB" : "Ingen kabel";

        String borrowerName = editTextBorrowerName.getText().toString();
        String contactInfo = editTextContactInfo.getText().toString();
        String loanDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());

        // Tjek for tomme felter
        if (tabletBrand.isEmpty() || borrowerName.isEmpty()) {
            Toast.makeText(this, "Udfyld alle påkrævede felter!", Toast.LENGTH_SHORT).show();
            return;
        }

        Loan loan = new Loan(tabletBrand, cableType, borrowerName, contactInfo, loanDate);
        databaseHelper.addLoan(loan);

        // Navigér til bekræftelsesskærmen
        Intent intent = new Intent(UserActivity.this, ConfirmationActivity.class);
        intent.putExtra("CONFIRMATION_MESSAGE", "Udlån registreret med succes!");
        startActivity(intent);
        finish(); // Luk UserActivity
    }
}

