package com.zxinsight.oozie;

import java.util.Collection;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.hadoop.fs.FsShell;

public class FsShellUtil {

  private static Logger LOG = LoggerFactory.getLogger(FsShellUtil.class);

  private static String HDFS_DIR_PATTERN =
      "/hs/dmaphase1/%s/year=%04d/month=%02d/day=%02d/hour=%02d/type=%s";

  private static String TEMP_SNAPPY_FILE_SUFFIX = ".snappy.tmp";

  private static FsShell fsShell = new FsShell(new Configuration());

  public boolean isWritingData(String tblName, int year, int month, int day,
      int hour, String type) {

    String hdfsDir =
        String.format(HDFS_DIR_PATTERN, tblName, year, month, day, hour, type);

    Collection<FileStatus> files = fsShell.ls(hdfsDir);

    LOG.info("files under this directory are : [\n" + files.toString() + "\n]");

    for (FileStatus file : files) {
      if (file.isFile()) {
        String fileName = file.getPath().getName();
        if (fileName.endsWith(TEMP_SNAPPY_FILE_SUFFIX)) {
          LOG.info("data are writing to the stream, try later ");
          return true;
        }
      }
    }

    LOG.info("there is no tmp files under this directory: " + hdfsDir);

    return false;
  }


}
