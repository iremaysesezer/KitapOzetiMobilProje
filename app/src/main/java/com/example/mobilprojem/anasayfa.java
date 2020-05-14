package com.example.mobilprojem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class anasayfa extends AppCompatActivity {

    FloatingActionButton kitapekle,dso;
    ListView ls;
    Context context = this;
    List<kitapBilgileri> liste;
    SQliteHelper db = new SQliteHelper(context);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anasayfa);
        tanimla();
        // db.onUpgrade(db.getReadableDatabase(), 1, 2);
        db.getWritableDatabase();
        listeleme();
        ozetEkleyeGec();
        dsoGec();
        ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setTitle("Yapmak İstediğiniz İşlemi Seçiniz");
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("Oku", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(getApplicationContext(), ozetokuma.class);
                                intent.putExtra("secilenkitapid", liste.get(position).getKitapid());
                                Log.i("id", String.valueOf(liste.get(position).getKitapid()));
                                startActivity(intent);
                            }
                        })
                        .setNeutralButton("Sil", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                int kitapId = liste.get(position).getKitapid();
                                db.KitapSil(kitapId);
                                db.dsoKitapSil(kitapId);
                                listeleme();
                            }
                        })
                        .setNegativeButton("Güncelle", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(getApplicationContext(), ozetguncelle.class);
                                intent.putExtra("secilenkitapid", liste.get(position).getKitapid());
                                Log.i("id", String.valueOf(liste.get(position).getKitapid()));
                                startActivity(intent);
                            }
                        });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();


            }
        });
    }

    public void listeleme() {

        liste = db.KitaplariGetir();
        List<String> listekitap = new ArrayList<>();
        for (int i = 0; i < liste.size(); i++) {
            listekitap.add(i, liste.get(i).getYazaradi());
        }
        ListAdapter lsa = new ArrayAdapter<>(this, R.layout.kitaplayout, R.id.kitap, listekitap);
        ls.setAdapter(lsa);
    }

    public void tanimla() {

        kitapekle = findViewById(R.id.ozetekle);
        dso = findViewById(R.id.dsogit);
        ls = findViewById(R.id.listView);

    }

    public void ozetEkleyeGec() {

        kitapekle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), kitapozet.class);
                startActivity(intent);
            }
        });
    }
    public void dsoGec() {

        dso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), dahasonraoku.class);
                startActivity(intent);
            }
        });
    }

}
