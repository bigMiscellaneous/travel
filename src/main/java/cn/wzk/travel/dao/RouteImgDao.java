package cn.wzk.travel.dao;

import cn.wzk.travel.domain.RouteImg;

import java.util.List;

public interface RouteImgDao {

    /**
     * 根据route的id查询图片
     * @param id
     * @return
     */
    public List<RouteImg> findByRid(int id);
}
