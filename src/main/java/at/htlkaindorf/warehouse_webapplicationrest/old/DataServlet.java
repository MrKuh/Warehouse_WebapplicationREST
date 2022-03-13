package at.htlkaindorf.warehouse_webapplicationrest.old;

import at.htlkaindorf.warehouse_webapplicationrest.beans.Pick;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@WebServlet(name = "startServlet", value = "/startServlet")
public class DataServlet extends HttpServlet {



}
