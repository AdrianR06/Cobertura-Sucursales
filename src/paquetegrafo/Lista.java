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
    this.inicio = null;
    this.iN = 0;
    }
    
    public Lista (String estacion) {
    this.inicio = new NodoLista(estacion);
    this.iN = 0;
    }

//----------getters y setters--------------
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
    
    //---------------metodos-------------------
    public boolean esVacia(){
        return inicio == null;
    }
    
    
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
    
    
    /*public void insertarPrimero(String parada){  
        NodoLista nuevo = new NodoLista(parada);                    
        inicio=nuevo;
        iN++;
    }
*/
    
    public void insertarUltimo(String palabra){  
        
        NodoLista ult = buscarUltimo();                    
        NodoLista nuevo = new NodoLista(palabra); 
        if(ult == null){
           inicio = nuevo;
        }else{
            ult.setNext(nuevo);
        }
        iN++;   
    }
    
    /*
    public void insertar1DespuesDe2(String nuevaParada, String parada) {
        NodoLista nuevoNodo = new NodoLista(nuevaParada);
        NodoLista actual = inicio;

        // Lista vacía
        if (esVacia()) {
            inicio = nuevoNodo;
        } else {
            // Buscar el nodo parada
            while (actual != null && !actual.getInfo().equals(parada)) {
                actual = actual.getNext();
            }

            // Si se encontró parada, insertar después
            if (actual != null) {
                nuevoNodo.setNext(actual.getNext());
                actual.setNext(nuevoNodo);
                iN++;
            } else {
                // Manejar el caso donde pValor no se encuentra
                System.out.println("El valor " + parada + " no se encontró en la lista.");
            }
        }
    }

    public boolean seEncuentra(String parada) {
        NodoLista actual = inicio;

        // Lista vacía
        if (esVacia()) {
            return false;
        } else {
            // Buscar el nodo parada
            while (actual != null && !actual.getInfo().equals(parada)) {
                actual = actual.getNext();
            }

            // Si se encontró parada, retornar verdadero
            return actual != null;
        }
    }
    
    public void eliminar(String parada) {
        NodoLista actual = inicio;

        // Lista vacía
        if (esVacia()) {
            System.out.println("La lista esta vacia.");
        } else if (iN < 3){
            if (inicio.getInfo().equals(parada)){
                eliminarPrimero();
            } else {
                inicio.setNext(null);
            }
        } else {
            // Buscar el nodo parada
            while (actual != null && !actual.getNext().getInfo().equals(parada)) {
                actual = actual.getNext();
            }

            // Si se encontró parada, insertar después
            if (actual != null) {
                actual.setNext(actual.getNext().getNext());
                actual.getNext().setNext(null);
                iN--;
            } else {
                // Manejar el caso donde pValor no se encuentra
                System.out.println("El valor " + parada + " no se encontró en la lista.");
            }
        }
    }
    
    public NodoLista eliminarPrimero(){
        if (esVacia()) {
            return null;
        }
        NodoLista aux = inicio;
        inicio = aux.getNext();
        aux.setNext(null);
        iN--;
        return aux;
    }*/
}