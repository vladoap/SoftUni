package ReflectionAndAnnotations.BarracksWars.core.commands;

import ReflectionAndAnnotations.BarracksWars.annotations.Inject;
import ReflectionAndAnnotations.BarracksWars.interfaces.Repository;

public class ReportCommand extends Command {

    @Inject
    private Repository repository;

    public ReportCommand(String[] data) {
        super(data);
    }

    @Override
    public String execute() {
        String output = repository.getStatistics();
        return output;
    }
}
