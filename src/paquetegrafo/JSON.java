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
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.graph.*;

/**
 *
 * @author Adrian
 */
public class JSON {
    private GrafoLA grafo;
    private Graph ventanaGrafo;
    
    public JSON(String archivo) {
        this.grafo = new GrafoLA(100);
        this.ventanaGrafo = new SingleGraph("Grafo");
    }    
        
    // M&eacute;todo para cargar una red de transporte desde un archivo JSON
    public void cargarDesdeJSON(String archivo) {
        JSONParser parser = new JSONParser();

        try {
            // Leer y parsear el archivo JSON
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
                        procesarEstaciones(estaciones);
                    }
                }
            }
            System.out.println("--------------------------");
            //Muestra el grafo de GraphStream
            System.setProperty("org.graphstream.ui", "swing");
            ventanaGrafo.display();
        } catch (IOException | ParseException e) {
            e.printStackTrace(); // Manejo de errores en caso de problemas al cargar el archivo
        }
    }
    
    // Método para procesar las estaciones de una línea
    private void procesarEstaciones(JSONArray estaciones) {
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
            agregarParada(parada);

            // Conectar con la estación anterior
            if (estacionAnterior != null) {
                agregarArista(estacionAnterior, parada);
            }
            estacionAnterior = parada;
        }
    }
    
    // Método para agregar una parada al grafo
    public void agregarParada(String parada) {
        if (ventanaGrafo.getNode(parada) == null){
            // Entra en este condicional en caso de ser una transferencia
            int indice = parada.indexOf(":");
            if (indice != -1) {
                String paradaNombreInver = cambiarOrdenTransferencia(parada);
                
                // Se asegura que no exista una transferencia con estaciones invertidas
                if (ventanaGrafo.getNode(paradaNombreInver) == null) {
                    grafo.insertarVertice(parada);
                    Node nodoA = ventanaGrafo.addNode(parada);
                    nodoA.setAttribute("ui.label", parada);
                }
                
            }else{ // Agrega al Graph el nodo con el nombre de la parada
            grafo.insertarVertice(parada);
            Node nodoA = ventanaGrafo.addNode(parada);
            nodoA.setAttribute("ui.label", parada);                
            }
        }
    }
    
    // Método para agregar una arista entre dos paradas
    public void agregarArista(String parada1, String parada2) {
        //Agrega la arista al graph 
        String aristaId = parada1 + parada2;
        Node nodo1 = ventanaGrafo.getNode(parada1);
        if (nodo1 == null){ //En caso de que no reconozca la transeferencia por estar en orden inverso
            parada1 = cambiarOrdenTransferencia(parada1);
            nodo1 = ventanaGrafo.getNode(parada1);
        }
        Node nodo2 = ventanaGrafo.getNode(parada2);
        if (nodo2 == null){ //En caso de que no reconozca la transeferencia por estar en orden inverso
            parada2 = cambiarOrdenTransferencia(parada2);
            nodo2 = ventanaGrafo.getNode(parada2); //cambia el orden de sus estaciones
        }
        Boolean verificacion = nodo1.hasEdgeToward(nodo2.getId());
        if (verificacion == false) { //Verifica que no exista ya una arista que conecte ambos nodos
            grafo.insertarArista(parada1, parada2);
            ventanaGrafo.addEdge(aristaId, nodo1, nodo2);
        }
    }

    // Cambia el orden de las estaciones en el nombre de las transferencias
    private String cambiarOrdenTransferencia(String transferencia){
        String[] cadena = transferencia.split(":", 2);
        String estacion1 = cadena[0];
        String estacion2 = cadena[1];
        return (estacion2+":"+estacion1);
    }
    
}
