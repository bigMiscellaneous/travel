package test;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.sql.rowset.JdbcRowSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class JedisTest1 {

    @Test
    public void test(){
        Jedis jedis = new Jedis("localhost",6379);
        jedis.set("username","zhangsan");
        jedis.close();
    }

    @Test
    public void test1(){
        Jedis jedis = new Jedis();
        jedis.set("password","123");
        String password = jedis.get("password");
        System.out.println(password);
        jedis.setex("sex",20,"man");
        jedis.close();
    }


    @Test
    public void test3(){
        Jedis jedis = new Jedis("localhost",6379);
        jedis.hset("user","name","zhangsan");
        jedis.hset("user","age","23");
        jedis.hset("user","sex","male");
        String name = jedis.hget("user","name");
        System.out.println(name);
        Map<String,String> map = jedis.hgetAll("user");
        Set<String> set = map.keySet();
        for(String str : set){
            String value = map.get(str);
            System.out.println(str + ":" + value);
        }
        jedis.close();
    }

    @Test
    public void test4(){
        Jedis jedis = new Jedis("localhost",6379);
        jedis.lpush("mylist","a","b","c");
        jedis.rpush("mylist","a","b","c");
        List<String> list = jedis.lrange("mylist",0,-1);
        System.out.println(list);
        jedis.lpop("mylist");
        jedis.rpop("mylist");
        List<String> list1 = jedis.lrange("mylist",0,-1);
        System.out.println(list1);
        jedis.close();
    }

    @Test
    public void test5(){
        Jedis jedis = new Jedis("localhost",6379);
        jedis.sadd("set","Java","Php","go","Java");
        Set<String> set = jedis.smembers("set");
        for(String name : set){
            System.out.println(name);
        }
        jedis.close();
    }

    @Test
    public void test6(){
        Jedis jedis = new Jedis("localhost",6379);
        jedis.zadd("myset",20,"张叶");
        jedis.zadd("myset",100,"胡贤佳");
        jedis.zadd("myset",50,"鲁慧雪");
        Set<String> set = jedis.zrange("myset",0,-1);
        for(String name : set){
            System.out.println(name);
        }
        jedis.close();
    }

    @Test
    public void test7(){
        GenericObjectPoolConfig con = new GenericObjectPoolConfig();
        con.setMaxIdle(60);
        con.setMaxTotal(100);
        JedisPool jedisPool = new JedisPool(con,"localhost",6379);
        Jedis jedis = jedisPool.getResource();
        jedis.set("name","wangzhikang");
        jedis.close();
        jedisPool.close();
    }

    @Test
    public void test8(){
        Jedis jedis = JedisUtis.getJedis();
        jedis.set("name","huxianjia");
        jedis.close();
    }
}
