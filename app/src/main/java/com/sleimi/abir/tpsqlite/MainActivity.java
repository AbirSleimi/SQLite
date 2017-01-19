package com.sleimi.abir.tpsqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.List;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private ListView lv;

    // Array of strings...
    String[] choixArray = {"Afficher tous vos contacts","Ajouter un contact",
            "Modifier un contact","Initialisation de la base !"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final DatabaseHandler db = new DatabaseHandler(this);
        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                R.layout.activity_listview, choixArray);

        ListView listView = (ListView) findViewById(R.id.list_choix);
        listView.setAdapter(adapter);
        /**
         * CRUD Operations
         * */
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0) {

                // traitement si l'utilisateur a choisi le premier item de la ListView } else { ...
                    Log.d("Afficher: ", "liste ..");
                    Intent i = new Intent(MainActivity.this, ListeContacts.class);
                    startActivity(i);

                }
                if (position == 1) {

                    // traitement si l'utilisateur a choisi le premier item de la ListView } else { ...
                    Log.d("Ajouter: ", "Ajout ..");
                    Intent i = new Intent(MainActivity.this, AjouterContact.class);
                    startActivity(i);
                }
                if (position == 2) {

                    // Reading all contacts
                    Log.d("Reading: ", "Reading all contacts..");
                    List<Contact> contacts = db.getAllContacts();

                    for (Contact cn : contacts) {
                        String log = "Id: "+cn.getID()+" ,Name: " + cn.getName() + " ,Phone: " + cn.getPhoneNumber();
                        // Writing Contacts to log
                        Log.d("Name: ", log);
                    }
                }
                if (position == 3) {

                    // traitement si l'utilisateur a choisi le 3ème item de la ListView } else { ...
                    Log.d("Supprimer: ", "liste ..");
                    List<Contact> contacts = db.getAllContacts();

                    for (Contact cn : contacts) {
                        db.deleteContact(cn);
                    }
                    Toast.makeText(MainActivity.this, "Contacts Supprimés!", Toast.LENGTH_LONG).show();

                }

            }
        });

    }
}
