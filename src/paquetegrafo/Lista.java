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
    private Nodo inicio;
    private int iN;
    
    public Lista () {
    this.inicio = null;
    this.iN = 0;
    }
    
    public Lista (String estacion) {
    this.inicio = new Nodo(estacion);
    this.iN = 0;
    }

//----------getters y setters--------------
    /**
     * @return the inicio
     */
    public Nodo getInicio() {
        return inicio;
    }

    /**
     * @param inicio the inicio to set
     */
    public void setInicio(Nodo inicio) {
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
    
    
    public Nodo buscarUltimo(){
    Nodo aux = inicio;
    if (getInicio()==null){
        return null;
    }
    while(aux.getSiguiente()!=null){
            aux=aux.getSiguiente();
    }
    return aux;
    }
    
    public void insertarUltimo(String palabra){  
        
        Nodo ult = buscarUltimo();                    
        Nodo nuevo = new Nodo(palabra); 
        if(ult == null){
           inicio = nuevo;
        }else{
            ult.setSiguiente(nuevo);
        }
        iN++;   
    }
    
        public boolean seEncuentra(String palabra) {
        Nodo actual = inicio;

        // Lista vacía
        if (esVacia()) {
            return false;
        } else {
            // Buscar el nodo parada
            while (actual != null && !actual.getInfo().equals(palabra)) {
                actual = actual.getSiguiente();
            }

            // Si se encontró parada, retornar verdadero
            return actual != null;
        }
    }
    
    /*
        
    public void insertarPrimero(String parada){  
        Nodo nuevo = new Nodo(parada); 
        nuevo.setSiguiente(inicio);
        inicio=nuevo;
        iN++;
    }
        
    public void insertar1DespuesDe2(String nuevaParada, String parada) {
        Nodo nuevoNodo = new Nodo(nuevaParada);
        Nodo actual = inicio;

        // Lista vacía
        if (esVacia()) {
            inicio = nuevoNodo;
        } else {
            // Buscar el nodo parada
            while (actual != null && !actual.getInfo().equals(parada)) {
                actual = actual.getSiguiente();
            }

            // Si se encontró parada, insertar después
            if (actual != null) {
                nuevoNodo.setSiguiente(actual.getSiguiente());
                actual.setSiguiente(nuevoNodo);
                iN++;
            } else {
                // Manejar el caso donde pValor no se encuentra
                System.out.println("El valor " + parada + " no se encontró en la lista.");
            }
        }
    }
    
    public void eliminar(String parada) {
        Nodo actual = inicio;

        // Lista vacía
        if (esVacia()) {
            System.out.println("La lista esta vacia.");
        } else if (iN < 3){
            if (inicio.getInfo().equals(parada)){
                eliminarPrimero();
            } else {
                inicio.setSiguiente(null);
            }
        } else {
            // Buscar el nodo parada
            while (actual != null && !actual.getSiguiente().getInfo().equals(parada)) {
                actual = actual.getSiguiente();
            }

            // Si se encontró parada, insertar después
            if (actual != null) {
                actual.setSiguiente(actual.getSiguiente().getSiguiente());
                actual.getSiguiente().setSiguiente(null);
                iN--;
            } else {
                // Manejar el caso donde pValor no se encuentra
                System.out.println("El valor " + parada + " no se encontró en la lista.");
            }
        }
    }
    
    public Nodo eliminarPrimero(){
        if (esVacia()) {
            return null;
        }
        Nodo aux = inicio;
        inicio = aux.getSiguiente();
        aux.setSiguiente(null);
        iN--;
        return aux;
    }*/
}