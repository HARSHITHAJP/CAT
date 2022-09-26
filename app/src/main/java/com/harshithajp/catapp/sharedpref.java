package com.harshithajp.catapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class sharedpref extends AppCompatActivity {
    SharedPreferences sharedpreferences;
    EditText name;
    EditText email;
    EditText sports;
    EditText teamname;
    Button btnsave,btnclear, btnrtr;


    public static final String mypreference = "pref";
    public static final String Name = "nameKey";
    public static final String Email = "emailKey";
    public static final String Sports = "sportsKey";
    public static final String Teamname = "teamKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sharedpref);

        name = (EditText) findViewById(R.id.etName);
        email = (EditText) findViewById(R.id.etEmail);
        sports = (EditText) findViewById(R.id.etSports);
        teamname = (EditText) findViewById(R.id.etTeamname);

        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);

        if (sharedpreferences.contains(Name)) {
            name.setText(sharedpreferences.getString(Name, ""));

        }
        if (sharedpreferences.contains(Email)) {
            email.setText(sharedpreferences.getString(Email, ""));

        }
        if (sharedpreferences.contains(Sports)) {
            sports.setText(sharedpreferences.getString(Sports, ""));

        }
        if (sharedpreferences.contains(Teamname)) {
            teamname.setText(sharedpreferences.getString(Teamname, ""));

        }


    }

    public void Save(View view) {
        String n = name.getText().toString();
        String e = email.getText().toString();
        String s = sports.getText().toString();
        String t = teamname.getText().toString();


        Intent intent1 = new Intent(sharedpref.this, MainActivity.class);
        intent1.putExtra("name", n);
        intent1.putExtra("email", e);
        intent1.putExtra("sports", s);
        intent1.putExtra("teamname", t);
        startActivity(intent1);


        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(Name, n);
        editor.putString(Email, e);
        editor.putString(Sports, s);
        editor.putString(Teamname, t);

        editor.commit();

        Toast.makeText(this, "contents saved", Toast.LENGTH_SHORT).show();
    }

    public void Clear(View view) {
        name.setText("");
        email.setText("");
        sports.setText("");
        teamname.setText("");
        Toast.makeText(this, "contents cleared", Toast.LENGTH_SHORT).show();

    }

    public void Retrieve(View view) {

        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);

        if (sharedpreferences.contains(Name)) {
            name.setText(sharedpreferences.getString(Name, ""));
        }
        if (sharedpreferences.contains(Email)) {
            email.setText(sharedpreferences.getString(Email, ""));

        }
        if (sharedpreferences.contains(Sports)) {
            sports.setText(sharedpreferences.getString(Sports, ""));

        }
        if (sharedpreferences.contains(Teamname)) {
            teamname.setText(sharedpreferences.getString(Teamname, ""));

        }
        Toast.makeText(this, "contents retrieved", Toast.LENGTH_SHORT).show();
    }
}
