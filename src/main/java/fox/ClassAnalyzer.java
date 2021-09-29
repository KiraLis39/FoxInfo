package fox;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ClassAnalyzer {
	Field[] publicFields;
	Method[] methods;
	Class<?> obj;
	Class<?>[] interfaces, paramTypes;


	public String analyzeClass(Class<?> cl) {
		obj = cl.getClass();
		StringBuilder result = new StringBuilder();

		result.append("\nКласс рассматривается: " + obj.getName() + "\n\tМодификатор:\n");
		int mods = obj.getModifiers();
		
		if (Modifier.isPublic(mods)) {result.append("public");}
		if (Modifier.isAbstract(mods)) {result.append("abstract");}
		if (Modifier.isFinal(mods)) {result.append("final");}
		result.append("\n\n\tСуперкласс: " + obj.getSuperclass());
		result.append("\n\n\tИнтерфейсы:\n");

		interfaces = obj.getInterfaces();
		for (Class<?> cInterface : interfaces) {result.append(cInterface.getName() + ";\n");}

		result.append("\n");

		publicFields = obj.getFields();
		for (Field field : publicFields) {
			Class<?> fieldType = field.getType();
			result.append("\tИмя: " + field.getName() + "\tТип: " + fieldType.getName() + "\n");
		}

		result.append("\n\nПовтор в declared-mode:\n");

		publicFields = obj.getDeclaredFields();
		for (Field field : publicFields) {
			Class<?> fieldType = field.getType();
			result.append("\tИмя: " + field.getName() + "\n");
			result.append("\tТип: " + fieldType.getName() + "\n");
		}
		// Field nameField = c.getField("name");

		result.append("\n\nКонструкторы:\n\n" + obj.getConstructors());
		result.append("\n\nКонструкторы declared-mode :\n\n" + obj.getDeclaredConstructors());
		result.append("\n\n\tМетоды: ");
		
		methods = obj.getMethods();
		for (Method method : methods) {
			result.append("\n\tИмя метода: " + method.getName());
			result.append("\n\tВозвращаемый тип: " + method.getReturnType().getName());
			result.append("\n\n\tТипы параметров: ");
			paramTypes = method.getParameterTypes();
			for (Class<?> paramType : paramTypes) {
				result.append("\n\t" + paramType.getName());
			}
		}
		
		result.append("\n\n\tМетоды declared-mode\n: ");
		methods = obj.getDeclaredMethods();
		for (Method method : methods) {
			result.append("\n\tИмя метода: " + method.getName());
			result.append("\tВозвращаемый тип: " + method.getReturnType().getName());
			result.append("\n\tТипы параметров: ");
			paramTypes = method.getParameterTypes();
			for (Class<?> paramType : paramTypes) {result.append("\n\t" + paramType.getName());}
		}

		result.append("\n");

		return result.toString();
	}
}
