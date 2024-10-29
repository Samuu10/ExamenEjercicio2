# Aplicación de Lista de Compras

## Objetivo

La aplicación de Lista de Compras está diseñada para ayudar a los usuarios a gestionar sus listas de compras de manera eficiente.  
Los usuarios pueden agregar, ver y eliminar productos de su lista de compras.  
La aplicación también guarda la lista de productos utilizando `SharedPreferences`, asegurando que la lista persista incluso después de cerrar la aplicación o reiniciar el dispositivo.

## Descripción de Clases Java

### `ActividadPrincipal.java`

La actividad principal de la aplicación. Inicializa los componentes de la interfaz de usuario, maneja la adición y eliminación de productos, actualiza el número total de productos y el precio total, y utiliza `RepositorioProducto` para gestionar la lista de productos.

### `AdaptadorProducto.java`

Adaptador para gestionar la lista de productos en un `RecyclerView`. Asigna los datos del producto a los elementos de la interfaz de usuario en cada ítem de la lista y maneja eventos de clic en los ítems para desencadenar la eliminación de productos.

### `Producto.java`

Representa un producto con un nombre, cantidad y precio. Contiene los atributos `nombre`, `cantidad` y `precio` con sus respectivos getters y setters.

### `RepositorioProducto.java`

Gestiona la lista de productos y maneja la persistencia utilizando `SharedPreferences`. Añade y elimina productos de la lista, guarda y carga la lista de productos desde `SharedPreferences`, y calcula el número total de productos y el precio total.

## Descripción de Archivos XML

- `actividad_principal.xml`: Diseño para la actividad principal.
- `dialogo_agregar_producto.xml`: Diseño para el diálogo de agregar un nuevo producto.
- `item_producto.xml`: Diseño para cada ítem en la lista de productos.

### Link al repositorio: https://github.com/Samuu10/ExamenEjercicio2.git
