package com.example.shadesofred;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class user_info extends AppCompatActivity {
    FloatingActionButton floatingActionButton;
    TextView tv_name , tv_status , tv_blood , tv_number , tv_email , tv_state , tv_city;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_user_info);

        floatingActionButton = findViewById(R.id.btn_edit);
        tv_name = findViewById(R.id.txt_name);
        tv_status = findViewById(R.id.txt_status);
        tv_blood = findViewById(R.id.blood);
        tv_email = findViewById(R.id.email);
        tv_number = findViewById(R.id.phone);
        tv_city = findViewById(R.id.enter_city);
        tv_state = findViewById(R.id.enter_state);
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = firebaseDatabase.getReference("Users");

        FirebaseUser user = firebaseAuth.getCurrentUser();
        final String email = user.getEmail();


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds: snapshot.getChildren()){
                    if(ds.child("email").getValue().equals(email)){
                        tv_name.setText(ds.child("name").getValue(String.class));
                        tv_status.setText(ds.child("rbstate").getValue(String.class));
                        tv_blood.setText(ds.child("Blood").getValue(String.class));
                        tv_number.setText(ds.child("number").getValue(String.class));
                        tv_city.setText(ds.child("city").getValue(String.class));
                        tv_state.setText(ds.child("state").getValue(String.class));
                        tv_email.setText(email);
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(user_info.this , userInfoEdit.class));
            }
        });

}

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}