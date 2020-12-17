import java.io.File;
import java.text.DecimalFormat;

public class Test {
    public static String readableFileSize(long size){
        if(size<0) return "0";
        final String[]units = new String[]{"B", "kB", "MB", "GB", "TB"};
        int digitGroups = (int)(Math.log10(size)/Math.log10(1024));
        return new DecimalFormat("#,##0.#").format(size/Math.pow(1024, digitGroups))+" "+ units[digitGroups];
    }

    public static void main(String[] args) {
        File file = new File("/Volumes");
        File[] dev = file.listFiles();
        double esp;
        for (File f: dev) {
            System.out.println(f.getName() + "---" + readableFileSize(f.getFreeSpace()));
        }


        //system's roots to identify devices
//        File[]roots = File.listRoots();
//        for (File f:roots)
//        {
//            System.out.println(f.getAbsolutePath());
//        }

    }
}
