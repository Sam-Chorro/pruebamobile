package com.example.prueba_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText edit1, edit2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit1= findViewById(R.id.inp1);
        edit2= findViewById(R.id.inp2);

    }

    public void created(View view) {
        Intent login = new Intent(MainActivity.this, singup.class);
        startActivity(login);
    }


    public void start(View view) {


        String email = edit1.getText().toString();
        String password = edit2.getText().toString();
        validateEmail(edit1);

        if (!email.isEmpty() && !password.isEmpty()) {
            AdminBase admin = new AdminBase(this, "administracion", null,1);
            SQLiteDatabase base = admin.getReadableDatabase();

            Cursor register = base.query
                    ("users", new String[]{"first", "last", "email", "password"},
                     "email like '" + email + "'" + "and password like '" + password + "'",
                            null, null, null, null);

            if (register.getCount() > 0){
                Intent home = new Intent(MainActivity.this, com.example.prueba_mobile.home.class);
                startActivity(home);
            }else{
                Toast.makeText(this, "Este Usuario no existe", Toast.LENGTH_SHORT).show();
            }



        }else{
            if (email.isEmpty()){
                edit1.setError("Campo Obligatorio");
            }else if (password.isEmpty()){
                edit2.setError("Campo Obligatorio");
            }

            Toast.makeText(this, "Los Campos no pueden quedar vacios", Toast.LENGTH_LONG).show();
        }


    }



    public void recordar(View view) {
        Toast.makeText(this, "Esta opcion sera habilitada en los proximos dias", Toast.LENGTH_SHORT).show();
    }

    private boolean validateEmail (EditText email){
        String mail = email.getText().toString();

        if (!mail.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(mail).matches()){

            return true;
        }else{

            edit1.setText("");
            edit1.setError("Correo Invalido");
            Toast.makeText(this, "Correo Invalido", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

}