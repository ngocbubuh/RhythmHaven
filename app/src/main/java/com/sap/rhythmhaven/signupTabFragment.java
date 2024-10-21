package com.sap.rhythmhaven;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;

import com.sap.rhythmhaven.entity.UserEntitySignUp;
import com.sap.rhythmhaven.interfaceRetrofit.ApiService;
import com.sap.rhythmhaven.interfaceRetrofit.RetrofitInstane;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.IOException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link signupTabFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class signupTabFragment extends Fragment {

    private EditText signupEmail, signupPassword, signupConfirm;
    private Button signupButton;
    private ProgressBar progressBar;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public signupTabFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment signupTabFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static signupTabFragment newInstance(String param1, String param2) {
        signupTabFragment fragment = new signupTabFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signup_tab, container, false);

        signupEmail = view.findViewById(R.id.signup_email);
        signupPassword = view.findViewById(R.id.signup_password);
        signupConfirm = view.findViewById(R.id.signup_confirm);
        signupButton = view.findViewById(R.id.signup_button);
        progressBar = view.findViewById(R.id.progressBar);

        signupButton.setOnClickListener(v -> attemptSignup());

        return view;
    }

    private void attemptSignup() {
        String username = signupEmail.getText().toString().trim();
        String password = signupPassword.getText().toString().trim();
        String confirmPassword = signupConfirm.getText().toString().trim();

        if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(getContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(confirmPassword)) {
            Toast.makeText(getContext(), "Passwords do not match", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.length() < 6) {
            Toast.makeText(getContext(), "Password must be at least 6 characters long", Toast.LENGTH_SHORT).show();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        signupButton.setEnabled(false);

        UserEntitySignUp newUser = new UserEntitySignUp(username, password, confirmPassword);
        ApiService apiService = RetrofitInstane.getClient().create(ApiService.class);
        Call<UserEntitySignUp> call = apiService.signup(newUser);

        call.enqueue(new Callback<UserEntitySignUp>() {
            @Override
            public void onResponse(Call<UserEntitySignUp> call, Response<UserEntitySignUp> response) {
                progressBar.setVisibility(View.GONE);
                signupButton.setEnabled(true);

                if (response.isSuccessful()) {
                    Toast.makeText(getContext(), "Signup successful", Toast.LENGTH_SHORT).show();
                    clearInputFields();
                    new Handler().postDelayed(() -> {
                        if (signupListener != null) {
                            signupListener.onSignupSuccess();
                        }
                    }, 2000);
                } else {
                    String errorMessage = "Signup failed";
                    if (response.code() == 409) {
                        errorMessage = "Username already exists";
                    } else if (response.errorBody() != null) {
                        try {
                            errorMessage = response.errorBody().string();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    Toast.makeText(getContext(), errorMessage, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<UserEntitySignUp> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                signupButton.setEnabled(true);
                String errorMessage = "Network error: ";
                if (t instanceof IOException) {
                    errorMessage += "Please check your internet connection.";
                } else {
                    errorMessage += t.getMessage();
                }
                Toast.makeText(getContext(), errorMessage, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void clearInputFields() {
        signupEmail.setText("");
        signupPassword.setText("");
        signupConfirm.setText("");
    }

    public interface SignupListener {
        void onSignupSuccess();
    }

    private SignupListener signupListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof SignupListener) {
            signupListener = (SignupListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement SignupListener");
        }
    }
}
