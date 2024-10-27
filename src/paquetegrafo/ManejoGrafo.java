/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package paquetegrafo;

import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.graph.*;



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
     
    
    public ManejoGrafo() {
        this.grafo = new GrafoLA(500);
        this.ventanaGrafo = new SingleGraph("Grafo");
        this.t = 0;
    } 
    
 public void crearGrafoConEstilos(GrafoLA grafo, String[] sucursales) {
    // Recorrer nodos existentes y aplicar estilos
    for (int i = 0; i < grafo.getNumVertices(); i++) {
        String estacion = grafo.getListaVertices()[i].getInicio().getInfo();
        
        // Verifica si el nodo ya existe antes de aplicar el estilo
        if (ventanaGrafo.getNode(estacion) != null) {
            // Aplica el estilo según si es una sucursal o no
            if (contiene(sucursales, estacion)) {
                ventanaGrafo.getNode(estacion).setAttribute("ui.class", "sucursal");
            } else {
                ventanaGrafo.getNode(estacion).setAttribute("ui.class", "normal");
            }
        }
    }

    // Configurar hoja de estilo CSS
    ventanaGrafo.setAttribute("ui.stylesheet", "url('file:///C:/Users/luisf/OneDrive/Documents/NetBeansProjects/Cobertura-Sucursales/resources/estilos.css')");
}

