package cn.hadron.hbase.dao;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Connection;
public class ScanDemo {
	public static void main(String[] args)throws Exception{
		Configuration conf = HBaseConfiguration.create();
		conf.set("hbase.rootdir", "hdfs://cetc/hbase");
        // 设置Zookeeper,直接设置IP地址
        conf.set("hbase.zookeeper.quorum", "node1,node2,node3");
        //建立连接
        Connection connection = ConnectionFactory.createConnection(conf);
        Table table = connection.getTable(TableName.valueOf("test1"));
        //初始化Scan
        Scan scan = new Scan();
        //指定开始的rowKey
        scan.setStartRow("001".getBytes());
        //指定结束的rowKey
        scan.setStopRow("005".getBytes());
        //添加过滤条件
        String family="info";
        String qualifier="name";
        scan.addColumn(family.getBytes(), qualifier.getBytes());
        //执行scan返回结果
        ResultScanner result=table.getScanner(scan);
        //迭代提取结果
        String value="";
        for(Result r:result){
        	 value=Bytes.toString(r.getValue(family.getBytes(), qualifier.getBytes())); 	
        	 System.out.println("value="+value);
        }   
        //关闭表和连接
        table.close();
        connection.close();
	}
}
