package ReflectionAndAnnotations.BarracksWars.core.factories;

import ReflectionAndAnnotations.BarracksWars.interfaces.Unit;
import ReflectionAndAnnotations.BarracksWars.interfaces.UnitFactory;
import jdk.jshell.spi.ExecutionControl;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class UnitFactoryImpl implements UnitFactory {

	private static final String UNITS_PACKAGE_NAME =
			"ReflectionAndAnnotations.BarracksWars.units.";

	@Override
	public Unit createUnit(String unitType) throws ExecutionControl.NotImplementedException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {


		Class clazz = Class.forName(UNITS_PACKAGE_NAME + unitType);
		Constructor<Unit> constructor = clazz.getDeclaredConstructor();
		return constructor.newInstance();
	}
}
