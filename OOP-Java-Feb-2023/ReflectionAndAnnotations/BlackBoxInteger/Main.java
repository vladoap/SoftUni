package ReflectionAndAnnotations.BlackBoxInteger;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {

        Scanner scan = new Scanner(System.in);

        Class<BlackBoxInt> clazz = BlackBoxInt.class;
        Constructor<BlackBoxInt> constructor = clazz.getDeclaredConstructor();
        constructor.setAccessible(true);
        BlackBoxInt blackBoxInt = constructor.newInstance();


        String command = scan.nextLine();

        while (!"END".equals(command)) {
            String commandType = command.split("_")[0];
            int number = Integer.parseInt(command.split("_")[1]);

            Method methodToInvoke = clazz.getDeclaredMethod(commandType, int.class);
            methodToInvoke.setAccessible(true);
            methodToInvoke.invoke(blackBoxInt, number);
            Field valueField = clazz.getDeclaredField("innerValue");
            valueField.setAccessible(true);
            System.out.println(valueField.get(blackBoxInt));

            command = scan.nextLine();
        }


    }
}
