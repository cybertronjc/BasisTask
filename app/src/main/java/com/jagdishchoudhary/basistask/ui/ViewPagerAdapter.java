package com.jagdishchoudhary.basistask.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.jagdishchoudhary.basistask.R;
import com.jagdishchoudhary.basistask.model.Data;

import java.util.List;

public class ViewPagerAdapter extends RecyclerView.Adapter<ViewPagerAdapter.ViewHolder> {

    private List<Data> mData;
    private LayoutInflater mInflater;
    private ViewPager2 viewPager2;

    public ViewPagerAdapter(List<Data> mData, LayoutInflater mInflater, ViewPager2 viewPager2) {
        this.mData = mData;
        this.mInflater = mInflater;
        this.viewPager2 = viewPager2;
    }

    @NonNull
    @Override
    public ViewPagerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.cardview_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewPagerAdapter.ViewHolder holder, int position) {
            String dataText = mData.get(position).getText();
            holder.dataText.setText(dataText);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView dataText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            dataText = (TextView)itemView.findViewById(R.id.textData);
        }
    }
}
