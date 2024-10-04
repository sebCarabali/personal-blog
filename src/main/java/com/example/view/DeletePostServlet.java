package com.example.view;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.services.PostService;

@WebServlet("/delete-post")
public class DeletePostServlet extends HttpServlet {

    @Inject
    private PostService postService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        postService.delete(id);
        resp.sendRedirect(req.getContextPath() + "/admin/dashboard.do?success="
                    + URLEncoder.encode("Post deleted successfully.", StandardCharsets.UTF_8));
    }
}
