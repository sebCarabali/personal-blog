package com.example.view;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.model.Post;
import com.example.services.PostService;

@WebServlet("/update-post")
public class EditPostServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(EditPostServlet.class.getCanonicalName());

    @Inject
    private PostService postService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPut(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        logger.finest("id: " + id);
        logger.finest("title: " + title);
        Optional<Post> found = postService.findById(id);
        if (found.isPresent()) {
            Post post = found.get();
            post.setContent(content);
            post.setTitle(title);
            postService.save(post);
            resp.sendRedirect(req.getContextPath() + "/admin/dashboard.do?success="
                    + URLEncoder.encode("Post updated successfully.", StandardCharsets.UTF_8));
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Post not found");
        }
    }
}
