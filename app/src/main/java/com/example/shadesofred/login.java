package com.example.shadesofred;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
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

public class login extends AppCompatActivity {
    EditText et_email , et_pass;
    public TextView tv_reg;
    public Button btn_log;
    ProgressBar progressBar;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);

        et_email = findViewById(R.id.et_email);
        et_pass = findViewById(R.id.et_password);
        tv_reg = (TextView)findViewById(R.id.tv_signup);
        btn_log = (Button) findViewById(R.id.button);
        progressBar = findViewById(R.id.progressBar);
        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), searchDonor.class));
            finish();
        }

        tv_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login.this, signup.class);
                startActivity(intent);
                finish();
            }
        });

        btn_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = et_email.getText().toString().trim();
                String password = et_pass.getText().toString().trim();

                if(email.isEmpty()){
                    et_email.setError("Email is required!");
                    et_email.requestFocus();
                    return;
                }

                if(password.isEmpty())
                {
                    et_pass.setError("Password is required");
                    et_pass.requestFocus();
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    et_email.setError("Enter a valid email");
                    et_email.requestFocus();
                    return;
                }
                if(password.length()<6){
                    et_pass.setError("Minimum length of password should be 6");
                    et_pass.requestFocus();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(login.this, searchDonor.class);
                            startActivity(intent);
                            finish();
                        }
                        else{
                            Toast.makeText(login.this, "Error! " + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });

    }
}