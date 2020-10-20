package com.atguigu.web;

import com.atguigu.dao.impl.BaseDao;
import com.atguigu.pojo.Book;
import com.atguigu.pojo.Page;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;
import com.atguigu.utils.WebUtils;
import sun.management.snmp.jvmmib.EnumJvmClassesVerboseLevel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author CubeMonkey
 * @create 2020-08-01 17:32
 */
@WebServlet("/manager/bookServlet")
public class BookServlet extends BaseServlet {
    private BookService bookService = new BookServiceImpl();

    /**
     * 处理分页功能
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1 获取请求参数 pageNo 和 pageSize
        int pageNo = WebUtils.parseInt(request.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);
        //2 调用BookService.page(pageNo, pageSize):Page对象
        Page<Book> page = bookService.page(pageNo, pageSize);
        page.setUrl("manager/bookServlet?action=page");
        //3 保存Page对象到Request域中
        request.setAttribute("page", page);
        //4 请求转发到pages/manager/book_manager.jsp页面
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request, response);
    }

    protected void getBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        Book book = bookService.queryBookById(WebUtils.parseInt(id, 0));

        request.setAttribute("book", book);

        request.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(request, response);
    }
    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Book book = WebUtils.copyPramToBean(request.getParameterMap(), new Book());
        int pageNo = WebUtils.parseInt(request.getParameter("pageNo"), 0);
        bookService.addBook(book);
        //本次请求已经结束
        response.sendRedirect("/book/manager/bookServlet?action=page&pageNo="+(pageNo+1));
    }

    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        bookService.deleteBookById(WebUtils.parseInt(id, 0));
        response.sendRedirect("/book/manager/bookServlet?action=page&pageNo="+request.getParameter("pageNo"));
    }

    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Book book = WebUtils.copyPramToBean(request.getParameterMap(), new Book());

        bookService.updateBook(book);

        response.sendRedirect("/book/manager/bookServlet?action=page&pageNo="+request.getParameter("pageNo"));
    }

    protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.通过BookService查询全部图书
        List<Book> books = bookService.queryBooks();
        //2.把全部图书保存到Request域中
        request.setAttribute("books", books);
        //3.请求转发到/pages/manager/book_manager.jsp页面
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request,response);
    }

}
