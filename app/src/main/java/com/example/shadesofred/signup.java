package com.example.shadesofred;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class signup extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener{
    public TextView tv_login;
    public Button btn_reg;
    EditText et_name, et_email , et_number , et_pass , et_cpass;
    Spinner sp_blood , sp_state , sp_city;
    RadioButton rb_patient , rb_donor;
    ProgressBar progressBar;
    String cities[] = null;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    String rbState = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_signup);

        tv_login = (TextView)findViewById(R.id.tv_log);
        btn_reg = (Button) findViewById(R.id.reg);
        et_name = findViewById(R.id.et_name);
        et_email = findViewById(R.id.et_email);
        et_number = findViewById(R.id.et_num);
        et_pass = findViewById(R.id.et_pwd);
        et_cpass = findViewById(R.id.et_cfmpwd);
        sp_blood = findViewById(R.id.sp_blood);
        sp_state = findViewById(R.id.sp_state);
        sp_city = findViewById(R.id.sp_city);
        progressBar = findViewById(R.id.pb);
        rb_patient = findViewById(R.id.rb_p);
        rb_donor = findViewById(R.id.rb_d);



        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        firebaseAuth = FirebaseAuth.getInstance();

        sp_state.setOnItemSelectedListener(this);
        btn_reg.setOnClickListener(this);

        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(signup.this, login.class);
                startActivity(intent);
                finish();
            }
        });


    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        if(i==0){
            cities = new String[]{"Enter your city"};
        }
        if(i == 1){
            cities = new String[]{"Enter your city","Delhi" , "New Delhi"};
        }

        if(i == 2){
            cities = new String[]{"Enter your city","Ahmedabad" , "Surat" , "Vadodara" , "Rajkot" , "Bhavnagar"};
        }
        if(i == 3){
            cities = new String[]{"Enter your city","Faridabad" , "Gurgaon" , "Rohtak" , "Panipat" , "Karnal"};
        }

        if(i == 4){
            cities = new String[]{"Enter your city","Indore" , "Bhopal" , "Gwalior" , "Ratlam" , "Rewa"};
        }
        if(i == 5){
            cities = new String[]{"Enter your city","Jaipur" , "Jodhpur" , "Kota" , "Ajmer" , "Udaipur"};
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,cities);
        sp_city.setAdapter(arrayAdapter);

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onClick(View view) {

        final String name = et_name.getText().toString();
        final String email = et_email.getText().toString();
        String password = et_pass.getText().toString();
        String cpassword = et_cpass.getText().toString();
        final String phoneNumber = et_number.getText().toString();
        final String bloodgrp = sp_blood.getSelectedItem().toString();
        final String state = sp_state.getSelectedItem().toString();
        final String city = sp_city.getSelectedItem().toString();

        if(name.isEmpty()){
            et_name.setError("Name is required!");
            et_name.requestFocus();
            return;
        }
        if(email.isEmpty()){
            et_email.setError("Email is required!");
            et_email.requestFocus();
            return;
        }

        if(phoneNumber.isEmpty()){
            et_number.setError("Number is required!");
            et_number.requestFocus();
            return;
        }

        if(password.isEmpty())
        {
            et_pass.setError("Password is required");
            et_pass.requestFocus();
            return;
        }
        //Adding password check
        if(password.length()<6){
            et_pass.setError("Minimum length of password should be 6");
            et_pass.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            et_email.setError("Enter a valid email");
            et_email.requestFocus();
            return;
        }
        if(!(cpassword.equals(password))){
            et_cpass.setError("Wrong password");
            et_cpass.requestFocus();
            return;
        }

        if(rb_patient.isChecked()){
            rbState = "patient";
        }

        if(rb_donor.isChecked()){
            rbState = "donor";
        }

        progressBar.setVisibility(View.VISIBLE);
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            users info = new users(name , email , phoneNumber,bloodgrp,state,city,rbState);
                            FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(info).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(signup.this, "Registration Complete", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(),searchDonor.class));
                                    finish();
                                }
                            });

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(signup.this, "Error!", Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });

    }
}