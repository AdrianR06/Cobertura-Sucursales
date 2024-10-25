/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package paquetegrafo;

/**
 *
 * @author aiannelli
 */
public class GrafoLA {
    private int max;
    private int numVertices;
    private Lista[] listaAdy;   

    public GrafoLA(int max) {
        this.max = max; 
        this.numVertices = 0;
        this.listaAdy = new Lista[max];
    }

    //-------getters y setters-------
    
    /**
     * @return the numVertices
     */
    public int getNumVertices() {
        return numVertices;
    }

    /**
     * @param numVertices the numVertices to set
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
    
    public void insertarVertice(String paradaNueva){
        if (numVertices == max) {
            System.out.println ("Error, se supera el número de nodos máximo del grafo");
        } else {
            listaAdy[numVertices] = new Lista(paradaNueva);
        }
        numVertices += 1;
    }
    
    public int obtenerIndice(String parada){
        for (int i = 0; i < max; i++) {
            Nodo p = (Nodo) listaAdy[i].getInicio();
            if (p.getInfo().equals(parada)) {
            return i;
            }
        }
        return -1;
    }
    
    public void insertarArista(String parada1, String parada2){
        int indice1 = obtenerIndice(parada1);
        int indice2 = obtenerIndice(parada2);
        
        listaAdy[indice1].insertarUltimo(parada2);
        listaAdy[indice2].insertarUltimo(parada1);
        
    }
    
    public boolean existeArista(int v, int i){
        String paradai = listaAdy[i].getInicio().getInfo();
        return listaAdy[v].seEncuentra(paradai);
        
    }
    
    // Para los recorridos 
    
    //procedimiento recursivo. Donde v es el indice en listaAdy del vertice inicial
    public void recorrerProfundidad (int v, boolean [ ] visitados) {
        //se marca el vértice v como visitado
        visitados [v] = true;
        //el tratamiento del vértice consiste únicamente en imprimirlo en pantalla
        System.out.println ( (listaAdy[v].getInicio()).getInfo() );
        //se examinan los vértices adyacentes a v para continuar el recorrido
        for (int i = 0; i < numVertices; i++) {
            if ((v != i) && ( !visitados [i]) && (existeArista(v, i)) )
            recorrerProfundidad (i, visitados);
        }
    }
    //procedimiento no recursivo
    public void profundidad() {
        boolean visitados [ ] = new boolean [numVertices];
        for (int i = 0; i < numVertices; i++) //inicializar vector con campos false
            visitados [i] = false;
        for (int i = 0; i < numVertices; i++) { //Relanza el recorrido en cada
            if (!visitados [i]) //vértice visitado
            recorrerProfundidad (i, visitados);
        }
    }
    
    public void amplitud () {
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