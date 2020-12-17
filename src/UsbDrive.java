public class UsbDrive {

    private long size;
    private String nameDrive;
    private String path;

    public UsbDrive(long size, String nameDrive, String path) {
        this.size = size;
        this.nameDrive = nameDrive;
        this.path = path;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getNameDrive() {
        return nameDrive;
    }

    public void setNameDrive(String nameDrive) {
        this.nameDrive = nameDrive;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
