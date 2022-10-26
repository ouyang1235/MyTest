package com.ouyang;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public abstract class At<T> {
    public At() throws ClassNotFoundException {
        Class<? extends At> subClass = this.getClass();
        Type genericSuperclass = subClass.getGenericSuperclass();
        ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        Class<?> superclass = subClass.getSuperclass();
        System.out.println(superclass.getName());
        Type actualTypeArgument = actualTypeArguments[0];
        Class<?> aClass = this.getClass().getClassLoader().loadClass(actualTypeArgument.getTypeName());
        System.out.println(aClass.getName());
        System.out.println();
    }

    public static void main(String[] args) throws ClassNotFoundException {
        Bt bt = new Bt();
        ArrayList<Object> objects = new ArrayList<>();
    }

    public <K,V,S> Map<V,K> listToMap(List<K> list, Function<K, V> function, S t){
        Map<V, K> map = new HashMap<>();
        for (K k : list) {
            V apply = function.apply(k);
            if (apply == null){
                continue;
            }
            map.put(apply,k);
        }
        return map;
    }
}
