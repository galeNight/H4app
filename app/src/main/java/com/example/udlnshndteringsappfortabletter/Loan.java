package com.example.udlnshndteringsappfortabletter;

public class Loan {
    private int id;
    private String tabletBrand;
    private String cableType;
    private String borrowerName;
    private String contactInfo;
    private String loanDate;

    // Constructor
    public Loan(String tabletBrand, String cableType, String borrowerName, String contactInfo, String loanDate) {
        this.tabletBrand = tabletBrand;
        this.cableType = cableType;
        this.borrowerName = borrowerName;
        this.contactInfo = contactInfo;
        this.loanDate = loanDate;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTabletBrand() { return tabletBrand; }
    public String getCableType() { return cableType; }
    public String getBorrowerName() { return borrowerName; }
    public String getContactInfo() { return contactInfo; }
    public String getLoanDate() { return loanDate; }
}
