import java.util.List;

import org.apache.hadoop.hive.service.HiveClient;
import org.springframework.data.hadoop.hive.HiveClientCallback;
import org.springframework.data.hadoop.hive.HiveTemplate;

public class HiveX {
  private HiveTemplate template;

  public void setHiveTemplate(HiveTemplate template) {
    this.template = template;
  }

  public List<String> getDbs() {
    return template.execute(new HiveClientCallback<List<String>>() {
      @Override
      public List<String> doInHive(HiveClient hiveClient) throws Exception {
        return hiveClient.get_all_databases();
      }
    });
  }

  public static void main(String[] args) throws Exception {
    List<String> dbs = new HiveX().getDbs();
    System.out.println(dbs);
  }
}
