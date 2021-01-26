package com.example.shadesofred;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import static androidx.core.content.ContextCompat.startActivity;

public class Adapter extends FirebaseRecyclerAdapter<users, Adapter.myViewHolder>  {



    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public Adapter(@NonNull FirebaseRecyclerOptions<users> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull final users user) {
        holder.name.setText(user.getName());
        holder.Number.setText(user.getNumber());
        holder.blood.setText(user.getBlood());

    }
    public  void makePhoneCall(Activity activity){
        Uri number = Uri.parse("9967940321");
        Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
        activity.startActivity(callIntent);
    }
    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_donar_card,parent,false);
        return new myViewHolder(view);
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
