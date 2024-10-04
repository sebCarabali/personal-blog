package com.example.view;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.model.Post;
import com.example.services.PostService;

@WebServlet("/home")
public class IndexController extends HttpServlet {

    @Inject
    private PostService postService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Post> posts = postService.findAllPost();
        posts.sort((a, b) -> b.getPubTime().compareTo(a.getPubTime()));
        req.setAttribute("posts", posts);
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}
