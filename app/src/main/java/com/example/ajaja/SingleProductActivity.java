package com.example.ajaja;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class SingleProductActivity extends AppCompatActivity {

    TextView descriptionP, titleP,  categorieP,
            sousCategorieP, gouvDelP, prixP
            , telP, adresseP;
    ImageView imageP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_product);
        titleP = findViewById(R.id.tvTitleProduct);
        descriptionP = findViewById(R.id.tvdescriptionProduct);
        prixP = findViewById(R.id.tvPrixProduct);
        categorieP = findViewById(R.id.tvCatProduct);
        sousCategorieP = findViewById(R.id.tvSousCatProduct);
        gouvDelP = findViewById(R.id.tvgovDel);
        telP = findViewById(R.id.tvTelPruduct);
        adresseP = findViewById(R.id.tvAdresseProduct);
        imageP = findViewById(R.id.imageProduct);
        Intent intent = getIntent();
        Picasso.get().load(intent.getStringExtra("imageUri"))
                .placeholder(R.drawable.imageslogo)
                .into(imageP);
        titleP.setText(intent.getStringExtra("Title"));
        descriptionP.setText(intent.getStringExtra("Description"));
        prixP.setText(intent.getStringExtra("Prix"));
        categorieP.setText(intent.getStringExtra("Categorie"));
        sousCategorieP.setText(intent.getStringExtra("SousCategorie"));
        gouvDelP.setText(intent.getStringExtra("Gouvernorat")+" : "+intent.getStringExtra("Delegation"));
        telP.setText(intent.getStringExtra("Tel"));
        adresseP.setText(intent.getStringExtra("Adresse"));


    }
}