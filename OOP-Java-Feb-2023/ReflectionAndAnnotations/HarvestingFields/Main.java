package ReflectionAndAnnotations.HarvestingFields;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        Class<RichSoilLand> clazz = RichSoilLand.class;
        Field[] fields = clazz.getDeclaredFields();

        String command = scan.nextLine();
        Consumer<Field> printer = field -> System.out.println(String.format("%s %s %s",
                Modifier.toString(field.getModifiers()),
                field.getType().getSimpleName(),
                field.getName()));
        while (!command.equals("HARVEST")) {
            switch (command) {
                case "private":
					Arrays.stream(fields).filter(f -> Modifier.isPrivate(f.getModifiers())).forEach(printer);
					break;
                case "protected":
                    Arrays.stream(fields).filter(f -> Modifier.isProtected(f.getModifiers())).forEach(printer);
                    break;
                case "public":
					Arrays.stream(fields).filter(f -> Modifier.isPublic(f.getModifiers())).forEach(printer);
					break;
                case "all":
					Arrays.stream(fields).forEach(printer);
					break;
            }


            command = scan.nextLine();
        }

    }
}
