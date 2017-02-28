package ass.poliape2.dependencyinjection;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class DIContainer {
	
	private static final String CONFIG_FILEPATH = "config.properties";
	
	protected Map<Class<?>, Class<?>> registeredInterfaces = new HashMap<>();
	protected Properties prop = new Properties();
	
	public DIContainer() throws FileNotFoundException, IOException {
		prop.load(new FileInputStream(CONFIG_FILEPATH));
	}
	
	public void register(Class<?> iface, Class<?> impl) {
		registeredInterfaces.put(iface, impl);
	}
	
	public <T> T resolve(Class<T> clazz) {
		// find registered implementation of given clazz
		Class<T> implClass = (Class<T>)registeredInterfaces.get(clazz);
		
		// creating instance like this requires T to have default constructor!
		T instance;
		try {
			instance = (T) implClass.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
		
		resolveFields(implClass, instance);
		
		return instance;
	}

	private <T> void resolveFields(Class<T> implClass, T instance) {
		Field[] fields = implClass.getDeclaredFields();
		
		resolveInjectFields(instance, fields);
		resolvePropertyFields(instance, fields);
	}

	private <T> void resolveInjectFields(T instance, Field[] fields) {
		Arrays.stream(fields)
		.filter(f -> f.getDeclaredAnnotation(Inject.class) != null)
		.forEach(f -> {
			setFieldValue(instance, this.resolve(f.getType()), f);
		});
	}

	private <T> void resolvePropertyFields(T instance, Field[] fields) {
		Arrays.stream(fields)
		.filter(f -> f.getDeclaredAnnotation(Property.class) != null)
		.forEach(f -> { 
			final String propertyName = f.getDeclaredAnnotation(Property.class).propertyName();
			final String propertyValue = prop.getProperty(propertyName);
			setFieldValue(instance, propertyValue, f);
		});
	}

	private <T> void setFieldValue(T instance, Object value, Field f) {
		f.setAccessible(true);
		try {
			f.set(instance, value);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}
	
}
