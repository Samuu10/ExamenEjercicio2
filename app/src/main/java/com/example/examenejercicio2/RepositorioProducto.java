package com.example.examenejercicio2;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

//Clase RepositorioProducto para gestionar los productos
public class RepositorioProducto {

    //Variables
    private static final String PREFS_NAME = "productos_prefs";
    private static final String KEY_PRODUCTOS = "productos";
    private List<Producto> productos;
    private SharedPreferences sharedPreferences;
    private Gson gson;

    //Constructor
    public RepositorioProducto(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        gson = new Gson();
        productos = cargarProductos();
    }

    //Método para añadir un producto a la lista de productos
    public void añadirProducto(Producto producto) {
        productos.add(producto);
        guardarProductos();
    }

    //Método para obtener la lista de productos
    public List<Producto> obtenerProductos() {
        return productos;
    }

    //Método para eliminar un producto de la lista de productos
    public void eliminarProducto(Producto producto) {
        productos.remove(producto);
        guardarProductos();
    }

    //Método para obtener la cantidad de productos
    public int obtenerNumeroDeProductos() {
        return productos.size();
    }

    //Método para obtener el precio total de los productos
    public double obtenerPrecioTotal() {
        double total = 0;
        for (Producto producto : productos) {
            int cantidad = producto.getCantidad() == 0 ? 1 : producto.getCantidad();
            total += producto.getPrecio() * cantidad;
        }
        return total;
    }

    //Método para guardar los productos en SharedPreferences
    private void guardarProductos() {
        String json = gson.toJson(productos);
        sharedPreferences.edit().putString(KEY_PRODUCTOS, json).apply();
    }

    //Método para cargar los productos guardados en SharedPreferences
    private List<Producto> cargarProductos() {
        String json = sharedPreferences.getString(KEY_PRODUCTOS, null);
        if (json == null) {
            return new ArrayList<>();
        }
        Type type = new TypeToken<List<Producto>>() {}.getType();
        return gson.fromJson(json, type);
    }
}