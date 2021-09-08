package com.example.dadjoke;

import android.content.Context;
import android.util.DisplayMetrics;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class WrappingRecyclerView extends RecyclerView {
    public WrappingRecyclerView(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        heightSpec = MeasureSpec.makeMeasureSpec(convertDpToPx(240), MeasureSpec.AT_MOST);
        super.onMeasure(widthSpec, heightSpec);
    }

    private int convertDpToPx(int dp){
        return Math.round(dp*(getResources().getDisplayMetrics().xdpi/ DisplayMetrics.DENSITY_DEFAULT));

    }
}
