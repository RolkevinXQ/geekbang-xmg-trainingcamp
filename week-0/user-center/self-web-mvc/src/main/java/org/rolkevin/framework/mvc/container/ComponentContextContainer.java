package org.rolkevin.framework.mvc.container;

import java.util.LinkedHashMap;
import java.util.Map;

public class ComponentContextContainer {

    private static ThreadLocal<ComponentContextContainer> threadLocal = new ThreadLocal<>();

    private static Map<String, Object> componentsMap ;

    public ComponentContextContainer(Map map){
        ComponentContextContainer.componentsMap = map;
        threadLocal.set(this);
    }

    public static ComponentContextContainer getInstance(){
        return threadLocal.get();
    }

    public <C> C getComponent(String name) {
        return (C) componentsMap.get(name);
    }






}
