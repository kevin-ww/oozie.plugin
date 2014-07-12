package com.zxinsight.oozie;

import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {

  private static Logger LOG = LoggerFactory.getLogger(App.class);

  public static void main(String[] args) throws Exception {
    //
    // String[] jobArgs = { "dmaevent", "2014", "6", "1", "0", "dmaevent" };

    // ImpalaAddPartitionAction.main(jobArgs);

    // add partition for dmaevent;
    exec("dmaevent", "dmaevent");
    // add partition for dmalog;
    // exec("dmalog", "dmalog");

    // add partition for table :events;
    // exec("events", "dmalog");
    // exec("events", "dmaevent");

  }

  public static void exec(String tblName, String type) {
    Calendar startTime = Calendar.getInstance();
    // start time ;
    startTime.set(2014, 6, 1, 0, 0);
    //
    LOG.info("starting time " + startTime);

    JobParameters jpLogEvent = new JobParameters();
    jpLogEvent.setTblNameAndType(tblName, type);

    Calendar now = Calendar.getInstance();
    now.add(Calendar.MONTH, +1);
    LOG.info("now is  " + now);

    int year, month, day, hour;

    while (startTime.before(now)) {
      // test(startTime);
      year = startTime.get(Calendar.YEAR);
      month = startTime.get(Calendar.MONTH);
      day = startTime.get(Calendar.DAY_OF_MONTH);
      hour = startTime.get(Calendar.HOUR_OF_DAY);
      jpLogEvent.setDate(year, month, day, hour);
      submitJob(jpLogEvent);
      startTime.add(Calendar.HOUR_OF_DAY, +1);
    }
  }

  public static void submitJob(JobParameters jobParameter) {
    LOG.info("submitting job " + jobParameter);
    new ImpalaAddPartitionAction().execute(jobParameter);
    LOG.info("ending job " + jobParameter);
  }

  public static void test(Calendar startTime) {

    // Calendar startTime = Calendar.getInstance();
    //
    // startTime.set(2014, 6, 1, 0, 0);

    int year = startTime.get(Calendar.YEAR);
    int month = startTime.get(Calendar.MONTH);
    int day = startTime.get(Calendar.DAY_OF_MONTH);
    int hour = startTime.get(Calendar.HOUR_OF_DAY);
    // int min = startTime.get(Calendar.MINUTE);

    String commandPattern =
        "hdfs dfs -ls -R hdfs://hdnode1:8020/hs/dmaphase1/dmaevent/year=%04d/month=%02d/day=%02d/hour=%02d";

    String command = String.format(commandPattern, year, month, day, hour);

    System.out.println(command);
  }

}
