package pe.bonifacio.pasapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import pe.bonifacio.pasapp.R;
import pe.bonifacio.pasapp.api.WebService;
import pe.bonifacio.pasapp.api.WebServiceApi;
import pe.bonifacio.pasapp.models.Mina;
import pe.bonifacio.pasapp.models.Proyecto;
import pe.bonifacio.pasapp.models.Usuario;
import pe.bonifacio.pasapp.shared_pref.SharedPrefManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MinaActivity extends AppCompatActivity {


    private EditText etFecha;
    private EditText etMina;
    private EditText etPlaca;
    private EditText etMotor;
    private EditText etHorometro;
    private EditText etObservacion;
    private Button btCrearMaquina;
    private Button btVerMinasProyecto;
    private Button btVerTodosMinas;

    private Usuario usuario;
    private Proyecto proyecto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mina);

        mina();
    }
    public void mina(){

        usuario=SharedPrefManager.getInstance(getApplicationContext()).getUsuario();
        etFecha=findViewById(R.id.etFecha);
        etMina=findViewById(R.id.etMina);
        etPlaca=findViewById(R.id.etPlaca);
        etMotor=findViewById(R.id.etMotor);
        etHorometro=findViewById(R.id.etHorometro);
        etObservacion=findViewById(R.id.etObservacion);

        btVerTodosMinas=findViewById(R.id.btVerTodosMinas);
        btVerTodosMinas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verTodosLosMinas();
                startActivity(new Intent(getApplicationContext(),ListaMinaActivity.class));
            }
        });
        btVerMinasProyecto=findViewById(R.id.btVerMinasProyecto);
        btVerMinasProyecto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verMinasPorProyecto();

            }
        });
        btCrearMaquina=findViewById(R.id.btCrearMaquina);
        btCrearMaquina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crearMina();
            }
        });

    }
    //Crear Maquina Mina
    public void crearMina(){
        Mina mina = new Mina();
        mina.setFecha_inicio(etFecha.getText().toString());
        mina.setNombre_min(etMina.getText().toString().toUpperCase());
        mina.setPlaca(etPlaca.getText().toString());
        mina.setSerie_motor(etMotor.getText().toString());
        mina.setLectura_horometro(etHorometro.getText().toString());
        mina.setObservacion(etObservacion.getText().toString());
        mina.setMinproyecto(usuario.getId());

        Call<Void>call=WebService
                .getInstance()
                .createService(WebServiceApi.class)
                .crearMina(mina);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.code() == 201) {
                    Log.d("TAG1", "Mina creado correctamente");
                    Toast.makeText(MinaActivity.this, "Máquina de Interior Mina creado", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),ListaMinaActivity.class));

                }

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    //Ver lista de todas maquinas minas
    public void verTodosLosMinas(){
        Call<List<Mina>> call = WebService
                .getInstance()
                .createService(WebServiceApi.class)
                .getTodosLosMinas();

        call.enqueue(new Callback<List<Mina>>() {
            @Override
            public void onResponse(Call<List<Mina>> call, Response<List<Mina>> response) {
                if(response.code()==200){
                    for(int i=0; i<response.body().size(); i++){
                        Log.d("TAG1", "Nombre Mina: " + response.body().get(i).getNombre_min()
                                + "Codigo Mina: " + response.body().get(i).getMinproyecto());
                    }
                }else if(response.code()==404){
                    Log.d("TAG1", "No hay maquinas interior mina");
                }
            }

            @Override
            public void onFailure(Call<List<Mina>> call, Throwable t) {

            }
        });

    }
    //Ver solo maquinas mina por Proyecto

    public void verMinasPorProyecto(){
        Call<List<Mina>> call = WebService
                .getInstance()
                .createService(WebServiceApi.class)
                .getMinasProyectos(proyecto);
        call.enqueue(new Callback<List<Mina>>() {
            @Override
            public void onResponse(Call<List<Mina>> call, Response<List<Mina>> response) {
                if(response.code()==200){
                    for(int i=0; i<response.body().size(); i++){
                        Log.d("TAG1", "Nombre Mina: " + response.body().get(i).getNombre_min()
                                + "  Codigo Proyecto: " + response.body().get(i).getMinproyecto());
                    }
                }else if(response.code()==404){
                    Log.d("TAG1", "No hay proyectos");
                }
            }
            @Override
            public void onFailure(Call<List<Mina>> call, Throwable t) {

            }
        });
    }

}
