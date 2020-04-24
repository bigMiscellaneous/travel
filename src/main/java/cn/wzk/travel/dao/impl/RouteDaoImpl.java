package cn.wzk.travel.dao.impl;

import cn.wzk.travel.dao.RouteDao;
import cn.wzk.travel.domain.Route;
import cn.wzk.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class RouteDaoImpl implements RouteDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public int findTotalCount(int cid,String rname) {
        //String sql = "select count(*) from tab_route where cid = ?";
        //1.定义sql模板
        String sql = "select count(*) from tab_route where 1 = 1";
        StringBuffer res = new StringBuffer(sql);
        List params = new ArrayList(); //条件
        //判断参数是否有值
        if(cid != 0){
            res.append(" and cid = ?");
            params.add(cid); //？对应的值
        }

        if(rname != null && rname.length() > 0){
            res.append(" and rname like ?");
            params.add("%" + rname + "%");
        }
        sql = res.toString();
        return template.queryForObject(sql,Integer.class,params.toArray());
    }

    @Override
    public List<Route> findByPage(int cid, int start, int pageSize,String rname) {
        //String sql = "select * from tab_route where cid = ? limit ? , ?";
        String sql = "select * from tab_route where 1 = 1 ";
        StringBuffer res = new StringBuffer(sql);
        List params = new ArrayList();
        //2.判断参数是否有值
        if(cid != 0){
            res.append(" and cid = ? ");
            params.add(cid);
        }
        if(rname != null && rname.length() > 0){
            res.append(" and rname like ? ");
            params.add("%" + rname + "%");
        }
        res.append(" limit ?,?");
        params.add(start);
        params.add(pageSize);
        sql = res.toString();
        return template.query(sql,new BeanPropertyRowMapper<Route>(Route.class),params.toArray());
    }

    @Override
    public Route findOne(int rid){
        String sql = "select * from tab_route where rid = ?";
        return template.queryForObject(sql,new BeanPropertyRowMapper<Route>(Route.class),rid);
    }
}
