package proyecto.kotions;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import proyecto.kotions.BDD.BaseDatos;

public class LoginActivity extends AppCompatActivity {
    EditText usuario, contrasenya;
    Button inicio;
    BaseDatos DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usuario = findViewById(R.id.usuario1);
        contrasenya = findViewById(R.id.contrasenya1);
        inicio = findViewById(R.id.btninicio1);
        DB = new BaseDatos(this);

        inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usu = usuario.getText().toString();
                String con = contrasenya.getText().toString();
                if(usuario.equals("")|| contrasenya.equals(""))
                    Toast.makeText(LoginActivity.this,"Por favor in",Toast.LENGTH_SHORT).show();
                else {
                    Boolean comprobarUsuCon = DB.comprobarUsuarioContrasenya(usu, con);
                    if (comprobarUsuCon == true) {
                        Toast.makeText(LoginActivity.this, "Datos Correctos", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), ActividadHome.class);
                        startActivity(intent);

                    }else {
                        Toast.makeText(LoginActivity.this, "Datos incorrectos", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}