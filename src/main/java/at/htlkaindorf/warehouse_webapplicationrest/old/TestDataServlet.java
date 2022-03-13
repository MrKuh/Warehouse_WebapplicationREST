package at.htlkaindorf.warehouse_webapplicationrest.old;

import at.htlkaindorf.warehouse_webapplicationrest.beans.Pick;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "testDataServlet", value = "/testDataServlet")
public class TestDataServlet extends HttpServlet {

    private static List<Pick> allPick;
    private List<Pick> picks;
    private List<Pick> sendPicks;


    public static void setTargetContainerContent(List<Pick> all) {
        allPick = all;
    }


    @Override
    public void init() throws ServletException {
        picks = (List<Pick>) getServletContext().getAttribute("picks");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ObjectMapper om = new ObjectMapper();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String next = request.getParameter("next").toString();

        try (PrintWriter out = response.getWriter()) {
            Pick pick = picks.get(0);
            if("next".equals(next)){
                pick = picks.get(1);
            }

            String data = om.writeValueAsString(pick);
            out.println(data);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper om = new ObjectMapper();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try (PrintWriter out = response.getWriter()) {
            picks.remove(0);
            Pick pick = picks.get(0);


            String data = om.writeValueAsString(pick);
            out.println(data);
        }

    }
}