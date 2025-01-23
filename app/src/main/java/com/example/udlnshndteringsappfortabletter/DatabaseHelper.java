package com.example.udlnshndteringsappfortabletter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

// Hjælpeklasse til at håndtere alle databaseoperationer
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "loanManager"; // Databasenavn
    private static final int DATABASE_VERSION = 1; // Versionsnummer

    private static final String TABLE_LOANS = "loans"; // Tabellenavn
    private static final String COLUMN_ID = "id";  // ID for hver post
    private static final String COLUMN_TABLET_BRAND = "tabletBrand"; // Tablet-brand
    private static final String COLUMN_CABLE_TYPE = "cableType"; // Kabeltype
    private static final String COLUMN_BORROWER_NAME = "borrowerName"; // Låners navn
    private static final String COLUMN_CONTACT_INFO = "contactInfo"; // Kontaktinfo
    private static final String COLUMN_LOAN_DATE = "loanDate"; // Udlånsdato

    // Constructor
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Opret tabellen i databasen
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_LOANS_TABLE = "CREATE TABLE " + TABLE_LOANS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_TABLET_BRAND + " TEXT," +
                COLUMN_CABLE_TYPE + " TEXT," +
                COLUMN_BORROWER_NAME + " TEXT," +
                COLUMN_CONTACT_INFO + " TEXT," +
                COLUMN_LOAN_DATE + " TEXT" + ")";
        db.execSQL(CREATE_LOANS_TABLE);
    }

    // Hvis databasen opgraderes, slet eksisterende tabel og opret en ny
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOANS);
        onCreate(db);
    }

    // Metoder til CRUD (Create, Read, Update, Delete)
    public void addLoan(Loan loan) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TABLET_BRAND, loan.getTabletBrand());
        values.put(COLUMN_CABLE_TYPE, loan.getCableType());
        values.put(COLUMN_BORROWER_NAME, loan.getBorrowerName());
        values.put(COLUMN_CONTACT_INFO, loan.getContactInfo());
        values.put(COLUMN_LOAN_DATE, loan.getLoanDate());

        db.insert(TABLE_LOANS, null, values);
        db.close();
    }

    // Metode til at hente alle udlån
    public List<Loan> getAllLoans() {
        List<Loan> loanList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_LOANS, null);

        if (cursor.moveToFirst()) {
            do {
                Loan loan = new Loan(
                        cursor.getString(1), // tabletBrand
                        cursor.getString(2), // cableType
                        cursor.getString(3), // borrowerName
                        cursor.getString(4), // contactInfo
                        cursor.getString(5)  // loanDate
                );
                loan.setId(cursor.getInt(0)); // id
                loanList.add(loan);  // Tilføj udlånet til listen
            } while (cursor.moveToNext());
        }
        cursor.close();
        return loanList; // Returnér listen over udlån
    }

    // Slet en specifik udlånspost fra databasen
    public void deleteLoan(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_LOANS, COLUMN_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
    }
}

