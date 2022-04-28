package com.example.ex2json;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView ls;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ls = findViewById(R.id.lst);
        String res = loadJsonFromRaw(R.raw.employes);
        ArrayList<Employe> emps = new ArrayList<>();

        try {
            JSONArray array = new JSONArray(res);

            for(int i=0;i<array.length();i++){
                JSONObject ob = array.getJSONObject(i);
                Employe e = new Employe();

                e.setNom(ob.getString("nom"));
                e.setPrenom(ob.getString("prenom"));
                e.setAdresse(ob.getString("adresse"));
                e.setSalaire(ob.getDouble("salaire"));

                emps.add(e);
            }

            ArrayList<String> items = new ArrayList<>();
            for(Employe ee : emps)
                items.add(ee.getNom() + " - " + ee.getPrenom());

            ArrayAdapter ad = new ArrayAdapter(this, android.R.layout.simple_list_item_1,items);
            ls.setAdapter(ad);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public String loadJsonFromRaw(int resId){
        String json = "";
        try {
            InputStream in = getResources().openRawResource(resId);
            int size = in.available();
            byte[] tab = new byte[size];
            in.read(tab);
            in.close();
            json = new String(tab);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return json;
    }
}