package com.atguigu.web;

import com.atguigu.pojo.User;
import com.atguigu.service.UserService;
import com.atguigu.service.impl.UserServiceImpl;
import com.atguigu.test.UserServletTest;
import com.atguigu.utils.WebUtils;
import com.google.gson.Gson;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * @author CubeMonkey
 * @create 2020-07-29 11:20
 */
@WebServlet("/user")
public class UserServlet extends BaseServlet{
    private UserService userService = new UserServiceImpl();

    /**
     * 注销
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath());
    }

    /**
     * 处理登录的功能
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User loginUser = userService.login(new User(null, username, password, null));

        if (loginUser != null){
            request.getSession().setAttribute("user", loginUser);
            request.getRequestDispatcher("/pages/user/login_success.jsp").forward(request, response);
        }else{
            request.setAttribute("msg", "用户名或密码错误");
            request.setAttribute("username", username);
            System.out.println("登录失败");
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
        }
    }

    /**
     * 处理注册的功能
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String code = request.getParameter("code");

        //获取验证码
        String token = (String) request.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        //删除验证码
        request.getSession().removeAttribute(KAPTCHA_SESSION_KEY);

        User user = WebUtils.copyPramToBean(request.getParameterMap(), new User());

        if (token.equalsIgnoreCase(code)){
            if (userService.existUsername(username)){
                System.out.println("用户名"+username+"不可用");


                //把回显信息，保存到request域中
                request.setAttribute("msg","用户名已存在");
                request.setAttribute("username", username);
                request.setAttribute("email", email);
                request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
            }else{
                userService.registUser(new User(null, username, password, email));
                request.getRequestDispatcher("/pages/user/regist_success.jsp").forward(request, response);
            }
        }else{
            //把回显信息，保存到request域中
            request.setAttribute("msg","验证码错误");
            request.setAttribute("username", username);
            request.setAttribute("email", email);
            System.out.println("验证码错误");
            request.getRequestDispatcher    ("/pages/user/regist.jsp").forward(request, response);
        }
    }

    protected void ajaxExistsUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        boolean existUsername = userService.existUsername(username);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("existUsername", existUsername);
        Gson gson = new Gson();
        String json = gson.toJson(resultMap);

        resp.getWriter().write(json);

    }
}
