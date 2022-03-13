package at.htlkaindorf.warehouse_webapplicationrest.old;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "configServlet", value = "/configServlet")
public class ConfigServlet extends HttpServlet {

    public void init() {
        //Config
        InputStream is = getClass().getClassLoader().getResourceAsStream("config.csv");
        List<String> config = new BufferedReader(
                new InputStreamReader(is, StandardCharsets.UTF_8)).lines().map(line -> line.split(":")[1]).collect(Collectors.toList());

        getServletContext().removeAttribute("sourceAmount");
        getServletContext().setAttribute("sourceAmount", Integer.parseInt(config.get(0).split(":")[1]));
        getServletContext().removeAttribute("targetAmount");
        getServletContext().setAttribute("targetAmount", Integer.parseInt(config.get(1).split(":")[1]));
    }

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
