package com.example.genralstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.auth.User;

public class UserLogin extends AppCompatActivity {
    Button submitLogin, gotoSignup;
    private EditText login_email, login_password;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        submitLogin = findViewById(R.id.btn_signin_submit);
        gotoSignup = findViewById(R.id.btn_goto_register);
        /// inssatize firebase auth


        auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(UserLogin.this, MainActivity.class));
            finish();
        }

        login_email = (EditText)  findViewById(R.id.login_email);
        login_password = (EditText) findViewById(R.id.login_password);

    }

    public void loginFun(View view)
    {

        String password = login_password.getText().toString().trim();
        String email = login_email.getText().toString().trim();

        if (!login_email.getText().toString().isEmpty() && !login_password.getText().toString().isEmpty()) {
            if (password.length() < 6) {
                Toast.makeText(UserLogin.this, "Password length should be at least 6", Toast.LENGTH_LONG).show();
            }else {
                //authenticate user
                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(UserLogin.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {
                                    Intent intent = new Intent(UserLogin.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();

                                } else {
                                    // there was an error
                                    Toast.makeText(UserLogin.this, "Failed to login", Toast.LENGTH_LONG).show();

                                }
                            }
                        });
            }
        }
    }
    public void GotoSigup(View view)
    {
        startActivity(new Intent(UserLogin.this, UserRegister.class));
    }
}
