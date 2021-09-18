package com.example.prueba_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.icu.text.Transliterator;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

public class singup extends AppCompatActivity {

    private EditText first, last, email, pass, confirmpass;
    private Button crear;
    private TextView lb1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singup);


        first = findViewById(R.id.inpfirst);
        last = findViewById(R.id.inplast);
        email = findViewById(R.id.inpmail);
        pass = findViewById(R.id.inpPass);
        confirmpass = findViewById(R.id.inpconfirmpass);
        lb1 = findViewById(R.id.p3);

        String lb = lb1.getText().toString();

        SpannableString color = new SpannableString(lb);

        ForegroundColorSpan blue = new ForegroundColorSpan(Color.BLUE);
        ForegroundColorSpan blue1 = new ForegroundColorSpan(Color.BLUE);

        color.setSpan(blue, 8, 21, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        color.setSpan(blue1, 35, 55, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);

        lb1.setText(color);


    }


    public void backsing(View view) {
        Intent back = new Intent(singup.this, MainActivity.class);
        startActivity(back);
    }


    public void createAccount(View view) {
        AdminBase admin = new AdminBase(this, "administracion", null, 1);
        SQLiteDatabase base = admin.getWritableDatabase();

        String nombre = first.getText().toString();
        String apellido = last.getText().toString();
        String mail = email.getText().toString();
        String contrasena = pass.getText().toString();
        String confirm = confirmpass.getText().toString();

        validateEmail(email);

        if (!nombre.isEmpty() && !apellido.isEmpty() && !mail.isEmpty() && !contrasena.isEmpty() && !confirm.isEmpty() && contrasena.equals(confirm)) {

            ContentValues registro = new ContentValues();

            registro.put("first", nombre);
            registro.put("last", apellido);
            registro.put("email", mail);
            registro.put("password", contrasena);

            base.insert("users", null, registro);
            base.close();

            first.setText("");
            last.setText("");
            email.setText("");
            pass.setText("");
            confirmpass.setText("");

            Intent noti = new Intent(singup.this, notification.class);
            startActivity(noti);

        } else {
            if (first.length() == 0) {
                first.setError("Campo Obligatorio");
            } else if (last.length() == 0) {
                last.setError("Campo Obligatorio");
            } else if (email.length() == 0) {
                email.setError("Campo Obligatorio");
            } else if (pass.length() == 0) {
                pass.setError("Campo Obligatorio");
            } else if (confirmpass.length() == 0) {
                confirmpass.setError("Campo Obligatorio");
            }else{
                pass.setError("Las contrasenas deben coincidir");
                confirmpass.setError("Las contrasenas deben coincidir");
            }
        }

    }

    private boolean validateEmail (EditText email){
        String mail = email.getText().toString();

        if (!mail.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(mail).matches()){
            return true;
        }else{
            email.setText("");
            email.setError("Correo Invalido");
            Toast.makeText(this, "Correo Invalido", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public void terms(View view) {
        Toast.makeText(singup.this, "Los terminos y condiciones estaran en los proximos dias!", Toast.LENGTH_LONG).show();
    }
}