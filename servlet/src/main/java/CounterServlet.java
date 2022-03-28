import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class CounterServlet extends HttpServlet {

    public static final Counter COUNTER = new Counter();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws IOException {
        PrintWriter writer = resp.getWriter();
        writer.println(COUNTER.getNumber());
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        COUNTER.increment();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        int subtractionValue = Integer.parseInt(req.getHeader("Subtraction-Value"));
        COUNTER.subtract(subtractionValue);
    }
}
