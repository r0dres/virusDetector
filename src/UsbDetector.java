import java.io.File;
import java.util.List;

public class UsbDetector
{
    private String nameOs;
    private String dirMacOs;

    public UsbDetector(String nameOs) {
        this.nameOs = nameOs;
        setDirOs();
    }

    public void setDirOs(){
        if(nameOs.contains("Mac Os")) {
            dirMacOs = "/Volume";
        }
    }

    private File[] getDriveDirectories(){
        File [] drives = null;
        if(nameOs.contains("Mac Os")){
            try{
                drives = new File(dirMacOs).listFiles();
            }catch (Exception exp) {
                exp.printStackTrace();
            }
        }
        return drives;
    }

    public List<UsbDrive> getDrives(){
        List<UsbDrive> drives = null;
        File[] dirs = getDriveDirectories();
        for (File file:dirs) {
            file.getName();

        }

        return drives;
    }
}
