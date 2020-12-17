
import java.io.File;

public class BuscarPendrive {
    private int contador;
    private long megaB;
    private File f;
    private File[] roots;
    private boolean esPendrive;
	
    public BuscarPendrive(){
        contador = 0;
        f = new File(",");
        roots = f.listRoots();
        esPendrive = false;
        for(int i=0;i<roots.length;i++){
            megaB = roots[i].getTotalSpace()/1024;
            if(megaB<16000000 && megaB>0){
                esPendrive=true;
                contador=i;
            }
        }
    }
    
    public boolean hayPendrive(){
        return esPendrive;
    }
    
    public File pendriveDetectado(){
        return roots[contador];
    }
}
