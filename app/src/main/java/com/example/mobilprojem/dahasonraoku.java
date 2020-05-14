package com.example.mobilprojem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class dahasonraoku extends AppCompatActivity {

    ListView ls;
    List<kitapBilgileri> secilenKitap;
    Context context = this;
    SQliteHelper db = new SQliteHelper(context);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dahasonraoku);
        tanimla();
        db.getReadableDatabase();
        listeleme();
        tiklama();
    }

    public void tanimla() {

        ls = findViewById(R.id.listView);
    }

    public void listeleme() {

        secilenKitap = db.dsoKitaplariGetir();
        List<String> dsokitap = new ArrayList<>();
        for (int j = 0; j < secilenKitap.size(); j++) {
            dsokitap.add(j, secilenKitap.get(j).getYazaradi());
        }
        ListAdapter lsa = new ArrayAdapter<>(this, R.layout.dahasonraoku, R.id.kitap, dsokitap);
        ls.setAdapter(lsa);

    }

    public void tiklama() {

        ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Intent intent = new Intent(getApplicationContext(), ozetokuma.class);
                intent.putExtra("secilenkitapid", secilenKitap.get(position).getKitapid());
                Log.i("iddso", String.valueOf(secilenKitap.get(position).getKitapid()));
                startActivity(intent);
            }
        });
    }
}
