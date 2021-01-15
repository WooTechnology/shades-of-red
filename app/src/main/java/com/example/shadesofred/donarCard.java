package com.example.shadesofred;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class donarCard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rough_donorlist);
        getSupportActionBar().hide();
    }
    public void callDonor(View view){
        Uri number = Uri.parse("9967940321");
        Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
        startActivity(callIntent);
    }
    public void callDonor1(View view){
        Uri number = Uri.parse("9978940628");
        Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
        startActivity(callIntent);
    }

    public void  sendMessage(View view){
        Uri number = Uri.parse("9967940321");
        Intent sentIntent = new Intent(Intent.ACTION_SENDTO,number );
        startActivity(sentIntent);
    }
}