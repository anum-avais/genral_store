package com.example.genralstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

public class MainActivity extends AppCompatActivity {


    FirebaseFirestore objfirebaseFirestore;
    BottomNavigationView objbottomNavigationView;
    String city = "lahore", email = "anum@gmail.com";


    ListView listView;
    String mTitle[] = {"LIPSTICK", "LIP_GLOSS", "MASCARA", "EYELINERS", "BLUSH", "BB_CREAM", "CONCEALER", "BRONZER", "SETTING_SPRAY", "SKIN_CARE"};
    String mDescription[] = {"LIPSTICK Description", "LIP_GLOSS Description", "MASCARA Description", "EYELINERS Description", "BLUSH Description", "BB_CREAM Description", "CONCEALER Description", "SETTING_SPRAY Description", "SKIN_CARE Description"};

    int images[] = {R.drawable.lipstick, R.drawable.lip_gloss, R.drawable.mascara, R.drawable.eyeliner, R.drawable.blush, R.drawable.bb_cream, R.drawable.concealer, R.drawable.bronzer, R.drawable.setting_spray, R.drawable.skin_care};

    /// make fragments objects
    HomeFragment objectHomeFramg;
    AboutFragment objectDetailFramg;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ///
        listView = findViewById(R.id.listView);

        MyAdapter adapter = new MyAdapter(this, mTitle, mDescription, images);
        listView.setAdapter(adapter);
        // there is my mistake...
        // now again check this..

        // now set item click on list view
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Toast.makeText(MainActivity.this, "you selected lipstick", Toast.LENGTH_SHORT).show();
                }
                if (position == 1) {
                    Toast.makeText(MainActivity.this, "you selected lip gloss", Toast.LENGTH_SHORT).show();
                }
                if (position == 2) {
                    Toast.makeText(MainActivity.this, "you selected mascara", Toast.LENGTH_SHORT).show();
                }
                if (position == 3) {
                    Toast.makeText(MainActivity.this, "you selected eyeliner", Toast.LENGTH_SHORT).show();
                }
                if (position == 4) {
                    Toast.makeText(MainActivity.this, "blush", Toast.LENGTH_SHORT).show();
                }
                if (position == 5) {
                    Toast.makeText(MainActivity.this, "foundation", Toast.LENGTH_SHORT).show();
                }
                if (position == 6) {
                    Toast.makeText(MainActivity.this, "you selected bb Cream", Toast.LENGTH_SHORT).show();
                }
                if (position == 7) {
                    Toast.makeText(MainActivity.this, "concelaler", Toast.LENGTH_SHORT).show();
                }
                if (position == 8) {
                    Toast.makeText(MainActivity.this, "bronzer", Toast.LENGTH_SHORT).show();
                }
                if (position == 9) {
                    Toast.makeText(MainActivity.this, "setting speay", Toast.LENGTH_SHORT).show();
                }
                if (position == 10) {
                    Toast.makeText(MainActivity.this, "skin care", Toast.LENGTH_SHORT).show();
                }
            }
        });
        // so item click is done now check list view

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

    public void showtoast(View view) {

        Toast objtoast = new Toast(this);

        objtoast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
        objtoast.setDuration(Toast.LENGTH_LONG);
        LayoutInflater objinflater = getLayoutInflater();
        View convertedview = objinflater.inflate(R.layout.loast_layout, (ViewGroup) findViewById(R.id.hz_toast), false);
        objtoast.setView(convertedview);
        objtoast.show();
    }

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


        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }


    }

    private void changeFragment(Fragment objectFragment) {
        try {
            FragmentTransaction objectTransaction =
                    getSupportFragmentManager().beginTransaction();

            objectTransaction.replace(R.id.container, objectFragment).commit();
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    class MyAdapter extends ArrayAdapter<String> {

        Context context;
        String rTitle[];
        String rDescription[];
        int rImgs[];

        MyAdapter(Context c, String title[], String description[], int imgs[]) {
            super(c, R.layout.row, R.id.textView1, title);
            this.context = c;
            this.rTitle = title;
            this.rDescription = description;
            this.rImgs = imgs;

        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.row, parent, false);
            ImageView images = row.findViewById(R.id.image);
            TextView myTitle = row.findViewById(R.id.textView1);
            TextView myDescription = row.findViewById(R.id.textView2);

            // now set our resources on views
            images.setImageResource(rImgs[position]);
            myTitle.setText(rTitle[position]);
            myDescription.setText(rDescription[position]);


            return row;
        }


    }
}