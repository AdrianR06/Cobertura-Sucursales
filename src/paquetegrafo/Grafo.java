package paquetegrafo;

import javax.swing.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
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
    public void agregarParada(Object parada) {
        if (!adyacencias.contieneClave(parada.toString())) {
            adyacencias.agregar(parada.toString(), new ListaEnlazada());
        }
    }
    public void reiniciarGrafo() {
        // Aquí reinicias tu estructura de datos
        adyacencias = new MapaSimple(100); // Restablecer el mapa con una nueva instancia
    }

    // Método para agregar una arista entre dos paradas
    public void cargarDesdeJSON() {
        // Crear el JFileChooser
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Seleccionar archivo JSON");

        // Filtrar para que solo muestre archivos JSON
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Archivos JSON", "json"));

        // Abrir el cuadro de diálogo para que el usuario seleccione el archivo
        int result = fileChooser.showOpenDialog(null);

        // Si el usuario selecciona un archivo
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();

            try {
                // Crear un parser para leer el archivo JSON
                JSONParser parser = new JSONParser();
                FileReader reader = new FileReader(selectedFile);

                // Parsear el archivo JSON
                Object obj = parser.parse(reader);
                JSONObject jsonObject = (JSONObject) obj;

                // Recorrer todas las líneas de la red
                for (Object key : jsonObject.keySet()) {
                    String red = (String) key;
                    JSONArray lineas = (JSONArray) jsonObject.get(red);

                    for (Object lineaObj : lineas) {
                        JSONObject linea = (JSONObject) lineaObj;
                        for (Object nombreLinea : linea.keySet()) {
                            String nombre = (String) nombreLinea;
                            JSONArray paradas = (JSONArray) linea.get(nombre);

                            Nodo anterior = null;
                            for (Object paradaObj : paradas) {
                                if (paradaObj instanceof String) {
                                    // Si es una parada simple
                                    String parada = (String) paradaObj;
                                    Nodo nodoActual = agregarORecuperarNodo(parada);

                                    if (anterior != null) {
                                        agregarArista(anterior, nodoActual);
                                    }

                                    anterior = nodoActual;
                                } else if (paradaObj instanceof JSONObject) {
                                    // Si es una conexión entre líneas
                                    JSONObject conexion = (JSONObject) paradaObj;
                                    for (Object clave : conexion.keySet()) {
                                        String origen = (String) clave;
                                        String destino = (String) conexion.get(clave);

                                        Nodo nodoOrigen = agregarORecuperarNodo(origen);
                                        Nodo nodoDestino = agregarORecuperarNodo(destino);

                                        // Conectar las estaciones
                                        agregarArista(nodoOrigen, nodoDestino);
                                        agregarArista(nodoDestino, nodoOrigen); // conexión bidireccional
                                    }
                                }
                            }
                        }
                    }
                }

                reader.close();
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al cargar el archivo JSON.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

   // Método para agregar o recuperar un nodo existente
private Nodo agregarORecuperarNodo(String nombreParada) {
    // Revisamos si la parada ya existe en el mapa de adyacencias
    if (!adyacencias.contieneClave(nombreParada)) {
        // Si no existe, creamos un nuevo nodo
        Nodo nuevoNodo = new Nodo(nombreParada);
        adyacencias.agregar(nombreParada, new ListaEnlazada()); // agregamos la nueva parada con su lista de adyacencias vacía
        return nuevoNodo;
    } else {
        // Si ya existe, devolvemos el nodo
        return new Nodo(nombreParada); // Este punto depende de cómo gestionas los nodos en tu grafo
    }
}
// Método para agregar una arista entre dos nodos
// Método para agregar una arista entre dos nodos
private void agregarArista(Nodo origen, Nodo destino) {
    origen.agregarAdyacente(destino); // Agrega destino a la lista de adyacentes de origen
    destino.agregarAdyacente(origen); // Agrega origen a la lista de adyacentes de destino para conexión bidireccional
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
    
  public void imprimirGrafo() {
    System.out.println("Contenido del Grafo:");
    for (int i = 0; i < adyacencias.getCapacidad(); i++) {
        ListaEnlazada lista = adyacencias.obtenerEnIndice(i);
        if (lista != null) {
            Nodo actual = lista.getCabeza();
            while (actual != null) {
                System.out.print(actual.getValor() + " -> ");
                // Aquí asumo que tienes un método para obtener las adyacencias
                ListaEnlazada adyacentes = adyacencias.obtener(actual.getValor());
                Nodo adyacenteActual = adyacentes.getCabeza();
                while (adyacenteActual != null) {
                    System.out.print(adyacenteActual.getValor() + " ");
                    adyacenteActual = adyacenteActual.getSiguiente();
                }
                System.out.println();
                actual = actual.getSiguiente();
            }
        }
    }
}

}
    

