package com.example.rajiv_lapy.aatestwallpaper2;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.zip.Inflater;

import static java.security.AccessController.getContext;

public class Wallpaper extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallpaper);

        Bundle b = getIntent().getExtras();
        String image12= b.getString("image");
//        String name1=b.getString("name");

        Toast.makeText(getApplicationContext(),image12,Toast.LENGTH_LONG).show();
//
//        ImageView img=(ImageView)findViewById(R.id.img_page);
//        //Picasso.with(getBaseContext()).load(b.getString(image1)).into(img);
    }




}
