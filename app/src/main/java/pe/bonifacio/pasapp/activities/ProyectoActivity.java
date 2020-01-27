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
import pe.bonifacio.pasapp.models.Proyecto;
import pe.bonifacio.pasapp.models.Usuario;
import pe.bonifacio.pasapp.shared_pref.SharedPrefManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProyectoActivity extends AppCompatActivity {

    private EditText etProyecto;
    private EditText etGerente;
    private EditText etCliente;
    private EditText etDistrito;
    private EditText etProvincia;
    private EditText etDepartamento;
    private EditText etTelefono;

    private Button  btCrearProyecto;
    private Button btVerTodosProyecto;
    private Button btVerProyectosUsuario;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proyecto);

        setUpView();

    }
    public void setUpView() {
        usuario = SharedPrefManager.getInstance(getApplicationContext()).getUsuario();
        etProyecto = findViewById(R.id.etProyecto);
        etGerente=findViewById(R.id.etGerente);
        etCliente=findViewById(R.id.etCliente);
        etDistrito=findViewById(R.id.etDistrito);
        etProvincia=findViewById(R.id.etProvincia);
        etDepartamento=findViewById(R.id.etDepartamento);
        etTelefono=findViewById(R.id.etTelefono);
        btCrearProyecto = findViewById(R.id.btCrearProyecto);
        btCrearProyecto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crearProyecto();
            }
        });

        btVerTodosProyecto = findViewById(R.id.btVerTodosProyectos);
        btVerTodosProyecto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verTodosLosProyectos();
                startActivity(new Intent(getApplicationContext(),ListaProyectoActivity.class));
            }
        });
        btVerProyectosUsuario=findViewById(R.id.btVerProyectosUsuario);
        btVerProyectosUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verProyectosPorUsuario();
            }
        });

    }
    //Crear Proyecto
    public void crearProyecto(){
        Proyecto proyecto=new Proyecto();
        proyecto.setNombre(etProyecto.getText().toString().toUpperCase().trim());
        proyecto.setGerente(etGerente.getText().toString().trim());
        proyecto.setCliente(etCliente.getText().toString().trim().toUpperCase());
        proyecto.setDistrito(etDistrito.getText().toString().trim().toUpperCase());
        proyecto.setProvincia(etProvincia.getText().toString().trim().toUpperCase());
        proyecto.setDepartamento(etDepartamento.getText().toString().trim().toUpperCase());
        proyecto.setTelefono(etTelefono.getText().toString().trim());
        proyecto.setProusuario(usuario.getId());

        Call<Void> call = WebService
                .getInstance()
                .createService(WebServiceApi.class)
                .crearProyecto(proyecto);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.code()==201){
                    Log.d("TAG1", "Proyecto creado correctamente");
                    startActivity(new Intent(getApplicationContext(),ListaProyectoActivity.class));
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }
    //Ver Todos los Proyectos
    public void verTodosLosProyectos(){

        Call<List<Proyecto>> call = WebService
                .getInstance()
                .createService(WebServiceApi.class)
                .getTodosLosProyectos();

        call.enqueue(new Callback<List<Proyecto>>() {
            @Override
            public void onResponse(Call<List<Proyecto>> call, Response<List<Proyecto>> response) {
                if(response.code()==200){
                    for(int i=0; i<response.body().size(); i++){
                        Log.d("TAG1", "Nombre Proyecto: " + response.body().get(i).getNombre()
                                + "Codigo Proyecto: " + response.body().get(i).getProusuario());
                    }
                }else if(response.code()==404){
                    Log.d("TAG1", "No hay proyectos");
                }
            }

            @Override
            public void onFailure(Call<List<Proyecto>> call, Throwable t) {

            }
        });


    }

    //Ver Proyecto por Usuarios
    public void verProyectosPorUsuario(){
        Call<List<Proyecto>> call = WebService
                .getInstance()
                .createService(WebServiceApi.class)
                .getProyectosUsuarios(usuario);
        call.enqueue(new Callback<List<Proyecto>>() {
            @Override
            public void onResponse(Call<List<Proyecto>> call, Response<List<Proyecto>> response) {
                if(response.code()==200){
                    for(int i=0; i<response.body().size(); i++){
                        Log.d("TAG1", "Nombre Proyecto: " + response.body().get(i).getNombre()
                                + "  Codigo Usuario: " + response.body().get(i).getProusuario());
                    }
                }else if(response.code()==404){
                    Log.d("TAG1", "No hay proyectos");
                }
            }
            @Override
            public void onFailure(Call<List<Proyecto>> call, Throwable t) {

            }
        });

    }

}
