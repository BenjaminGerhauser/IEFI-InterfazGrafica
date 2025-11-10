/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vistaBatalla;

import iefi.interfazgrafica.ModeloBatalla.Batalla;
import iefi.interfazgrafica.ModeloPersonajes.Personaje;
import iefi.interfazgrafica.controladores.ControladorBatalla;
import iefi.interfazgrafica.controladores.ControladorPersonajes;
import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.plaf.basic.BasicProgressBarUI;
import modelosBD.modeloBatalla;

/**
 *
 * @author Usuario
 */
public class frmBatalla extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(frmBatalla.class.getName());

    private ControladorBatalla ctrlBatalla;

    private int batallaActual = 1;
    private int cantidadBatallas = 1;

    private int vidaMaximaHeroe;
    private int vidaMaximaVillano;

    // 🔹 Valores originales para reiniciar batallas
    private int saludOriginalHeroe;
    private int ataqueOriginalHeroe;
    private int defensaOriginalHeroe;
    private int bendicionOriginalHeroe;

    private int saludOriginalVillano;
    private int ataqueOriginalVillano;
    private int defensaOriginalVillano;
    private int bendicionOriginalVillano;
    
    private int idVillano;
    private int idHeroe;
    private int turnos;
    private int idBatalla;
    private modelosBD.modeloEstadoPartida modeloEstadoPartida;

    /**
     * Creates new form frmBatalla
     */
    public frmBatalla() {
        initComponents();
    }

    public frmBatalla(ControladorPersonajes ctrlHeroe, ControladorPersonajes ctrlVillano, int cantidadPartidas, boolean habilidadPermitida, int idVillano, int idHeroe) {
        initComponents();

        this.cantidadBatallas = cantidadPartidas;

        ctrlHeroe.personaje.habilidadPermitida = habilidadPermitida;
        ctrlVillano.personaje.habilidadPermitida = habilidadPermitida;
        
        pbVidaHeroe.setUI(new javax.swing.plaf.basic.BasicProgressBarUI());
        pbVidaVillano.setUI(new javax.swing.plaf.basic.BasicProgressBarUI());
        
        ctrlBatalla = new ControladorBatalla(ctrlHeroe.personaje, ctrlVillano.personaje, this);

        lblBatallaActual.setText("Batalla Actual: Partida " + batallaActual + "/" + cantidadPartidas);
        txtEventos.setText("⚔️  ¡Comienza la batalla entre " + ctrlHeroe.personaje.GetApodo() + " y " + ctrlVillano.personaje.GetApodo() + "!");

        pbVidaHeroe.setMaximum(ctrlHeroe.personaje.GetSalud());
        pbVidaVillano.setMaximum(ctrlVillano.personaje.GetSalud());
        pbVidaHeroe.setValue(ctrlHeroe.personaje.GetSalud());
        pbVidaVillano.setValue(ctrlVillano.personaje.GetSalud());

        /* DATOS HEROE */
        lblApodoHeroe.setText(ctrlHeroe.personaje.GetApodo());
        lblVidaHeroe.setText("Vida: " + ctrlHeroe.personaje.GetSalud());
        lblDefensaHeroe.setText("Defensa: " + ctrlHeroe.personaje.GetDefensa());
        lblAtaqueHeroe.setText("Ataque: " + ctrlHeroe.personaje.GetAtaque());
        lblBendicionHeroe.setText("Bendicion: " + ctrlHeroe.personaje.GetBendicion());
        vidaMaximaHeroe = ctrlHeroe.personaje.GetSalud();

        /* DATOS VILLANO */
        lblApodoVillano.setText(ctrlVillano.personaje.GetApodo());
        lblVidaVillano.setText("Vida: " + ctrlVillano.personaje.GetSalud());
        lblDefensaVillano.setText("Defensa: " + String.valueOf(ctrlVillano.personaje.GetDefensa()));
        lblAtaqueVillano.setText("Ataque: " + String.valueOf(ctrlVillano.personaje.GetAtaque()));
        lblBendicionVillano.setText("Bendicion: " + String.valueOf(ctrlVillano.personaje.GetBendicion()));
        vidaMaximaVillano = ctrlVillano.personaje.GetSalud();

        // ✅ Guardar valores originales de ambos personajes
        saludOriginalHeroe = ctrlHeroe.personaje.GetSalud();
        ataqueOriginalHeroe = ctrlHeroe.personaje.GetAtaque();
        defensaOriginalHeroe = ctrlHeroe.personaje.GetDefensa();
        bendicionOriginalHeroe = ctrlHeroe.personaje.GetBendicion();

        saludOriginalVillano = ctrlVillano.personaje.GetSalud();
        ataqueOriginalVillano = ctrlVillano.personaje.GetAtaque();
        defensaOriginalVillano = ctrlVillano.personaje.GetDefensa();
        bendicionOriginalVillano = ctrlVillano.personaje.GetBendicion();

        this.idVillano = idVillano;
        this.idHeroe = idHeroe;
        // Acción de botón
        
        guardarBatallaYEstadoInicial();
        
        btnSiguienteTurno.addActionListener(e -> ctrlBatalla.siguienteTurno(txtEventos, btnSiguienteTurno, btnSiguienteBatalla, idVillano, idHeroe, this.turnos + 1, idBatalla, modeloEstadoPartida));
    }

    private void guardarBatallaYEstadoInicial(){
        modelosBD.modeloBatalla modeloBatalla = new modeloBatalla(idHeroe,idVillano, null, turnos + 1);
        daos.DAObatalla DAObatalla = new daos.DAObatalla();
        this.idBatalla = DAObatalla.guardar(modeloBatalla);
        
        
        modelosBD.modeloEstadoPartida modeloEstadoPartida = new modelosBD.modeloEstadoPartida(idBatalla, idHeroe,idVillano,saludOriginalHeroe, saludOriginalVillano,ataqueOriginalHeroe,defensaOriginalHeroe,ataqueOriginalVillano,defensaOriginalVillano,bendicionOriginalHeroe, bendicionOriginalVillano,turnos);
        modeloEstadoPartida.setFinalizada(false);
        daos.DAOestadoPartida DAOestadoPartida = new daos.DAOestadoPartida();
        int idEstadoPartida = DAOestadoPartida.guardar(modeloEstadoPartida);
        modeloEstadoPartida.setId(idEstadoPartida);
        this.modeloEstadoPartida = modeloEstadoPartida;
    }
    
    
    private void actualizarColorBarra(JProgressBar barra) {

        int max = barra.getMaximum();
        int value = barra.getValue(); // clamp

        int porcentaje = (value * 100) / max; // <-- SIEMPRE con el máximo real de la barra
        if (porcentaje <= 25 && porcentaje > 5) {
            barra.setForeground(Color.ORANGE);
        } else if (porcentaje <= 5) {
            barra.setForeground(Color.RED);
        } else {
            barra.setForeground(Color.GREEN);
        }

        barra.repaint();
    }

    public void actualizarEstado(Batalla modelo) {
        lblTurnoActual.setText("Turno actual: " + modelo.getTurno());
        this.turnos = modelo.getTurno();

        // 🧍‍♂️ HÉROE
        int vidaHeroe = modelo.getHeroe().GetSalud();
        pbVidaHeroe.setValue(vidaHeroe);
        lblVidaHeroe.setText("Vida: " + vidaHeroe);
        lblDefensaHeroe.setText("Defensa: " + modelo.getHeroe().GetDefensa());
        lblAtaqueHeroe.setText("Ataque: " + modelo.getHeroe().GetAtaque());
        lblBendicionHeroe.setText("Bendición: " + modelo.getHeroe().GetBendicion());

        actualizarColorBarra(pbVidaHeroe);

        // 😈 VILLANO
        int vidaVillano = modelo.getVillano().GetSalud();
        pbVidaVillano.setValue(vidaVillano);
        lblVidaVillano.setText("Vida: " + vidaVillano);
        lblDefensaVillano.setText("Defensa: " + modelo.getVillano().GetDefensa());
        lblAtaqueVillano.setText("Ataque: " + modelo.getVillano().GetAtaque());
        lblBendicionVillano.setText("Energia del Vacio: " + modelo.getVillano().GetBendicion());
        actualizarColorBarra(pbVidaVillano);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblTurnoActual = new javax.swing.JLabel();
        lblBatallaActual = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        lblVidaHeroe = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblApodoHeroe = new javax.swing.JLabel();
        pbVidaHeroe = new javax.swing.JProgressBar();
        lblDefensaHeroe = new javax.swing.JLabel();
        lblAtaqueHeroe = new javax.swing.JLabel();
        lblBendicionHeroe = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        lblBendicionVillano = new javax.swing.JLabel();
        lblApodoVillano = new javax.swing.JLabel();
        lblVidaVillano = new javax.swing.JLabel();
        pbVidaVillano = new javax.swing.JProgressBar();
        lblDefensaVillano = new javax.swing.JLabel();
        lblAtaqueVillano = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtEventos = new javax.swing.JTextArea();
        btnSiguienteBatalla = new javax.swing.JButton();
        btnSiguienteTurno = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        btnGuardarPartida = new javax.swing.JMenuItem();
        btnSalir = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        btnHistorial = new javax.swing.JMenuItem();
        btnEstadisticas = new javax.swing.JMenuItem();
        btnRanking = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Batalla");

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setText("Información de Partida");

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblTurnoActual.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblTurnoActual.setText("Turno Actual: Turno 1");

        lblBatallaActual.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblBatallaActual.setText("Batalla Actual: Partida xx/xx");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTurnoActual)
                    .addComponent(lblBatallaActual))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(lblBatallaActual)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTurnoActual)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel4.setText("Estado de Personajes");

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblVidaHeroe.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblVidaHeroe.setText("Vida: xx");
        lblVidaHeroe.setToolTipText("");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setText("Héroe");

        lblApodoHeroe.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblApodoHeroe.setText("Apodo");

        pbVidaHeroe.setForeground(new java.awt.Color(51, 255, 51));
        pbVidaHeroe.setValue(100);

        lblDefensaHeroe.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblDefensaHeroe.setText("Defensa: xx");
        lblDefensaHeroe.setToolTipText("");

        lblAtaqueHeroe.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblAtaqueHeroe.setText("Ataque: xx");
        lblAtaqueHeroe.setToolTipText("");

        lblBendicionHeroe.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblBendicionHeroe.setText("Bendicion: xx");
        lblBendicionHeroe.setToolTipText("");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pbVidaHeroe, javax.swing.GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblVidaHeroe)
                            .addComponent(lblDefensaHeroe)
                            .addComponent(lblAtaqueHeroe)
                            .addComponent(lblBendicionHeroe)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addComponent(lblApodoHeroe)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(lblApodoHeroe))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblVidaHeroe)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pbVidaHeroe, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblDefensaHeroe)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblAtaqueHeroe)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblBendicionHeroe)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setText("Villano");

        lblBendicionVillano.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblBendicionVillano.setText("Energia del vacio: xx");
        lblBendicionVillano.setToolTipText("");

        lblApodoVillano.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblApodoVillano.setText("Apodo");

        lblVidaVillano.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblVidaVillano.setText("Vida: xx");
        lblVidaVillano.setToolTipText("");

        pbVidaVillano.setForeground(new java.awt.Color(51, 255, 51));
        pbVidaVillano.setValue(100);

        lblDefensaVillano.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblDefensaVillano.setText("Defensa: xx");
        lblDefensaVillano.setToolTipText("");

        lblAtaqueVillano.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblAtaqueVillano.setText("Ataque: xx");
        lblAtaqueVillano.setToolTipText("");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pbVidaVillano, javax.swing.GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblVidaVillano)
                            .addComponent(lblDefensaVillano)
                            .addComponent(lblAtaqueVillano)
                            .addComponent(lblBendicionVillano)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(lblApodoVillano)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(lblApodoVillano))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblVidaVillano)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pbVidaVillano, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblDefensaVillano)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblAtaqueVillano)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblBendicionVillano)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        jLabel9.setText("Eventos");

        txtEventos.setColumns(20);
        txtEventos.setRows(5);
        txtEventos.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtEventos.setEnabled(false);
        jScrollPane1.setViewportView(txtEventos);

        btnSiguienteBatalla.setText("Siguiente Batalla");
        btnSiguienteBatalla.setEnabled(false);
        btnSiguienteBatalla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteBatallaActionPerformed(evt);
            }
        });

        btnSiguienteTurno.setText("Siguiente Turno");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel9)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 10, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(282, 282, 282)
                .addComponent(btnSiguienteTurno)
                .addGap(18, 18, 18)
                .addComponent(btnSiguienteBatalla)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSiguienteTurno)
                    .addComponent(btnSiguienteBatalla))
                .addGap(14, 14, 14))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jMenu1.setText("Partida");

        btnGuardarPartida.setText("Guardar Partida");
        btnGuardarPartida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarPartidaActionPerformed(evt);
            }
        });
        jMenu1.add(btnGuardarPartida);

        btnSalir.setText("Salir");
        jMenu1.add(btnSalir);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Ver");

        btnHistorial.setText("Historial de Partidas");
        btnHistorial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHistorialActionPerformed(evt);
            }
        });
        jMenu2.add(btnHistorial);

        btnEstadisticas.setText("Estadísticas");
        btnEstadisticas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEstadisticasActionPerformed(evt);
            }
        });
        jMenu2.add(btnEstadisticas);

        btnRanking.setText("Ranking Personajes");
        btnRanking.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRankingActionPerformed(evt);
            }
        });
        jMenu2.add(btnRanking);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSiguienteBatallaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteBatallaActionPerformed
        // Verificar si quedan batallas por jugar
        if (batallaActual < cantidadBatallas) {
            batallaActual++;

            // 🔹 Restaurar estadísticas originales del héroe
            Personaje heroe = ctrlBatalla.getHeroe();
            heroe.SetSalud(saludOriginalHeroe);
            heroe.SetAtaque(ataqueOriginalHeroe);
            heroe.SetDefensa(defensaOriginalHeroe);
            heroe.SetBendicion(bendicionOriginalHeroe);
            heroe.arma = null;
            heroe.habilidad = null;

            // 🔹 Restaurar estadísticas originales del villano
            Personaje villano = ctrlBatalla.getVillano();
            villano.SetSalud(saludOriginalVillano);
            villano.SetAtaque(ataqueOriginalVillano);
            villano.SetDefensa(defensaOriginalVillano);
            villano.SetBendicion(bendicionOriginalVillano);
            villano.arma = null;
            villano.habilidad = null;

            // 🔹 Reiniciar el modelo de batalla (turnos, contador, etc.)
            ctrlBatalla.getBatalla().reiniciarTurnos();

            // 🔹 Actualizar interfaz
            lblBatallaActual.setText("Batalla Actual: Partida " + batallaActual + "/" + cantidadBatallas);
            lblTurnoActual.setText("Turno actual: 1");

            pbVidaHeroe.setMaximum(saludOriginalHeroe);
            pbVidaVillano.setMaximum(saludOriginalVillano);
            pbVidaHeroe.setValue(saludOriginalHeroe);
            pbVidaVillano.setValue(saludOriginalVillano);
            // Refrescar color inicial (100%)
            actualizarColorBarra(pbVidaHeroe);
            actualizarColorBarra(pbVidaVillano);

            lblVidaHeroe.setText("Vida: " + saludOriginalHeroe);
            lblVidaVillano.setText("Vida: " + saludOriginalVillano);
            lblDefensaHeroe.setText("Defensa: " + defensaOriginalHeroe);
            lblDefensaVillano.setText("Defensa: " + defensaOriginalVillano);
            lblAtaqueHeroe.setText("Ataque: " + ataqueOriginalHeroe);
            lblAtaqueVillano.setText("Ataque: " + ataqueOriginalVillano);
            lblBendicionHeroe.setText("Bendición: " + bendicionOriginalHeroe);
            lblBendicionVillano.setText("Energía del Vacío: " + bendicionOriginalVillano);

            txtEventos.setText("🔄 ¡Comienza la Batalla " + batallaActual + " entre "
                    + heroe.GetApodo() + " y " + villano.GetApodo() + "!\n");

            // 🔹 Habilitar / deshabilitar botones
            btnSiguienteTurno.setEnabled(true);
            btnSiguienteBatalla.setEnabled(false);
            
            guardarBatallaYEstadoInicial();
        } else {
            // 🔹 Si ya se jugaron todas las batallas
            txtEventos.append("\n🏁 Todas las batallas han finalizado.\n");
            btnSiguienteBatalla.setEnabled(false);
            btnSiguienteTurno.setEnabled(false);
        }
    }//GEN-LAST:event_btnSiguienteBatallaActionPerformed

    private void btnHistorialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHistorialActionPerformed
        vistaReportes.frmHistorialBatallas ventana = new vistaReportes.frmHistorialBatallas();
        ventana.setLocationRelativeTo(this);
        ventana.setVisible(true);
    }//GEN-LAST:event_btnHistorialActionPerformed

    private void btnEstadisticasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEstadisticasActionPerformed
        vistaReportes.frmEstadisticas ventana = new vistaReportes.frmEstadisticas();
        ventana.setLocationRelativeTo(this);
        ventana.setVisible(true);
    }//GEN-LAST:event_btnEstadisticasActionPerformed

    private void btnRankingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRankingActionPerformed
        vistaReportes.frmRanking ventana = new vistaReportes.frmRanking();
        ventana.setLocationRelativeTo(this);
        ventana.setVisible(true);
    }//GEN-LAST:event_btnRankingActionPerformed
    private void btnGuardarPartidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarPartidaActionPerformed
        modeloEstadoPartida.setVidaHeroe(ctrlBatalla.getHeroe().GetSalud());
        modeloEstadoPartida.setAtaqueHeroe(ctrlBatalla.getHeroe().GetAtaque());
        modeloEstadoPartida.setBendicionHeroe(ctrlBatalla.getHeroe().GetBendicion());
        modeloEstadoPartida.setDefensaHeroe(ctrlBatalla.getHeroe().GetDefensa());
        
        
        modeloEstadoPartida.setVidaVillano(ctrlBatalla.getVillano().GetSalud());
        modeloEstadoPartida.setAtaqueVillano(ctrlBatalla.getVillano().GetAtaque());
        modeloEstadoPartida.setBendicionVillano(ctrlBatalla.getVillano().GetBendicion());
        modeloEstadoPartida.setDefensaVillano(ctrlBatalla.getVillano().GetDefensa());

        modeloEstadoPartida.setTurnoActual(turnos);
        daos.DAOestadoPartida DAOestadoPartida = new daos.DAOestadoPartida();
        DAOestadoPartida.actualizar(modeloEstadoPartida);
    }//GEN-LAST:event_btnGuardarPartidaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new frmBatalla().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem btnEstadisticas;
    private javax.swing.JMenuItem btnGuardarPartida;
    private javax.swing.JMenuItem btnHistorial;
    private javax.swing.JMenuItem btnRanking;
    private javax.swing.JMenuItem btnSalir;
    private javax.swing.JButton btnSiguienteBatalla;
    private javax.swing.JButton btnSiguienteTurno;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblApodoHeroe;
    private javax.swing.JLabel lblApodoVillano;
    private javax.swing.JLabel lblAtaqueHeroe;
    private javax.swing.JLabel lblAtaqueVillano;
    private javax.swing.JLabel lblBatallaActual;
    private javax.swing.JLabel lblBendicionHeroe;
    private javax.swing.JLabel lblBendicionVillano;
    private javax.swing.JLabel lblDefensaHeroe;
    private javax.swing.JLabel lblDefensaVillano;
    private javax.swing.JLabel lblTurnoActual;
    private javax.swing.JLabel lblVidaHeroe;
    private javax.swing.JLabel lblVidaVillano;
    private javax.swing.JProgressBar pbVidaHeroe;
    private javax.swing.JProgressBar pbVidaVillano;
    private javax.swing.JTextArea txtEventos;
    // End of variables declaration//GEN-END:variables
}
