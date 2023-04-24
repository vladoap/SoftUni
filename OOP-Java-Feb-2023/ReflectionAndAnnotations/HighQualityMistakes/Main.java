package ReflectionAndAnnotations.HighQualityMistakes;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

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

        Set<Field> fields = Arrays.stream(reflectionClass.getDeclaredFields())
                .collect(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Field::getName))));

        for (Field field : fields) {
            field.setAccessible(true);
            int modifiers = field.getModifiers();
            if (!Modifier.isPrivate(modifiers)) {
                System.out.println(field.getName() + " must be private!");
            }
        }

        for (Method getter : getters) {
            getter.setAccessible(true);
            int modifiers = getter.getModifiers();
            if (!Modifier.isPublic(modifiers)) {
                System.out.println(getter.getName() + " have to be public!");
            }
        }

        for (Method setter : setters) {
            setter.setAccessible(true);
            int modifiers = setter.getModifiers();
            if (!Modifier.isPrivate(modifiers)) {
                System.out.println(setter.getName() + " have to be private!");
            }
        }


    }
}
