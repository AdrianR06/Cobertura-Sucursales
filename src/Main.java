/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author aiannelli
 */


import javax.swing.*;
import java.awt.event.*;
import java.io.*;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }


public class FileChooserExample {
    public static void main(String[] args) {
        // Crear un marco (JFrame)
        JFrame frame = new JFrame("Cargar Archivo de Texto");
        JButton button = new JButton("Cargar Archivo");

        // Acción del botón
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Crear un JFileChooser
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Selecciona un archivo de texto");

                // Mostrar el diálogo de selección de archivo
                int userSelection = fileChooser.showOpenDialog(frame);

                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    File fileToOpen = fileChooser.getSelectedFile();
                    leerArchivo(fileToOpen);
                }
            }
        });

        // Configurar el marco
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(button);
        frame.setSize(300, 200);
        frame.setVisible(true);
    }

    // Método para leer el archivo
    private static void leerArchivo(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line); // Imprimir cada línea en la consola
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
}
