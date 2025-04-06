package com.ysf.mslh.guideme.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.ysf.mslh.guideme.R;
import com.ysf.mslh.guideme.models.DateModel;

import java.util.List;

public class DateAdapter extends RecyclerView.Adapter<DateAdapter.DateViewHolder> {

    private Context context;
    private List<DateModel> dateList;
    private OnDateSelectedListener onDateSelectedListener;

    public DateAdapter(Context context, List<DateModel> dateList, OnDateSelectedListener onDateSelectedListener) {
        this.context = context;
        this.dateList = dateList;
        this.onDateSelectedListener = onDateSelectedListener;
    }

    @Override
    public DateViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_date, parent, false);
        return new DateViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DateViewHolder holder, int position) {
        DateModel dateModel = dateList.get(position);

        // Set the text for day and date
        holder.dayTextView.setText(dateModel.getDay()); // Use getDay() to display the day of the week
        holder.dateTextView.setText(String.valueOf(dateModel.getDate().getDate())); // Set the date as text

        // Change the background color if the item is selected
        if (dateModel.isSelected()) {
            holder.layout.setBackgroundColor(Color.parseColor("#1B2F5D")); // Set selected color
            holder.dateTextView.setTextColor(Color.WHITE); // Make text white
            holder.dayTextView.setTextColor(Color.WHITE); // Make text white

        } else {
            holder.layout.setBackgroundColor(Color.TRANSPARENT); // No background for unselected
            holder.dateTextView.setTextColor(Color.BLACK); // Normal text color
            holder.dayTextView.setTextColor(Color.BLACK); // Normal text color
        }

        // Set a click listener to notify when a date is selected
        holder.itemView.setOnClickListener(v -> {
            onDateSelectedListener.onDateSelected(dateModel.getDate());
            // Update selection state
            for (int i = 0; i < dateList.size(); i++) {
                dateList.get(i).setSelected(i == position);
            }
            notifyDataSetChanged(); // Notify adapter to refresh views
        });
    }

    @Override
    public int getItemCount() {
        return dateList.size();
    }

    public interface OnDateSelectedListener {
        void onDateSelected(java.util.Date date);
    }

    public static class DateViewHolder extends RecyclerView.ViewHolder {

        LinearLayout layout;
        TextView dayTextView;
        TextView dateTextView;

        public DateViewHolder(View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.layout_date); // Reference to the LinearLayout
            dayTextView = itemView.findViewById(R.id.text_day); // Reference to the day TextView
            dateTextView = itemView.findViewById(R.id.dateTextView); // Reference to the date TextView
        }
    }
}
