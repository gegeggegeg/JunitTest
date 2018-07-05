package com.test.peterphchen.junittest;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class ResultHolder extends RecyclerView.ViewHolder {

    private TextView resultView;
    private String item;

    public ResultHolder(View itemView) {
        super(itemView);
        resultView = itemView.findViewById(R.id.resultView);
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
}
