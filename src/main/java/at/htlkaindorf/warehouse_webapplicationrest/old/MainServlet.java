package at.htlkaindorf.warehouse_webapplicationrest.old;

import at.htlkaindorf.warehouse_webapplicationrest.beans.Pick;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@WebServlet(name = "mainServlet", value = "/mainServlet")
public class MainServlet extends HttpServlet {

    private List<Pick> picks;

    public void init() {
        //Config
        InputStream is = getClass().getClassLoader().getResourceAsStream("config.csv");
        List<String> config = new BufferedReader(
                new InputStreamReader(is, StandardCharsets.UTF_8)).lines().collect(Collectors.toList());

        getServletContext().removeAttribute("sourceAmount");
        getServletContext().setAttribute("sourceAmount", Integer.parseInt(config.get(0).split(":")[1]));
        getServletContext().removeAttribute("targetAmount");
        getServletContext().setAttribute("targetAmount", Integer.parseInt(config.get(1).split(":")[1]));

        //Data
        is = getClass().getClassLoader().getResourceAsStream("old/testdata.csv");
        Stream<String> resources = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8)).lines();
        picks = resources.skip(1).map(Pick::fromString).collect(Collectors.toList());
        getServletContext().removeAttribute("picks");
        getServletContext().setAttribute("picks", picks);
        //TestDataServlet.setTargetContainerContent(picks);

        //TestDataServlet_old.setTargetContainerContent(Integer.parseInt(config.get(1).split(":")[1]),picks);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.getRequestDispatcher("index.html").include(request,response);
    }

    public void destroy() {
    }
}