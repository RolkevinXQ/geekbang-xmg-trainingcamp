package org.rolkevin.configuration.microprofile.config.source;

import org.eclipse.microprofile.config.spi.ConfigSource;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 基于 Map 数据结构 {@link ConfigSource} 实现
 */
public abstract class MapBasedConfigSource implements ConfigSource {

    private final String name;

    private final int ordinal;

    private Map<String, String> source;


    protected MapBasedConfigSource(String name, int ordinal) {
        this.name = name;
        this.ordinal = ordinal;
        this.source = getProperties();

    }

    /**
     * 通过addListener的时候，会先调用父类构造方法，而父类方法中又会调用getProperties，此时子类还没初始化完成，会出现NPE
     * @param name
     * @param ordinal
     * @param isLoad 是否加载prepareConfigData，true-加载，false 不加载
     */
    protected MapBasedConfigSource(String name, int ordinal,boolean isLoad) {
        this.name = name;
        this.ordinal = ordinal;
        if(isLoad){
            this.source = getProperties();
        }

    }

    /**
     * 获取配置数据 Map
     *
     * @return 不可变 Map 类型的配置数据
     */
    public final Map<String, String> getProperties() {
        Map<String,String> configData = new HashMap<>();
        try {
            prepareConfigData(configData);
        } catch (Throwable cause) {
            throw new IllegalStateException("准备配置数据发生错误",cause);
        }
        return Collections.unmodifiableMap(configData);
    }

    /**
     * 准备配置数据
     * @param configData
     * @throws Throwable
     */
    protected abstract void prepareConfigData(Map configData) throws Throwable;

    @Override
    public final String getName() {
        return name;
    }

    @Override
    public final int getOrdinal() {
        return ordinal;
    }

    @Override
    public Set<String> getPropertyNames() {
        return source.keySet();
    }

    @Override
    public String getValue(String propertyName) {
        return source.get(propertyName);
    }

}
