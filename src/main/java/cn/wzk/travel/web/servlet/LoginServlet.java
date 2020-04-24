package cn.wzk.travel.web.servlet;

import cn.wzk.travel.domain.ResultInfo;
import cn.wzk.travel.domain.User;
import cn.wzk.travel.service.UserService;
import cn.wzk.travel.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {

    private UserService service = new UserServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取用户名和密码数据
        Map<String, String[]> map = request.getParameterMap();
        //2.封装User对象
        User user = new User();
        try {
            BeanUtils.populate(user,map);
        }catch (Exception e){
            e.printStackTrace();
        }

        //调用service查询
        User u = service.login(user);

        ResultInfo info = new ResultInfo();

        //判断用户对象是否为null
        if(u != null){
            String status = u.getStatus();
            if("Y".equals(status)){
                request.getSession().setAttribute("user",u);//登录成功标记

                //登录成功
                info.setFlag(true);
            }else{
                //用户尚未激活
                info.setFlag(false);
                info.setErrorMsg("您尚未激活，请激活");
            }
        }else{
            //用户名或密码错误
            info.setFlag(false);
            info.setErrorMsg("用户名或密码错误");
        }

        //响应数据
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(),info);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
