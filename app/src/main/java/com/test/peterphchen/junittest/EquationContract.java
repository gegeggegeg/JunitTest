package com.test.peterphchen.junittest;

public class EquationContract {
    public static final int SCHEMA = 2;
    public static final String TABLE_NAME = "result";
    public static final String NUMMBER = "number";
    public static final String EQUATION = "equation";
    public static final String DATE = "date";
    public static final String EQUATIONS_TABLE_CTRATE
            ="CREATE TABLE " + TABLE_NAME
            +" (_id INTEGER PRIMARY KEY AUTOINCREMENT, number INTEGER, " +
            "equation TEXT, date TEXT);";
    public static final String EQUATIONS_TALBE_DELETE
            = "DROP TABLE IF EXIST"+ TABLE_NAME;
}
