package com.zxinsight.oozie;

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
    JobParameters jobParameters =
        new JobParameters(tblName, year, month, day, hour, type);
    execute(jobParameters);
  }

  public void execute(JobParameters jobParameters) {

    LOG.info("to add partition");

    String tblName = jobParameters.getTblName();
    int year = jobParameters.getYear();
    int month = jobParameters.getMonth();
    int day = jobParameters.getDay();
    int hour = jobParameters.getHour();
    String type = jobParameters.getType();

    boolean accept =
        !shellAction.isWritingData(tblName, year, month, day, hour, type);
    if (accept) {
      impalaAction.addPartition(tblName, year, month, day, hour, type);
    }

  }

  public static void main(String[] args) throws Exception {

    // JobParameters jobParams = ImpalaAddPartitionAction.convert(args);

    // get current time;
    // extract parameters from current time like year,month,day and hour.
    // execute the shell command to verify if those HDFS files are already there
    // and has no tmp snappy files under the given path;
    // execute the add partition command on impala;
    // capture the output and verify;
    // execute the show partitions command to verify the previous command has
    // been executed successfully

    //

    JobParameters jobParams = new JobParameters(args);

    new ImpalaAddPartitionAction().execute(jobParams);

  }

}