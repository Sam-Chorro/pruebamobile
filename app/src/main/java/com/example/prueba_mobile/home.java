package com.example.prueba_mobile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

    }

    public void market(View view) {
        Toast.makeText(this, "Esta opcion estara habilitada en los proximos dias", Toast.LENGTH_SHORT).show();
    }

    public void jobs(View view) {

        Toast.makeText(this, "Esta opcion estara habilitada en los proximos dias", Toast.LENGTH_SHORT).show();

    }

    public void setting(View view) {

        AlertDialog.Builder cerrar = new AlertDialog.Builder(home.this);
        cerrar.setIcon(android.R.drawable.ic_dialog_alert);
        cerrar.setTitle("Cerrar Secion");
        cerrar.setMessage("Seguro que quieres cerrar secion?");
        cerrar.setCancelable(false);
        cerrar.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
                Intent login = new Intent(home.this, MainActivity.class);
                startActivity(login);
            }
        });

        cerrar.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).show();


    }

    public void profile(View view) {
        Toast.makeText(this, "Esta opcion estara habilitada en los proximos dias", Toast.LENGTH_SHORT).show();
    }
}