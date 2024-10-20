package paquetegrafo;


import java.io.FileReader;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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
    public void agregarParada(String parada) {
        if (!adyacencias.contieneClave(parada)) {
            adyacencias.agregar(parada, new ListaEnlazada());
        }
    }

    // Método para agregar una arista entre dos paradas
    public void agregarArista(String parada1, String parada2) {
        agregarParada(parada1);
        agregarParada(parada2);
        ListaEnlazada lista1 = adyacencias.obtener(parada1);
        ListaEnlazada lista2 = adyacencias.obtener(parada2);

        if (lista1 != null && lista2 != null) {
            lista1.agregar(parada2);
            lista2.agregar(parada1);  // Grafo no dirigido
        }
    }

    // Método para cargar una red de transporte desde un archivo JSON
    // Método para cargar una red de transporte desde un archivo JSON
    public void cargarDesdeJSON(String archivo) {
        JSONParser parser = new JSONParser();

        try {
            // Leer y parsear el archivo JSON
            Object obj = parser.parse(new FileReader(archivo));
            JSONObject jsonObject = (JSONObject) obj;

            // Iterar sobre las claves principales del JSON (e.g., "Metro de Caracas")
            for (Object claveRed : jsonObject.keySet()) {
                String claveRedStr = (String) claveRed;
                JSONArray lineas = (JSONArray) jsonObject.get(claveRedStr);

                // Procesar cada línea
                for (Object lineaObj : lineas) {
                    JSONObject lineaJSON = (JSONObject) lineaObj;

                    for (Object claveLinea : lineaJSON.keySet()) {
                        String claveLineaStr = (String) claveLinea;
                        JSONArray estaciones = (JSONArray) lineaJSON.get(claveLineaStr);

                        // Procesar las estaciones de cada línea
                        procesarEstaciones(claveLineaStr, estaciones);
                    }
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    // Método para procesar las estaciones de una línea
    private void procesarEstaciones(String linea, JSONArray estaciones) {
        String estacionAnterior = null;

        for (Object estacionObj : estaciones) {
            String estacionActual = (String) estacionObj;
            agregarParada(estacionActual);

            // Conectar con la estación anterior
            if (estacionAnterior != null) {
                agregarArista(estacionAnterior, estacionActual);
            }
            estacionAnterior = estacionActual;
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
        System.out.println(nodo); // Procesar el nodo (puedes cambiarlo por otro procesamiento)

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
    
}
    

