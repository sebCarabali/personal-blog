package com.example.view;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.model.Post;
import com.example.services.PostService;

@WebServlet("/create-post")
public class CreatePostServlet extends HttpServlet {

    @Inject
    private PostService postService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        LocalDateTime pubDate = LocalDateTime.now();
        String uuid = UUID.randomUUID().toString();
        postService.save(new Post(uuid, title, content, pubDate));
        resp.sendRedirect(req.getContextPath() + "/admin/dashboard.do?success="
                    + URLEncoder.encode("Post saved.", StandardCharsets.UTF_8));
    }
}
