package com.example.shadesofred;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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
        recview = (RecyclerView)findViewById(R.id.recview);
        recview.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<users> options =
                new FirebaseRecyclerOptions.Builder<users>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Users").orderByChild("Blood").equalTo(blood), users.class)
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
                       try {
                           Intent intent = new Intent(Intent.ACTION_CALL);
                           intent.setData(Uri.parse("tel:" + user.getNumber()));
                           startActivity(intent);
                       }catch (ActivityNotFoundException exception){
                           Log.e("Calling a number", "callFailed", exception);
                       }
                    }
                });
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

