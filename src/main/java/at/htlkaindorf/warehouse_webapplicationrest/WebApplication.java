package at.htlkaindorf.warehouse_webapplicationrest;

import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@WebListener
@ApplicationPath("/api")
public class WebApplication extends Application implements ServletContextListener {
}