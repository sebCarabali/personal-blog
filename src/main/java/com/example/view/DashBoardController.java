package com.example.view;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.model.Post;
import com.example.services.PostService;

@WebServlet("/admin/dashboard.do")
public class DashBoardController extends HttpServlet {

    private static final Logger logger = Logger.getLogger(DashBoardController.class.getCanonicalName());

    @Inject
    private PostService postService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Post> posts = postService.findAllPost().stream()
        .sorted((a, b) -> b.getPubTime().compareTo(a.getPubTime())).collect(Collectors.toList());
        logger.info("Posts: " + posts.toString());
        req.setAttribute("posts", posts);
        req.getRequestDispatcher("/admin/dashboard.jsp").forward(req, resp);
    }
}
