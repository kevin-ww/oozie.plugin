package com.zxinsight.oozie;

import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImpalaAddPartitionAction {
  //
  private static Logger LOG = LoggerFactory
      .getLogger(ImpalaAddPartitionAction.class);

  private FsShellUtil shellAction = new FsShellUtil();

  private ImpalaUtil impalaAction = new ImpalaUtil();

  public void execute(String tblName, int year, int month, int day, int hour,
      String type) {
    LOG.info("to see if necessary to add partition");
    boolean accept =
        !shellAction.isWritingData(tblName, year, month, day, hour, type);
    if (accept) {
      impalaAction.addPartition(tblName, year, month, day, hour, type);
    }

  }

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

    new ImpalaAddPartitionAction().execute("dmaevent", 2014, 6, 1, 0,
        "dmaevent");

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
