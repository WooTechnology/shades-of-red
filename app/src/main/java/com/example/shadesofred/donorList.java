package com.example.shadesofred;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;


public class donorList extends AppCompatActivity {
    RecyclerView recview;
    FirebaseRecyclerAdapter<users,myViewHolder > adapter1;
    //Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.donor_list);

        String blood = getIntent().getExtras().get("blood").toString();
        String state = getIntent().getExtras().get("state").toString();
        String city =  getIntent().getExtras().get("city").toString();
        String filter = city + state + blood + "Donor";
        recview = (RecyclerView)findViewById(R.id.recview);
        recview.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<users> options =
                new FirebaseRecyclerOptions.Builder<users>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Users").orderByChild("filter").equalTo(filter) , users.class)
                        .build();

             adapter1 = new FirebaseRecyclerAdapter<users, myViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull myViewHolder holder, final int position, @NonNull final users user) {
                holder.name.setText(user.getName());
                holder.Number.setText(user.getNumber());
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String visitUserId = getRef(position).getKey();
                        Intent profile = new Intent(donorList.this, profile_page.class);
                        profile.putExtra("visitUserId" ,visitUserId);
                        startActivity(profile);
                    }
                });
                holder.call.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        intent.setData(Uri.parse("tel:" + user.getNumber()));
                        if(ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED) {
                            Toast.makeText(getApplicationContext(), "Please Grant Permission To Proceed", Toast.LENGTH_SHORT).show();
                            requestPermission();
                            startActivity(intent);
                        }else
                            startActivity(intent);
                    }
                });
                holder.message.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_DEFAULT);
                        intent.setType("vnd.android-dir/mms-sms");
                        startActivity(intent);
                    }
                });
            }
                 private void requestPermission()
                 {
                     ActivityCompat.requestPermissions(donorList.this, new String[] {Manifest.permission.CALL_PHONE},1);

                 }
                 private void messageRequestPermission()
                 {
                     ActivityCompat.requestPermissions(donorList.this, new String[] {Manifest.permission.SEND_SMS},1);

                 }


            @Override
            public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_donar_card,parent,false);
                return new myViewHolder(view);
            }
        };
        //adapter = new Adapter(options);

        recview.setAdapter(adapter1);


    }
    public  void  onStart(){
        super.onStart();
        adapter1.startListening();
    }

    public  void  onStop(){
        super.onStop();
        adapter1.stopListening();
    }

    @Override
    public  void onBackPressed(){
        Intent intent = new Intent(donorList.this, searchDonor.class);
        this.finish();
        startActivity(intent);
    }
    class myViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView Number;
        TextView blood;
        ImageButton call;
        ImageButton message;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.Name);
            Number = (TextView)itemView.findViewById(R.id.Number);
            call = (ImageButton)itemView.findViewById(R.id.Call);
            message = (ImageButton)itemView.findViewById(R.id.Message);
        }
    }


}

