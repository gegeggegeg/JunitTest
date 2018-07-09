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
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RecyclerView recyclerView = new RecyclerView(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new CalculatorAdapter(createArraylist()));
        setContentView(recyclerView);
    }

    private ArrayList<String> createArraylist() {
        DatabaseHelper dbhelper = new DatabaseHelper(getApplicationContext());
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
}
