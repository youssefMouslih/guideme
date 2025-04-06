package com.ysf.mslh.guideme.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.ysf.mslh.guideme.R;

/**
 * Login Fragment that handles user authentication
 * Provides login functionality with username/password
 * and social media integration options
 */
public class LoginFragment extends Fragment {

    // UI Components for login form
    private EditText editUsername;
    private EditText editPassword;
    private Button btnSignIn;
    private TextView txtForgotPassword;
    private ImageView imgGoogle;
    private ImageView imgFacebook;
    private TextView txtSignUp;

    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of this fragment.
     * @return A new instance of LoginFragment
     */
    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        // Initialize UI components
        initViews(view);
        // Set up click listeners for UI elements
        setupListeners();

        return view;
    }

    // Initialize views by finding them in the layout
    private void initViews(View view) {
        editUsername = view.findViewById(R.id.editUsername);
        editPassword = view.findViewById(R.id.editPassword);
        btnSignIn = view.findViewById(R.id.btnSignIn);
        txtForgotPassword = view.findViewById(R.id.txtForgotPassword);
        imgGoogle = view.findViewById(R.id.imgGoogle);
        imgFacebook = view.findViewById(R.id.imgFacebook);
        txtSignUp = view.findViewById(R.id.txtSignUp);
    }

    // Set up click listeners for various UI elements
    private void setupListeners() {
        //Sign in button click listener
        btnSignIn.setOnClickListener(v -> handleSignIn());

        // Forgot password textview click listener
        txtForgotPassword.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Forgot password clicked", Toast.LENGTH_SHORT).show();
            // Navigate to forgot password screen
        });

        // Google sign in imageview click listener
        imgGoogle.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Google sign-in clicked", Toast.LENGTH_SHORT).show();
            // Handle Google sign-in
        });

        // Facebook sign in imageview click listener
        imgFacebook.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Facebook sign-in clicked", Toast.LENGTH_SHORT).show();
            // Handle Facebook sign-in
        });

        // Sign up textview click listener
        txtSignUp.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Sign up clicked", Toast.LENGTH_SHORT).show();
            // Navigate to sign up screen
        });
    }

    // Handle sign in button click event
    private void handleSignIn() {
        String username = editUsername.getText().toString().trim();
        String password = editPassword.getText().toString().trim();

        // Validate username and password
        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(getContext(), "Please enter both username and password", Toast.LENGTH_SHORT).show();
            return;
        }

        // In a real app, you would validate credentials against a backend service
        // For demonstration, we'll just show a success message and navigate to the profile
        if (username.equals("reda_1") && password.length() > 0) {
            Toast.makeText(getContext(), "Sign in successful", Toast.LENGTH_SHORT).show();

//            // Navigate to profile fragment
//            // Example of navigation using FragmentManager
//            if (getActivity() != null) {
//                getActivity().getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.fragmentContainer, ProfileFragment.newInstance())
//                        .addToBackStack(null)
//                        .commit();
//            }
        } else {
            Toast.makeText(getContext(), "Invalid credentials", Toast.LENGTH_SHORT).show();
        }
    }
}