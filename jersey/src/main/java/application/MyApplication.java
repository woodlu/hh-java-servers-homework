package application;

import resource.MyCounter;
import service.CounterService;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

public class MyApplication extends Application {

    MyCounter myCounter = new MyCounter();

    @Override
    public Set<Object> getSingletons() {
        Set<Object> s = new HashSet<>();
        s.add(myCounter);
        return s;
    }
}
