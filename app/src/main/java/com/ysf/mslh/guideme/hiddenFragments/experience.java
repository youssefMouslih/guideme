package com.ysf.mslh.guideme.hiddenFragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ysf.mslh.guideme.R;

public class experience extends Fragment {
    private OnFragmentInteractionListener mListener;
    private ImageView    backButton;
    private ExperienceBottomSheet bottomSheet;

    // Interface for communication with MainActivity
    public interface OnFragmentInteractionListener {
        void onBackToHomeTab(); // Callback method to switch to Home tab
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_experience, container, false);
        backButton = view.findViewById(R.id.imageBack);

        backButton.setOnClickListener(v -> {
            // Dismiss BottomSheet
            bottomSheet.dismiss();
            // Notify MainActivity to select the first tab
            if (mListener != null) {
                mListener.onBackToHomeTab(); // Notify activity to switch to Home tab
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bottomSheet = ExperienceBottomSheet.newInstance();
        bottomSheet.show(requireActivity().getSupportFragmentManager(), bottomSheet.getTag());
        bottomSheet.setCancelable(false);
    }

    // This method allows the activity to pass the listener
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}
