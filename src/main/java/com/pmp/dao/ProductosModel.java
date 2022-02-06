/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pmp.dao;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

import java.util.ArrayList;

/**
 *
 * @author leona
 */
public class ProductosModel {
    
    private Connection _conexion = null;
    
    public ProductosModel(){
        _conexion = Conexion.getConexion();
        String sqlCreateTable = "CREATE TABLE IF NOT EXISTS productos"
                + " (codigo INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + " nombre TEXT NOT NULL,"
                + " precio DECIMAL(10,2),"
                + " cantidad NUMERIC,"
                + " observacion TEXT);";
        
        try{
            Statement comando = _conexion.createStatement();
            int resultado = comando.executeUpdate(sqlCreateTable);
        }catch (Exception ex){
            System.err.println(ex.getMessage());
        }
    }
    
    public ArrayList<Producto> obtenerProductos () {
        
        try {
            ArrayList productos = new ArrayList<Producto>();
            Statement comandoSQL = _conexion.createStatement();
            ResultSet registroEnTabla = comandoSQL.executeQuery("SELECT * FROM productos;");
            while (registroEnTabla.next()) {
                Producto productoActual = new Producto();
                productoActual.setCodigo( registroEnTabla.getInt("codigo") );
                productoActual.setNombre( registroEnTabla.getString("nombre"));
                productoActual.setObservacion( registroEnTabla.getString("observacion"));
                productoActual.setCantidad( registroEnTabla.getInt("cantidad"));
                productoActual.setPrecio( registroEnTabla.getDouble("precio"));
                
                productos.add(productoActual);
            }
            
            return productos;
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            return new ArrayList<Producto>();
        }
    }
    
    public Producto obtenerProducto (int codigo) {
        try {
            PreparedStatement comandoSQL = _conexion.prepareStatement("SELECT * FROM productos where codigo = ?;");
            comandoSQL.setInt(1, codigo);
            ResultSet registroEnTabla = comandoSQL.executeQuery();
            Producto productoActual = new Producto();
            while (registroEnTabla.next()) {
                productoActual.setCodigo( registroEnTabla.getInt("codigo") );
                productoActual.setNombre( registroEnTabla.getString("nombre"));
                productoActual.setObservacion( registroEnTabla.getString("observacion"));
                productoActual.setCantidad( registroEnTabla.getInt("cantidad"));
                productoActual.setPrecio( registroEnTabla.getDouble("precio"));
                break;
            }
            return productoActual;
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            return null;
        }
    }
    
    public int agregarProducto (Producto nuevoProducto) {
        
        try {
            String insertSql = "INSERT INTO productos (nombre, observacion, cantidad, precio) values (?, ?, ?, ?);";
            PreparedStatement comandoSQL = _conexion.prepareStatement(insertSql);
            comandoSQL.setString(1, nuevoProducto.getNombre());
            comandoSQL.setString(2, nuevoProducto.getObservacion());
            comandoSQL.setInt(3, nuevoProducto.getCantidad());
            comandoSQL.setDouble(4, nuevoProducto.getPrecio());
            
            int RegistroAfectados = comandoSQL.executeUpdate();
            comandoSQL.close();
            return RegistroAfectados;
        } catch (Exception ex){
            System.err.println(ex.getMessage());
            return 0;
        }
    }
    
    public int actualizarProducto (Producto updateProducto) {
        
        try {
            String updateSql = "UPDATE productos SET nombre=?, observacion=?, cantidad=?, precio=? where codigo =?;";
            PreparedStatement comandoSQL = _conexion.prepareStatement(updateSql);
            comandoSQL.setString(1, updateProducto.getNombre());
            comandoSQL.setString(2, updateProducto.getObservacion());
            comandoSQL.setInt(3, updateProducto.getCantidad());
            comandoSQL.setDouble(4, updateProducto.getPrecio());
            comandoSQL.setInt(5, updateProducto.getCodigo());

            int RegistroAfectados  = comandoSQL.executeUpdate();
            comandoSQL.close();
            return RegistroAfectados;
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            return 0;
        }
    }
    
    public int deleteProducto (int codigo) {
        
        try {
            String deleteSql = "DELETE From productos where codigo =?;";
            PreparedStatement comandoSQL = _conexion.prepareStatement(deleteSql);
            comandoSQL.setInt(1, codigo);
            
            int RegistroAfectados = comandoSQL.executeUpdate();
            comandoSQL.close();
            return RegistroAfectados;
        } catch (Exception ex){
            System.err.println(ex.getMessage());
            return 0;
        }
    }
}
