package com.watermelon.Common.injection.framework;

import java.util.HashMap;

public class InjectionBase {
    private static HashMap<Class, Object> singletons = new HashMap<>();

    protected static <T> T provideSingleton(Class type, SingletonFactory<T> singletonFactory) {
        Object obj = singletons.get(type);
        if (obj !=null){
            return (T) obj;
        }else{
            T newInstance = singletonFactory.create();
            singletons.put(type, newInstance);
            return newInstance;
        }
    }

    protected interface  SingletonFactory<T>{
       T create();
    }
}
