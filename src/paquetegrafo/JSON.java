/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package paquetegrafo;
import Interfaces.*;

import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Esta clase se encarga de cargar una red de transporte desde un archivo JSON, 
 * de la misma estructura que los archivos de ejemplo de las redes de transporte de Caracas y Bogot&aacute;
 * @author Adrian
 */
public class JSON {
    private String archivo;
    
    
    /**
     * Crea una nueva instancia de JSON para cargar una red de transporte desde un archivo .json
     *
     * @param rutaArchivo La ruta al archivo JSON.
     */
    public JSON(String rutaArchivo){
        this.archivo = rutaArchivo;
    }
    
    // M&eacute;todo para cargar una red de transporte desde un archivo ManejoGrafo
    /**
     * Carga la red de transporte desde el archivo JSON especificado y crea un grafo.
     *
     * @param grafos El objeto ManejoGrafo donde se almacenar&aacute; la representaci&oacute;n del grafo.
     * @param ventana2 La ventana donde se visualizar&aacute; el grafo.
     * @throws IOException Si ocurre un error al leer el archivo.
     * @throws ParseException Si ocurre un error al analizar el JSON.
     */
    public void cargarDesdeJSON(ManejoGrafo grafos, Ventana2 ventana2) {
    JSONParser parser = new JSONParser();

    try {
        Object obj = parser.parse(new FileReader(archivo));
        JSONObject jsonObject = (JSONObject) obj;

        // Iterar sobre las claves principales del JSON (e.g., "Metro de Caracas")
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
        // Establecer el grafo cargado en Ventana2
        ventana2.setGrafo(grafos); 

    } catch (IOException | ParseException e) {
        e.printStackTrace();
    }
}
    /**
     * Muestra el grafo cargado en la ventana de visualizaci&oacute;n.
     *
     * @param grafos El objeto ManejoGrafo que contiene el grafo.
     */
    public void mostrarGrafo(ManejoGrafo grafos){
        if (grafos != null){
            System.setProperty("org.graphstream.ui", "swing");
            grafos.ventanaGrafo.display();
            grafos.grafo.profundidad();
            System.out.println("--------------------------");
            grafos.grafo.amplitud();    
        } else {
            System.out.println("Grafo no Cargado");
        }
    } 
    
    // Método para procesar las estaciones de una l&iacute;nea
    /**
     * Procesa las estaciones de una l&iacute;nea y las agrega al grafo.
     *
     * @param estaciones Un arreglo JSON que contiene los nombres de las estaciones del metro.
     * @param grafos El objeto ManejoGrafo donde se agregarán las estaciones y lineas que conectan las paradas.
     */
    private void procesarEstaciones(JSONArray estaciones, ManejoGrafo grafos) {
        String estacionAnterior = null;

        for (Object estacionObj : estaciones) {
            
            // Asigna la cadena del nombre de la estacion a una variable parada para mayor legibilidad
            String parada = estacionObj.toString();
            if (parada.startsWith("{")) { //Le quita las llaves al nombre de las transferencias
                parada = parada.replace("{","");
                parada = parada.replace("}","");
            }
            
            System.out.println(parada);
            //String estacionActual = (String) estacionObj;
            grafos.agregarParada(parada);

            // Conectar con la estacion anterior
            if (estacionAnterior != null) {
                grafos.agregarArista(estacionAnterior, parada);
            }
            estacionAnterior = parada;
        }
    }
    
}
