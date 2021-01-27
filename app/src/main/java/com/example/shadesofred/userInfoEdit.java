package com.example.shadesofred;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class userInfoEdit extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    RadioButton rb_patient , rb_donor;
    Spinner sp_city , sp_state;
    String cities[] = null;
    String rState = null;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info_edit);
        setTitle("Edit Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        rb_patient = findViewById(R.id.rb_p);
        rb_donor = findViewById(R.id.rb_d);
        sp_city = findViewById(R.id.sp_city);
        sp_state = findViewById(R.id.sp_state);
        progressBar = findViewById(R.id.progressBar);
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");

        sp_state.setOnItemSelectedListener(this);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
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

    public void saveInfo(View view) {
        if(!rb_donor.isChecked() && !rb_patient.isChecked()){
            Toast.makeText(this, "Select whether you are a patient or donor.", Toast.LENGTH_SHORT).show();
            return;
        }
        if(rb_patient.isChecked()){
            rState = "Patient";
        }

        if(rb_donor.isChecked()){
            rState = "Donor";
        }
        int position1 = sp_state.getSelectedItemPosition();
        if(position1 == 0){
            Toast.makeText(this, "Select your state", Toast.LENGTH_SHORT).show();
            return;
        }

        int position2 = sp_city.getSelectedItemPosition();
        if(position1 > 0 && position2 == 0){
            Toast.makeText(this, "Select your city", Toast.LENGTH_SHORT).show();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        final String state = sp_state.getSelectedItem().toString();
        final String city = sp_city.getSelectedItem().toString();

        databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("rbstate").setValue(rState);
        databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("state").setValue(state);
        databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("city").setValue(city);
        Toast.makeText(this, "Profile Updated", Toast.LENGTH_SHORT).show();

        startActivity(new Intent(userInfoEdit.this , user_info.class));
        finish();
    }
}