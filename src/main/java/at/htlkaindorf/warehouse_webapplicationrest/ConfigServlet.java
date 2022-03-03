package at.htlkaindorf.warehouse_webapplicationrest;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

@WebServlet(name = "configServlet", value = "/configServlet")
public class ConfigServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ObjectMapper om = new ObjectMapper();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        try (PrintWriter out = resp.getWriter()) {
            HashMap<String, Integer> config = new HashMap<String, Integer>();
            config.put("sourceAmount", (int) getServletContext().getAttribute("sourceAmount"));
            config.put("targetAmount", (int) getServletContext().getAttribute("targetAmount"));
            String data = om.writeValueAsString(config);
            out.println(data);
        }
    }

}
