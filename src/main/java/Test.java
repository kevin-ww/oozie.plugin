import java.util.Collection;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.Path;
import org.springframework.data.hadoop.fs.FsShell;

public class Test {

  public static void main(String[] args) {
    //
    Configuration configuration = new Configuration();

    // use spring api
    FsShell fsShell = new FsShell(configuration);
    String dir = "/hs";
    Collection<FileStatus> files = fsShell.ls(dir);
    for (FileStatus file : files) {
      System.out.println(file.toString());
      Path p = file.getPath();
      String name = p.getName();
      System.out.println(name);
    }
  }
}
