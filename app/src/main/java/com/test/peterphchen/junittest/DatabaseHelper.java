package com.test.peterphchen.junittest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context) {
        super(context, EquationContract.TABLE_NAME, null, EquationContract.SCHEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(EquationContract.EQUATIONS_TABLE_CTRATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int i, int i1) {
        database.execSQL(EquationContract.EQUATIONS_TALBE_DELETE);
        onCreate(database);
    }
}
