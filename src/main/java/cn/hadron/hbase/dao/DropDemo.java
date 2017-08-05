package cn.hadron.hbase.dao;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
public class DropDemo {
	public static void main(String[] args)throws Exception{
		Configuration conf = HBaseConfiguration.create();
		conf.set("hbase.rootdir", "hdfs://cetc/hbase");
        // 设置Zookeeper,直接设置IP地址
        conf.set("hbase.zookeeper.quorum", "node1,node2,node3");
        //建立连接
        Connection connection = ConnectionFactory.createConnection(conf);
        //表管理类
        Admin admin = connection.getAdmin();
        //定义表名
        TableName table = TableName.valueOf("test1");
        //先禁用
        admin.disableTable(table);
        //再删除
        admin.deleteTable(table);
        //关闭
        admin.close();
        connection.close();
        System.out.println("Successfully deleted data table！");
	}
}
