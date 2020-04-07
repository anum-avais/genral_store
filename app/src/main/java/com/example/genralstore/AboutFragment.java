package com.example.genralstore;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class AboutFragment extends Fragment {

    public AboutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about, container, false);
    }}

   //     public void list_view(View view)
     //   {
       //     if(view.getItemId() == R.id.item_register_book)
         //   {
                // Toast.makeText(MainActivity.this, "Home item clicked", Toast.LENGTH_LONG).show();
           //     startActivity(new Intent(MainActivity.this,ProductListActivity.class));
             //   return true;


        //    }
      //  }

