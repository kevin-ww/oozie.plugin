import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import org.apache.hadoop.util.Shell.ExitCodeException;
import org.apache.hadoop.util.Shell.ShellCommandExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AddPartitionAction {
  //
  private static Logger LOG = LoggerFactory.getLogger(AddPartitionAction.class);

  public static void main(String[] args) throws Exception {

    // get current time;
    // extract parameters from current time like year,month,day and hour.
    // execute the shell command to verify if those HDFS files are already there
    // and has no tmp snappy files under the given path;
    // execute the add partition command on impala;
    // capture the output and verify;
    // execute the show partitions command to verify the previous command has
    // been executed successfully

    //
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

    String[] commandArgs =
        { "ls", "/home/kvn/workspace/zx.solution/hs.v2/dmaooziejob/scripts" };
    // { command };

    String output = exec(commandArgs);
    System.out.println(output);
  }

  public static String exec(final String[] args) throws Exception {

    ShellCommandExecutor shexec = new ShellCommandExecutor(args);

    LOG.info("now executing shell command "
        + Arrays.toString(shexec.getExecString()));
    shexec.execute();

    return shexec.getOutput();

  }
}
