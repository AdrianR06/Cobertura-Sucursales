/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Interfaces;
import paquetegrafo.*;
import javax.swing.JOptionPane;       
/**
 *
 * @author aiannelli
 */

public class Ventana2 extends javax.swing.JFrame {
    private ManejoGrafo grafos;
    private JSON json;
    private Lista nuevaLinea;
    private StringBuilder resultado = new StringBuilder();
    /**
     * Creates new form Interfaz
     */
    
    public Ventana2() {
        initComponents();
        // Inicialmente, desactiva los componentes que dependen del grafo
        comboBoxEstaciones.setEnabled(false);
        sucursalComboBox.setEnabled(false);
        primeraParada.setEnabled(false);
        siguienteParada.setEnabled(false);
        borrarLínea.setEnabled(false);
        conectarButton.setEnabled(false);
    }

    public void setGrafo(ManejoGrafo grafos) {
    this.grafos = grafos;
    }
    
    public void setJson(JSON json) {
        this.json = json;
    }

    /**
     * Método para configurar el grafo después de cargar el archivo.
     */
    public void configurarGrafo() {

        if (this.grafos.grafo != null) {
            // Activa y actualiza los ComboBoxes ahora que el grafo está cargado
            
            comboBoxEstaciones.setEnabled(true);
            sucursalComboBox.setEnabled(true);
            primeraParada.setEnabled(true);
            actualizarComboBoxEstaciones();
            primeraParada.addItem("Seleccionar...");
            primeraParada.setSelectedItem("Seleccionar...");
            siguienteParada.addItem("Seleccionar...");
            siguienteParada.setSelectedItem("Seleccionar...");
            configurarLinea(true);
        } else {
            System.out.println("El grafo aún no está cargado.");
        }
    }

     
    private void actualizarComboBoxSucursales() {
        // Limpiar el JComboBox
        sucursalComboBox.removeAllItems();

        // Verificar si 'grafos' está inicializado
        if (grafos == null) {
            JOptionPane.showMessageDialog(this, "El objeto de ManejoGrafo no está inicializado.");
            return; // Sale del método si grafos es nulo
        }

        // Obtener las sucursales del manejo de grafo
        String[] sucursalesActuales = grafos.obtenerSucursales();

        // Comprobar si hay sucursales antes de añadir
        if (sucursalesActuales != null && sucursalesActuales.length > 0) {
            for (String sucursal : sucursalesActuales) {
                sucursalComboBox.addItem(sucursal);
            }
        } else {
            JOptionPane.showMessageDialog(this, "No hay sucursales disponibles.");
        }
    
    }
    public void actualizarComboBoxEstaciones() {
        if (this.grafos.grafo == null) {
            System.out.println("No se puede actualizar el ComboBox de estaciones porque grafo es null.");
            return;
        }

        Lista estaciones = this.grafos.grafo.obtenerTodasLasParadas();
        cargarEstacionesEnComboBox(estaciones);
    }
    
    public void cargarEstacionesEnComboBox(Lista estaciones) {
        comboBoxEstaciones.removeAllItems(); // Elimina elementos previos en caso de recarga
        primeraParada.removeAllItems();
        siguienteParada.removeAllItems();
        
        Nodo nodo = estaciones.getInicio(); // Accedemos a la primera estación
        while (nodo != null) { // Recorre la lista de estaciones
            comboBoxEstaciones.addItem(nodo.getInfo()); // Agrega cada estación al ComboBox
            primeraParada.addItem(nodo.getInfo());
            siguienteParada.addItem(nodo.getInfo());
            nodo = nodo.getSiguiente();
        }
    }
    
    public void configurarLinea(boolean verificacion) {
        if (primeraParada.getSelectedItem() != null && siguienteParada.getSelectedItem() != null){
            if (primeraParada.getSelectedItem().toString().equals("Seleccionar...")) {
                this.primeraParada.setEnabled(true);
                this.siguienteParada.setEnabled(false);
                this.conectarButton.setEnabled(false);
                this.borrarLínea.setEnabled(false);

            } else if ( siguienteParada.getSelectedItem().toString().equals("Seleccionar...") ) {
                this.siguienteParada.setEnabled(true);
                this.conectarButton.setEnabled(false);
                this.borrarLínea.setEnabled(false);   
            } else {
                this.primeraParada.setEnabled(false);
                this.conectarButton.setEnabled(true);
                this.borrarLínea.setEnabled(true);
            }
        }
        if (verificacion == true) configurarLinea(!verificacion);
    }
    
