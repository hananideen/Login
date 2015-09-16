package com.login;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

/**
 * A placeholder fragment containing a simple view.
 */
public class LoginFragment extends Fragment {

    LoginButton fbLogin;
    CallbackManager callbackManager;

    public LoginFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FacebookSdk.sdkInitialize(getActivity());
        callbackManager = CallbackManager.Factory.create();

        View view = inflater.inflate(R.layout.fragment_main, container, false);
        fbLogin = (LoginButton) view.findViewById(R.id.login_button);

        fbLogin.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(getActivity(), "User ID: " + loginResult.getAccessToken().getUserId(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancel() {
                Toast.makeText(getActivity(), "Login attempt canceled.", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(FacebookException exception) {
                Toast.makeText(getActivity(), "Login attempt failed.", Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
