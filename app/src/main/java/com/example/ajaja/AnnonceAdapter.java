package com.example.ajaja;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AnnonceAdapter extends RecyclerView.Adapter<AnnonceAdapter.ViewHolder>{

    ArrayList<Annonce> list;
    Context context;

    public AnnonceAdapter(ArrayList<Annonce> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Annonce annonce = list.get(position);
        Picasso.get().load(annonce.getImageUri()).placeholder(R.drawable.imageslogo).into(holder.itemImage);
        holder.itemTitle.setText(annonce.getTitle());
        holder.itemDescription.setText(annonce.getDescription());
        holder.itemPrix.setText(annonce.getPrix());
        holder.itemAdresse.setText(annonce.getAdresse());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,SingleProductActivity.class);
                intent.putExtra("imageUri", annonce.getImageUri() );
                intent.putExtra("Title", annonce.getTitle() );
                intent.putExtra("Description", annonce.getDescription() );
                intent.putExtra("Adresse", annonce.getAdresse() );
                intent.putExtra("Gouvernorat", annonce.getGouvernorat() );
                intent.putExtra("Delegation", annonce.getDelegation() );
                intent.putExtra("Categorie", annonce.getCategorie() );
                intent.putExtra("SousCategorie", annonce.getSousCategorie() );
                intent.putExtra("Prix", annonce.getPrix() );
                intent.putExtra("Tel", annonce.getTel() );
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{

        TextView itemTitle, itemDescription, itemPrix, itemAdresse;
        ImageView itemImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemTitle = itemView.findViewById(R.id.tvTitle);
            itemDescription = itemView.findViewById(R.id.tvdescription);
            itemPrix = itemView.findViewById(R.id.tvprix);
            itemAdresse = itemView.findViewById(R.id.tvadresse);


            itemImage= itemView.findViewById(R.id.urlImage);
        }
    }
}
