package com.test.peterphchen.junittest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "result.db";
    private static final int SCHEMA = EquationContract.SCHEMA;
    static final String NUMBER = EquationContract.NUMMBER;
    static final String EQUATION = EquationContract.EQUATION;
    static final String TABLE = EquationContract.TABLE_NAME;

    public DatabaseHelper(Context context) {
        super(context, EquationContract.TABLE_NAME, null, EquationContract.SCHEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        //database.execSQL("CREATE TABLE result (number INT, equation TEXT);");
        database.execSQL(EquationContract.EQUATIONS_TABLE_CTRATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int i, int i1) {
        //database.execSQL("DROP TABLE IF EXISTS " + TABLE);
        database.execSQL(EquationContract.EQUATIONS_TALBE_DELETE);
        onCreate(database);
    }
}
