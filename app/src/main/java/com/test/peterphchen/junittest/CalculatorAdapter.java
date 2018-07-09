package com.test.peterphchen.junittest;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;

public class CalculatorAdapter extends RecyclerView.Adapter<ResultHolder> {

    private static final String TAG = "CalculatorAdapter";
    private ArrayList<String> equations;

    public CalculatorAdapter(ArrayList<String> equations) {
        super();
        this.equations = equations;
    }

    @NonNull
    @Override
    public ResultHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return new ResultHolder(inflater.inflate(R.layout.result_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ResultHolder holder, int position) {
        holder.setItem(equations.get(position));
        holder.setResultView();
        holder.setIndexView(String.valueOf(position));
    }

    @Override
    public int getItemCount() {
        return equations.size();
    }
}
