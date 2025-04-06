package com.ysf.mslh.guideme.hiddenFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ysf.mslh.guideme.R;
import com.ysf.mslh.guideme.adapters.DateAdapter;
import com.ysf.mslh.guideme.models.DateModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Booking extends Fragment {

    private RecyclerView recyclerViewDates;
    private List<DateModel> dateList = new ArrayList<>();
    private DateAdapter adapter;

    private Calendar calendar;
    private ImageView btnPreviousWeek, btnNextWeek;
    private TextView currentMonthText;

    public Booking() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_booking, container, false);

        // Initialize UI components
        recyclerViewDates = view.findViewById(R.id.recyclerViewDates);
        btnPreviousWeek = view.findViewById(R.id.btnPreviousWeek);
        btnNextWeek = view.findViewById(R.id.btnNextWeek);
        currentMonthText = view.findViewById(R.id.currentMonthText);

        // Initialize the adapter before calling updateDateList
        adapter = new DateAdapter(requireContext(), dateList, new DateAdapter.OnDateSelectedListener() {
            @Override
            public void onDateSelected(Date date) {
                Toast.makeText(requireContext(), "Selected date: " + date.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        recyclerViewDates.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerViewDates.setAdapter(adapter);

        // Initialize calendar
        calendar = Calendar.getInstance();
        updateDateList();

        // Previous week button
        btnPreviousWeek.setOnClickListener(v -> {
            calendar.add(Calendar.WEEK_OF_YEAR, -1);  // Go back 1 week
            updateDateList();
        });

        // Next week button
        btnNextWeek.setOnClickListener(v -> {
            calendar.add(Calendar.WEEK_OF_YEAR, 1);  // Go forward 1 week
            updateDateList();
        });

        return view;
    }

    // Update the date list for the current week
    private void updateDateList() {
        dateList.clear();
        generateDates();

        // Notify adapter to refresh
        adapter.notifyDataSetChanged();

        // Update the current month text
        SimpleDateFormat monthFormat = new SimpleDateFormat("MMMM yyyy", Locale.getDefault());
        currentMonthText.setText(monthFormat.format(calendar.getTime()));
    }

    // Generate 7 days from the current calendar
    private void generateDates() {
        Calendar tempCalendar = (Calendar) calendar.clone();  // Copy the current calendar to not modify the original
        for (int i = 0; i < 7; i++) {
            dateList.add(new DateModel(tempCalendar.getTime(), i == 0)); // Mark the first day as selected
            tempCalendar.add(Calendar.DATE, 1);
        }
    }
}
