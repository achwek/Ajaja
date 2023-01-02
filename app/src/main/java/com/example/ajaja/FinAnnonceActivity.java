package com.example.ajaja;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.ImageReader;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ajaja.databinding.ActivityFinAnnonceBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class FinAnnonceActivity extends AppCompatActivity {
    ActivityFinAnnonceBinding binding;
    FirebaseDatabase firebaseDatabase;
    FirebaseStorage firebaseStorage;
        private int counter = 0;
       public Uri uri;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFinAnnonceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();
        binding.imgCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.textViewAjoute.setVisibility(View.INVISIBLE);
                uploadImage();
            }
        });
        binding.btnPublier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final StorageReference reference = firebaseStorage.getReference()
                        .child("Images")
                        .child(System.currentTimeMillis()+"");
                reference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                       reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                           @Override
                           public void onSuccess(Uri uri) {
                               Intent intent = getIntent();

                               Annonce annonce = new Annonce();
                               annonce.setTitle(intent.getStringExtra("Title"));
                               annonce.setDescription(intent.getStringExtra("Description"));
                               annonce.setCategorie(intent.getStringExtra("Categorie"));
                               annonce.setSousCategorie(intent.getStringExtra("SousCategories"));
                               annonce.setGouvernorat(intent.getStringExtra("Gouvernorat"));
                               annonce.setDelegation(intent.getStringExtra("Delegation"));
                               annonce.setImageUri(uri.toString());
                               annonce.setPrix(binding.etPrix.getText().toString());
                               annonce.setAdresse(binding.etAdresse.getText().toString());
                               annonce.setTel(binding.etTel.getText().toString());
                               annonce.setImageUri(uri.toString());


                                firebaseDatabase.getReference().child("data")
                                        .push()
                                        .setValue(annonce)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {
                                                Toast.makeText(getApplicationContext(), "Upload Successfully",Toast.LENGTH_LONG).show();
                                                binding.progressBar.setVisibility(View.VISIBLE);
                                                Timer timer= new Timer();
                                                TimerTask timerTask= new TimerTask() {
                                                    @Override
                                                    public void run() {
                                                        counter += 10;
                                                        binding.progressBar.setProgress(counter);
                                                        if(counter == 100){
                                                            timer.cancel();

                                                            Intent i = new Intent(getApplicationContext(), HomeActivity.class);
                                                            startActivity(i);
                                                        }
                                                    }
                                                };
                                                timer.schedule(timerTask,100,100);
;
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(getApplicationContext(), "Upload Error",Toast.LENGTH_LONG).show();

                                            }
                                        });
                           }
                       });

                    }
                });



            }
        });
    }

    private void uploadImage() {
        Dexter.withContext(this)
                .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        Intent intent = new Intent();
                        intent.setType("image/*");
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(intent,101);
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                        Toast.makeText(getApplicationContext(),"Permission Denied",Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                            permissionToken.continuePermissionRequest();
                    }
                }).check();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 101 && resultCode == RESULT_OK);
        uri = data.getData();
        binding.imgCapture.setImageURI(uri);
    }
}