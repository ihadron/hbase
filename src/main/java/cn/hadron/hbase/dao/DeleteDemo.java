package cn.hadron.hbase.dao;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Connection;
public class DeleteDemo {
	public static void main(String[] args)throws Exception{
		Configuration conf = HBaseConfiguration.create();
		conf.set("hbase.rootdir", "hdfs://cetc/hbase");
        // 设置Zookeeper,直接设置IP地址
        conf.set("hbase.zookeeper.quorum", "node1,node2,node3");
        //建立连接
        Connection connection = ConnectionFactory.createConnection(conf);
        //获取表
        Table table = connection.getTable(TableName.valueOf("test1"));
        //通过rowKey实例化Delete
        Delete delete=new Delete(Bytes.toBytes("001"));
        //指定列族名、列名和值
        String family="info";
        String qualifier="name";
        delete.addColumn(family.getBytes(), qualifier.getBytes());
        //执行Delete
        table.delete(delete);
        //关闭表和连接
        table.close();
        connection.close();
        System.out.println("ok!");
	}
}
