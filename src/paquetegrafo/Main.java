package paquetegrafo;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 * Esta es la clase main donde se hace visible la ventana inicial de navegaci&oacute;n donde 
 * se puede empezar a realizar las funciones solicitadas en el enunciado del proyecto
 * @author aiannelli
 */


import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import Interfaces.Ventana1;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Ventana1 v1 = new Ventana1();
        v1.setVisible(true);
        System.setProperty("org.graphstream.ui.logger", "OFF");
    }
}
