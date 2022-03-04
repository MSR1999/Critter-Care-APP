package com.example.petapp20;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class My_pet extends AppCompatActivity {

    private TextView pet_name;
    private TextView pet_age;
    private TextView pet_breed;
    private TextView pet_gender;
    private TextView pet_color;
    ImageView Profile_camera;
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private DatabaseReference databaseReference;
    private StorageReference mStorageReference;
    private String UID;

//    private String UID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_pet);


        pet_name = findViewById(R.id.Profile_Pet_name);
        pet_age = findViewById(R.id.Profile_Pet_Age);
        pet_breed = findViewById(R.id.Profile_Pet_Breed);
        pet_gender = findViewById(R.id.Profile_Pet_Gender);
        pet_color = findViewById(R.id.Profile_Pet_Color);
        Profile_camera = findViewById(R.id.Profile_camera);


        UID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        mStorageReference = FirebaseStorage.getInstance().getReference("images/").child(UID);

        //retrieve image from Storage
        mStorageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri.toString()).into(Profile_camera);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });


        databaseReference = FirebaseDatabase.getInstance().getReference("Clients");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Users_Form curr_user = snapshot.child(user.getUid()).getValue(Users_Form.class);
                Pet_form curr_add = curr_user.getPet();
                pet_name.setText(curr_add.getPet_name());
                pet_age.setText(curr_add.getAge()+" years old");
                pet_breed.setText(curr_add.getBreed());
                pet_gender.setText(curr_add.getGender());
                pet_color.setText(curr_add.getPet_Color());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}