    private void actualizarOpcionesNuevaLinea() {

        primeraParada.addItem(nombreNuevaParada.getText());
        siguienteParada.addItem(nombreNuevaParada.getText());
    }
    

    // Otros métodos de Ventana2


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelAgregarLinea = new javax.swing.JTabbedPane();
        PanelSucursales = new javax.swing.JPanel();
        ValorT = new javax.swing.JTextField();
        CambiarValorT = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        ColocarSucursal = new javax.swing.JButton();
        RevisarCoberturaTotal = new javax.swing.JButton();
        MostrarGrafo = new javax.swing.JButton();
        RevisarCoberturaSucursal1 = new javax.swing.JButton();
        sucursalComboBox = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ResultadoTextArea = new javax.swing.JTextArea();
        tipoBusqueda = new javax.swing.JComboBox<>();
        comboBoxEstaciones = new javax.swing.JComboBox<>();
        EliminarSucursal = new javax.swing.JButton();
        PanelNuevaLinea = new javax.swing.JPanel();
        conectarButton = new javax.swing.JButton();
        primeraParada = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        ResultadoTextArea1 = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        siguienteParada = new javax.swing.JComboBox<>();
        borrarLínea = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        nombreNuevaParada = new javax.swing.JTextField();
        agregarParadaButton = new javax.swing.JButton();
        agregarLinea = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PanelSucursales.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ValorT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ValorTActionPerformed(evt);
            }
        });
        PanelSucursales.add(ValorT, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 100, 130, -1));

        CambiarValorT.setText("Cambiar valor de T");
        CambiarValorT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CambiarValorTActionPerformed(evt);
            }
        });
        PanelSucursales.add(CambiarValorT, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 100, -1, -1));

        jLabel2.setText("Indique el valor de t:");
        PanelSucursales.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, -1, -1));

        jLabel4.setText("Ubicacion Sucursal");
        PanelSucursales.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 100, 20));

        ColocarSucursal.setText("Colocar Sucursal");
        ColocarSucursal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ColocarSucursalActionPerformed(evt);
            }
        });
        PanelSucursales.add(ColocarSucursal, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 180, -1, -1));

        RevisarCoberturaTotal.setText("Revisar cobertura total");
        RevisarCoberturaTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RevisarCoberturaTotalActionPerformed(evt);
            }
        });
        PanelSucursales.add(RevisarCoberturaTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 420, -1, -1));

        MostrarGrafo.setText("Mostrar Red de Transporte");
        MostrarGrafo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MostrarGrafoActionPerformed(evt);
            }
        });
        PanelSucursales.add(MostrarGrafo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, -1, -1));

        RevisarCoberturaSucursal1.setText("Revisar cobertura");
        RevisarCoberturaSucursal1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RevisarCoberturaSucursal1ActionPerformed(evt);
            }
        });
        PanelSucursales.add(RevisarCoberturaSucursal1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 330, -1, -1));

        sucursalComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        PanelSucursales.add(sucursalComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, -1, -1));

        jLabel1.setText("Tipo de Busqueda");
        PanelSucursales.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 310, -1, -1));

        jLabel6.setText("Sucursal");
        PanelSucursales.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, -1, 20));

        ResultadoTextArea.setColumns(20);
        ResultadoTextArea.setRows(5);
        jScrollPane1.setViewportView(ResultadoTextArea);

        PanelSucursales.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 230, 400, -1));

        tipoBusqueda.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DFS", "BFS" }));
        tipoBusqueda.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentRemoved(java.awt.event.ContainerEvent evt) {
                tipoBusquedaComponentRemoved(evt);
            }
        });
        PanelSucursales.add(tipoBusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 330, -1, -1));

        comboBoxEstaciones.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboBoxEstaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxEstacionesActionPerformed(evt);
            }
        });
        PanelSucursales.add(comboBoxEstaciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 100, -1));

        EliminarSucursal.setText("Eliminar Sucursal");
        EliminarSucursal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarSucursalActionPerformed(evt);
            }
        });
        PanelSucursales.add(EliminarSucursal, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 330, -1, -1));

        PanelAgregarLinea.addTab("Sucursales", PanelSucursales);

        PanelNuevaLinea.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        conectarButton.setText("Conectar");
        conectarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                conectarButtonActionPerformed(evt);
            }
        });
        PanelNuevaLinea.add(conectarButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 290, -1, -1));

        primeraParada.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        primeraParada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                primeraParadaActionPerformed(evt);
            }
        });
        PanelNuevaLinea.add(primeraParada, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, 100, -1));

        jLabel5.setText("Primera parada");
        PanelNuevaLinea.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, 100, 20));

        ResultadoTextArea1.setColumns(20);
        ResultadoTextArea1.setRows(5);
        jScrollPane2.setViewportView(ResultadoTextArea1);

        PanelNuevaLinea.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 150, 400, 210));

        jLabel7.setText("Siguiente Parada");
        PanelNuevaLinea.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 220, 100, 20));

        siguienteParada.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        siguienteParada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siguienteParadaActionPerformed(evt);
            }
        });
        PanelNuevaLinea.add(siguienteParada, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 240, 100, -1));

        borrarLínea.setText("Borrar línea");
        borrarLínea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrarLíneaActionPerformed(evt);
            }
        });
        PanelNuevaLinea.add(borrarLínea, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 380, -1, -1));

        jLabel3.setText("Crear nueva parada:");
        PanelNuevaLinea.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 80, -1, -1));
        PanelNuevaLinea.add(nombreNuevaParada, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 80, 130, -1));

        agregarParadaButton.setText("Agregar");
        agregarParadaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarParadaButtonActionPerformed(evt);
            }
        });
        PanelNuevaLinea.add(agregarParadaButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 80, -1, -1));

        agregarLinea.setText("Agregar línea");
        agregarLinea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarLineaActionPerformed(evt);
            }
        });
        PanelNuevaLinea.add(agregarLinea, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 380, -1, -1));

        PanelAgregarLinea.addTab("Agregar Línea", PanelNuevaLinea);

        getContentPane().add(PanelAgregarLinea, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, 500));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CambiarValorTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CambiarValorTActionPerformed
   
        // Obtener el valor del campo de texto ValorT y eliminar espacios en blanco
        String textoValorT = ValorT.getText().trim();

        // Verificar si el campo está vacío
        if (textoValorT.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa un valor para T.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return; // Sale del método si el campo está vacío
        }

        int nuevoT;
        try {
            // Intentar convertir el texto a un número entero
            nuevoT = Integer.parseInt(textoValorT);
        } catch (NumberFormatException e) {
            // Si la conversión falla, mostrar un mensaje de error y salir del método
            JOptionPane.showMessageDialog(this, "Por favor, ingresa un número válido para T.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Llamar al método establecerT para asignar el valor en el grafo
        grafos.establecerT(nuevoT);
        JOptionPane.showMessageDialog(this, "El valor de T se ha actualizado a " + nuevoT, "Éxito", JOptionPane.INFORMATION_MESSAGE);


    }//GEN-LAST:event_CambiarValorTActionPerformed

    private void conectarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_conectarButtonActionPerformed
    // Construir el resultado para mostrarlo en ResultadoTextArea
    String primera = primeraParada.getSelectedItem().toString();
    String siguiente = siguienteParada.getSelectedItem().toString();
    
        configurarLinea(true);
        if ( nuevaLinea == null ) {
            nuevaLinea = new Lista(primera);

            nuevaLinea.setInicio(primera);
            resultado.append(primera+"\n");
            nuevaLinea.insertarUltimo(siguiente);
            resultado.append(siguiente+"\n");
        } else {
            nuevaLinea.insertarUltimo(siguiente);
            resultado.append(siguiente+"\n");
        }
        // Mostrar el resultado en el JTextArea
        ResultadoTextArea1.setText(resultado.toString());
    }//GEN-LAST:event_conectarButtonActionPerformed

    private void ColocarSucursalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ColocarSucursalActionPerformed
        // Verifica si el objeto grafos es null
    if (grafos == null) {
        System.out.println("El grafo no ha sido cargado. Por favor, carga el archivo primero.");
        return; // Sale del método si el grafo es null
    }

    // Suponiendo que tienes un JComboBox llamado comboBoxEstaciones
    String paradaSeleccionada = (String) comboBoxEstaciones.getSelectedItem();

    // Llama al método que coloca la sucursal
    if (paradaSeleccionada != null) {
        grafos.colocarSucursal(paradaSeleccionada);
        System.out.println("Sucursal colocada en la parada: " + paradaSeleccionada);
        
        // Actualiza el ComboBox para reflejar los cambios
        actualizarComboBoxEstaciones();
        actualizarComboBoxSucursales(); // También actualiza el ComboBox de sucursales si es necesario
    } else {
        System.out.println("Por favor, selecciona una parada válida.");
    }



        
    }//GEN-LAST:event_ColocarSucursalActionPerformed

    private void RevisarCoberturaTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RevisarCoberturaTotalActionPerformed
        // TODO add your handling code here:                                                    
    // Llama al método revisarCoberturaTotal de ManejoGrafo
    String resultado = grafos.revisarCoberturaTotal();
    
    // Muestra el resultado en un cuadro de diálogo
    JOptionPane.showMessageDialog(this, resultado, "Resultado de Cobertura", JOptionPane.INFORMATION_MESSAGE);

    }//GEN-LAST:event_RevisarCoberturaTotalActionPerformed

    private void RevisarCoberturaSucursal1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RevisarCoberturaSucursal1ActionPerformed
    
    String sucursalSeleccionada = (String) sucursalComboBox.getSelectedItem();

