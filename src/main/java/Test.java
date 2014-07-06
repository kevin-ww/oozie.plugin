import org.apache.hadoop.conf.Configuration;
import org.springframework.data.hadoop.fs.FsShell;

public class Test {

  public static void main(String[] args) {
    //
    Configuration configuration = new Configuration();

    // use spring api
    FsShell fsShell = new FsShell(configuration);
    String dir = "/hs";
    String r = fsShell.ls(dir).toString();
    System.out.println(r);
    // use hadoop api
    org.apache.hadoop.fs.FsShell shell = new org.apache.hadoop.fs.FsShell(configuration);

    // String x = shell.
  }
}
