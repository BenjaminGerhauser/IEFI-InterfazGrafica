package iefi.interfazgrafica.controladores;

import iefi.interfazgrafica.ModeloBatalla.Batalla;
import iefi.interfazgrafica.ModeloPersonajes.Personaje;
import javax.swing.JButton;
import javax.swing.JTextArea;
import modelosBD.modeloBatalla;
import modelosBD.modeloEstadoPartida;
import vistaBatalla.frmBatalla;

public class ControladorBatalla {

    private Batalla batalla;
    private frmBatalla vista;

    private Personaje heroe;
    private Personaje villano;

    public Personaje getHeroe() {
        return this.heroe;
    }

    public Personaje getVillano() {
        return this.villano;
    }

    public Batalla getBatalla() {
        return this.batalla;
    }

    public ControladorBatalla(Personaje heroe, Personaje villano, frmBatalla vista) {
        this.batalla = new Batalla(heroe, villano);
        this.heroe = heroe;
        this.villano = villano;
        this.vista = vista;
    }

    public void calcualrMaximoAtaque(int idAtacante, int ataqueActual, int idBatalla){
        daos.DAObatalla DAObatalla = new daos.DAObatalla();
        int mayorAtaqueAnterior = DAObatalla.obtenerMayorAtaqueActual(idBatalla);
        if (ataqueActual > mayorAtaqueAnterior ) {
            DAObatalla.actualizarMayorAtaque(idBatalla, ataqueActual, idAtacante);
        }   
    }
    public void siguienteTurno(JTextArea txtEventos, JButton siguienteTurno, JButton siguienteBatalla, int idVillano, int idHeroe, int turnos, int idBatalla, modeloEstadoPartida estadoPartida, ControladorPersonajes ctrlHeroe,ControladorPersonajes ctrlVillano) {
        batalla.siguienteTurno(txtEventos);
        vista.actualizarEstado(batalla);
        
        if (turnos % 2 == 0) {
            calcualrMaximoAtaque(idHeroe, batalla.getHeroe().GetAtaque(), idBatalla);
        }
        else{
            calcualrMaximoAtaque(idVillano, batalla.getVillano().GetAtaque(), idBatalla);
        }
        
        estadoPartida.setVidaHeroe(batalla.getHeroe().GetSalud());
        estadoPartida.setAtaqueHeroe(batalla.getHeroe().GetAtaque());
        estadoPartida.setBendicionHeroe(batalla.getHeroe().GetBendicion());
        estadoPartida.setDefensaHeroe(batalla.getHeroe().GetDefensa());
        
        
        estadoPartida.setVidaVillano(batalla.getVillano().GetSalud());
        estadoPartida.setAtaqueVillano(batalla.getVillano().GetAtaque());
        estadoPartida.setBendicionVillano(batalla.getVillano().GetBendicion());
        estadoPartida.setDefensaVillano(batalla.getVillano().GetDefensa());

        estadoPartida.setTurnoActual(turnos);
        daos.DAOestadoPartida DAOestadoPartida = new daos.DAOestadoPartida();
        DAOestadoPartida.actualizar(estadoPartida);

        daos.DAOpersonaje daoPersonaje = new daos.DAOpersonaje();
        modelosBD.modeloPersonaje modeloVillano = new modelosBD.modeloPersonaje();
        modelosBD.modeloPersonaje modeloHeroe = new modelosBD.modeloPersonaje();

        

        

        daoPersonaje.actualizarEstadisticas(modeloHeroe);
        daoPersonaje.actualizarEstadisticas(modeloVillano);
        // Chequeamos si hay un ganador
        String ganador = batalla.chequearVictoria();
        if (!batalla.getHeroe().estaVivo() || !batalla.getVillano().estaVivo()) {
            txtEventos.setText(ganador);
            siguienteTurno.setEnabled(false);
            siguienteBatalla.setEnabled(true);
            
            modeloHeroe.setId(idHeroe);
            modeloHeroe.setVidaFinal(ctrlHeroe.personaje.GetSalud());
            modeloHeroe.setAtaque(ctrlHeroe.personaje.GetAtaque());
            modeloHeroe.setSupremosUsados(ctrlHeroe.personaje.cantHabilidadesInvocadas);
            modeloHeroe.setArmasInvocadas(ctrlHeroe.personaje.cantArmasInvocadas);
            modeloHeroe.setDefensa(ctrlHeroe.personaje.GetDefensa());
        
            modeloVillano.setId(idVillano);
            modeloVillano.setVidaFinal(ctrlVillano.personaje.GetSalud());
            modeloVillano.setAtaque(ctrlVillano.personaje.GetAtaque());
            modeloVillano.setSupremosUsados(ctrlVillano.personaje.cantHabilidadesInvocadas);
            modeloVillano.setArmasInvocadas(ctrlVillano.personaje.cantArmasInvocadas);
            modeloVillano.setDefensa(ctrlVillano.personaje.GetDefensa());
            //Guardar batalla en la base de datos
            if (batalla.getHeroe().estaVivo()) {
                modelosBD.modeloBatalla modeloBatalla = new modeloBatalla(idHeroe,idVillano, idHeroe, turnos);
                modeloBatalla.setId(idBatalla);
                daos.DAObatalla DAObatalla = new daos.DAObatalla();
                DAObatalla.actualizarResultado(modeloBatalla);
                estadoPartida.setFinalizada(true);
                DAOestadoPartida.actualizar(estadoPartida);
                

                modeloHeroe.setVictorias(modeloHeroe.getVictorias() + 1);
                daoPersonaje.actualizarEstadisticas(modeloHeroe);
            }
            else{
                modelosBD.modeloBatalla modeloBatalla = new modeloBatalla(idHeroe,idVillano, idVillano, turnos);
                modeloBatalla.setId(idBatalla);
                daos.DAObatalla DAObatalla = new daos.DAObatalla();
                DAObatalla.actualizarResultado(modeloBatalla);        
                estadoPartida.setFinalizada(true);
                DAOestadoPartida.actualizar(estadoPartida);
                
                
                modeloVillano.setVictorias(modeloVillano.getVictorias() + 1);
                daoPersonaje.actualizarEstadisticas(modeloVillano);
            }
        }
        

    }
}
