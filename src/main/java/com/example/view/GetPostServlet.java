package com.example.view;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.mappers.PostMapper;
import com.example.model.Post;
import com.example.services.PostService;

@WebServlet("/get-post")
public class GetPostServlet extends HttpServlet {

    @Inject
    private PostService postService;
    @Inject
    private PostMapper postMapper;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        Optional<Post> post = postService.findById(id);
        if (post.isPresent()) {
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().println(postMapper.mapToJson(post.get()));
            resp.getWriter().flush();
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Post not found");
        }
    }
}
