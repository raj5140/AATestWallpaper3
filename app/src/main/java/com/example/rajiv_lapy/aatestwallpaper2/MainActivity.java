package com.example.rajiv_lapy.aatestwallpaper2;


import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ArrayList<product> array;

    GridView gr;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        array=new ArrayList<>();


        gr=(GridView) findViewById(R.id.gridView);
        gr.setOnItemClickListener(this);

        runOnUiThread(  new Runnable() {
            @Override
            public void run() {
                new ReadJSON().execute("https://script.google.com/macros/s/AKfycbygukdW3tt8sCPcFDlkMnMuNu9bH5fpt7bKV50p2bM/exec?id=12hNTvm13zVJgcJo1J2Wa_w-z_6UIfHzgdsGUUURcZ_4&sheet=products");
            }
        });

    }





    class ReadJSON extends AsyncTask<String,Integer,String>{


        @Override
        protected String doInBackground(String... params) {
            return readURL(params[0]);
        }

        @Override
        protected void onPostExecute(String content){

            try {
                JSONObject jsonObject=new JSONObject(content);
                JSONArray jsonArray=jsonObject.getJSONArray("products");

                for(int i=0;i<jsonArray.length();i++){
                    JSONObject prdobj = jsonArray.getJSONObject(i);

                    String image1= prdobj.getString("image");
                    String name1=prdobj.getString("name");
                    String price1=prdobj.getString("price");

                    array.add(new product(image1));



//                    array.add(new product(
//                            prductObject.getString("image"),
//                            prductObject.getString("name"),
//                            prductObject.getString("price")
//
//
//                    ));



                }


            } catch (JSONException e) {
                e.printStackTrace();
            }

            CustomListAdapter adapter=new CustomListAdapter(getApplicationContext(),R.layout.grid_item, array);
            gr.setAdapter(adapter);
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


        String selected= parent.getItemAtPosition(position).toString();
        Toast.makeText(MainActivity.this, selected, Toast.LENGTH_SHORT).show();
//        String selected=adapterView.getItemAtPosition(position).toString();
//        Toast.makeText(getApplicationContext(),selected,Toast.LENGTH_LONG).show();



//        Intent intent=new Intent(getApplicationContext(),Wallpaper.class);
//        intent.putExtra(view.getTag());
//        intent.putExtra("image",selected);
//        intent.putExtra("name","0415");
 //       startActivity(intent);

    }



    private static String readURL(String theUrl) {

        StringBuilder content = new StringBuilder();

        try {
            URL url = new URL(theUrl);
            URLConnection connection = url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;

            while ((line = reader.readLine()) != null) {
                content.append(line + "\n");
            }
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();

    }

}
