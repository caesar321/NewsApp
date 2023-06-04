package com.example.newsapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

       public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder>{
                Context context;
                ArrayList<ModalClass> modal;

           public NewsAdapter(Context context, ArrayList<ModalClass> modal) {
               this.context = context;
               this.modal = modal;
           }
            public  void setFilteredList(ArrayList<ModalClass> filteredList){
               this.modal= filteredList;
               notifyDataSetChanged();
            }
           @NonNull
           @Override
           public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
             View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item,parent,false);
             return new ViewHolder(view);
           }

           @Override
           public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
               holder.Title.setText(modal.get(position).getTitle());
               holder.Description.setText(modal.get(position).getDescription());
               holder.source.setText(modal.get(position).getSource().getName());
               Picasso.get().load(modal.get(position).getUrlToImage()).into(holder.img);
               holder.cardView.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       Intent intent = new Intent(context,NewsActivity2.class);
                       intent.putExtra("url",modal.get(position).getUrlToImage());
                       intent.putExtra("title",modal.get(position).getTitle());
                       intent.putExtra("content",modal.get(position).getContent());
                       intent.putExtra("author",modal.get(position).getAuthor());
                       intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                       context.startActivity(intent);


                   }
               });


           }

           @Override
           public int getItemCount() {
               return modal.size();
           }

           public class ViewHolder extends RecyclerView.ViewHolder {
               TextView source,Description,Title;
               ImageView img;
               MaterialCardView cardView;
               public ViewHolder(@NonNull View itemView) {
                   super(itemView);
                   source= itemView.findViewById(R.id.txt_Author);
                  Description= itemView.findViewById(R.id.txt_description);
                 Title= itemView.findViewById(R.id.txt_Title);
                  img= itemView.findViewById(R.id.img_image);
                  cardView= itemView.findViewById(R.id.cardview);
               }
           }
       }