package ReflectionAndAnnotations.BarracksWars.core.commands;

import ReflectionAndAnnotations.BarracksWars.annotations.Inject;
import ReflectionAndAnnotations.BarracksWars.interfaces.Repository;
import ReflectionAndAnnotations.BarracksWars.interfaces.Unit;
import ReflectionAndAnnotations.BarracksWars.interfaces.UnitFactory;
import jdk.jshell.spi.ExecutionControl;

import java.lang.reflect.InvocationTargetException;

public class AddCommand extends Command {

    @Inject
    private Repository repository;

    @Inject
    private UnitFactory unitFactory;

    public AddCommand(String[] data) {
        super(data);
    }

    @Override
    public String execute() throws ExecutionControl.NotImplementedException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        String unitType = getData()[1];
        		Unit unitToAdd = unitFactory.createUnit(unitType);
        repository.addUnit(unitToAdd);
        		String output = unitType + " added!";
        		return output;
    }




}
