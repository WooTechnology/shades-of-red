package com.example.shadesofred;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class searchDonor extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner sp_state , sp_city , sp_blood;
    public Button search;
    String cities[] = null;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Search Donor");
        setContentView(R.layout.activity_search_donor);

        search = (Button) findViewById(R.id.searchbutton);
        sp_state = findViewById(R.id.state);
        sp_city = findViewById(R.id.city);
        sp_blood = findViewById(R.id.blood);
        firebaseAuth = FirebaseAuth.getInstance();
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(searchDonor.this, donorList.class);
                intent.putExtra("blood", sp_blood.getSelectedItem().toString());
                intent.putExtra("state", sp_state.toString());
                intent.putExtra("bl", sp_blood.toString());
                System.out.println(sp_city.toString());
                startActivity(intent);
                finish();
            }
        });

        sp_state.setOnItemSelectedListener(this);
        sp_blood.setOnItemSelectedListener(this);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater(); inflater.inflate(R.menu.dmenu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.profile:{
                Intent i = new Intent(searchDonor.this,user_info.class);
                startActivity(i);
                break;
            }

            case R.id.faq:{
                startActivity(new Intent(searchDonor.this , faq.class));
                break;
            }

            case R.id.logout:{
                firebaseAuth.signOut();
                Toast.makeText(searchDonor.this, "Logged out successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(searchDonor.this,login.class));
                finish();
            }


        }
        return super.onOptionsItemSelected(item);
    }

    public void search(View view) {
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
}