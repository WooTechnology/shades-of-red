package com.example.shadesofred;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;


public class donorList extends AppCompatActivity {
    RecyclerView recview;
    Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.donor_list);
        recview = (RecyclerView)findViewById(R.id.recview);
        recview.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<users> options =
                new FirebaseRecyclerOptions.Builder<users>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Users"), users.class)
                        .build();
        adapter = new Adapter(options);
        recview.setAdapter(adapter);
    }
    @Override
    protected  void onStart(){
        super.onStart();
        adapter.startListening();

    }

    protected  void onStop(){
        super.onStop();
        adapter.stopListening();

    }

}
