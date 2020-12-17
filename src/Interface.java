import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

public class Interface extends JFrame{
    
    JButton bActualizar, bIniciar, bEliminar;
    JLabel lUnidad;
    JPanel jPanel;
    JTabbedPane jPestanas;
    JTextArea jTAanalizador,jTAvirus, jTAsospechosos;
    
    ManejadorDeBotones manejador;
    
    BuscarPendrive bPendrive;
    
    Analizador analizador;
    
    public Interface(){
        super("Detector de Virus");
        setSize(375,500);
        setResizable(false);
        setLayout(null);

        manejador = new ManejadorDeBotones();
        
        bActualizar = new JButton ("Actualizar");
        bActualizar.setIcon(new ImageIcon("src/res/img/actualizar.png"));
        
        bEliminar = new JButton("Eliminar Virus");
        bEliminar.setIcon(new ImageIcon("src/res/img/eliminarAmenazas1.png"));
        
        lUnidad = new JLabel("");
        
        bIniciar = new JButton ("Iniciar");
        bIniciar.setIcon(new ImageIcon("src/res/img/lupa.png"));
        
        jPestanas = new JTabbedPane();
        jTAanalizador = new JTextArea();
        jTAvirus = new JTextArea();
        jTAsospechosos = new JTextArea();
        
        JScrollPane scrolll = new JScrollPane(jTAanalizador);
        JScrollPane scroll2 = new JScrollPane(jTAvirus);
        JScrollPane scroll3 = new JScrollPane(jTAsospechosos);
        
        jPestanas.addTab("analizados",null,scrolll);
        jPestanas.addTab("Virus",null,scroll2);
        jPestanas.addTab("Sospechosos",null,scroll3);
        
        bActualizar.setBounds(20,20,145,50);
        bIniciar.setBounds(195,20,140,50);
        bEliminar.setBounds(195, 90, 165, 60);
        
        bIniciar.setEnabled(false);
        bEliminar.setEnabled(false);
        
        lUnidad.setBounds(14,100,250,50);
        jPestanas.setBounds(10,200,350,250);
        
        bActualizar.addActionListener(manejador);
        bIniciar.addActionListener(manejador);
        bEliminar.addActionListener(manejador);
        
        getContentPane().add(jPestanas);
        
        add(jPestanas);
        add(bActualizar);
        add(lUnidad);
        add(bIniciar);
        add(bEliminar);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new Interface();
    }

    
    private class ManejadorDeBotones implements ActionListener{
        
        @Override
        public void actionPerformed(ActionEvent e) {
            String accion = e.getActionCommand();
            if(accion.equals(bActualizar.getText())){
                bPendrive = new BuscarPendrive();
                if(bPendrive.hayPendrive() == true){
                    lUnidad.setText("Unidad "+ bPendrive.pendriveDetectado() + " Detectada");
                    lUnidad.setIcon(new ImageIcon("src/res/img/pendrive.png"));
                    bIniciar.setEnabled(true);
                    bEliminar.setEnabled(true);
                }
                else{
                    lUnidad.setIcon(new ImageIcon("src/res/imagenes/noPendrive.png"));
                    lUnidad.setText("No hay ningun Pendrive Conectado");
                    jTAanalizador.setText("");
                    jTAsospechosos.setText("");
                    jTAvirus.setText("");
                    bIniciar.setEnabled(false);
                }
            }
            
            if(accion.equals(bIniciar.getText())){
                analizador = new Analizador();
                analizador.recorrerArchivos(bPendrive.pendriveDetectado());
                analizador.detectarVirus();
                
                for(int i=0; i<analizador.mostrarArchivos().size() ;i++){
                    jTAanalizador.append(analizador.mostrarArchivos().get(i)+"\n");
                    jTAanalizador.repaint();
                }   
                
                for(int i=0;i<analizador.mostrarVirusEncontrados().size();i++){
                    jTAvirus.append(analizador.mostrarVirusEncontrados().get(i)+"\n");
                }
                
                analizador.archivosSospechosos(bPendrive.pendriveDetectado());
                
                for(int i=0;i<analizador.mostrarSospechosos().size();i++){
                    jTAsospechosos.append(analizador.mostrarSospechosos().get(i)+"\n");
                }   
            }
            
            if(accion.equals(bEliminar.getText())){
                ArrayList aElim = analizador.mostrarVirusEncontrados();
                for(int i=0; i<aElim.size(); i++){
                    String ruta = (String)aElim.get(i);
                    System.out.println(ruta);
                }
                ArrayList elim = analizador.mostrarAEliminar();
                for(int i=0; i<elim.size(); i++){
                    File f = (File)elim.get(i);
                    if(f.delete()){
                        JOptionPane.showMessageDialog(null, " El archivo se elimino correctamente \n" 
                                + f.getAbsolutePath(), "Felicidades", JOptionPane.INFORMATION_MESSAGE);
                    }else{
                        JOptionPane.showMessageDialog(null, "El archivo NO se pudo eliminar \n" 
                                + f.getAbsolutePath(),"Error Fatal", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }        
    }
}