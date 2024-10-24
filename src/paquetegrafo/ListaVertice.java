/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package paquetegrafo;

/**
 *
 * @author aiannelli
 */
public class ListaVertice {
    private NodoVertice inicio;
    private int iN;

    public ListaVertice() {
        this.inicio = null;
        this.iN = 0;
    }

    public NodoVertice buscarUltimo(){
        NodoVertice aux = inicio;
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
    
    public NodoVertice primero(){ 
        return inicio;
    }
    
    public NodoVertice ultimo(){
        return null;
    }
    
    public void preinsertarPrimero(int numParada){  
        NodoVertice nuevo = new NodoVertice(numParada);                 
        nuevo.setNext(inicio);               
        inicio=nuevo;
        iN++;
    }

    public void insertarUltimo(int numParada){  
        
        NodoVertice ult=buscarUltimo();                    
        NodoVertice nuevo = new NodoVertice(numParada); 
        if(ult == null){
           inicio = nuevo;
        }else{
            ult.setNext(nuevo);
        }
        iN++;   
    }
    
 
    /**
     * @return the inicio
     */
    public NodoVertice getInicio() {
        return inicio;
    }

    /**
     * @param inicio the inicio to set
     */
    public void setInicio(NodoVertice inicio) {
        this.inicio = inicio;
    }

    /**
     * @return the iN
     */
    public int getiN() {
        return iN;
    }

    /**
     * @param iN the iN to set
     */
    public void setiN(int iN) {
        this.iN = iN;
    }
    
}
