import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import application.MyApplication;

public class JerseyApplication {

  public static void main(String[] args) throws Exception {
    // run, Jetty, run!
    int port = 8081;
    Server server = createServer(port);
    server.start();
    server.join();
  }

  private static Server createServer(int port) {
    Server server = new Server(port);

    ServletContextHandler context = new ServletContextHandler();
    server.setHandler(context);

    ServletContainer servletContainer = new ServletContainer(ResourceConfig.forApplicationClass(MyApplication.class));

    ServletHolder servletHolder = new ServletHolder(servletContainer);
    servletHolder.setInitOrder(1);
    context.addServlet(servletHolder, "/*");

    return server;
  }
}
