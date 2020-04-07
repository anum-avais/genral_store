package com.example.genralstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

public class MainActivity extends AppCompatActivity {


    FirebaseFirestore objfirebaseFirestore;
    BottomNavigationView objbottomNavigationView;
    String city = "lahore1", email = "anum@gmail.com";
    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;


   /// make fragments objects
    HomeFragment objectHomeFramg;
    AboutFragment objectDetailFramg;

    ////
    NavigationView objectNavigationView;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

////////// side bar... navigation

        objectNavigationView = findViewById(R.id.navigationView);

        objectNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.item_register_book)
                {
                   // Toast.makeText(MainActivity.this, "Home item clicked", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(MainActivity.this,ProductListActivity.class));
                    return true;


                }else if(item.getItemId() == R.id.item_add_customer)
                {
                    startActivity(new Intent(MainActivity.this,SelectProduct.class));
                    return true;
                }
                return false;
            }
        });

        auth = FirebaseAuth.getInstance();
        // check if user not logged in
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {

          //  Toast.makeText(MainActivity.this, "User is null", Toast.LENGTH_LONG).show();
            ////start funtion ...internt class... reconnct krna ka lua

            startActivity(new Intent(MainActivity.this, UserLogin.class));
            finish();
        }
        ///object auth user login hain ..? ya state check kra rahe hai
        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                Toast.makeText(MainActivity.this, "inside listner auth", Toast.LENGTH_LONG).show();
                if (user == null) {
                    Toast.makeText(MainActivity.this, "User is null", Toast.LENGTH_LONG).show();

                    startActivity(new Intent(MainActivity.this, UserLogin.class));
                    finish();
                }
            }
        };
      ///////////////////
        /// initializing fragments objects
        objectHomeFramg = new HomeFragment();
        objectDetailFramg = new AboutFragment();

        //object of BottomNavigationView
        objbottomNavigationView = findViewById(R.id.BTN);


        // set Home as a defauly fragment
        changeFragment(objectHomeFramg);

        objbottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.item_home) {

                    changeFragment(objectHomeFramg);
//                    objbottomNavigationView.setBackgroundColor(Color.BLUE);
                    Toast.makeText(MainActivity.this, "home page is selected", Toast.LENGTH_SHORT).show();
                    return true;
                } else if (menuItem.getItemId() == R.id.item_about) {
                    changeFragment(objectDetailFramg);
//                    objbottomNavigationView.setBackgroundColor(Color.GREEN);
                    Toast.makeText(MainActivity.this, "SELECT ABOUT OPTION", Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;
            }
        });


        try {
            objfirebaseFirestore = FirebaseFirestore.getInstance();

        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }


    }
////////toset 1
    public void showtoast(View view) {

        Toast objtoast = new Toast(this);

        objtoast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
        objtoast.setDuration(Toast.LENGTH_LONG);
        LayoutInflater objinflater = getLayoutInflater();
        View convertedview = objinflater.inflate(R.layout.loast_layout, (ViewGroup) findViewById(R.id.hz_toast), false);
        objtoast.setView(convertedview);
        objtoast.show();
    }


    ////////toset 2
    public void quotes(View view) {

        Toast objtoast = new Toast(this);

        objtoast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
        objtoast.setDuration(Toast.LENGTH_LONG);
        LayoutInflater objinflater = getLayoutInflater();
        View convertedview = objinflater.inflate(R.layout.loast_layout2, (ViewGroup) findViewById(R.id.hz_toast1), false);
        objtoast.setView(convertedview);
        objtoast.show();
    }
    ////first code for creat the conectionwith fire base its working
    public void addvaluesToFirebase(View view) {
        try {
            Map<String, Object> objectmap = new HashMap<>();
            objectmap.put("city", city);
            objectmap.put("email", email);
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

              } catch (Exception e) { Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show(); }


    }

    private void changeFragment(Fragment objectFragment) {
        try {
            FragmentTransaction objectTransaction = getSupportFragmentManager().beginTransaction();

            objectTransaction.replace(R.id.container, objectFragment).commit();
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}