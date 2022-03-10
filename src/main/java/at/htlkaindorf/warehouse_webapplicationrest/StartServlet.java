package at.htlkaindorf.warehouse_webapplicationrest;

import at.htlkaindorf.warehouse_webapplicationrest.beans.Pick;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@WebServlet(name = "startServlet", value = "/startServlet")
public class StartServlet extends HttpServlet {
    private List<Pick> picks;

    public void init() {
        //Data
        InputStream is = getClass().getClassLoader().getResourceAsStream("testdata.csv");
        Stream<String> resources = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8)).lines();
        picks = resources.skip(1).map(Pick::fromString).collect(Collectors.toList());
        getServletContext().removeAttribute("picks");
        getServletContext().setAttribute("picks", picks);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.getRequestDispatcher("index.html").include(request,response);
    }
}
