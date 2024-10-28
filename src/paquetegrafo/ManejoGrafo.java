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

    public Graph ventanaGrafo;
    public int t;//limite de covertura
    private String[] sucursales = new String[500];
    private int numSucursales = 0;
    private Viewer viewer;
     
    
    public ManejoGrafo() {
        this.grafo = new GrafoLA(500);
        System.setProperty("org.graphstream.ui", "swing");
        this.ventanaGrafo = new SingleGraph("Grafo");
        this.t = 0;
    } 
    
    
    public GrafoLA getGrafo() {
    if (grafo == null) {
        // Puedes retornar una nueva instancia vacía o lanzar una excepción
        System.out.println("El grafo no ha sido cargado.");
    }
    return grafo;
}
   
    // Establece el valor de t
    public void establecerT(int nuevoT) {
    this.t = nuevoT;
    }

   

    //mostrar graphstream
    public void mostrarGrafo(){
        this.viewer = ventanaGrafo.display();
        viewer.setCloseFramePolicy(Viewer.CloseFramePolicy.HIDE_ONLY);
        viewer.enableAutoLayout();
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
                    Node nodoB = ventanaGrafo.addNode(parada);
                    nodoB.setAttribute("ui.label", parada);
                    nodoB.setAttribute("ui.shape", "diamond");
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
    
    
    public void colocarSucursal(String parada) {
        // Verificar si ya existe una sucursal en la parada
        for (int i = 0; i < sucursales.length; i++) {
            if (parada.equals(sucursales[i])) {
                System.out.println("Ya existe una sucursal en la parada: " + parada);
                return; // Sale del método si la sucursal ya existe
            }
        }

        // Continuar si no hay sucursal en la parada
        if (numSucursales < sucursales.length && grafo.obtenerIndice(parada) != -1) {
            for (int i = 0; i < sucursales.length; i++) {
                if (sucursales[i] == null) {
                    sucursales[i] = parada;
                    Node nodoSucursal = ventanaGrafo.getNode(parada);
                    nodoSucursal.setAttribute("ui.class", "sucursal");
                    numSucursales++;
                    System.out.println("Sucursal colocada en la parada: " + parada);
                    break;
                }
            }
        } else {
            System.out.println("No se puede colocar sucursal en " + parada + ". Verifica si la parada existe o si se alcanzó el límite de sucursales.");
        }
    }
    
// Des-seleccionar sucursal
public void eliminarSucursal(String parada) {
    for (int i = 0; i < sucursales.length; i++) {
        if (sucursales[i] != null && sucursales[i].equals(parada)) {
            // Eliminar la sucursal del arreglo
            sucursales[i] = null;

            // Quitar marca visual
            Node nodoSucursal = ventanaGrafo.getNode(parada);
            if (nodoSucursal != null) {
                nodoSucursal.removeAttribute("ui.class");
            }

            // Mensaje de confirmación
            System.out.println("Sucursal eliminada: " + parada);
            break; // Salir del ciclo una vez que se elimina
        }
    }
}

public Lista verCoberturaDFS(String sucursal) {
        Lista cobertura = new Lista(); // Lista de paradas cubiertas
        verCoberturaDFSRecursivo(sucursal, 0, cobertura);
        return cobertura;
    }
private void verCoberturaDFSRecursivo(String parada, int distancia, Lista cobertura) {
        if (distancia > t || cobertura.seEncuentra(parada)) return; // Límite alcanzado o parada ya cubierta
        
        cobertura.insertarUltimo(parada); // Marca como cubierta

        Lista adyacentes = grafo.obtenerAdyacentes(parada);
        Nodo nodo = adyacentes.getInicio();
        
        while (nodo != null) {
            verCoberturaDFSRecursivo(nodo.getInfo(), distancia + 1, cobertura);
            nodo = nodo.getSiguiente();
        }
    }

    // Ver cobertura de una sucursal usando BFS
    public Lista verCoberturaBFS(String sucursal) {
        Lista cobertura = new Lista();
        Cola cola = new Cola();
        cola.encolar(sucursal);  // Encolamos directamente el String
        int nivel = 0;

        while (!cola.estaVacia() && nivel <= t) {
            int elementosEnNivel = cola.getiN();
            while (elementosEnNivel > 0) {
                String paradaActual = cola.desencolar();  // Desencolamos y asignamos directamente a String

                if (!cobertura.seEncuentra(paradaActual)) {
                    cobertura.insertarUltimo(paradaActual);

                    Lista adyacentes = grafo.obtenerAdyacentes(paradaActual);
                    Nodo nodo = adyacentes.getInicio();
                    while (nodo != null) {
                        if (!cobertura.seEncuentra(nodo.getInfo())) {
                            cola.encolar(nodo.getInfo());  // Encolamos el String directamente
                        }
                        nodo = nodo.getSiguiente();
                    }
                }
                elementosEnNivel--;
            }
            nivel++;
        }
        return cobertura;
    }
    
    public String revisarCoberturaTotal() {
    Lista totalCubierto = new Lista(); // Todas las paradas cubiertas por las sucursales
    StringBuilder resultado = new StringBuilder(); // Usaremos un StringBuilder para construir el mensaje
    
    // Revisa cobertura de cada sucursal
    for (String sucursal : sucursales) {
        if (sucursal != null) {
            Lista coberturaSucursal = verCoberturaBFS(sucursal); // Usamos BFS para este caso
            Nodo nodo = coberturaSucursal.getInicio();
            
            while (nodo != null) {
                if (!totalCubierto.seEncuentra(nodo.getInfo())) {
                    totalCubierto.insertarUltimo(nodo.getInfo());
                }
                nodo = nodo.getSiguiente();
            }
        }
    }

    // Revisa si la cobertura es completa
    Lista todasParadas = grafo.obtenerTodasLasParadas(); // Asegúrate de tener este método
    Nodo parada = todasParadas.getInicio();
    boolean coberturaCompleta = true;

    while (parada != null) {
        if (!totalCubierto.seEncuentra(parada.getInfo())) {
            coberturaCompleta = false;
            break;
        }
        parada = parada.getSiguiente();
    }

    // Sugerencia de paradas adicionales si no hay cobertura total
    if (!coberturaCompleta) {
        Lista sugerencias = new Lista();
        parada = todasParadas.getInicio();
        while (parada != null) {
            if (!totalCubierto.seEncuentra(parada.getInfo())) {
                sugerencias.insertarUltimo(parada.getInfo());
            }
            parada = parada.getSiguiente();
        }
        resultado.append("Cobertura incompleta. Sugerencias de paradas adicionales: ").append(sugerencias);
    } else {
        resultado.append("Cobertura completa con las sucursales actuales.");
    }

    return resultado.toString(); // Retorna el mensaje construido
}

    
   public String[] obtenerSucursales() {
    // Verificar si el arreglo de sucursales es nulo o si no hay sucursales
    if (sucursales == null || numSucursales <= 0) {
        return new String[0]; // Devuelve un arreglo vacío si no hay sucursales
    }
    
    String[] resultado = new String[numSucursales]; // Crea un arreglo del tamaño correcto
    for (int i = 0; i < numSucursales; i++) {
        resultado[i] = sucursales[i]; // Copiamos cada sucursal al nuevo arreglo
    }
    return resultado; // Retornamos el nuevo arreglo de sucursales
}


    public void eliminarGrafos(){
        grafo.eliminarGrafo();
        ventanaGrafo.clear();
    }
}
