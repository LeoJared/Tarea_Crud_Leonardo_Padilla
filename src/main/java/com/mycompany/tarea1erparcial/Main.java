/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tarea1erparcial;

import java.util.Scanner;
import java.util.ArrayList;

import com.pmp.dao.Producto;
import com.pmp.dao.ProductosModel;

/**
 *
 * @author leona
 */
public class Main {
    
    private static Scanner entradaTeclado = new Scanner(System.in);
    private static ProductosModel model = new ProductosModel();
    
    public static void main ( String [] args ){
        
        Utilidades.encabezado("Gestión de Productos Cafeteria");
        String menuOption = "L";
        
        while (!menuOption.endsWith("S")){
            switch(menuOption){
                case "L":
                    Utilidades.print("LISTADO");
                    mostrarListado();
                    break;
                case "I":
                    insertarUnProducto();
                    break;
                case "A":
                    actualizarProducto();
                    break;
                case "E":
                    eliminarProducto();
                    break;
                default:
                    Utilidades.print("Opción no encontrada!");
            }
            Utilidades.menu();
            menuOption = entradaTeclado.nextLine().toUpperCase();
        }
        Utilidades.menu();
    }
    
    private static void mostrarListado() {
        
        Utilidades.printEncabezadoTabla();
        ArrayList<Producto> productos = model.obtenerProductos();
        for (int i = 0; i < productos.size(); i++) {
            Utilidades.print(productos.get(i).getRow());
            Utilidades.separador();
        }
    }
    
    private static void insertarUnProducto(){
        
        Producto nuevoProducto = new Producto();
        Utilidades.encabezado("Nuevo Producto");
        nuevoProducto.setNombre(Utilidades.capturarCampo(entradaTeclado, "Nombre","Producto XYZ"));
        nuevoProducto.setPrecio(Double.parseDouble(Utilidades.capturarCampo(entradaTeclado, "Precio","100.00")));
        nuevoProducto.setCantidad(Integer.parseInt(Utilidades.capturarCampo(entradaTeclado, "Cantidad","10")));
        nuevoProducto.setObservacion(Utilidades.capturarCampo(entradaTeclado, "Observación",""));
        Utilidades.separador();
        int insertado = model.agregarProducto(nuevoProducto);
        if (insertado > 0){
            Utilidades.print("Producto Agregado Satisfactoriamente!");
        }
        Utilidades.separador();
    }
    
    private static void actualizarProducto() {
        int actualizado = 0;
        Producto productoActualizado = new Producto();
        ArrayList<Producto> productos = model.obtenerProductos();

        Utilidades.encabezado("Actualizar un Producto");
        Utilidades.print("Ingrese Codigo del producto a actualizar: ");
        
        int capturarId = Integer.parseInt(entradaTeclado.nextLine());

        for (int i = 0; i < productos.size(); i++) {
            if (capturarId == (productos.get(i).getCodigo())) {
                productoActualizado.setNombre(Utilidades.capturarCampo(entradaTeclado, "Nombre", productos.get(i).getNombre()));
                productoActualizado.setPrecio(Double.parseDouble(Utilidades.capturarCampo(entradaTeclado, "Precio", Double.toString(productos.get(i).getPrecio()))));
                productoActualizado.setCantidad(Integer.parseInt(Utilidades.capturarCampo(entradaTeclado, "Cantidad", Integer.toString(productos.get(i).getCantidad()))));
                productoActualizado.setObservacion(Utilidades.capturarCampo(entradaTeclado, "Observacion", productos.get(i).getObservacion()));
                productoActualizado.setCodigo(productos.get(i).getCodigo());
                Utilidades.separador();

                actualizado = model.actualizarProducto(productoActualizado);
            }
        }
        
        if (actualizado > 0) {
            Utilidades.print("Producto Actualizado Satisfactoriamente!");
        }
        Utilidades.separador();
    }
    
    private static void eliminarProducto() {//
        int eliminar = 0;
        ArrayList<Producto> productos = model.obtenerProductos();

        Utilidades.encabezado("Eliminar Producto");
        Utilidades.print("Ingrese el Codigo del producto a eliminar: ");
        
        int capturarCodigo = Integer.parseInt(entradaTeclado.nextLine());

        for (int i = 0; i < productos.size(); i++) {
            if (capturarCodigo == (productos.get(i).getCodigo())) {
                Utilidades.print("¿Esta seguro de eliminar el producto? S/N: ");
                String confirmacion = entradaTeclado.nextLine().toUpperCase();

                if (confirmacion.contentEquals("S")) {
                    Utilidades.separador();
                    eliminar = model.deleteProducto(capturarCodigo);
                } else {
                    break;
                }
            }
        }
        if (eliminar > 0) {
            Utilidades.print("Producto Eliminado Satisfactoriamente!");
        }
    }
}
