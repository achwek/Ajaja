package com.example.ajaja;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.net.Inet4Address;
import java.util.ArrayList;

public class AddAnnonceActivity extends AppCompatActivity {
    private AutoCompleteTextView spinnerCategorie;
         private FirebaseFirestore db = FirebaseFirestore.getInstance();
         private ArrayList<String> listCat = new ArrayList<>();
    private AutoCompleteTextView spGov;
    private ArrayList<String> listGov = new ArrayList<>();
    private AutoCompleteTextView spDelegation;
    private ArrayList<String> listDelegation = new ArrayList<>();
    private AutoCompleteTextView spSousCat;
    private ArrayList<String> listSousCat = new ArrayList<>();
        private Button btnNext;
        private EditText title, description;
        String categorie, sousCategorie, gouvernorat, delegation;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_annonce);
        title = findViewById(R.id.editTitle);
        description= findViewById(R.id.editDescription);

        btnNext = findViewById(R.id.next_button);
        spinnerCategorie = findViewById(R.id.spCat);
        spGov = findViewById(R.id.spGov);
        spSousCat = findViewById(R.id.spSousCat);
        spDelegation = findViewById(R.id.spDelegation);
        showDataSpinnerCategories();
        showDataSpinnerGouvernorat();
        spinnerCategorie.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String cat = parent.getItemAtPosition(position).toString();
                categorie  = cat;
                showDataSpinnerSousCat(cat);

            }
        });
        spSousCat.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String sousCat= parent.getItemAtPosition(position).toString();
                sousCategorie= sousCat;


            }
        });
        spGov.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String gov = parent.getItemAtPosition(position).toString();
                gouvernorat= gov;

                showDataSpinnerDelegation(gov);

            }
        });
        spDelegation.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String d = parent.getItemAtPosition(position).toString();
                delegation= d;


            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String valtitle = title.getText().toString();
                String valdescription =description.getText().toString();

               // if( valtitle.isEmpty() && valdescription.isEmpty() && categorie.isEmpty() && sousCategorie.isEmpty()
              //  && gouvernorat.isEmpty() && delegation.isEmpty()){
                    Intent intent = new Intent(AddAnnonceActivity.this,FinAnnonceActivity.class);
                    intent.putExtra("Title",valtitle);
                    intent.putExtra("Description",valdescription );
                    intent.putExtra("Categorie",categorie);
                    intent.putExtra("SousCategories",sousCategorie);
                    intent.putExtra("Gouvernorat",gouvernorat);
                    intent.putExtra("Delegation",delegation);
                    startActivity(intent);
            //    }else{
               //     Toast.makeText(getApplicationContext(),"Veuillez  remplir tous les champs",Toast.LENGTH_LONG).show();

               // }


            }
        });
    }



    private void showDataSpinnerCategories(){
        db.collection("categories").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                listCat.clear();
                if(queryDocumentSnapshots.size() > 0){

                      for (DocumentSnapshot doc : queryDocumentSnapshots){

                          listCat.add(doc.getString("title"));


                      }
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.style_spinner,listCat);

                    spinnerCategorie.setAdapter(adapter);
                }else{

                    Toast.makeText(getApplicationContext(),"Data Failed",Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }
    private void showDataSpinnerSousCat(String cat){
        db.collection(cat).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if(queryDocumentSnapshots.size() > 0){
                            listSousCat.clear();
                            for (DocumentSnapshot doc : queryDocumentSnapshots){
                                listSousCat.add(doc.getString("name"));
                            }
                            ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.style_spinner,listSousCat);
                            spSousCat.setAdapter(adapter);
                        }else{
                            Toast.makeText(getApplicationContext(),"Data Failed",Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });


    }


    private void showDataSpinnerGouvernorat(){
        db.collection("gouvernorats").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if(queryDocumentSnapshots.size() > 0){
                    listGov.clear();
                    for (DocumentSnapshot doc : queryDocumentSnapshots){
                        listGov.add(doc.getString("name"));
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.style_spinner,listGov);
                    spGov.setAdapter(adapter);
                }else{
                    Toast.makeText(getApplicationContext(),"Data Failed",Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }
    private void showDataSpinnerDelegation(String gov){
        db.collection(gov).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if(queryDocumentSnapshots.size() > 0){
                    listDelegation.clear();
                    for (DocumentSnapshot doc : queryDocumentSnapshots){
                        listDelegation.add(doc.getString("name"));
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.style_spinner,listDelegation);
                    spDelegation.setAdapter(adapter);
                }else{
                    Toast.makeText(getApplicationContext(),"Data Failed",Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });


    }


}