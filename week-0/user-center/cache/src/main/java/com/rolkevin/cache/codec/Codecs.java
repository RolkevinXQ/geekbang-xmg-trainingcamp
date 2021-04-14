package com.rolkevin.cache.codec;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

public class Codecs {

    private final Map<Class<?>,GenericSerializer<?>> typedCodecs= new HashMap<>();

    public static Codecs newInstance(){
        return new Codecs();
    }

    public GenericSerializer<?> targetSerializer(Class<?> targetClass){
        return typedCodecs.get(targetClass);
    }

    public Codecs(){
        initCodecs();
    }

    protected void initCodecs() {
        ClassLoader classLoader = Codecs.class.getClassLoader();
        loadSpi(classLoader);
    }

    private void loadSpi(ClassLoader classLoader) {
        ServiceLoader<GenericSerializer> genericSerializers = ServiceLoader.load(GenericSerializer.class,classLoader);
        genericSerializers.forEach(v->{
            //Type superType = v.getClass().getGenericSuperclass();
            //Class<?> convertedType = resolveConvertedType(superType);
            Class<?> convertedType = resolveConvertedType(v.getClass());
            typedCodecs.put(convertedType,v);
        });
    }

    protected Class<?> resolveConvertedType(GenericSerializer<?> genericSerializer){
        Class<?> convertedType = null;
        Class<?> convertedClass = genericSerializer.getClass();
        while (convertedClass != null){
            convertedType = resolveConvertedType(convertedClass);

            if (convertedType !=null){
                break;
            }
        }
        return convertedType;
    }

    private Class<?> resolveConvertedType(Class<?> convertedClass){
        Class<?> convertedType = null;
        for(Type superType : convertedClass.getGenericInterfaces()){
            convertedType = resolveConvertedType(superType);
            if (convertedType != null){
                break;
            }
        }
        return convertedType;
    }
    private Class<?> resolveConvertedType(Type type) {
        Class<?> convertedType = null;
        if (type instanceof ParameterizedType) {
            ParameterizedType pType = (ParameterizedType) type;
            if (pType.getRawType() instanceof Class) {
                Class<?> rawType = (Class) pType.getRawType();
                if (GenericSerializer.class.isAssignableFrom(rawType)) {
                    Type[] arguments = pType.getActualTypeArguments();
                    if (arguments.length == 1 && arguments[0] instanceof Class) {
                        convertedType = (Class) arguments[0];
                    }
                }
            }
        }
        return convertedType;
    }


}
