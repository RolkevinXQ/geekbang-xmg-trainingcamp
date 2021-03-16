package org.rolkevin.user.management;

import org.rolkevin.user.domain.User;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

public class MBeanDemo {

    public static void main(String[] args) throws Exception{
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        ObjectName objectName = new ObjectName("org.rolkevin.user.management.MBeanDemo:type=MBeanDemo");
        User user = new User();
        user.setName("XQ");
        user.setEmail("xq@foxmail.com");
        user.setPhoneNumber("10086");

        UserManager userManager = new UserManager(user);
        mBeanServer.registerMBean(userManager,objectName);

        while (true){
            Thread.sleep(5000);
            System.out.println(userManager.toString());
        }
    }
}
