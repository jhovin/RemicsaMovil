package pe.bonifacio.pasapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import pe.bonifacio.pasapp.R;
import pe.bonifacio.pasapp.models.Superficie;

public class SuperficiesAdapter extends RecyclerView.Adapter<SuperficiesAdapter.ViewHolder> {

    private static final String TAG=SuperficiesAdapter.class.getSimpleName();

    private List<Superficie> superficies;

    public SuperficiesAdapter(){
        this.superficies=new ArrayList<>();
    }

    public void setSuperficies(List<Superficie>superficies){
        this.superficies=superficies;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView nombreText;


        ViewHolder(View itemView) {
            super(itemView);
            nombreText = itemView.findViewById(R.id.nombre_superficie_text);

        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_superficie, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SuperficiesAdapter.ViewHolder viewHolder, int position) {
        final Context context = viewHolder.itemView.getContext();

        final Superficie superficie = this.superficies.get(position);

        viewHolder.nombreText.setText(superficie.getNombre_sup());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

    }


    @Override
    public int getItemCount() {
        return this.superficies.size();
    }
}
