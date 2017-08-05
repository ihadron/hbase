package cn.hadron.hbase.dao;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
public class CreateDemo {
	public static void main(String[] args)throws Exception{
		Configuration conf = HBaseConfiguration.create();
		conf.set("hbase.rootdir", "hdfs://cetc/hbase");
        // 设置Zookeeper,直接设置IP地址
        conf.set("hbase.zookeeper.quorum", "node1,node2,node3");
        //建立HBase连接
        Connection connection = ConnectionFactory.createConnection(conf);
        //表管理类
        Admin admin = connection.getAdmin();
        //定义表名
        String tablename="test1";
        TableName tableNameObj = TableName.valueOf(tablename);
        //判断表是否存在
        if (admin.tableExists(tableNameObj)) {
            System.out.println("Table exists!");
            System.exit(0);
        } else {
        	//定义表结构
            HTableDescriptor tableDesc = new HTableDescriptor(TableName.valueOf(tablename));
            //添加列族
            tableDesc.addFamily(new HColumnDescriptor("info"));
            //创建表
            admin.createTable(tableDesc);
            System.out.println("create table success!");
        }
        admin.close();
        connection.close();
	}
}
