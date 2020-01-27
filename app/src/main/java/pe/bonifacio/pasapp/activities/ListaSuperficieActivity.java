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
import pe.bonifacio.pasapp.adapters.SuperficiesAdapter;
import pe.bonifacio.pasapp.api.WebServiceApi;
import pe.bonifacio.pasapp.models.ApiServiceGenerator;
import pe.bonifacio.pasapp.models.Superficie;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListaSuperficieActivity extends AppCompatActivity {

    private static final String TAG=ListaProyectoActivity.class.getSimpleName();
    private RecyclerView superficieList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_superficie);

        superficieList=findViewById(R.id.recyclerview);
        superficieList.setLayoutManager(new LinearLayoutManager(this));

        superficieList.setAdapter(new SuperficiesAdapter());

        initializable();
    }
    public void initializable(){

        WebServiceApi service = ApiServiceGenerator.createService(WebServiceApi.class);

        service.getTodosLosSuperficies().enqueue(new Callback<List<Superficie>>() {

            @Override
            public void onResponse(@NonNull Call<List<Superficie>> call, @NonNull Response<List<Superficie>> response) {
                try {

                    if (response.isSuccessful()) {

                        List<Superficie> superficies = response.body();
                        Log.d(TAG, "superficies: " + superficies);

                        SuperficiesAdapter adapter = (SuperficiesAdapter) superficieList.getAdapter();
                        adapter.setSuperficies(superficies);
                        adapter.notifyDataSetChanged();
                        Toast.makeText(ListaSuperficieActivity.this, "Lista de Máquinas de Superficies", Toast.LENGTH_SHORT).show();
                    } else {
                        throw new Exception(ApiServiceGenerator.parseError(response).getMessage());
                    }

                } catch (Throwable t) {
                    Log.e(TAG, "onThrowable: " + t.getMessage(), t);
                    Toast.makeText(ListaSuperficieActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Superficie>> call, @NonNull Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage(), t);
                Toast.makeText(ListaSuperficieActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }

        });
    }

}
