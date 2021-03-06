package cn.hyrkg.fastspigot.innercore.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReflectHelper {
    public static List<Field> findFieldIsAnnotated(Class clazz, Class<? extends Annotation> annotation) {
        ArrayList<Field> fields = new ArrayList<>();
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(annotation))
                fields.add(field);
        }
        return fields;
    }

    public static void findAndInvokeMethodIsAnnotated(Class clazz, Object instance, Class<? extends Annotation> annotation) {
        Arrays.asList(clazz.getDeclaredMethods()).stream().filter((j -> j.isAnnotationPresent(annotation))).forEach(j -> {
            try {
                j.setAccessible(true);
                if (Modifier.isStatic(j.getModifiers()))
                    j.invoke(null, null);
                else
                    j.invoke(instance, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public static void findAndInvokeMethodIsAnnotatedSupered(Class clazz, Object instance, Class<? extends Annotation> annotation) {
        if (clazz == null || clazz.equals(Object.class))
            return;
        if (clazz.getSuperclass() != null)
            findAndInvokeMethodIsAnnotatedSupered(clazz.getSuperclass(), instance, annotation);
        findAndInvokeMethodIsAnnotated(clazz, instance, annotation);

    }

    public static Class findNearestExtendsClass(Class clazz, Class targetClazz) {

        Class nearest = null;

        for (Class interfaceClazz : clazz.getInterfaces()) {
            if (interfaceClazz.equals(targetClazz)) {
                return clazz;
            }
            nearest = findNearestExtendsClass(interfaceClazz, targetClazz);
        }


        return nearest;
    }

}
