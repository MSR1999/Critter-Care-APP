package com.example.petapp20;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Post_pet extends AppCompatActivity{

    Button BTN_New_Pet,BTN_Gallery, post_btn;
//    FirebaseListAdapter;
    Uri imageUri;
    ImageView imageSelected;

    private String UID;
    FirebaseStorage storage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_pet);

//        listview = findViewById(R.id.LV_animal);
        BTN_New_Pet = findViewById(R.id.BTN_New_Pet);
        UID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        imageSelected = findViewById(R.id.image_selected);
        BTN_Gallery = findViewById(R.id.BTN_Gallery);
        post_btn = findViewById(R.id.BTN_my_pet);
        storage = FirebaseStorage.getInstance();


        //run the below method on imageview click
        imageSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mGetContent.launch("image/*");


            }
        });

        BTN_Gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //upload image on button click
                uploadImage();

            }
        });




        //        Jump to the New Pet interface
        BTN_New_Pet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Post_pet.this, New_pet.class));
            }
        });

        //        Jump to the My Pet interface
        post_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Post_pet.this, My_pet.class));
            }
        });


    }

    private void uploadImage() {


        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Uploading....");
        dialog.show();


            if (imageUri != null){
                StorageReference reference = storage.getReference().child("images/" + UID);


                reference.putFile(imageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                        if (task.isSuccessful()){

                            dialog.dismiss();
                            Toast.makeText(Post_pet.this,"Image Uploaded Successfully!", Toast.LENGTH_SHORT).show();

                        }else {
                            dialog.dismiss();
                            Toast.makeText(Post_pet.this,task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }


    }

    ActivityResultLauncher<String> mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(),
            new ActivityResultCallback<Uri>() {
                @Override
                public void onActivityResult(Uri result) {

                    //this result is the result of uri
                    if (result != null){
                        imageSelected.setImageURI(result);
                        //result will be set in imageUri
                        imageUri = result;
                    }

                }
            });

}