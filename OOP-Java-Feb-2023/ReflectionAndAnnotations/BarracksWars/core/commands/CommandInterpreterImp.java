package ReflectionAndAnnotations.BarracksWars.core.commands;

import ReflectionAndAnnotations.BarracksWars.annotations.Inject;
import ReflectionAndAnnotations.BarracksWars.interfaces.CommandInterpreter;
import ReflectionAndAnnotations.BarracksWars.interfaces.Executable;
import ReflectionAndAnnotations.BarracksWars.interfaces.Repository;
import ReflectionAndAnnotations.BarracksWars.interfaces.UnitFactory;
import jdk.jshell.spi.ExecutionControl;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Locale;


public class CommandInterpreterImp implements CommandInterpreter {

    private static final String UNITS_PACKAGE_NAME =
            "ReflectionAndAnnotations.BarracksWars.core.commands.";

    private Repository repository;
    private UnitFactory unitFactory;

    public CommandInterpreterImp(Repository repository, UnitFactory unitFactory) {
        this.repository = repository;
        this.unitFactory = unitFactory;
    }

    @Override
    public Executable interpretCommand(String[] data, String commandName) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, ExecutionControl.NotImplementedException {

        String commandNameEdited = editCommandName(commandName);
        Class clazz = Class.forName(UNITS_PACKAGE_NAME + commandNameEdited);
        Constructor<Executable> constructor = clazz.getDeclaredConstructor(String[].class);
        Executable executable = constructor.newInstance(new Object[]{data});

        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(Inject.class)) {
                field.setAccessible(true);
                if (field.getName().equals("repository")) {
                    field.set(executable, repository);
                } else if (field.getName().equals("unitFactory")) {
                    field.set(executable, unitFactory);
                }
            }
        }


        return executable;

    }


    private String editCommandName(String commandName) {
        String commandNameUpperCase = commandName.substring(0, 1).toUpperCase(Locale.ROOT);
        String commandNameOutput = commandNameUpperCase + commandName.substring(1) + "Command";
        return commandNameOutput;
    }
}

