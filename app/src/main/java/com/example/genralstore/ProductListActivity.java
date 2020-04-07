package com.example.genralstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import javax.annotation.Nullable;

public class ProductListActivity extends AppCompatActivity {
    ListView listView;
    String mTitle[] = {"LIPSTICK", "LIP_GLOSS", "MASCARA", "EYELINERS", "BLUSH", "BB_CREAM", "CONCEALER", "BRONZER", "SETTING_SPRAY", "SKIN_CARE"};
    String mDescription[] = {"LIPSTICK Description", "LIP_GLOSS Description", "MASCARA Description", "EYELINERS Description", "BLUSH Description", "BB_CREAM Description", "CONCEALER Description", "CONCEALER Description","SETTING_SPRAY Description", "SKIN_CARE Description"};

    int images[] = {R.drawable.lipstick, R.drawable.lip_gloss, R.drawable.mascara, R.drawable.eyeliner, R.drawable.blush, R.drawable.bb_cream, R.drawable.concealer, R.drawable.bronzer, R.drawable.setting_spray, R.drawable.skin_care};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        ///
        listView = findViewById(R.id.listView);

        ProductListActivity.MyAdapter adapter = new ProductListActivity.MyAdapter(this, mTitle, mDescription, images);
        listView.setAdapter(adapter);
        // there is my mistake...
        // now again check this..

        // now set item click on list view
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Toast.makeText(ProductListActivity.this, "you selected lipstick", Toast.LENGTH_SHORT).show();
                }
                if (position == 1) {
                    Toast.makeText(ProductListActivity.this, "you selected lip gloss", Toast.LENGTH_SHORT).show();
                }
                if (position == 2) {
                    Toast.makeText(ProductListActivity.this, "you selected mascara", Toast.LENGTH_SHORT).show();
                }
                if (position == 3) {
                    Toast.makeText(ProductListActivity.this, "you selected eyeliner", Toast.LENGTH_SHORT).show();
                }
                if (position == 4) {
                    Toast.makeText(ProductListActivity.this, "blush", Toast.LENGTH_SHORT).show();
                }
                if (position == 5) {
                    Toast.makeText(ProductListActivity.this, "foundation", Toast.LENGTH_SHORT).show();
                }
                if (position == 6) {
                    Toast.makeText(ProductListActivity.this, "you selected bb Cream", Toast.LENGTH_SHORT).show();
                }
                if (position == 7) {
                    Toast.makeText(ProductListActivity.this, "concelaler", Toast.LENGTH_SHORT).show();
                }
                if (position == 8) {
                    Toast.makeText(ProductListActivity.this, "bronzer", Toast.LENGTH_SHORT).show();
                }
                if (position == 9) {
                    Toast.makeText(ProductListActivity.this, "setting speay", Toast.LENGTH_SHORT).show();
                }
                if (position == 10) {
                    Toast.makeText(ProductListActivity.this, "skin care", Toast.LENGTH_SHORT).show();
                }
            }
        });
        // so item click is done now check list view
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
