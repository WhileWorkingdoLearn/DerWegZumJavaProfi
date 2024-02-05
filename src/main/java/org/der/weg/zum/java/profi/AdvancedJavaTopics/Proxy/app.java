package org.der.weg.zum.java.profi.AdvancedJavaTopics.Proxy;

import org.der.weg.zum.java.profi.AdvancedJavaTopics.Proxy.IService;
import org.der.weg.zum.java.profi.AdvancedJavaTopics.Proxy.InvocationHandler;
import org.der.weg.zum.java.profi.AdvancedJavaTopics.Proxy.Proxy;
import org.der.weg.zum.java.profi.AdvancedJavaTopics.Proxy.Service;

public class app {
    public static void main(String[] args) {
        final IService service = createProxyService();
        service.calculateSomething(42);
    }

    private static IService createService() {
        final IService service = new Service();
        return new Proxy(service);
    }

    private static IService createProxyService(){
            final  IService service = new Service();

            final InvocationHandler handler = new InvocationHandler(service);

            final Class<?>[] proxyInterfaces = {IService.class};

            return (IService) java.lang.reflect.Proxy.newProxyInstance(Service.class.getClassLoader(),proxyInterfaces,handler);

    }
}
