package resource;

import dto.GetCounterDto;
import service.CounterService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.stream.Stream;

@Path("/counter")
public class MyCounter {

    private final CounterService counterService = new CounterService();

    @GET
    @Produces("application/json")
    public Response getNumber() {
        GetCounterDto getCounterDto = new GetCounterDto(counterService.getNumber());
        return Response.ok(getCounterDto).build();
    }

    @POST
    public Response increment() {
        counterService.increment();
        return Response.ok().build();
    }

    @DELETE
    public Response subtract(@Context HttpServletRequest request) {
        int subtractionValue = Integer.parseInt(request.getHeader("Subtraction-Value"));
        counterService.subtract(subtractionValue);
        return Response.ok().build();
    }

    @POST
    @Path("/clear")
    public Response clear(@Context HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        Stream<Cookie> cookieStream = cookies != null ? Arrays.stream(cookies) : Stream.empty();

        String cookieValue = cookieStream.filter(cookie -> cookie.getName().equals("hh-auth"))
                .findFirst()
                .orElse(new Cookie("empty", "")).getValue();
        if (cookieValue.length() > 10) {
            counterService.clear();
            return Response.ok("counter set to 0").build();
        } else {
            return Response.status(401, "hhAuth should be > 10").build();
        }
    }
}
