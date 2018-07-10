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
        recyclerView.setAdapter(new CalculatorAdapter(createArraylist(),getApplicationContext()));
    }

    private ArrayList<String> createArraylist() {
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        String[] projection = {
                "ROWID AS _id",
                DatabaseHelper.NUMBER,
                DatabaseHelper.EQUATION
        };
        Cursor result = db.query(DatabaseHelper.TABLE,projection,
                null,null,null,null,DatabaseHelper.NUMBER);
        ArrayList<String> equations = new ArrayList<>();
        while (result.moveToNext()){
            equations.add(result.getString(2));
        }
        result.close();
        db.close();
        return  equations;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