// Método auxiliar para verificar si una estación está en el arreglo
private boolean contiene(String[] estacionesConSucursal, String estacion) {
    for (String sucursal : estacionesConSucursal) {
        if (estacion.equals(sucursal)) {
            return true;
        }
    }
    return false;
}

    public void mostrarGrafo() {
        System.setProperty("org.graphstream.ui", "swing");
        ventanaGrafo.display();
    }
    
    public GrafoLA getGrafo() {
    if (grafo == null) {
        // Puedes retornar una nueva instancia vacía o lanzar una excepción
        System.out.println("El grafo no ha sido cargado.");
        
    }
    return grafo;
}
    public int getT() {
        return t;
    }
   
    // Establece el valor de t
    public void establecerT(int nuevoT) {
    this.t = nuevoT;
}
    public Lista obtenerSucursalesIniciales() {
    Lista sucursalesList = new Lista();
    for (String sucursal : sucursales) {
        if (sucursal != null) {
            sucursalesList.insertarUltimo(sucursal);
        }
    }
    return sucursalesList;
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
                crearGrafoConEstilos(grafo,sucursales);
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
   
   
public Lista verCoberturaBFS(String paradaInicial, int t) {
    // Lista para almacenar las paradas alcanzables dentro del rango de t paradas
    Lista cobertura = new Lista();

    // Cola para manejar el recorrido en anchura (BFS)
    Cola cola = new Cola();

    // Lista para registrar las paradas visitadas y evitar duplicados en la cobertura
    Lista visitados = new Lista();

    // Inicializa la cola con la parada de partida y marca como visitada
    cola.encolar(paradaInicial);
    visitados.insertarUltimo(paradaInicial);

    // Nivel de profundidad actual y control de paradas por nivel
    int nivelActual = 0;               // Nivel de profundidad (distancia en paradas)
    int paradasEnNivel = 1;            // Contador de paradas en el nivel actual
    int paradasEnSiguienteNivel = 0;   // Contador de paradas en el siguiente nivel

    // Recorre el grafo hasta alcanzar el límite de t paradas o hasta que la cola esté vacía
    while (!cola.estaVacia() && nivelActual <= t) {
        // Desencola la siguiente parada y la agrega a la cobertura
        String parada = cola.desencolar();
        cobertura.insertarUltimo(parada);

        // Obtiene la lista de paradas adyacentes de la parada actual
        Lista adyacentes = grafo.obtenerAdyacentes(parada);
        Nodo nodo = adyacentes.getInicio();

        // Itera sobre cada parada adyacente
        while (nodo != null) {
            String paradaAdyacente = nodo.getInfo();

            // Si la parada adyacente no ha sido visitada, la agrega a la cola y la marca como visitada
            if (!visitados.seEncuentra(paradaAdyacente)) {
                cola.encolar(paradaAdyacente);
                visitados.insertarUltimo(paradaAdyacente);
                paradasEnSiguienteNivel++;
            }
            nodo = nodo.getSiguiente();
        }

        // Actualiza el conteo de paradas en el nivel actual
        paradasEnNivel--;

        // Si se han procesado todas las paradas del nivel actual, pasa al siguiente nivel
        if (paradasEnNivel == 0) {
            nivelActual++;
            paradasEnNivel = paradasEnSiguienteNivel;
            paradasEnSiguienteNivel = 0;
        }
    }

    // Retorna la lista de paradas alcanzables dentro del rango de t
    return cobertura;
}

public Lista verCoberturaMinima(Lista sucursalesIniciales) {
    // Lista para almacenar las paradas necesarias mínimas adicionales
    int t = getT();
    Lista paradasNecesarias = new Lista();
    
    // Lista para almacenar la cobertura total alcanzada
    Lista coberturaTotal = new Lista();

    // Marca inicialmente las paradas ya cubiertas por las sucursales existentes
    Nodo nodoInicial = sucursalesIniciales.getInicio();
    while (nodoInicial != null) {
        String paradaInicial = nodoInicial.getInfo();
        Lista coberturaSucursal = verCoberturaBFS(paradaInicial, t); // Cobertura limitada a t paradas
        Nodo nodoCobertura = coberturaSucursal.getInicio();
        
        // Añade cada parada cubierta a la cobertura total, evitando duplicados
        while (nodoCobertura != null) {
            String parada = nodoCobertura.getInfo();
            if (!coberturaTotal.seEncuentra(parada)) {
                coberturaTotal.insertarUltimo(parada);
            }
            nodoCobertura = nodoCobertura.getSiguiente();
        }
        
        nodoInicial = nodoInicial.getSiguiente();
    }
    
    // Verifica si la cobertura actual ya abarca todas las paradas del grafo
    Lista todasLasParadas = grafo.obtenerTodasLasParadas();
    if (coberturaTotal.longitud() == todasLasParadas.longitud()) {
        return paradasNecesarias; // No se necesitan más paradas
    }

    // Mientras la cobertura total no cubra todas las paradas, sigue buscando paradas adicionales
    while (coberturaTotal.longitud() < todasLasParadas.longitud()) {
        Nodo mejorParada = null;
        int maxNuevasParadas = 0;
        
        // Recorre todas las paradas para encontrar la que maximice la cobertura adicional
        Nodo paradaNodo = todasLasParadas.getInicio();
        while (paradaNodo != null) {
            String paradaCandidata = paradaNodo.getInfo();
            
            // Si la parada candidata ya está cubierta, pasa a la siguiente
            if (coberturaTotal.seEncuentra(paradaCandidata)) {
                paradaNodo = paradaNodo.getSiguiente();
                continue;
            }
            
            // Calcula la cobertura que aportaría esta parada
            Lista coberturaCandidata = verCoberturaBFS(paradaCandidata, t);
            int nuevasParadas = 0;
            Nodo nodoCandidato = coberturaCandidata.getInicio();
            while (nodoCandidato != null) {
                String paradaCobierta = nodoCandidato.getInfo();
                if (!coberturaTotal.seEncuentra(paradaCobierta)) {
                    nuevasParadas++;
                }
                nodoCandidato = nodoCandidato.getSiguiente();
            }
            
            // Si esta parada maximiza la cobertura adicional, se elige como la mejor candidata
            if (nuevasParadas > maxNuevasParadas) {
                mejorParada = paradaNodo;
                maxNuevasParadas = nuevasParadas;
            }
            
            paradaNodo = paradaNodo.getSiguiente();
        }
        
        // Añade la mejor parada encontrada a las paradas necesarias y a la cobertura total
        if (mejorParada != null) {
            String mejorParadaInfo = mejorParada.getInfo();
            paradasNecesarias.insertarUltimo(mejorParadaInfo);
            
            // Actualiza la cobertura total con la nueva parada seleccionada
            Lista coberturaMejorParada = verCoberturaBFS(mejorParadaInfo, t);
            Nodo nodoMejorCobertura = coberturaMejorParada.getInicio();
            while (nodoMejorCobertura != null) {
                String parada = nodoMejorCobertura.getInfo();
                if (!coberturaTotal.seEncuentra(parada)) {
                    coberturaTotal.insertarUltimo(parada);
                }
                nodoMejorCobertura = nodoMejorCobertura.getSiguiente();
            }
        } else {
            break; // Si no se encontró una mejor parada, finaliza el proceso
        }
    }

    return paradasNecesarias; // Retorna la lista mínima de paradas adicionales
}
    
public String revisarCoberturaTotal() {
    Lista totalCubierto = new Lista(); // Lista con todas las paradas cubiertas por las sucursales
    StringBuilder resultado = new StringBuilder();
    
    // Cobertura inicial con todas las sucursales dentro del rango t
    for (String sucursal : sucursales) {
        if (sucursal != null) {
            Lista coberturaSucursal = verCoberturaBFS(sucursal, t); // Cobertura limitada a t paradas
            Nodo nodo = coberturaSucursal.getInicio();
            
            while (nodo != null) {
                if (!totalCubierto.seEncuentra(nodo.getInfo())) {
                    totalCubierto.insertarUltimo(nodo.getInfo());
                }
                nodo = nodo.getSiguiente();
            }
        }
    }

    // Verificación de cobertura completa
    Lista todasParadas = grafo.obtenerTodasLasParadas(); 
    Nodo parada = todasParadas.getInicio();
    boolean coberturaCompleta = true;

    while (parada != null) {
        if (!totalCubierto.seEncuentra(parada.getInfo())) {
            coberturaCompleta = false;
            break;
        }
        parada = parada.getSiguiente();
    }

    // Si no se logra la cobertura completa, sugerir paradas adicionales
    if (!coberturaCompleta) {
        Lista sugerencias = new Lista();
        
        Nodo paradaNoCubierta = todasParadas.getInicio();
        while (paradaNoCubierta != null) {
            if (!totalCubierto.seEncuentra(paradaNoCubierta.getInfo())) {
                Lista coberturaPosible = buscarParadasMinimas(paradaNoCubierta.getInfo(), t);
                
                if (coberturaPosible != null) {
                    Nodo nodoCubriente = coberturaPosible.getInicio();
                    while (nodoCubriente != null) {
                        if (!totalCubierto.seEncuentra(nodoCubriente.getInfo())) {
                            sugerencias.insertarUltimo(nodoCubriente.getInfo());
                            totalCubierto.insertarUltimo(nodoCubriente.getInfo());
                        }
                        nodoCubriente = nodoCubriente.getSiguiente();
                    }
                }
            }
            paradaNoCubierta = paradaNoCubierta.getSiguiente();
        }

        // Filtrar y mostrar solo las paradas únicas necesarias
        sugerencias = filtrarParadasUnicas(sugerencias);

        resultado.append("Cobertura incompleta. Sugerencias mínimas de paradas adicionales: ")
                 .append(sugerencias.toString());
                 
    } else {
        resultado.append("Cobertura completa con las sucursales actuales.");
        resultado.append("Cobertura completa con las sucursales actuales.");
        
        // Llamar al método crearGrafoConEstilos si la cobertura es completa
        crearGrafoConEstilos(grafo,sucursales);
    }

    return resultado.toString(); 
}

// Método para buscar paradas mínimas necesarias para cubrir la parada especificada en un rango t
private Lista buscarParadasMinimas(String paradaNoCubierta, int t) {
    // Realiza una búsqueda BFS desde la parada no cubierta para identificar paradas cubrientes en un rango t
    return verCoberturaBFS(paradaNoCubierta, t); 
}

private Lista filtrarParadasUnicas(Lista sugerencias) {
    Lista paradasUnicas = new Lista();
    Nodo nodo = sugerencias.getInicio();
    while (nodo != null) {
        if (!paradasUnicas.seEncuentra(nodo.getInfo())) {
            paradasUnicas.insertarUltimo(nodo.getInfo());
        }
        nodo = nodo.getSiguiente();
    }
    return paradasUnicas;
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
    
    
    
    public void agregarLinea(){
        
    }
    
}
