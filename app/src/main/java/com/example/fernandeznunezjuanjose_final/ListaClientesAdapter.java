package com.example.fernandeznunezjuanjose_final;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fernandeznunezjuanjose_final.BaseDatos.Clientes;

import java.util.ArrayList;

public class ListaClientesAdapter extends RecyclerView.Adapter<ListaClientesAdapter.ClienteViewHolder> {

    ArrayList <Clientes> listaClientes;

    public ListaClientesAdapter(ArrayList<Clientes> listaClientes){
        this.listaClientes = listaClientes;
    }

    @NonNull
    @Override
    public ClienteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_clientes, null, false);
        return new ClienteViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull ClienteViewHolder holder, int position) {
        holder.viewNombre.setText(listaClientes.get(position).getNombre());
       // holder.viewDNI.setText("DNI: "+listaClientes.get(position).getDni());
       // holder.viewFechaNac.setText("Fecha de Nacimiento: " +listaClientes.get(position).getFechaNac());

    }

    @Override
    public int getItemCount() {
        return listaClientes.size();
    }

    public class ClienteViewHolder extends RecyclerView.ViewHolder {
        TextView viewNombre, viewDNI, viewFechaNac, viewEdad;
        public ClienteViewHolder(@NonNull View itemView) {
            super(itemView);

            viewNombre = itemView.findViewById(R.id.viewNombre);
            /*viewDNI = itemView.findViewById(R.id.viewDNI);
            viewFechaNac = itemView.findViewById(R.id.viewFechaNac);
            viewEdad = itemView.findViewById(R.id.viewEdad);*/

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, VisualizarActivity.class);
                    intent.putExtra("ID", listaClientes.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });

        }
    }
}
