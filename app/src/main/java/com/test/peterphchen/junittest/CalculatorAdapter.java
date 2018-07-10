package com.test.peterphchen.junittest;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class CalculatorAdapter extends RecyclerView.Adapter<ResultHolder> {

    private static final String TAG = "CalculatorAdapter";
    private ArrayList<String> equations;
    private SQLiteDatabase database;
    private Context context;

    public CalculatorAdapter(ArrayList<String> equations,Context context) {
        super();
        this.equations = equations;
        this.context = context;
    }

    @NonNull
    @Override
    public ResultHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return new ResultHolder(inflater.inflate(R.layout.result_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ResultHolder holder, final int position) {
        holder.setItem(equations.get(position));
        holder.setResultView();
        holder.setIndexView(String.valueOf(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialogbuilder = new AlertDialog.Builder(view.getContext());
                dialogbuilder.setMessage("Do you want to delete this item?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        database = new DatabaseHelper(context).getWritableDatabase();
                        database.delete(DatabaseHelper.TABLE,DatabaseHelper.EQUATION+"=?"
                                ,new String[]{equations.get(position)});
                        database.close();
                        equations.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, position);
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Do nothing
                    }
                }).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return equations.size();
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull ResultHolder holder) {
        super.onViewDetachedFromWindow(holder);
    }
}
