/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package paquetegrafo;

import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.graphstream.graph.*;

/**
 *
 * @author Adrian
 */
public class JSON {
    private String archivo;
    
    public JSON(String rutaArchivo){
        this.archivo = rutaArchivo;
    }
    
    // M&eacute;todo para cargar una red de transporte desde un archivo ManejoGrafo
    public void cargarDesdeJSON(ManejoGrafo grafos) {
        JSONParser parser = new JSONParser();

        try {
            // Leer y parsear el archivo ManejoGrafo
            Object obj = parser.parse(new FileReader(archivo));
            JSONObject jsonObject = (JSONObject) obj;

            // Iterar sobre las claves principales del ManejoGrafo (e.g., "Metro de Caracas")
            for (Object claveRed : jsonObject.keySet()) {
                JSONArray lineas = (JSONArray) jsonObject.get(claveRed);

                // Procesar cada línea
                for (Object lineaObj : lineas) {
                    JSONObject lineaJSON = (JSONObject) lineaObj;

                    for (Object claveLinea : lineaJSON.keySet()) {
                        String claveLineaStr = (String) claveLinea;
                        JSONArray estaciones = (JSONArray) lineaJSON.get(claveLineaStr);

                        // Procesar las estaciones de cada línea
                        procesarEstaciones(estaciones, grafos);
                    }
                }
            }
            System.out.println("--------------------------");
            //Muestra el grafo de GraphStream
            System.setProperty("org.graphstream.ui", "swing");
            grafos.ventanaGrafo.display();
            
            grafos.grafo.profundidad();
            System.out.println("--------------------------");
            grafos.grafo.amplitud();
        } catch (IOException | ParseException e) {
            e.printStackTrace(); // Manejo de errores en caso de problemas al cargar el archivo
        }
    }
    
    // Método para procesar las estaciones de una línea
    private void procesarEstaciones(JSONArray estaciones, ManejoGrafo grafos) {
        String estacionAnterior = null;

        for (Object estacionObj : estaciones) {
            
            // Asigna la cadena del nombre de la etacion a una variable parada para mayor legibilidad
            String parada = estacionObj.toString();
            if (parada.startsWith("{")) { //Le quita las llaves al nombre de las transferencias
                parada = parada.replace("{","");
                parada = parada.replace("}","");
            }
            
            System.out.println(parada);
            //String estacionActual = (String) estacionObj;
            grafos.agregarParada(parada);

            // Conectar con la estación anterior
            if (estacionAnterior != null) {
                grafos.agregarArista(estacionAnterior, parada);
            }
            estacionAnterior = parada;
        }
    }
    
}
