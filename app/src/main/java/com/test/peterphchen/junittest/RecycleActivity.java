package com.test.peterphchen.junittest;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class RecycleActivity extends AppCompatActivity{
    private DatabaseHelper dbhelper;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbhelper = new DatabaseHelper(getApplicationContext());
        setContentView(R.layout.acivity_recycler);
        RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new CalculatorAdapter(getApplicationContext()));
    }

    private ArrayList<String> createArraylist() {
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        String[] projection = {
                "_id",
                EquationContract.NUMMBER,
                EquationContract.EQUATION,
                EquationContract.DATE
        };
        Cursor cursor = db.rawQuery("SELECT * FROM "+ EquationContract.TABLE_NAME,projection);
        ArrayList<String> equations = new ArrayList<>();
        while (cursor.moveToNext()){
            equations.add(cursor.getString(2));
        }
        cursor.close();
        db.close();
        return  equations;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
