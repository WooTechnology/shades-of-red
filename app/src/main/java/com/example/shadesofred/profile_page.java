package com.example.shadesofred;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class profile_page extends AppCompatActivity {

    private String userId;

    private DatabaseReference UserRef;
    TextView Name;
    TextView Status;
    TextView BloodGroup;
    TextView Email;
    TextView Phone;
    TextView State;
    TextView City;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Search Donor");
        setContentView(R.layout.activity_profile);
        userId = getIntent().getExtras().get("visitUserId").toString();

        UserRef = FirebaseDatabase.getInstance().getReference().child("Users");

        Name = (TextView)findViewById(R.id.txt_name);
        Status = (TextView)findViewById(R.id.txt_status);
        BloodGroup = (TextView)findViewById(R.id.bloodGroups);
        Email = (TextView)findViewById(R.id.emailId);
        Phone = (TextView)findViewById(R.id.phone_no);
        State = (TextView)findViewById(R.id.enter_city);
        City = (TextView)findViewById(R.id.enter_state);
        
        RetrieveUserInfo();
    }

    private void RetrieveUserInfo() {
        UserRef.child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists() && snapshot.hasChild("blood")){
                    String blood = snapshot.child("blood").getValue().toString();
                    String name = snapshot.child("name").getValue().toString();
                    String city =  snapshot.child("city").getValue().toString();
                    String state = snapshot.child("state").getValue().toString();
                    String status =  snapshot.child("rbstate").getValue().toString();
                    String email = snapshot.child("email").getValue().toString();

                    Name.setText(name);
                    BloodGroup.setText(blood);
                    City.setText(city);
                    State.setText(state);
                    Status.setText(status);
                    Email.setText(email);
                }
                else {
                    String blood = snapshot.child("Blood").getValue().toString();
                    String name = snapshot.child("name").getValue().toString();
                    String city =  snapshot.child("city").getValue().toString();
                    String state = snapshot.child("state").getValue().toString();
                    String status =  snapshot.child("rbstate").getValue().toString();
                    String email = snapshot.child("email").getValue().toString();

                    Name.setText(name);
                    BloodGroup.setText(blood);
                    City.setText(city);
                    State.setText(state);
                    Status.setText(status);
                    Email.setText(email);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
