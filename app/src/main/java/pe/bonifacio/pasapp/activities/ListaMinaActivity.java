package pe.bonifacio.pasapp.activities;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import pe.bonifacio.pasapp.R;
import pe.bonifacio.pasapp.adapters.MinasAdapter;
import pe.bonifacio.pasapp.api.WebServiceApi;
import pe.bonifacio.pasapp.models.ApiServiceGenerator;
import pe.bonifacio.pasapp.models.Mina;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListaMinaActivity extends AppCompatActivity {

    private static final String TAG=ListaMinaActivity.class.getSimpleName();
    private RecyclerView minasList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_mina);

        minasList = findViewById(R.id.recyclerview);
        minasList.setLayoutManager(new LinearLayoutManager(this));

        minasList.setAdapter(new MinasAdapter());

        initialize();
    }
    public  void initialize(){

        WebServiceApi service = ApiServiceGenerator.createService(WebServiceApi.class);

        service.getTodosLosMinas().enqueue(new Callback<List<Mina>>() {

            @Override
            public void onResponse(@NonNull Call<List<Mina>> call, @NonNull Response<List<Mina>> response) {
                try {

                    if (response.isSuccessful()) {

                        List<Mina> minas = response.body();
                        Log.d(TAG, "minas: " + minas);

                        MinasAdapter adapter = (MinasAdapter) minasList.getAdapter();
                        adapter.setMinas(minas);
                        adapter.notifyDataSetChanged();
                        Toast.makeText(ListaMinaActivity.this, "Lista de Maquinas Interior Mina", Toast.LENGTH_SHORT).show();
                    } else {
                        throw new Exception(ApiServiceGenerator.parseError(response).getMessage());
                    }

                } catch (Throwable t) {
                    Log.e(TAG, "onThrowable: " + t.getMessage(), t);
                    Toast.makeText(ListaMinaActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Mina>> call, @NonNull Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage(), t);
                Toast.makeText(ListaMinaActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }

        });
    }

}


