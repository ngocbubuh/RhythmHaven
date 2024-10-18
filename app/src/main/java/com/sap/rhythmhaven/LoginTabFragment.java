package com.sap.rhythmhaven;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sap.rhythmhaven.entity.UserEntity;
import com.sap.rhythmhaven.interfaceRetrofit.ApiService;
import com.sap.rhythmhaven.interfaceRetrofit.RetrofitInstane;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginTabFragment extends Fragment {

    private  EditText login_email, login_password;
    private  Button login_button;
    private LoginListener loginListener;

    public interface LoginListener {
        void onLoginAttempt(String email, String password);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof LoginListener) {
            loginListener = (LoginListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement LoginListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login_tab, container, false);

        login_email = view.findViewById(R.id.login_email);
        login_password = view.findViewById(R.id.login_password);

        login_button = view.findViewById(R.id.login_button);
        login_button.setOnClickListener(v -> attemptLogin());

        return view;
    }

    private void attemptLogin() {
        String email = login_email.getText().toString();
        String password = login_password.getText().toString();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(getActivity(), "Please enter both email and password", Toast.LENGTH_SHORT).show();
            return;
        }

        loginListener.onLoginAttempt(email, password);
    }
}
