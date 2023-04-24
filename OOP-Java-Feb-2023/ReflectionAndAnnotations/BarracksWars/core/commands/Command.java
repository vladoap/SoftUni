package ReflectionAndAnnotations.BarracksWars.core.commands;

import ReflectionAndAnnotations.BarracksWars.interfaces.Executable;

public abstract class Command implements Executable {


    private String[] data;

    public Command(String[] data) {
        this.data = data;
    }



    public String[] getData() {
        return data;
    }
}
