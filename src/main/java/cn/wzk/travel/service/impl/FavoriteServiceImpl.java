package cn.wzk.travel.service.impl;

import cn.wzk.travel.dao.FavoriteDao;
import cn.wzk.travel.dao.impl.FavoriteDaoImpl;
import cn.wzk.travel.domain.Favorite;
import cn.wzk.travel.service.FavoriteService;

public class FavoriteServiceImpl implements FavoriteService {

    private FavoriteDao favoriteDao = new FavoriteDaoImpl();

    @Override
    public boolean isFavorite(int rid,int uid){
        Favorite favorite = favoriteDao.findByRidAndUid(rid,uid);
        if(favorite == null){
            return false;
        }
        else{
            return true;
        }
    }

    @Override
    public void add(int rid,int uid){
        favoriteDao.add(rid,uid);
    }
}
