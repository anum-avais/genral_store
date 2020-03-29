package com.example.genralstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    
    FirebaseFirestore   objfirebaseFirestore;
    String city="lahore", email="anum@gmail.com";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            objfirebaseFirestore=FirebaseFirestore.getInstance();

        }
        catch (Exception e)
        {
            Toast.makeText(this,e.getMessage(), Toast.LENGTH_SHORT).show();
        }


    }
    public void showtoast(View view)
    {

        Toast objtoast = new Toast(this);

                objtoast.setGravity(Gravity.CENTER_HORIZONTAL,0,0);
                objtoast.setDuration(Toast.LENGTH_LONG);
        LayoutInflater objinflater = getLayoutInflater();
         View convertedview = objinflater.inflate(R.layout.loast_layout ,(ViewGroup) findViewById(R.id.hz_toast) , false);
        objtoast.setView(convertedview);
        objtoast.show();
    }
    public void addvaluesToFirebase(View view){
        try {
            Map<String,Object> objectmap=new HashMap<>();
            objectmap.put("city", city);
            objectmap.put("email",email);
            objfirebaseFirestore
                    .collection("record")
                    .add(objectmap)
                    .addOnSuccessListener(
                            new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Toast.makeText(MainActivity.this, "DATA STORE YOYO", Toast.LENGTH_SHORT).show();
                                }
                            }
                    )
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(MainActivity.this, "fail to add data", Toast.LENGTH_SHORT).show();
                        }
                    });


        }
        catch (Exception e)
        {
            Toast.makeText(this,e.getMessage(), Toast.LENGTH_SHORT).show();
        }


    };


}
