package com.example.view;

import java.io.IOException;
import java.util.Optional;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.model.Post;
import com.example.services.PostService;

@WebServlet("/show")
public class ShowController extends HttpServlet {

    Logger logger = Logger.getLogger(ShowController.class.getCanonicalName());

    @Inject
    private PostService postService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        logger.info("Id: " + id);
        Optional<Post> post = postService.findById(id);
        if (post.isPresent()) {
            req.setAttribute("post", post.get());
            req.getRequestDispatcher("post.jsp").forward(req, resp);
        } else{
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Post with id not found.");
        }
    }
}
