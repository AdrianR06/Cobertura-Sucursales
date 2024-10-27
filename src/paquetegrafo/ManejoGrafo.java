/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package paquetegrafo;

import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.graph.*;
import org.graphstream.ui.view.Viewer;
import org.graphstream.ui.view.ViewerListener;



/**
 *
 * @author Adrian
 */
public class ManejoGrafo {
    public GrafoLA grafo;
    public Graph grafoGraphStream;
    public int t;
    
    public ManejoGrafo() {
        this.grafo = new GrafoLA(500);
        this.grafoGraphStream = new SingleGraph("Grafo");
        this.t = 0;
        grafoGraphStream.setAttribute("ui.stylesheet", "node {"
                + " size: 10px, 15px;"
                + " shape: box;"
                + " fill-color: green;"
                + " text-size: 14px;"
                + " }");
        grafoGraphStream.setAttribute("ui.quality");
        grafoGraphStream.setAttribute("ui.antialias");
    }    

    //mostrar graphstream
    public void mostrarGrafo(){
        System.setProperty("org.graphstream.ui", "swing");
        Viewer viewer = grafoGraphStream.display();
        viewer.enableAutoLayout();
    }
    
    // Método para agregar una parada al grafo
    public void agregarParada(String parada) {
        if (grafoGraphStream.getNode(parada) == null){
            // Entra en este condicional en caso de ser una transferencia
            int indice = parada.indexOf(":");
            if (indice != -1) {
                String paradaNombreInver = cambiarOrdenTransferencia(parada);
                
                // Se asegura que no exista una transferencia con estaciones invertidas
                if (grafoGraphStream.getNode(paradaNombreInver) == null) {
                    grafo.insertarVertice(parada);
                    Node nodoB = grafoGraphStream.addNode(parada);
                    nodoB.setAttribute("ui.label", parada);
                    nodoB.setAttribute("ui.shape", "diamond");
                }
                
            }else{ // Agrega al Graph el nodo con el nombre de la parada
            grafo.insertarVertice(parada);
            Node nodoA = grafoGraphStream.addNode(parada);
            nodoA.setAttribute("ui.label", parada);
            }
        }
    }
    
    // Método para agregar una arista entre dos paradas
    public void agregarArista(String parada1, String parada2) {
        //Agrega la arista al graph 
        String aristaId = parada1 + parada2;
        Node nodo1 = grafoGraphStream.getNode(parada1);
        if (nodo1 == null){ //En caso de que no reconozca la transeferencia por estar en orden inverso
            parada1 = cambiarOrdenTransferencia(parada1);
            nodo1 = grafoGraphStream.getNode(parada1);
        }
        Node nodo2 = grafoGraphStream.getNode(parada2);
        if (nodo2 == null){ //En caso de que no reconozca la transeferencia por estar en orden inverso
            parada2 = cambiarOrdenTransferencia(parada2);
            nodo2 = grafoGraphStream.getNode(parada2); //cambia el orden de sus estaciones
        }
        Boolean verificacion = nodo1.hasEdgeToward(nodo2.getId());
        if (verificacion == false) { //Verifica que no exista ya una arista que conecte ambos nodos
            grafo.insertarArista(parada1, parada2);
            grafoGraphStream.addEdge(aristaId, nodo1, nodo2);
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
        grafoGraphStream.clear();
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
