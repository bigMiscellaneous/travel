package cn.wzk.travel.web.servlet;

import cn.wzk.travel.domain.PageBean;
import cn.wzk.travel.domain.Route;
import cn.wzk.travel.domain.User;
import cn.wzk.travel.service.FavoriteService;
import cn.wzk.travel.service.RouteService;
import cn.wzk.travel.service.impl.FavoriteServiceImpl;
import cn.wzk.travel.service.impl.RouteServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/route/*")
public class RouteServlet extends  BaseServlet {

    private RouteService routeService = new RouteServiceImpl();
    private FavoriteService favoriteService = new FavoriteServiceImpl();

    public void pageQuery(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.接受参数
        String currentPageStr = req.getParameter("currentPage");
        String pageSizeStr = req.getParameter("pageSize");
        String cidStr = req.getParameter("cid");
        //接受rname线路名称
        String rname = req.getParameter("rname");

        int cid = 0; //类别id
        //2.处理参数
        if (cidStr != null && cidStr.length() > 0 && !"null".equals(cidStr)) {
            cid = Integer.parseInt(cidStr);
        }

        int currentPage = 0; //当前页码，如果不传递，则默认为第一页
        if (currentPageStr != null && currentPageStr.length() > 0) {
            currentPage = Integer.parseInt(currentPageStr);
        } else {
            currentPage = 1;
        }

        int pageSize = 0;//当前页码，如果不传递，则默认每页显示5条记录
        if (pageSizeStr != null && pageSizeStr.length() > 0) {
            pageSize = Integer.parseInt(pageSizeStr);
        } else {
            pageSize = 5;
        }

        //3.调用service查询PageBean对象
        PageBean<Route> pb = routeService.pageQuery(cid, currentPage, pageSize, rname);

        //4.将pageBean对象序列化json返回
        writeValue(pb, resp);
    }


    /**
     * 根据id查询一个旅游线路的详情信息
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void findOne(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String rid = req.getParameter("rid");
        Route route = routeService.findOne(Integer.parseInt(rid));
        writeValue(route, resp);
    }

    public void isFavorite(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取线路id
        String rid = req.getParameter("rid");

        //2.获取当前登录的用户user
        User user = (User) req.getSession().getAttribute("user");
        int uid;
        if (user == null) {
            //用户尚未登录
            uid = 0;
        } else {
            //用户已经登录
            uid = user.getUid();
        }

        //3.调用FavoriteService查询是否收藏
        boolean flag = favoriteService.isFavorite(Integer.parseInt(rid), uid);

        //4.写回客户端
        writeValue(flag, resp);
    }

    public void addFavorite(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String rid = req.getParameter("rid");
        User user = (User)req.getSession().getAttribute("user");
        int uid;
        if(user == null){
            return ;
        }else{
            uid = user.getUid();
        }
        //添加收藏
        favoriteService.add(Integer.parseInt(rid),uid);
    }
}