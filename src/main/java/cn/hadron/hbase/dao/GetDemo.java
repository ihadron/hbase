package cn.hadron.hbase.dao;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Connection;
public class GetDemo {
	public static void main(String[] args)throws Exception{
		Configuration conf = HBaseConfiguration.create();
		conf.set("hbase.rootdir", "hdfs://cetc/hbase");
        // 设置Zookeeper,直接设置IP地址
        conf.set("hbase.zookeeper.quorum", "node1,node2,node3");
        //建立连接
        Connection connection = ConnectionFactory.createConnection(conf);
        Table table = connection.getTable(TableName.valueOf("test1"));
        //通过rowKey实例化Get
        Get get = new Get(Bytes.toBytes("001"));
        //添加列族名和列名条件
        String family="info";
        String qualifier="name";
        get.addColumn(family.getBytes(), qualifier.getBytes());
        //执行Get，返回结果
        Result result=table.get(get);
        //提取结果
        String value=Bytes.toString(result.getValue(family.getBytes(), qualifier.getBytes()));
        System.out.println("value="+value);
        //关闭表和连接
        table.close();
        connection.close();
	}
}
