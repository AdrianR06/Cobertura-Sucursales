/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package paquetegrafo;

import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.graph.*;
import javax.swing.JFrame;
import org.graphstream.ui.view.Viewer;
import org.graphstream.ui.view.ViewerPipe;

/**
 *
 * @author Adrian
 */
public class ManejoGrafo {
    public GrafoLA grafo;
    public Graph ventanaGrafo;
    public int t;
     public Viewer viewer; // Agregar Viewer para manejar la visualización
     
    
    public ManejoGrafo() {
        this.grafo = new GrafoLA(500);
        this.ventanaGrafo = new SingleGraph("Grafo");

        this.t = 0;
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
   

    
    
    public void eliminarGrafos(){
        grafo.eliminarGrafo();
        ventanaGrafo.clear();
    }
    
    public void colocarSucursal(){
        
    }
    
    public void verCoberturaSucursal(){
        
    }
    
    public void revisarCoverturaTotal(){
        
    }
    
    public void agregarLinea(){
        
    }
    
}
