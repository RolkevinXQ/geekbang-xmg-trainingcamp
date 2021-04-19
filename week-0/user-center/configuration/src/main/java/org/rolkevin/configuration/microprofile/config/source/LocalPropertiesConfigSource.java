package org.rolkevin.configuration.microprofile.config.source;

import org.eclipse.microprofile.config.spi.ConfigSource;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class LocalPropertiesConfigSource implements ConfigSource {

    private static final Map<String, String> localProperties  = new HashMap<>();

    static{
       ClassLoader classLoader =  Thread.currentThread().getContextClassLoader();
       InputStream in = classLoader.getResourceAsStream("META-INF/localconfig.properties");
       Properties properties = new Properties();
        try {
            properties.load(in);
            properties.forEach((k,v)->{
                localProperties.put((String) k,properties.getProperty((String) k));
            });
        } catch (IOException e) {
        }finally {
            try {
                if (in !=null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            ;
        }
    }
    @Override
    public Map<String, String> getProperties() {
//        Map<String, String> properties = new HashMap();
//        getPropertyNames().forEach(propName->{
//
//        });
        return localProperties;
    }

    @Override
    public Set<String> getPropertyNames() {
        return localProperties.keySet();
    }

    @Override
    public int getOrdinal() {
        return 200;
    }

    @Override
    public String getValue(String name) {
        return localProperties.get(name);
    }

    @Override
    public String getName() {
        return "Local Properties";
    }
}
