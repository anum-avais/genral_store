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

public class UserRegister extends AppCompatActivity {

    Button btn_signup_submit, gotoSigin;
    private EditText signup_email, signup_password;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(UserRegister.this, MainActivity.class));
            finish();
        }

        auth = FirebaseAuth.getInstance();
        signup_email = (EditText)  findViewById(R.id.signup_email);
        signup_password = (EditText) findViewById(R.id.signup_password);

    }

    public void registerFun(View view)
    {

        String password = signup_password.getText().toString().trim();
        String email = signup_email.getText().toString().trim();

        Toast.makeText(UserRegister.this, "Signup failed."+ email.toString(),
                Toast.LENGTH_LONG).show();

        if (!signup_email.getText().toString().isEmpty() && !signup_password.getText().toString().isEmpty()) {
            if (password.length() < 6) {
                Toast.makeText(UserRegister.this, "Password length should be at least 6", Toast.LENGTH_LONG).show();
            }else {
                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(UserRegister.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(UserRegister.this, "Error occur", Toast.LENGTH_SHORT).show();

                                if (!task.isSuccessful()) {
                                    Toast.makeText(UserRegister.this, "Signup failed."+ task.getException(),
                                            Toast.LENGTH_LONG).show();
                                } else {
                                    startActivity(new Intent(UserRegister.this, MainActivity.class));
                                    finish();
                                }
                            }
                        });
            }
        }

    }

    public void GotoSigin(View view)
    {
        startActivity(new Intent(UserRegister.this, UserLogin.class));
    }
}
