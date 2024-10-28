package com.example.examenejercicio2;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

//Clase que representa la actividad principal de la aplicación
public class ActividadPrincipal extends AppCompatActivity {

    //Variables
    private RepositorioProducto repositorioProducto;
    private AdaptadorProducto adaptadorProducto;
    private TextView tvTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_principal);

        repositorioProducto = new RepositorioProducto();
        tvTotal = findViewById(R.id.total);
        Button botonAñadirProducto = findViewById(R.id.boton_añadir_producto);

        RecyclerView recyclerView = findViewById(R.id.recycler_view_tareas_hechas);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adaptadorProducto = new AdaptadorProducto(repositorioProducto.obtenerProductos(), this, repositorioProducto, tvTotal);
        recyclerView.setAdapter(adaptadorProducto);

        botonAñadirProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarDialogoAgregarProducto();
            }
        });
    }

    private void mostrarDialogoAgregarProducto() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialogo_agregar_producto, null);
        builder.setView(dialogView);
        builder.setTitle("Añadir Producto");

        EditText etNombre = dialogView.findViewById(R.id.etNombre);
        EditText etCantidad = dialogView.findViewById(R.id.etCantidad);
        EditText etPrecio = dialogView.findViewById(R.id.etPrecio);

        builder.setPositiveButton("Añadir", null);
        builder.setNegativeButton("Cancelar", (dialog, which) -> dialog.dismiss());

        AlertDialog dialog = builder.create();
        dialog.show();

        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(v -> {
            String nombre = etNombre.getText().toString().trim();
            if (nombre.isEmpty()) {
                Toast.makeText(this, "Debes introducir el nombre del producto", Toast.LENGTH_SHORT).show();
                return;
            }

            int cantidad = etCantidad.getText().toString().isEmpty() ? 1 : Integer.parseInt(etCantidad.getText().toString());
            double precio = etPrecio.getText().toString().isEmpty() ? 0 : Double.parseDouble(etPrecio.getText().toString());

            Producto producto = new Producto(nombre, cantidad, precio);
            repositorioProducto.añadirProducto(producto);
            adaptadorProducto.notifyDataSetChanged();
            actualizarTotal();
            dialog.dismiss();
        });
    }

    private void actualizarTotal() {
        int numeroDeProductos = repositorioProducto.obtenerNumeroDeProductos();
        double precioTotal = repositorioProducto.obtenerPrecioTotal();
        tvTotal.setText("Productos: " + numeroDeProductos + " - Total: " + precioTotal + "€");
    }
}