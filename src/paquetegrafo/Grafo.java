package paquetegrafo;


import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.graphstream.graph.*;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author VictorB
 */

public class Grafo {

    private MapaSimple adyacencias; // Reemplaza este tipo con tu implementación de mapa
    private int t;

    // Constructor que inicializa la capacidad del grafo
    public Grafo(int capacidadInicial) {
        adyacencias = new MapaSimple(capacidadInicial); // Asegúrate de tener una implementación para MapaSimple
    }

    // Método para agregar una parada al grafo
    public void agregarParada(Object parada, Graph graph) {
        
        // Agrega una variable paradaNombre para mayor legibilidad
        String paradaNombre = parada.toString();
        
        if (!adyacencias.contieneClave(paradaNombre)) {
            adyacencias.agregar(paradaNombre, new ListaEnlazada());
            
            // Entra en este condicional en caso de ser una transferencia
            int indice = paradaNombre.indexOf(":");
            if (indice != -1) {
                String paradaNombreInver = cambiarOrdenTransferencia(paradaNombre);
                
                if (graph.getNode(paradaNombreInver) == null) { // Si no existe un nodo con el nombre inverso
                    Node nodoA = graph.addNode(paradaNombre); //agrega al Graph el nodo con el nombre
                    nodoA.setAttribute("ui.label", paradaNombre); //de las estaciones de la transferencia
                }
                
            }else{ // Agrega al Graph el nodo con el nombre de la parada
            Node nodoA = graph.addNode(parada.toString());
            nodoA.setAttribute("ui.label", parada.toString());                
            }
        }
    }

