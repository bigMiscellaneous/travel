package cn.wzk.travel.dao;

import cn.wzk.travel.domain.User;

public interface UserDao {

    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    public User findByUsername(String username);

    /**
     * 根据激活吗查询对象
     */
    public User findByCode(String code);

    /**
     *修改制定用户激活状态
     * @param user
     */
    public void updateStatus(User user);

    /**
     * 用户保存
     * @param user
     */
    public void save(User user);

    /**
     * 根据用户名和密码查询用户
     * @param username
     * @param password
     * @return
     */
    User findByUsernameAndPassword(String username,String password);
}
