package com.sleimi.abir.tpsqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.List;

import static com.sleimi.abir.tpsqlite.DatabaseHandler.TABLE_CONTACTS;

/**
 * Created by PackardBell on 25/11/2016.
 */

public class ListeContacts extends AppCompatActivity  {
    private Context context;
    //DatabaseHandler db=new DatabaseHandler(context);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_contact);
        context=this;
        final DatabaseHandler db = new DatabaseHandler(this);
        // Create DatabaseHelper instance


        // Reference to TableLayout
        TableLayout tableLayout=(TableLayout)findViewById(R.id.tablelayout);
        // Add header row
        TableRow rowHeader = new TableRow(context);
        rowHeader.setBackgroundColor(Color.parseColor("#c0c0c0"));
        rowHeader.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT));
        String[] headerText={"ID","NAME","PHONE"};
        for(String c:headerText) {
            TextView tv = new TextView(this);
            tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            tv.setGravity(Gravity.CENTER);
            tv.setTextSize(18);
            tv.setPadding(5, 5, 5, 5);
            tv.setText(c);
            rowHeader.addView(tv);
        }
        tableLayout.addView(rowHeader);

/*

        List<Contact> contacts = db.getAllContacts();
        for (Contact cn : contacts) {
            String log = cn.getID() + cn.getName() + cn.getPhoneNumber();
            TextView txt = new TextView(this);
            txt.setText(log);
            setContentView(txt);
        }*/

        try
        {
            List<Contact> contacts = db.getAllContacts();
        for (Contact cn : contacts) {

            TableRow row = new TableRow(context);
            row.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT));
            String[] colText={cn.getID()+"",cn.getName(),cn.getPhoneNumber()};
            for(String text:colText) {
                TextView tv = new TextView(this);
                tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.WRAP_CONTENT));
                tv.setGravity(Gravity.CENTER);
                tv.setTextSize(16);
                tv.setPadding(5, 5, 5, 5);
                tv.setText(text);
                row.addView(tv);
            }
            tableLayout.addView(row);
        }
        }
        catch (SQLiteException e)
        {
            e.printStackTrace();

        }
        finally
        {
            db.close();
            // Close database
        }

/*
        // Reading all contacts
        Log.d("Reading: ", "Reading all contacts..");
        List<Contact> contacts = db.getAllContacts();
        tableLayout.addView((View) contacts);

        for (Contact cn : contacts) {
            String log = "Id: "+cn.getID()+" ,Name: " + cn.getName() + " ,Phone: " + cn.getPhoneNumber();
            // Writing Contacts to log
            Log.d("Name: ", log);
        }

*/
    }
}