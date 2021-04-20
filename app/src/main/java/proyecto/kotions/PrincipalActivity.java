package proyecto.kotions;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.VectorEnabledTintResources;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import proyecto.kotions.BDD.BaseDatos;

public class PrincipalActivity extends AppCompatActivity {
    EditText username, password, repassword;
    Button signup,signin;
    BaseDatos DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        username = findViewById(R.id.usuario);
        password = findViewById(R.id.contrasenya);
        repassword = findViewById(R.id.contrasenya2);

        signup = findViewById(R.id.btnregistro);
        signin = findViewById(R.id.btninicio);

        DB = new BaseDatos(this);

        signup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view){

                String usuario = username.getText().toString();
                String contr = password.getText().toString();
                String recont = repassword.getText().toString();

                if(usuario.equals("")|| contr.equals("")|| recont.equals(""))
                    Toast.makeText(PrincipalActivity.this,"Los campos no pueden estar vacíos",Toast.LENGTH_SHORT).show();
                else{
                    if(contr.equals(recont)){//comprueba si la contraseña coincide
                        Boolean compusuario = DB.comprobarNombre(usuario);
                        if(compusuario == false){
                            Boolean insert = DB.insertarDatos(usuario,contr);
                            if(insert == true){
                                Toast.makeText(PrincipalActivity.this, "Usuario Registrado", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),ActividadHome.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(PrincipalActivity.this,"No se ha podido Registrar el usuario",Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(PrincipalActivity.this,"Usuario ya existe, inicie sesión",Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(PrincipalActivity.this,"Contraseña no coincide",Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                    startActivity(intent);
            }
        });

    }
}