package cn.wzk.travel.service;

import cn.wzk.travel.domain.PageBean;
import cn.wzk.travel.domain.Route;

/**
 * 线路service
 */
public interface RouteService {

    /**
     * 根据类别进分页查询
     * @param cid
     * @param currentPage
     * @param pageSize
     * @return
     */
    public PageBean<Route> pageQuery(int cid,int currentPage,int pageSize,String rname);

    /**
     * 根据id查询
     * @param rid
     * @return
     */
    public Route findOne(int rid);
}
