package com.zxinsight.oozie;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

public class ImpalaUtil {

  private static Logger LOG = LoggerFactory.getLogger(ImpalaUtil.class);

  private static JdbcTemplate jdbcTemplate = null;

  static {
    @SuppressWarnings("resource")
    AbstractApplicationContext context =
        new ClassPathXmlApplicationContext("jdbc-context.xml",
            ImpalaUtil.class);
    context.registerShutdownHook();

    jdbcTemplate = (JdbcTemplate) context.getBean("jdbcTemplate");

  }

  public static final String ADD_PARTITION =
      "ALTER TABLE %s ADD PARTITION (year='%04d', month='%02d',day='%02d',hour='%02d',type='%s')";

  public void addPartition(String tblName, int year, int month, int day,
      int hour, String type) {

    String addPartitionStatement =
        String.format(ADD_PARTITION, tblName, year, month, day, hour, type);
    LOG.info("going to execute this statement :" + addPartitionStatement);
    try {
      jdbcTemplate.execute(addPartitionStatement);
    } catch (DataAccessException e) {
      LOG.error("data exception occured while trying add partition", e);
    }

  }

}
