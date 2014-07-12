package com.zxinsight.oozie;

import com.google.common.base.Preconditions;

public class JobParameters {

  String tblName;
  int year;
  int month;
  int day;
  int hour;
  String type;

  public JobParameters() {
    // TODO Auto-generated constructor stub
  }

  public JobParameters(String[] args) {

    if (args == null || args.length != 6) {
      throw new IllegalArgumentException(
          "arguments must not be null and the lenght must be 6, args:" + args);
    }

    try {
      this.tblName = args[0];
      this.year = Integer.valueOf(args[1]);
      this.month = Integer.valueOf(args[2]);
      this.day = Integer.valueOf(args[3]);
      this.hour = Integer.valueOf(args[4]);
      this.type = args[5];
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException(
          "arguments are not as exepcted" + args, e);
    }

  }

  public JobParameters(String tblName, int year, int month, int day, int hour,
      String type) {

    // Sanity checks
    Preconditions.checkNotNull(tblName);
    Preconditions.checkNotNull(type);
    //

    this.tblName = tblName;
    this.year = year;
    this.month = month;
    this.day = day;
    this.hour = hour;
    this.type = type;

  }

  public String getTblName() {
    return tblName;
  }

  public int getYear() {
    return year;
  }

  public int getMonth() {
    return month;
  }

  public int getDay() {
    return day;
  }

  public int getHour() {
    return hour;
  }

  public String getType() {
    return type;
  }

  public void setTblName(String tblName) {
    this.tblName = tblName;
  }

  public void setYear(int year) {
    this.year = year;
  }

  public void setMonth(int month) {
    this.month = month;
  }

  public void setDay(int day) {
    this.day = day;
  }

  public void setHour(int hour) {
    this.hour = hour;
  }

  public void setType(String type) {
    this.type = type;
  }

  public void setTblNameAndType(String tblName, String type) {
    setTblName(tblName);
    setType(type);
  }

  public void setDate(int year, int month, int day, int hour) {
    setYear(year);
    setMonth(month);
    setDay(day);
    setHour(hour);
  }

  @Override
  public String toString() {
    return "JobParameters [tblName=" + tblName + ", year=" + year + ", month="
        + month + ", day=" + day + ", hour=" + hour + ", type=" + type + "]";
  }
}
