package com.example.a2remember;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    LayoutInflater inflater;
    List<Not> notlar;

    Adapter (Context context, List<Not>notlar){
        this.inflater=LayoutInflater.from(context);
        this.notlar= notlar;
    }

    @NonNull
    //@org.jetbrains.annotations.NotNull
    @Override
    //public Adapter.ViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= inflater.inflate(R.layout.custom_list_view,viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    //public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull Adapter.ViewHolder holder, int position) {
    public void onBindViewHolder(@NonNull Adapter.ViewHolder viewHolder, int i) {
        String baslik =notlar.get(i).getBaslik();
        String date = notlar.get(i).getDate();
        String time= notlar.get(i).getTime();
        long id = notlar.get(i).getID();


        Log.d("Baslik", "onBindViewHolder: Baslik-> "+baslik);

        viewHolder.nBaslik.setText(baslik);
        viewHolder.nDate.setText(date);
        viewHolder.nTime.setText(time);
        // viewHolder.nID.setText(String.valueOf(notlar.get(i).getID()));
    }

    @Override
    public int getItemCount() {
        return notlar.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView nBaslik , nDate , nTime, nID ;
        //public ViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            nBaslik =itemView.findViewById(R.id.nBaslik);
            // nIcerik=itemView.findViewById(R.id.notIcerik);
            nDate = itemView.findViewById(R.id.nDate);
            nTime= itemView.findViewById(R.id.nTime);
            //nID = itemView.findViewById(R.id.list);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(v.getContext(),Detay.class);
                    //detaylardaki extra yerine buradaki ile eşleşen şeyi yazmak gerekiyor ID
                    i.putExtra("ID",notlar.get(getAdapterPosition()).getID());
                    v.getContext().startActivity(i);
                }
            });

        }
    }
}

