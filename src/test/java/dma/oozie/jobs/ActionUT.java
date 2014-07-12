package dma.oozie.jobs;

import java.util.Calendar;

public class ActionUT {
  public static void main(String[] args) {
    Calendar startTime = Calendar.getInstance();
    startTime.set(2014, 7, 10, 0, 0);

    System.out.println("now checking time ");
    System.out.println("startime ->" + startTime);
    Calendar now = Calendar.getInstance();
    now.add(Calendar.MONTH, +1);
    System.out.println("now ->" + now);

    while (startTime.before(now)) {
      test(startTime);
      startTime.add(Calendar.HOUR_OF_DAY, +1);
    }

  }

  public static void test(Calendar startTime) {

    // Calendar startTime = Calendar.getInstance();
    //
    // startTime.set(2014, 6, 1, 0, 0);

    int year = startTime.get(Calendar.YEAR);
    int month = startTime.get(Calendar.MONTH);
    int day = startTime.get(Calendar.DAY_OF_MONTH);
    int hour = startTime.get(Calendar.HOUR_OF_DAY);
    int min = startTime.get(Calendar.MINUTE);

    String commandPattern =
        "hdfs dfs -ls -R hdfs://hdnode1:8020/hs/dmaphase1/dmaevent/year=%04d/month=%02d/day=%02d/hour=%02d";

    String command = String.format(commandPattern, year, month, day, hour, min);

    System.out.println(command);
  }

}
