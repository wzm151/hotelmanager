package com.newer.web.servlet;

import com.google.gson.Gson;
import com.newer.mapper.RoomsMapper;
import com.newer.service.RoomsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
@WebServlet("/rooms")
public class RoomsServlet extends HttpServlet {

    private RoomsService roomsService=new RoomsService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
   this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("application/json;charset=utf-8");

        String action = req.getParameter("action");

        if(action.equals("1")){
            query(req,resp);
        }else if(action.equals("roomtype1")){
            query1(req,resp);
        }

    }

    private void query1(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String roomtype=req.getParameter("roomtype");

       List<Integer>list= roomsService.findRoomid(roomtype);
        Gson gson=new Gson();
        PrintWriter out =resp.getWriter();
        out.print(gson.toJson(list));
        out.close();
    }

    private void query(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<String>list =roomsService.findTypes();
        Gson gson=new Gson();
        PrintWriter out =resp.getWriter();
        out.print(gson.toJson(list));
        out.close();
    }
}
