import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class ServletApplication {

  public static void main(String[] args) throws Exception {
    // run, Jetty, run!
    int port = 8080;
    Server server = createServer(port);
    server.start();
    server.join();
  }

  private static Server createServer(int port) {
    Server server = new Server(port);

    ServletHandler servletHandler = new ServletHandler();
    servletHandler.addServletWithMapping(CounterServlet.class, "/counter");
    servletHandler.addServletWithMapping(ClearCounterServlet.class, "/counter/clear");
    servletHandler.addFilterWithMapping(ClearFilter.class, "/counter/clear", 0);
    server.setHandler(servletHandler);
    return server;
  }
}
