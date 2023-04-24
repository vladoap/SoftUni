package ReflectionAndAnnotations.BarracksWars;

import ReflectionAndAnnotations.BarracksWars.core.commands.CommandInterpreterImp;
import ReflectionAndAnnotations.BarracksWars.interfaces.CommandInterpreter;
import ReflectionAndAnnotations.BarracksWars.interfaces.Repository;
import ReflectionAndAnnotations.BarracksWars.interfaces.Runnable;
import ReflectionAndAnnotations.BarracksWars.interfaces.UnitFactory;
import ReflectionAndAnnotations.BarracksWars.core.Engine;
import ReflectionAndAnnotations.BarracksWars.core.factories.UnitFactoryImpl;
import ReflectionAndAnnotations.BarracksWars.data.UnitRepository;

import java.lang.reflect.InvocationTargetException;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Repository repository = new UnitRepository();
        UnitFactory unitFactory = new UnitFactoryImpl();

        CommandInterpreter commandInterpreter = new CommandInterpreterImp(repository, unitFactory);
        Runnable engine = new Engine(commandInterpreter);
        engine.run();
    }
}
