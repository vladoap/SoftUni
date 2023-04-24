package football;

import football.core.Engine;
import football.core.EngineImpl;
import football.entities.field.BaseField;

public class Main {
    public static void main(String[] args) {
        Engine engine = new EngineImpl();
        engine.run();
    }
}
