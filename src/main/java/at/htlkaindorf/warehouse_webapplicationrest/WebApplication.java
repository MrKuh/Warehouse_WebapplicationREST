package at.htlkaindorf.warehouse_webapplicationrest;

import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@WebListener
@ApplicationPath("/api")
public class WebApplication extends Application implements ServletContextListener {

    /*@Override
    public void contextInitialized(ServletContextEvent sce){
        CustomerDB.getInstance().setXmlFile(sce.getServletContext().getRealPath("/xml/customerList.xml"));
        CustomerDB.getInstance().loadXMLData();
    }

     */
}