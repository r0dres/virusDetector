import java.io.File;

public class Executer
{
    private UsbDetector usbDetector;
    private OSTest osTest;

    public Executer(){
        this.osTest = new OSTest();
        this.usbDetector = new UsbDetector(osTest.getOsName());
    }

    public void run(){
        File[] files =  usbDetector.getDriveDirectories();
    }
}
