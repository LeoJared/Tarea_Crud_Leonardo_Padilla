/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tarea1erparcial;

import java.util.Scanner;

/**
 *
 * @author leona
 */
public class Utilidades {
    
    public static void separador(){
        System.out.println(new String(new char[80]).replace("\0", "-"));
    }
    
    public static void print(String text){
        System.out.println(text);
    }
    
    public static void encabezado(String text) {
        separador();
        print(text);
        separador();
    }
    
    public static void menu (){
        String menu = "L Lista\t | I Insertar\t | A Actualizar\t | E Eliminar\t | S Salir\t | ";
        separador();
        print(menu);
    }
    public static String capturarCampo(Scanner entradaTeclado, String leyenda, String valorPredeterminado){
        print(leyenda + "(" + valorPredeterminado + ") :");
        String input = entradaTeclado.nextLine();
        if (input.isEmpty() || input.contentEquals(valorPredeterminado)){
            return valorPredeterminado;
        }
        return input;
    }
    
    public static void printEncabezadoTabla () {
        separador();
        print(String.format("%s\t%s\t%s", "CODIGO", "PRODUCTO", "PRECIO"));//
        separador();
    }
}
