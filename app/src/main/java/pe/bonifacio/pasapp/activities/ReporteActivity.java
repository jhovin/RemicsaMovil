package pe.bonifacio.pasapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import pe.bonifacio.pasapp.R;

public class ReporteActivity extends AppCompatActivity {

    private EditText etSistema;
    private EditText etMotivo;
    private EditText etEvento;
    private EditText etDescripcion;
    private EditText etEstado;
    private EditText etObservacion;
    private EditText etFechaReporte;
    private EditText etHorometroReporte;
    private EditText etOt;
    private Button btnReporte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reporte);

        ingreso();
    }

    public void ingreso(){

        etSistema=findViewById(R.id.etSistema);
        etDescripcion=findViewById(R.id.etDescripcion);
        etMotivo=findViewById(R.id.etMotivo);
        etOt=findViewById(R.id.etOt);
        etHorometroReporte=findViewById(R.id.etHorometro_reporte);
        etEvento=findViewById(R.id.etEvento);
        etFechaReporte=findViewById(R.id.etFecha_reporte);
        etEstado=findViewById(R.id.etEstado);
        etObservacion=findViewById(R.id.etObservacion);
        btnReporte=findViewById(R.id.btn_reporte);
        btnReporte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarReporte();
            }
        });
    }
    public void registrarReporte(){






    }

}
