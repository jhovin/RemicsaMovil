package pe.bonifacio.pasapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import pe.bonifacio.pasapp.R;
import pe.bonifacio.pasapp.activities.ReporteActivity;
import pe.bonifacio.pasapp.models.Mina;

public class MinasAdapter extends RecyclerView.Adapter<MinasAdapter.ViewHolder> {

    private static final String TAG = MinasAdapter.class.getSimpleName();

    private List<Mina> minas;

    public MinasAdapter(){
        this.minas = new ArrayList<>();
    }

    public void setMinas(List<Mina> minas){
        this.minas = minas;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView nombreText;

        ViewHolder(View itemView) {
            super(itemView);
            nombreText = itemView.findViewById(R.id.nombre_mina_text);

        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mina, parent, false);
        return new ViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        final Context context = viewHolder.itemView.getContext();

        final Mina mina = this.minas.get(position);

        viewHolder.nombreText.setText(mina.getNombre_min());


        final Mina min = this.minas.get(position);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ReporteActivity.class);
                intent.putExtra("id", min.getMina_id());
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return this.minas.size();
    }
}

