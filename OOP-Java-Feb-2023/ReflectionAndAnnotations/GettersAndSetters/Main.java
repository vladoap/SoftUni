package ReflectionAndAnnotations.GettersAndSetters;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        Comparator<Method> comparator = Comparator.comparing(Method::getName);

        Set<Method> getters = new TreeSet<>(comparator);
        Set<Method> setters = new TreeSet<>(comparator);


        Class<Reflection> reflectionClass = Reflection.class;
        Method[] methods = reflectionClass.getDeclaredMethods();

        for (Method method : methods) {
            method.setAccessible(true);
            String methodName = method.getName();

            if (methodName.startsWith("get")) {
                getters.add(method);
            } else if (methodName.startsWith("set")) {
                setters.add(method);
            }
        }
//

        getters.forEach(m -> System.out.printf("%s will return class %s%n", m.getName(), m.getReturnType().getSimpleName()));
        setters.forEach(m -> System.out.printf("%s and will set field of class %s%n", m.getName(), m.getParameterTypes()[0].getSimpleName()));

    }
}
