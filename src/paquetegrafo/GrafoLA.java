/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package paquetegrafo;

/**
 *
 * @author aiannelli
 *  * Clase que representa un grafo utilizando listas de adyacencia para almacenar las conexiones entre v&eacute;rtices.
 */
public class GrafoLA {
    private int max; // Máximo número de vertices en el grafo
    private int numVertices; // Número actual de vertices en el grafo
    private Lista[] listaAdy; // Arreglo de listas que almacena las adyacencias de cada vértice

    
/**
     * Constructor que inicializa el grafo con una capacidad m&aacute;xima especificada.
     *
     * @param max N&uacute;mero m&aacute;ximo de v&eacute;rtices que puede tener el grafo
     */

    public GrafoLA(int max) {
        this.max = max; 
        this.numVertices = 0;
        this.listaAdy = new Lista[max];
        for (int i = 0; i < max; i++) {
        this.listaAdy[i] = new Lista();
    }
    }

    //-------getters y setters-------
    
    /**
     * Devuelve un n&uacute;mero entero correspondiente a la cantidad de v&eacute;rtices
     * @return el numero de v&eacute;rtices
     */
    public int getNumVertices() {
        return numVertices;
    }

    /**
     * Permite definir el n&uacute;mero de v&eacute;rtices deseado
     * @param numVertices el n&uacute;mero de v&eacute;rtices a establecer
     */
    public void setNumVertices(int numVertices) {
        this.numVertices = numVertices;
    }

    /**
     * @return the max
     */
    public int getMax() {
        return max;
    }

    /**
     * @param max the max to set
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     * @return the listaVertices
     */
    public Lista[] getListaVertices() {
        return listaAdy;
    }

    /**
     * @param listaVertices the listaVertices to set
     */
    public void setListaVertices(Lista[] listaVertices) {
        this.listaAdy = listaVertices;
    }
    
    //--------metodos---------------
    
    /**
     * Agrega un nuevo v&eacute;rtice (parada) al grafo.
     *
     * @param paradaNueva Nombre de la parada que se agregar&aacute; como un nuevo v&eacute;rtice
     */
    
    public void insertarVertice(String paradaNueva){
        if (numVertices == max) {
            System.out.println ("Error, se supera el número de nodos máximo del grafo");
        } else {
            listaAdy[numVertices] = new Lista(paradaNueva);
        }
        numVertices += 1;
    }
    
    /**
     * Devuelve el &iacute;ndice de una parada espec&iacute;fica en el grafo.
     *
     * @param parada Nombre de la parada a buscar
     * @return &iacute;ndice de la parada en el arreglo o -1 si no se encuentra
     */
    
    public int obtenerIndice(String parada){
        for (int i = 0; i < max; i++) {
            Nodo p = (Nodo) listaAdy[i].getInicio();
            if (p.getInfo().equals(parada)) {
            return i;
            }
        }
        return -1;
    }
    
    /**
     * Inserta una arista entre dos paradas en el grafo.
     *
     * @param parada1 Primera parada que se conecta
     * @param parada2 Segunda parada que se conecta
     */
    
    public void insertarArista(String parada1, String parada2){
        int indice1 = obtenerIndice(parada1);
        int indice2 = obtenerIndice(parada2);
        
        listaAdy[indice1].insertarUltimo(parada2);
        listaAdy[indice2].insertarUltimo(parada1);
        
    }
    /**
     * Elimina todas las aristas y v&eacute;rtices del grafo, dej&aacute;ndolo vac&iacute;o.
     */
    public void eliminarGrafo(){
        for (int i =0; i < numVertices; i++){
            listaAdy[i] = null;
        }
        this.setNumVertices(0);
    }
    /**
     * Verifica si existe una arista entre dos v&eacute;rtices dados por sus &iacute;ndices.
     *
     * @param v &iacute;ndice del primer v&eacute;rtice
     * @param i &iacute;ndice del segundo v&eacute;rtice
     * @return true si existe la arista, false en caso contrario
     */
    public boolean existeArista(int v, int i){
        String paradai = listaAdy[i].getInicio().getInfo();
        return listaAdy[v].seEncuentra(paradai);
        
    }
    