if (sucursalSeleccionada != null) {
    // Verificar si el campo de texto ValorT está vacío
    String textoValorT = ValorT.getText().trim();
    if (textoValorT.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Por favor, ingresa un valor para el límite de cobertura.");
        return; // Sale del método si el campo está vacío
    }

    // Convertir el valor de texto a entero
    int limiteCobertura;
    try {
        limiteCobertura = Integer.parseInt(textoValorT);
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Por favor, ingresa un número válido para el límite de cobertura.");
        return; // Sale del método si el valor no es un número válido
    }

    // Establecer el valor de cobertura en la clase de manejo del grafo
    grafos.establecerT(limiteCobertura);

    // Llamar a la función para ver la cobertura usando DFS o BFS según lo seleccionado
    Lista cobertura;
    String metodoSeleccionado = (String) tipoBusqueda.getSelectedItem();
    if (metodoSeleccionado.equals("DFS")) {
        cobertura = grafos.verCoberturaDFS(sucursalSeleccionada);
    } else {
        cobertura = grafos.verCoberturaBFS(sucursalSeleccionada);
    }

    // Verificar si la lista de cobertura está vacía
    if (cobertura == null || cobertura.getInicio() == null) {
        JOptionPane.showMessageDialog(this, "No hay paradas alcanzables desde esta sucursal dentro del límite de cobertura.");
        return; // Sale del método si no hay cobertura disponible
    }

    // Construir el resultado para mostrarlo en ResultadoTextArea
    StringBuilder resultado = new StringBuilder("Paradas alcanzables desde " + sucursalSeleccionada + ":\n");
    Nodo nodo = cobertura.getInicio();
    while (nodo != null) {
        // Agregar verificación adicional para asegurar que nodo tenga información
        if (nodo.getInfo() != null) {
            resultado.append(nodo.getInfo()).append("\n");
        } else {
            System.out.println("Nodo vacío encontrado."); // Mensaje de depuración
        }
        nodo = nodo.getSiguiente();
    }

    // Mostrar el resultado en el JTextArea
    ResultadoTextArea.setText(resultado.toString());
} else {
    JOptionPane.showMessageDialog(this, "Por favor, selecciona una sucursal.");
}                                           
   

    }//GEN-LAST:event_RevisarCoberturaSucursal1ActionPerformed

    private void MostrarGrafoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MostrarGrafoActionPerformed

        if (json != null) {
            grafos.mostrarGrafo();
        } else {
            JOptionPane.showMessageDialog(this, "El grafo no ha sido cargado.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_MostrarGrafoActionPerformed

    private void tipoBusquedaComponentRemoved(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_tipoBusquedaComponentRemoved
        // TODO add your handling code here:
    }//GEN-LAST:event_tipoBusquedaComponentRemoved

    private void comboBoxEstacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxEstacionesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBoxEstacionesActionPerformed

    private void EliminarSucursalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarSucursalActionPerformed
        // TODO add your handling code here:
    // Obtener la sucursal seleccionada del comboBox
    String paradaSeleccionada = (String) sucursalComboBox.getSelectedItem();

    // Verificar que se haya seleccionado una sucursal
    if (paradaSeleccionada != null) {
        // Llamar al método eliminarSucursal para eliminar la sucursal del grafo
        grafos.eliminarSucursal(paradaSeleccionada);

        // Eliminar la sucursal del comboBox
        sucursalComboBox.removeItem(paradaSeleccionada);

        // Vaciar el ResultadoTextArea
        ResultadoTextArea.setText("");

        // Mostrar mensaje de confirmación
        JOptionPane.showMessageDialog(this, "Sucursal " + paradaSeleccionada + " eliminada.");
    } else {
        JOptionPane.showMessageDialog(this, "Por favor, selecciona una sucursal para eliminar.");
    }



        
        
    }//GEN-LAST:event_EliminarSucursalActionPerformed

    private void primeraParadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_primeraParadaActionPerformed
        configurarLinea(true);
    }//GEN-LAST:event_primeraParadaActionPerformed

    private void siguienteParadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siguienteParadaActionPerformed
        configurarLinea(true);
    }//GEN-LAST:event_siguienteParadaActionPerformed

    private void borrarLíneaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrarLíneaActionPerformed
        nuevaLinea = null;
        ResultadoTextArea1.setText("");


    }//GEN-LAST:event_borrarLíneaActionPerformed
        
    private void agregarParadaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarParadaButtonActionPerformed
        actualizarOpcionesNuevaLinea();
    }//GEN-LAST:event_agregarParadaButtonActionPerformed

    private void ValorTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ValorTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ValorTActionPerformed

    private void agregarLineaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarLineaActionPerformed

        String estacionAnterior = null;

        for (int i = 0; i < nuevaLinea.getiN(); i++) {
            
            // Asigna la cadena del nombre de la etacion a una variable parada para mayor legibilidad
            String parada = nuevaLinea.getInicio().getInfo();
            if (parada.startsWith("{")) { //Le quita las llaves al nombre de las transferencias
                parada = parada.replace("{","");
                parada = parada.replace("}","");
            }
            
            grafos.agregarParada(parada);

            // Conectar con la estación anterior
            
            if (estacionAnterior != null) {
                if (!estacionAnterior.equals(parada)){
                    grafos.agregarArista(estacionAnterior, parada);                    
                }
            }
            estacionAnterior = parada;
            if (nuevaLinea.getInicio().getSiguiente() != null){
                nuevaLinea.setInicio(nuevaLinea.getInicio().getSiguiente().getInfo());   
            }
        }
        primeraParada.setSelectedItem("Seleccionar...");
        siguienteParada.setSelectedItem("Seleccionar...");
    }//GEN-LAST:event_agregarLineaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CambiarValorT;
    private javax.swing.JButton ColocarSucursal;
    private javax.swing.JButton EliminarSucursal;
    private javax.swing.JButton MostrarGrafo;
    private javax.swing.JTabbedPane PanelAgregarLinea;
    private javax.swing.JPanel PanelNuevaLinea;
    private javax.swing.JPanel PanelSucursales;
    private javax.swing.JTextArea ResultadoTextArea;
    private javax.swing.JTextArea ResultadoTextArea1;
    private javax.swing.JButton RevisarCoberturaSucursal1;
    private javax.swing.JButton RevisarCoberturaTotal;
    private javax.swing.JTextField ValorT;
    private javax.swing.JButton agregarLinea;
    private javax.swing.JButton agregarParadaButton;
    private javax.swing.JButton borrarLínea;
    private javax.swing.JComboBox<String> comboBoxEstaciones;
    private javax.swing.JButton conectarButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField nombreNuevaParada;
    private javax.swing.JComboBox<String> primeraParada;
    private javax.swing.JComboBox<String> siguienteParada;
    private javax.swing.JComboBox<String> sucursalComboBox;
    private javax.swing.JComboBox<String> tipoBusqueda;
    // End of variables declaration//GEN-END:variables
}
