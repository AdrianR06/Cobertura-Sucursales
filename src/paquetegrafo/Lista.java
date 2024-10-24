package paquetegrafo;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author aiannelli
 */
public class Lista {
    private NodoLista inicio;
    private int iN;
    
    public Lista () {
    inicio = null;
    iN = 0;
}

    /**
     * @return the inicio
     */
    public NodoLista getInicio() {
        return inicio;
    }

    /**
     * @param inicio the inicio to set
     */
    public void setInicio(NodoLista inicio) {
        this.inicio = inicio;
    }
    
//    boolean dirigido; // Indica si es dirigido o no.
//    int maxNodos; // Tamaño máximo de la tabla.
//    int numVertices; // Número de vértices del grafo.
//    Lista listaAdy []; // Vector de listas de adyacencias del grafo.

    public NodoLista buscarUltimo(){
        NodoLista aux = inicio;
        if (getInicio()==null){
            return null;
        }
        while(aux.getNext()!=null){
                aux=aux.getNext();
        }
        return aux;
    }
    public boolean esVacia(){
        return inicio == null;
    }
    
    public NodoLista primero(){ 
        return inicio;
    }
    
    public NodoLista ultimo(){
        return null;
    }
    
    public void preinsertarPrimero(String parada){  
        NodoLista nuevo = new NodoLista(parada);                 
        nuevo.setNext(inicio);               
        inicio=nuevo;
        iN++;
    }

    public void insertarUltimo(String palabra){  
        
        NodoLista ult=buscarUltimo();                    
        NodoLista nuevo = new NodoLista(palabra); 
        if(ult == null){
           inicio = nuevo;
        }else{
            ult.setNext(nuevo);
        }
        iN++;   
    }
    
        public NodoLista eliminarPrimero(){
        if (esVacia()) {
            return null;
        }
        NodoLista aux= null;
        aux = inicio;
        inicio = aux.getNext();
        aux.setNext(null);
        iN--;
        return aux;
        }
    
    /**
     * @param iN the iN to set
     */
    public void setiN(int iN) {
        this.iN = iN;
    }

    /**
     * @return the iN
     */
    public int getiN() {
        return iN;
    }
}
