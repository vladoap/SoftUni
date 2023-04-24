package ReflectionAndAnnotations.BarracksWars.core.commands;

import ReflectionAndAnnotations.BarracksWars.annotations.Inject;
import ReflectionAndAnnotations.BarracksWars.interfaces.Repository;
import jdk.jshell.spi.ExecutionControl;

public class RetireCommand extends Command {

    @Inject
    private Repository repository;

    public RetireCommand(String[] data) {
        super(data);
    }

    @Override
    public String execute() {
        String unitType = getData()[1];
        try {
            repository.removeUnit(unitType);
            return unitType + " retired!";
        } catch (IllegalArgumentException | ExecutionControl.NotImplementedException e) {
            return e.getMessage();
        }
    }
}