    // Método para agregar una arista entre dos paradas
    public void agregarArista(Object parada1, Object parada2, Graph graph) {

        // Asigna Strings con los nombres de las paradas a variables cortas 
        String p1 = parada1.toString();
        String p2 = parada2.toString();
        
        ListaEnlazada lista1 = adyacencias.obtener(p1);
        ListaEnlazada lista2 = adyacencias.obtener(p2);

        if (lista1 != null && lista2 != null) {
            lista1.agregar(p2);
            lista2.agregar(p1);  // Grafo no dirigido
            
            //Agrega la arista al graph 
            String aristaId = p1 + p2;
            Node nodo1 = graph.getNode(p1);
            if (nodo1 == null){ //En caso de que no reconozca la transeferencia por estar en orden inverso
                p1 = cambiarOrdenTransferencia(p1);
                nodo1 = graph.getNode(p1);
            }
            Node nodo2 = graph.getNode(p2);
            if (nodo2 == null){ //En caso de que no reconozca la transeferencia por estar en orden inverso
                p2 = cambiarOrdenTransferencia(p2);
                nodo2 = graph.getNode(p2); //cambia el orden de sus estaciones
            }
            Boolean verificacion = nodo1.hasEdgeToward(nodo2.getId());
            if (verificacion == false) { //Verifica que no exista ya una arista que conecte ambos nodos
                graph.addEdge(aristaId, nodo1, nodo2);
            }
        }
    }

<<<<<<< HEAD
    // M&eacute;todo para cargar una red de transporte desde un archivo JSON
    public void cargarDesdeJSON(String archivo, Graph graph) {
=======
    // Método para cargar una red de transporte desde un archivo JSON
    // Método para cargar una red de transporte desde un archivo JSON
public void cargarDesdeJSON(String archivo) {
>>>>>>> 78a8e9170791f19281225faad586e5871b7b0c63
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
<<<<<<< HEAD
                        procesarEstaciones(claveLineaStr, estaciones, graph);
=======
                        //procesarEstaciones(claveLinea, estaciones);
                        
                       procesarEstaciones(claveLineaStr, estaciones);

>>>>>>> 78a8e9170791f19281225faad586e5871b7b0c63
                    }
                }
            }
            System.out.println("--------------------------");
            //this.DFS("Palo Verde", 2);
            //adyacencias.todasLasParadas();
        } catch (IOException | ParseException e) {
            e.printStackTrace(); // Manejo de errores en caso de problemas al cargar el archivo
        }
    }

    // Método para procesar las estaciones de una línea
    private void procesarEstaciones(Object linea, JSONArray estaciones, Graph graph) {
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
            agregarParada(parada, graph);

            // Conectar con la estación anterior
            if (estacionAnterior != null) {
                agregarArista(estacionAnterior, parada, graph);
            }
            estacionAnterior = parada;
        }
    }


   public ListaEnlazada DFS(String inicio, int t) {
    ListaEnlazada visitados = new ListaEnlazada();
    DFSRecursivo(inicio, t, 0, visitados);
    return visitados;
}

    private void DFSRecursivo(String nodo, int t, int profundidad, ListaEnlazada visitados) {
        if (profundidad > t) {
            return; // Detenemos la búsqueda si superamos el límite
        }

        // Marca el nodo como visitado
        visitados.agregar(nodo);
        System.out.println(nodo); 
// Procesar el nodo (puedes cambiarlo por otro procesamiento)

        ListaEnlazada adyacentes = adyacencias.obtener(nodo);
        Nodo actual = adyacentes.getCabeza();
        while (actual != null) {
            if (!visitados.contiene(actual.getValor())) {
                DFSRecursivo(actual.getValor(), t, profundidad + 1, visitados);
            }
            actual = actual.getSiguiente();
        }
    }
    
    public boolean estaCubierta(ListaEnlazada paradas, int t) {
    ListaEnlazada todasVisitadas = new ListaEnlazada();
    
    Nodo actual = paradas.getCabeza();
    while (actual != null) {
        ListaEnlazada visitados = DFS(actual.getValor(), t);
        Nodo nodoVisitado = visitados.getCabeza();
        while (nodoVisitado != null) {
            if (!todasVisitadas.contiene(nodoVisitado.getValor())) {
                todasVisitadas.agregar(nodoVisitado.getValor());
            }
            nodoVisitado = nodoVisitado.getSiguiente();
        }
        actual = actual.getSiguiente();
    }

    // Ahora, verificamos si el tamaño de todasVisitadas es igual al número de paradas en el grafo
    return todasVisitadas.contarNodos() == contarTotalDeParadas();
}
    
    
    public ListaEnlazada obtenerParadasNoCubiertas(ListaEnlazada sucursales) {
        ListaEnlazada noCubiertas = new ListaEnlazada();
        ListaEnlazada todasLasParadas = adyacencias.todasLasParadas(); // Usamos el método implementado

        Nodo actualParada = todasLasParadas.getCabeza();
        while (actualParada != null) {
            String parada = actualParada.getValor();
            boolean estaCubierta = false;

            // Verificamos si la parada está cubierta por alguna sucursal
            Nodo actualSucursal = sucursales.getCabeza();
            while (actualSucursal != null) {
                ListaEnlazada paradasCubiertas = DFS(actualSucursal.getValor(), t);
                if (paradasCubiertas.contiene(parada)) {
                    estaCubierta = true;
                    break;
                }
                actualSucursal = actualSucursal.getSiguiente();
            }

            // Si la parada no está cubierta, la agregamos a la lista de no cubiertas
            if (!estaCubierta) {
                noCubiertas.agregar(parada);
            }

            actualParada = actualParada.getSiguiente();
        }

        return noCubiertas;
    }

    
    public int contarTotalDeParadas() {
    int total = 0;

    // Iterar sobre todas las listas de adyacencia (los valores del mapa)
    for (int i = 0; i < adyacencias.getCapacidad(); i++) {
        ListaEnlazada lista = adyacencias.obtenerEnIndice(i);
        if (lista != null) {
            total += lista.contarNodos(); // Suma el número de nodos en cada lista enlazada
        }
    }

    return total;
    }
    
    // Cambia el orden de las estaciones en el nombre de las transferencias
    private String cambiarOrdenTransferencia(String transferencia){
                String[] cadena = transferencia.split(":", 2);
                String transferencia1 = cadena[0];
                String transferencia2 = cadena[1];
                return (transferencia2+":"+transferencia1);
    }
}
    

