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
    private int numVertices;
    private int max;
    private Vertice[] listaVertices;   

    public GrafoLA(int max) {
        this.numVertices = 0; 
        this.max = max; 
        this.listaVertices = new Vertice[max];
    }
        //Permite agregar una parada
        public void agregarVertice(String parada){             
        Vertice v = new Vertice(parada); 
        v.setNumVertice(getNumVertices());               
        listaVertices[getNumVertices()] = v;
        numVertices++;                      
    }
    
//        public boolean adyacente(String a, String b) throws Exception{
//            int v1, v2;
//            v1 = getNumVertice(a);
//            v2 = getNumVertice(b);
//            if(v1<0 || v2<0){
//                throw new Exception("El vértice no existe");
//            }
//            if(listaVertices[v1].getListaAdy())
//            
//        }
        
        public boolean existeArista(int a, int b){
        NodoArista aux = this.listaVertices[a].getListaAdy().primero();
        boolean encontrado=false;
        while(aux!=null && encontrado==false){
            if (b==aux.getInfo().getDestino()){
                encontrado=true;
            }else{
                encontrado=false;
            }
            aux=aux.getNext();
        }
        return encontrado;
    }
        
        public void agregarArista(int origen, int destino){
         if (origen < 0 || origen >= getNumVertices() || destino < 0 || destino >= getNumVertices()) { 
            return;
        }
        
        if (existeArista(origen,destino)==false){
            
            Arista ab = new Arista(destino, this.listaVertices[destino].getParada());
            Arista ba = new Arista(origen, this.listaVertices[origen].getParada());
            listaVertices[origen].getListaAdy().preinsertarPrimero(ab);
            listaVertices[destino].getListaAdy().preinsertarPrimero(ba);        
        }
    }
        
        
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
    public Vertice[] getListaVertices() {
        return listaVertices;
    }

    /**
     * @param listaVertices the listaVertices to set
     */
    public void setListaVertices(Vertice[] listaVertices) {
        this.listaVertices = listaVertices;
    }

    

}