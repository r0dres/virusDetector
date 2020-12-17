
import java.io.*;

public final class prueba {
    
    public prueba(){	
        File f = new File(",");
        File [] roots = File.listRoots();
        try{
            for(int i=0;i<roots.length;i++){
                System.out.println(i+""+ roots[i]);
                System.out.println(i+""+ roots[i].getAbsolutePath());
                System.out.println(""+ roots[i].getCanonicalPath());
                System.out.println(i+" espacio libre "+ roots[i].getFreeSpace());
                System.out.println(i+""+ roots[i].getName());
                System.out.println(i+""+ roots[i].getParent());
                System.out.println(i+""+ roots[i].getPath());
                System.out.println(i+" espacio total "+ roots[i].getTotalSpace());
                System.out.println(i+" espacio que se puede usar "+ roots[i].getUsableSpace());
                System.out.println(i+""+ roots[i].getAbsoluteFile());
                System.out.println(""+ roots[i].getCanonicalFile());
                System.out.println(i+""+ roots[i].getClass());
                System.out.println(i+""+ roots[i].getParentFile());
            }
        }catch(IOException e){}
            recorrerArchivos(roots[3]);
    }
    
    public void recorrerArchivos(File dir){
        try{
            File directorio[]=dir.listFiles();
            for(int i=0;i<directorio.length;i++){
                if(directorio[i].isDirectory()==true){
                    recorrerArchivos(directorio[i]);
                }else{
                    System.out.println(i+".- unidad " + directorio[i]);
                }
            }	
        }catch(Exception e){
            System.out.println("algo salio mal"+ e.getMessage());
        }
    }
	
    public static void main(String args[]){
//        Interface p = new Interface();
//        p.setBounds(400,150,375,500);
        new prueba();
    }
    
}