   /**
     * Realiza un recorrido en profundidad (DFS) recursivo desde un v&eacute;rtice dado.
     *
     * @param v &iacute;ndice del v&eacute;rtice inicial
     * @param visitados Arreglo de booleanos para rastrear los v&eacute;rtices visitados
     */
    public void recorrerProfundidad (int v, boolean [ ] visitados) {
        //se marca el vértice v como visitado
        visitados [v] = true;
        //el tratamiento del vértice consiste únicamente en imprimirlo en pantalla
        System.out.println ( (listaAdy[v].getInicio()).getInfo() );
        //se examinan los vértices adyacentes a v para continuar el recorrido
        for (int i = 0; i < numVertices; i++) {
            if ((v != i) && ( !visitados [i]) && (existeArista(v, i)) ){
                recorrerProfundidad (i, visitados);
                
            }
        }
    }
    /**
     * Realiza un recorrido en profundidad (DFS) no recursivo de todo el grafo.
     */
    public void profundidad() {
        if (numVertices != 0) {
            boolean visitados [ ] = new boolean [numVertices];
            for (int i = 0; i < numVertices; i++) //inicializar vector con campos false
                visitados [i] = false;
            for (int i = 0; i < numVertices; i++) { //Relanza el recorrido en cada
                if (!visitados [i]) //vértice visitado
                recorrerProfundidad (i, visitados);
            }
        }
        else {
            System.out.println ("Error, el grafo esta vacio");
        }
    }
    
    /**
     * Realiza un recorrido en amplitud (BFS) de todo el grafo.
     */
    public void amplitud () {
        if (numVertices != 0) {
            Cola cola = new Cola ();
            boolean visitados [ ] = new boolean [numVertices];
            String v; //vértice actual

            //Se inicializa el vector visitados [] a false
            for (int i = 0; i < numVertices; i++) {
            visitados [i] = false;
            }

            //El recorrido en amplitud se inicia en cada vértice no visitado
            for (int i = 0; i < numVertices; i++) {
                //se pone en la cola el vértide de partida y se marca como visitado
                if (!visitados [i]){
                    cola.encolar (listaAdy[i].getInicio().getInfo());
                    visitados [i] = true;
                    while (!cola.estaVacia ()) {
                        v = cola.desencolar(); //desencolar y tratar el vértice
                        System.out.println (v);
                        //y encolo los nodos adyacentes a v.
                        for (int j = 0; j < numVertices; j++){
                            if ( (!v.equals(listaAdy[j].getInicio().getInfo())) && (existeArista (obtenerIndice(v), j)) && (!visitados [j]) ) {
                                cola.encolar ( listaAdy[j].getInicio().getInfo() );
                                visitados [j] = true;
                            }
                        }
                    }
                }
            }
        }
    }
    /**
     * Obtiene una lista de las paradas adyacentes a una parada espec&iacute;fica.
     *
     * @param parada Nombre de la parada para la cual se obtendr&aacute;n las adyacencias
     * @return Lista de paradas adyacentes a la parada especificada
     */
    public Lista obtenerAdyacentes(String parada) {
    int indice = obtenerIndice(parada);
    if (indice != -1) {
        return listaAdy[indice];  // Retorna la lista de adyacencias de la parada encontrada
    }
    return new Lista();  // Retorna una lista vac&iacute;a si no se encuentra la parada
}
    
    /**
     * Obtiene una lista de todas las paradas (v&eacute;rtices) en el grafo.
     *
     * @return Lista con todas las paradas en el grafo
     */
    public Lista obtenerTodasLasParadas() {
        Lista todasLasParadas = new Lista();
        for (int i = 0; i < numVertices; i++) {
            Nodo nodo = listaAdy[i].getInicio();
            while (nodo != null) {
                if (!todasLasParadas.seEncuentra(nodo.getInfo())) {
                    todasLasParadas.insertarUltimo(nodo.getInfo());
                }
                nodo = nodo.getSiguiente();
            }
        }
        return todasLasParadas;
    }
}