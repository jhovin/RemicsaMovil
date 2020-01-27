package pe.bonifacio.pasapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import pe.bonifacio.pasapp.R;

public class TipoActivity extends AppCompatActivity {

    private ImageView mina;
    private  ImageView superficie;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo);

        mina=findViewById(R.id.MaquinaMina);
        superficie=findViewById(R.id.MaquinaSuperficie);
        mina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verMina();
            }
        });
        superficie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verSuperficie();
            }
        });
    }
    public void verMina(){
        startActivity(new Intent(getApplicationContext(),MinaActivity.class));
    }
    public void  verSuperficie(){
        startActivity(new Intent(getApplicationContext(),SuperficieActivity.class));
    }
}
