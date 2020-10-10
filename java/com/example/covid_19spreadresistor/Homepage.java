package com.example.covid_19spreadresistor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.covid_19spreadresistor.ui.home.HomeFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class Homepage extends AppCompatActivity {

    CheckBox b1,b2,b3,b4,bc1,bc2,bc3,bc4;
    DatabaseReference reference,r2;
    FirebaseAuth auth;
    FirebaseUser user;
    Map<String,Object> healthproblems;
    Map<String,Object> varyinglocation;
    SharedPreferences sharedprefrence;
    Button b;
    TextView title1,title2,t1,t2,t3,t4,t5,t6,t7,t8;
    String age="";
    String complications="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        auth = FirebaseAuth.getInstance();
        healthproblems = new HashMap<>();
        b = findViewById(R.id.button3);
        user = FirebaseAuth.getInstance().getCurrentUser();

        sharedprefrence = (SharedPreferences) this.getSharedPreferences("com.example.covid_19spreadresistor", Context.MODE_PRIVATE);
        varyinglocation = new HashMap<>();

        b1 = findViewById(R.id.age);
        b2 = findViewById(R.id.agehigh);
        b3 = findViewById(R.id.agemid);
        b4 = findViewById(R.id.ageveryhigh);
        bc1 = findViewById(R.id.fourd);
        bc2 = findViewById(R.id.oned);
        bc3 = findViewById(R.id.twod);
        bc4 = findViewById(R.id.threed);

        title1 = findViewById(R.id.agetitleid);
        title2 = findViewById(R.id.diseaseid);
        t1 = findViewById(R.id.baby);
        t3 = findViewById(R.id.mid);
        t2 = findViewById(R.id.lowmid);
        t4 = findViewById(R.id.veryhigh);
        t5 = findViewById(R.id.d1);
        t6 = findViewById(R.id.d2);
        t7 = findViewById(R.id.d3);
        t8 = findViewById(R.id.d4);

        try {
            if (Locationservice.postalcode.equals("")) {
                new AlertDialog.Builder(this).setIcon(R.drawable.ic_info_black_24dp).setTitle("Please fill the following Details!")
                        .setMessage("Fill your details for ultimate protection").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(Homepage.this, Profilesettings.class);
                        startActivity(i);
                    }
                }).setNegativeButton("No", null).show();


            }
        }catch(Exception e) {
              e.printStackTrace();
        }






        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(user!=null) {
                    reference = FirebaseDatabase.getInstance().getReference("People").child(user.getUid());
                     reference.addValueEventListener(new ValueEventListener() {
                         @Override
                         public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                age=dataSnapshot.child("Age").getValue().toString();
                                complications=dataSnapshot.child("Complications").getValue().toString();

                         }

                         @Override
                         public void onCancelled(@NonNull DatabaseError databaseError) {

                         }
                     });

                }

                if (b1.isChecked()) {
                    healthproblems.put("Age", t1.getText().toString());
                    sharedprefrence.edit().putString("age", String.valueOf(3)).apply();
                    reference.updateChildren(healthproblems);

                }
                if (b2.isChecked()) {
                    healthproblems.put("Age", t2.getText().toString());
                    sharedprefrence.edit().putString("age", String.valueOf(19)).apply();
                    reference.updateChildren(healthproblems);
                }

                if (b3.isChecked()) {
                    healthproblems.put("Age", t3.getText().toString());
                    sharedprefrence.edit().putString("age", String.valueOf(45)).apply();
                    reference.updateChildren(healthproblems);

                }

                if (b4.isChecked()) {
                    healthproblems.put("Age", t4.getText().toString());
                    sharedprefrence.edit().putString("age", String.valueOf(70)).apply();
                    reference.updateChildren(healthproblems);
                }

                if (bc1.isChecked()) {
                    healthproblems.put("Complications", t5.getText().toString());
                    sharedprefrence.edit().putString("health", String.valueOf(50)).apply();
                    reference.updateChildren(healthproblems);
                }

                if (bc2.isChecked()) {
                    healthproblems.put("Complications", t6.getText().toString());
                    sharedprefrence.edit().putString("health", String.valueOf(50)).apply();
                    reference.updateChildren(healthproblems);
                }

                if (bc3.isChecked()) {
                    healthproblems.put("Complications", t7.getText().toString());
                    sharedprefrence.edit().putString("health", String.valueOf(90)).apply();
                    reference.updateChildren(healthproblems);
                }

                if (bc4.isChecked()) {
                    healthproblems.put("Complications", t8.getText().toString());
                    sharedprefrence.edit().putString("health", String.valueOf(70)).apply();
                    reference.updateChildren(healthproblems);
                }

                if (!complications.equals("")) {
                    b1.setVisibility(View.INVISIBLE);
                    b2.setVisibility(View.INVISIBLE);
                    b3.setVisibility(View.INVISIBLE);
                    b4.setVisibility(View.INVISIBLE);
                    title1.setVisibility(View.INVISIBLE);
                    t1.setVisibility(View.INVISIBLE);
                    t2.setVisibility(View.INVISIBLE);
                    t3.setVisibility(View.INVISIBLE);
                    t4.setVisibility(View.INVISIBLE);

                }
                if (!age.equals("")) {
                    bc1.setVisibility(View.INVISIBLE);
                    bc2.setVisibility(View.INVISIBLE);
                    bc3.setVisibility(View.INVISIBLE);
                    bc4.setVisibility(View.INVISIBLE);
                    t5.setVisibility(View.INVISIBLE);
                    t6.setVisibility(View.INVISIBLE);
                    t7.setVisibility(View.INVISIBLE);
                    t8.setVisibility(View.INVISIBLE);
                    title2.setVisibility(View.INVISIBLE);
                    b.setVisibility(View.INVISIBLE);
                }
                Toast.makeText(Homepage.this, "Thank you!", Toast.LENGTH_SHORT).show();

                 b.setText("Final Changes ?");

            }
        });

    }
}
