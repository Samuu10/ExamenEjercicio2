package com.example.examenejercicio2;

import java.util.ArrayList;
import java.util.List;

//Clase RepositorioProducto para gestionar los productos
public class RepositorioProducto {

    //Variables
    private List<Producto> productos = new ArrayList<>();

    //Método para añadir un producto a la lista de productos
    public void añadirProducto(Producto producto) {
        productos.add(producto);
    }

    //Método para obtener la lista de productos
    public List<Producto> obtenerProductos() {
        return productos;
    }

    //Método para eliminar un producto de la lista de productos
    public void eliminarProducto(Producto producto) {
        productos.remove(producto);
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
}
