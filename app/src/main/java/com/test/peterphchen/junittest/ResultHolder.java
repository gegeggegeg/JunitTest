package com.test.peterphchen.junittest;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ResultHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{

    private TextView resultView;
    private TextView indexView;
    private TextView dateView;
    private String item;

    public TextView getDateView() {
        return dateView;
    }

    public void setDateView(String text) {
        this.dateView.setText(text);
    }

    public ResultHolder(View itemView) {
        super(itemView);
        resultView = itemView.findViewById(R.id.resultView);
        indexView = itemView.findViewById(R.id.index);
        dateView = itemView.findViewById(R.id.dateView);

        itemView.setOnClickListener(this);
    }

    public TextView getIndexView() {
        return indexView;
    }

    public void setIndexView(String index) {
        indexView.setText(index);
    }

    public TextView getResultView() {
        return resultView;
    }

    public void setResultView() {
        resultView.setText(item);
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    @Override
    public void onClick(View view) {
        //
    }
}
