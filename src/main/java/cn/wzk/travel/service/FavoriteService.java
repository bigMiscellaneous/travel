package cn.wzk.travel.service;

public interface FavoriteService {

    /**
     * 判断是否收藏
     * @param rid
     * @param uid
     * @return
     */
    public boolean isFavorite(int rid,int uid);

    public void add(int rid,int uid);
}
