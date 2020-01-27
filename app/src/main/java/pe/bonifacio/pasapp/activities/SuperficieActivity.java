package pe.bonifacio.pasapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import pe.bonifacio.pasapp.R;
import pe.bonifacio.pasapp.api.WebService;
import pe.bonifacio.pasapp.api.WebServiceApi;
import pe.bonifacio.pasapp.models.Superficie;
import pe.bonifacio.pasapp.models.Usuario;
import pe.bonifacio.pasapp.shared_pref.SharedPrefManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SuperficieActivity extends AppCompatActivity {


    private EditText etFecha;
    private EditText etSuperficie;
    private EditText etPlaca;
    private EditText etMotor;
    private EditText etHorometro;
    private EditText etObservacion;
    private Button btCrearMaquina;
    private Button btVerTodosSuperficies;

    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_superficie);

        superficie();
    }
    public void superficie(){

        usuario= SharedPrefManager.getInstance(getApplicationContext()).getUsuario();
        etFecha=findViewById(R.id.etFecha);
        etSuperficie=findViewById(R.id.etSuperficie);
        etPlaca=findViewById(R.id.etPlaca);
        etMotor=findViewById(R.id.etMotor);
        etHorometro=findViewById(R.id.etHorometro);
        etObservacion=findViewById(R.id.etObservacion);
        btCrearMaquina=findViewById(R.id.btCrearMaquina);
        btCrearMaquina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crearMaquinaSuperficie();
            }
        });
        btVerTodosSuperficies=findViewById(R.id.btVerTodosSuperficies);
        btVerTodosSuperficies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verTodosLosSuperficies();
                startActivity(new Intent(getApplicationContext(),ListaSuperficieActivity.class));
            }
        });
    }
    //Crear Maquina Superficie
    public void crearMaquinaSuperficie(){
        Superficie superficie=new Superficie();
        superficie.setFecha_inicio(etFecha.getText().toString());
        superficie.setNombre_sup(etSuperficie.getText().toString());
        superficie.setPlaca(etPlaca.getText().toString());
        superficie.setLectura_horometro(etHorometro.getText().toString());
        superficie.setSerie_motor(etMotor.getText().toString());
        superficie.setObservacion(etObservacion.getText().toString());
        superficie.setSupproyecto(usuario.getId());
        Call<Void> call = WebService
                .getInstance()
                .createService(WebServiceApi.class)
                .crearSuperficie(superficie);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.code()==201){
                    Log.d("TAG1", "Máquina de Superficie creado correctamente");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }
    //Ver todos las Superficies
    public void verTodosLosSuperficies(){

        Call<List<Superficie>> call = WebService
                .getInstance()
                .createService(WebServiceApi.class)
                .getTodosLosSuperficies();

        call.enqueue(new Callback<List<Superficie>>() {
            @Override
            public void onResponse(Call<List<Superficie>> call, Response<List<Superficie>> response) {
                if(response.code()==200){
                    for(int i=0; i<response.body().size(); i++){
                        Log.d("TAG1", "Nombre Superficie: " + response.body().get(i).getSuperficie_id()
                                + "Codigo Superficie: " + response.body().get(i).getSupproyecto());
                    }
                }else if(response.code()==404){
                    Log.d("TAG1", "No hay Máquinas de Superficie");
                }
            }

            @Override
            public void onFailure(Call<List<Superficie>> call, Throwable t) {

            }
        });

    }
}
