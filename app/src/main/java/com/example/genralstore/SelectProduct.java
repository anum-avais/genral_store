package com.example.genralstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SelectProduct extends AppCompatActivity {
    private final String COLLECTION_NAME="items";
    private EditText exp_date,name,price;
    //Step 1 - Create Firebase Firestore object
    private FirebaseFirestore objectFirebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_product);

        initializeFirebaseFirestoreObject();
        name=findViewById(R.id.name);
        exp_date=findViewById(R.id.exp_date);

        price=findViewById(R.id.price);

    }
    private void initializeFirebaseFirestoreObject()
    {
        //Step 2 - initialize Firebase Firestore object
        objectFirebaseFirestore=FirebaseFirestore.getInstance();
    }
    public void addValues(View v)
    {
        try {
            if (!exp_date.getText().toString().isEmpty()
                    && !name.getText().toString().isEmpty()
                    && !price.getText().toString().isEmpty()
            ) {
                //Step 3 -- Creating Map to store values
                Map<String, Object> objectMap = new HashMap<>();

                objectMap.put("name", name.getText().toString());
                objectMap.put("price", price.getText().toString());
                objectMap.put("date", exp_date.getText().toString());
                //Step 4- add objectMap to Firebase Firestore

                objectFirebaseFirestore.collection(COLLECTION_NAME)
                        .document(exp_date.getText().toString())
                        .set(objectMap)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast objectToast = Toast.makeText(SelectProduct.this,
                                        "Values Added", Toast.LENGTH_SHORT);

                                objectToast.setGravity(Gravity.TOP, 0, 0);
                                objectToast.show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast objectToast = Toast.makeText(SelectProduct.this,
                                        "Fails to add values:" +
                                                e.getMessage(), Toast.LENGTH_SHORT);

                                objectToast.setGravity(Gravity.TOP, 0, 0);
                                objectToast.show();
                            }
                        });
            } else if (name.getText().toString().isEmpty()) {
                Toast objectToast = Toast.makeText(this,
                        "Please enter document id", Toast.LENGTH_SHORT);

                objectToast.setGravity(Gravity.TOP, 0, 0);
                objectToast.show();

            } else if (exp_date.getText().toString().isEmpty()) {
                Toast objectToast = Toast.makeText(this,
                        "Please enter name", Toast.LENGTH_SHORT);

                objectToast.setGravity(Gravity.TOP, 0, 0);
                objectToast.show();
            } else if (price.getText().toString().isEmpty()) {
                Toast objectToast = Toast.makeText(this,
                        "Please enter email", Toast.LENGTH_SHORT);

                objectToast.setGravity(Gravity.TOP, 0, 0);
                objectToast.show();
            }
        } catch (Exception e) {
            Toast.makeText(this,"Successfully", Toast.LENGTH_SHORT).show();
        }
    }


}





