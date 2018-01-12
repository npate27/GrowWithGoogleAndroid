package com.example.android.sunshine;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by npatel on 1/12/18.
 */

public class ForcastAdapter extends RecyclerView.Adapter<ForcastAdapter.ForcastAdapterViewHolder> {

    private String[] mWeatherData;

    public ForcastAdapter(){}

    @Override
    public ForcastAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.forecast_list_item, parent, false);
        return new ForcastAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ForcastAdapterViewHolder holder, int position) {
        holder.mWeatherTextView.setText(String.valueOf(position));
    }

    @Override
    public int getItemCount() {
        if(mWeatherData == null){
            return 0;
        }
        return mWeatherData.length;
    }

    public void setmWeatherData(String[] mWeatherData) {
        this.mWeatherData = mWeatherData;
        notifyDataSetChanged();
    }

    class ForcastAdapterViewHolder extends  RecyclerView.ViewHolder {
        public final TextView mWeatherTextView;

        public ForcastAdapterViewHolder(View itemView) {
            super(itemView);
            mWeatherTextView = (TextView) itemView.findViewById(R.id.tv_weather_data);
        }
    }
}