package com.example.examenejercicio2;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

//Clase AdaptadorProducto para gestionar la lista de productos en un RecyclerView
public class AdaptadorProducto extends RecyclerView.Adapter<AdaptadorProducto.ProductoViewHolder> {

    //Variables
    private List<Producto> productos;
    private Context context;
    private RepositorioProducto repositorioProducto;
    private TextView tvTotal;

    //Constructor
    public AdaptadorProducto(List<Producto> productos, Context context, RepositorioProducto repositorioProducto, TextView tvTotal) {
        this.productos = productos;
        this.context = context;
        this.repositorioProducto = repositorioProducto;
        this.tvTotal = tvTotal;
    }

    //Método onCreateViewHolder para inflar el layout de cada item de la lista
    @NonNull
    @Override
    public ProductoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_producto, parent, false);
        return new ProductoViewHolder(view);
    }

    //Método onBindViewHolder para asignar los datos de cada producto a los elementos de la vista
    @Override
    public void onBindViewHolder(@NonNull ProductoViewHolder holder, int position) {
        Producto producto = productos.get(position);
        int cantidad = producto.getCantidad() == 0 ? 1 : producto.getCantidad();
        holder.tvNombreProducto.setText(producto.getNombre());
        holder.tvCantidad.setText("x" + cantidad + "  -");
        holder.tvPrecio.setText(producto.getPrecio() * cantidad + "€");

        holder.itemView.setOnClickListener(v -> mostrarDialogoEliminarProducto(producto, position));
    }

    //Método getItemCount para obtener la cantidad de productos en la lista
    @Override
    public int getItemCount() {
        return productos.size();
    }

    //Clase ProductoViewHolder para representar los elementos de la vista
    public static class ProductoViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombreProducto, tvCantidad, tvPrecio;

        public ProductoViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombreProducto = itemView.findViewById(R.id.tvNombreProducto);
            tvCantidad = itemView.findViewById(R.id.tvCantidad);
            tvPrecio = itemView.findViewById(R.id.tvPrecio);
        }
    }

    //Método para mostrar el diálogo de confirmación de eliminación
    private void mostrarDialogoEliminarProducto(Producto producto, int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Eliminar Producto");
        builder.setMessage("¿Estás seguro de que deseas eliminar este producto?");
        builder.setPositiveButton("Eliminar", (dialog, which) -> {
            repositorioProducto.eliminarProducto(producto);
            productos.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, productos.size());
            actualizarTotal();
        });
        builder.setNegativeButton("Cancelar", (dialog, which) -> dialog.dismiss());
        builder.create().show();
    }

    //Método para actualizar el total de productos y precio
    private void actualizarTotal() {
        int numeroDeProductos = repositorioProducto.obtenerNumeroDeProductos();
        double precioTotal = repositorioProducto.obtenerPrecioTotal();
        tvTotal.setText("Productos: " + numeroDeProductos + " - Total: " + precioTotal + "€");
    }
}