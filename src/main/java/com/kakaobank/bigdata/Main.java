package com.kakaobank.bigdata;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.shell.Count;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HRegionLocation;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String args[]) {
        TableName tableName = TableName.valueOf("Table1");
        String cf1 = "a";

        Configuration config = HBaseConfiguration.create();

        String path = Main.class.getClassLoader().getResource("hbase-site.xml").getPath();
        config.addResource(new Path(path));

//        Scan scan = new Scan();
        try (Connection conneciton = ConnectionFactory.createConnection(config)) {
            HTable table = (HTable) conneciton.getTable(tableName);
            List<HRegionLocation> list = table.getRegionLocator().getAllRegionLocations();
            for (HRegionLocation regionLocation : list) {
                System.out.println( regionLocation.getRegion().getEncodedName() );
                regionLocation.getRegion().getStartKey();
                regionLocation.getRegion().getEndKey();
                Scan scan = new Scan();
//                scan.withStartRow();
//                scan.withStartRow()

            }

            list.parallelStream().forEach(item -> item.getRegion());

        } catch (IOException ignored) {
            ignored.printStackTrace();
        }


//        HBaseAdmin.checkHBaseAvailable(config);

    }

}


