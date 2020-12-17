public class OSTest
{
    private String os;

    public OSTest() {
        os = System.getProperty("os.name");
    }

    public String getOsName(){
        return os;
    }

}
