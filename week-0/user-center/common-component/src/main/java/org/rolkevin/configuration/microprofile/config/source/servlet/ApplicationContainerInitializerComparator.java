package org.rolkevin.configuration.microprofile.config.source.servlet;

import org.eclipse.microprofile.config.spi.ConfigSource;
import org.rolkevin.configuration.microprofile.config.source.ConfigSourceOrdinalComparator;

import java.util.Comparator;

public class ApplicationContainerInitializerComparator implements Comparator<ApplicationContainerInitializer> {

    public static final Comparator<ApplicationContainerInitializer> INSTANCE = new ApplicationContainerInitializerComparator();

    private ApplicationContainerInitializerComparator(){

    }

    @Override
    public int compare(ApplicationContainerInitializer a1,
                       ApplicationContainerInitializer a2) {
        return Integer.compare(a1.getOrdinal(), a2.getOrdinal());
    }
}
