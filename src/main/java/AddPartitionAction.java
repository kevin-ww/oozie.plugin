import java.util.Calendar;
import java.util.Collection;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.hadoop.fs.FsShell;

public class AddPartitionAction {
  //
  private static Logger LOG = LoggerFactory.getLogger(AddPartitionAction.class);

  private static String HDFS_DIR_PATTERN =
      "/hs/dmaphase1/dmaevent/year=%04d/month=%02d/day=%02d/hour=%02d/type=%s";

  private static String TEMP_SNAPPY_FILE_SUFFIX = ".snappy.tmp";

  private static FsShell fsShell = new FsShell(new Configuration());

  public static void execute(int year, int month, int day, int hour, String type) {

    String hdfsDir =
        String.format(HDFS_DIR_PATTERN, year, month, day, hour, type);

    Collection<FileStatus> files = fsShell.ls(hdfsDir);
    LOG.info("files under this directory are : [\n" + files.toString() + "\n]");
    for (FileStatus file : files) {
      if (file.isFile()) {
        String fileName = file.getPath().getName();
        if (fileName.endsWith(TEMP_SNAPPY_FILE_SUFFIX)) {
          LOG.info("having data stream , try later ");
          return;
        }
      }
    }
    // add partition;

    //
  }

  public static boolean isValid() {
    return true;
  }

  public static void main(String[] args) throws Exception {

    execute(2014, 6, 1, 0, "dmaevent");

    // get current time;
    // extract parameters from current time like year,month,day and hour.
    // execute the shell command to verify if those HDFS files are already there
    // and has no tmp snappy files under the given path;
    // execute the add partition command on impala;
    // capture the output and verify;
    // execute the show partitions command to verify the previous command has
    // been executed successfully

    //

  }

  public void test() {
    Calendar now = Calendar.getInstance();

    int year = now.get(Calendar.YEAR);
    int month = now.get(Calendar.MONTH) + 1;
    int day = now.get(Calendar.DAY_OF_MONTH);
    int hour = now.get(Calendar.HOUR_OF_DAY);
    int min = now.get(Calendar.MINUTE);

    String commandPattern =
        "hdfs dfs -ls -R hdfs://hdnode1:8020/hs/dmaphase1/dmaevent/year=%04d/month=%02d/day=%02d/hour=%02d";
    String command = String.format(commandPattern, year, month, day, hour, min);

    System.out.println(command);
  }

}
