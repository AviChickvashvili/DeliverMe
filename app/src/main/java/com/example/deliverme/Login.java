package com.example.deliverme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    EditText Email, password;
    Button mLogin;
    TextView Create;
    ProgressBar progressBar;
    FirebaseAuth mhuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        password = findViewById(R.id.editTextTextPassword4);
        Email = findViewById(R.id.editTextTextEmailAddress4);
        mLogin = findViewById(R.id.BLogin);
        Create = (TextView)findViewById(R.id.create);
        progressBar = findViewById(R.id.progressBar2);
        mhuth = FirebaseAuth.getInstance();
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = Email.getText().toString().trim();
                String Password = password.getText().toString().trim();
                if (TextUtils.isEmpty(email)) {
                    Email.setError("Email is Required");
                    return;
                }
                if (TextUtils.isEmpty(Password)) {
                    password.setError("password is Required");
                    return;
                }
                if (password.length() < 6) {
                    password.setError("Your password must be greater than 6");
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                //check the user
                mhuth.signInWithEmailAndPassword(email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Login.this, "התחברות בוצעה בהצלחה", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        } else {
                            Toast.makeText(Login.this, "שגיאה!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);

                        }
                    }
                });

            }
        });
        Create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //LOGIN -> create account -> Button
                create_User();
            }
        });

    }
    public void create_User()
    {
        Intent intent=new Intent(this,Register.class);
        startActivity(intent);
    }
}



