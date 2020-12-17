
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Analizador {
    
    ArrayList<File>   buffer;
    ArrayList<String> bufferVirus;
    ArrayList<File>   bufferArchivos;
    ArrayList<File>   bufferSospechosos;
    ArrayList<File>   bufferVirusAEliminar;
    File              fBDvirus;
    FileReader        fr;
    BufferedReader    br;
	
    public Analizador(){
        buffer               = new ArrayList<>();
        bufferArchivos       = new ArrayList<>();
        bufferVirus          = new ArrayList<>();
        bufferSospechosos    = new ArrayList<>();
        bufferVirusAEliminar = new ArrayList<>();
        fBDvirus          = null;
        fr                = null;
        br                = null;
    }
	
    public void recorrerArchivos(File dir){
        try{
            File directorio[] = dir.listFiles();
            for (File directorio1 : directorio) {
                if (directorio1.isDirectory() == true) {
                    recorrerArchivos(directorio1);
                }else{
                    buffer.add(directorio1);
                }
            }
        }catch(Exception e){
            System.out.println("algo salio mal"+ e.getMessage());
        }
    }
	
    public void detectarVirus(){
        try{
            fBDvirus = new File("src/virus/virus.txt");
            fr       = new FileReader(fBDvirus);
            br       = new BufferedReader(fr);
            String linea;
            while((linea = br.readLine())!=null){
                for(int i=0; i<buffer.size(); i++){
                    if(linea.equals(buffer.get(i).getName())){
                        bufferVirus.add(linea);
                        bufferVirusAEliminar.add(buffer.get(i));
                    }
                }
            }
        }catch(IOException e){
            System.out.println("algo salio mal   "+ e.getMessage());
        }
    }
    
    public void archivosSospechosos(File dir){
        try{
            File directorio[]=dir.listFiles();
            for (File directorio1 : directorio) {
                if (directorio1.isDirectory() == true) {
                    recorrerArchivos(directorio1);
                }else{
                    if (directorio1.canWrite() == false) {
                        if (directorio1.isHidden()) {
                            bufferSospechosos.add(directorio1);
                        }
                    }
                }
            }
        }catch(Exception e){
            System.out.println("algo salio mal"+ e.getMessage());
        }
    }
    
    public ArrayList<File> mostrarArchivos(){
        return buffer;
    }
	
    public ArrayList<String> mostrarVirusEncontrados(){
        return bufferVirus;
    }

    public ArrayList<File> mostrarSospechosos(){
        return bufferSospechosos;
    }
    
    public ArrayList<File> mostrarAEliminar(){
        return bufferVirusAEliminar;
    }
    
}