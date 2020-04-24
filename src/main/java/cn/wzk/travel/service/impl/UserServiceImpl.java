package cn.wzk.travel.service.impl;

import cn.wzk.travel.dao.UserDao;
import cn.wzk.travel.dao.impl.UserDaoImpl;
import cn.wzk.travel.domain.User;
import cn.wzk.travel.service.UserService;
import cn.wzk.travel.util.MailUtils;
import cn.wzk.travel.util.UuidUtil;


public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    @Override
    public boolean regist(User user) {
        //1.根据用户名查询用户对象
        User u = userDao.findByUsername(user.getUsername());
        //判断u是否为空
        if(u != null){
            return false;
        }else{
            //2.保存用户信息
            //2.1设置激活码，唯一字符串
            user.setCode(UuidUtil.getUuid());
            //2.2设置激活码状态
            user.setStatus("N");
            userDao.save(user);
            //3.邮件发送，邮件正文
            String content = "<a href = 'http://localhost:8080/travel/user/active?code=" + user.getCode() + "'>点击激活，【黑马旅游网】</a>";
            MailUtils.sendMail(user.getEmail(),content,"激活邮件");
            return true;
        }
    }

    @Override
    public boolean active(String code){
        //1.根据激活码查询用户对象
        User user = userDao.findByCode(code);

        if(user != null){
            //2.调用dao的修改激活状态的对象
            userDao.updateStatus(user);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public User login(User user){
        User u = userDao.findByUsernameAndPassword(user.getUsername(),user.getPassword());
        return u;
    }
}
