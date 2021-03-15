package org.rolkevin.user.management;

import javax.management.*;
import javax.management.loading.ClassLoaderRepository;
import java.io.ObjectInputStream;
import java.lang.management.ManagementFactory;
import java.util.Set;

public class DelegatingMBeanServer implements MBeanServer {

protected MBeanServer createMBeanServer(){
    return ManagementFactory.getPlatformMBeanServer();
}

    /**
     * {@inheritDoc}
     * <p>If this method successfully creates an MBean, a notification
     * is sent as described <a href="#notif">above</a>.</p>
     *
     * @param className
     * @param name
     * @throws RuntimeOperationsException {@inheritDoc}
     * @throws RuntimeMBeanException      {@inheritDoc}
     * @throws RuntimeErrorException      {@inheritDoc}
     */
    @Override
    public ObjectInstance createMBean(String className, ObjectName name) throws ReflectionException, InstanceAlreadyExistsException, MBeanRegistrationException, MBeanException, NotCompliantMBeanException {
        return null;
    }

    /**
     * {@inheritDoc}
     * <p>If this method successfully creates an MBean, a notification
     * is sent as described <a href="#notif">above</a>.</p>
     *
     * @param className
     * @param name
     * @param loaderName
     * @throws RuntimeOperationsException {@inheritDoc}
     * @throws RuntimeMBeanException      {@inheritDoc}
     * @throws RuntimeErrorException      {@inheritDoc}
     */
    @Override
    public ObjectInstance createMBean(String className, ObjectName name, ObjectName loaderName) throws ReflectionException, InstanceAlreadyExistsException, MBeanRegistrationException, MBeanException, NotCompliantMBeanException, InstanceNotFoundException {
        return null;
    }

    /**
     * {@inheritDoc}
     * <p>If this method successfully creates an MBean, a notification
     * is sent as described <a href="#notif">above</a>.</p>
     *
     * @param className
     * @param name
     * @param params
     * @param signature
     * @throws RuntimeOperationsException {@inheritDoc}
     * @throws RuntimeMBeanException      {@inheritDoc}
     * @throws RuntimeErrorException      {@inheritDoc}
     */
    @Override
    public ObjectInstance createMBean(String className, ObjectName name, Object[] params, String[] signature) throws ReflectionException, InstanceAlreadyExistsException, MBeanRegistrationException, MBeanException, NotCompliantMBeanException {
        return null;
    }

    /**
     * {@inheritDoc}
     * <p>If this method successfully creates an MBean, a notification
     * is sent as described <a href="#notif">above</a>.</p>
     *
     * @param className
     * @param name
     * @param loaderName
     * @param params
     * @param signature
     * @throws RuntimeOperationsException {@inheritDoc}
     * @throws RuntimeMBeanException      {@inheritDoc}
     * @throws RuntimeErrorException      {@inheritDoc}
     */
    @Override
    public ObjectInstance createMBean(String className, ObjectName name, ObjectName loaderName, Object[] params, String[] signature) throws ReflectionException, InstanceAlreadyExistsException, MBeanRegistrationException, MBeanException, NotCompliantMBeanException, InstanceNotFoundException {
        return null;
    }

    /**
     * <p>Registers a pre-existing object as an MBean with the MBean
     * server. If the object name given is null, the MBean must
     * provide its own name by implementing the {@link
     * MBeanRegistration MBeanRegistration} interface
     * and returning the name from the {@link
     * MBeanRegistration#preRegister preRegister} method.
     *
     * <p>If this method successfully registers an MBean, a notification
     * is sent as described <a href="#notif">above</a>.</p>
     *
     * @param object The  MBean to be registered as an MBean.
     * @param name   The object name of the MBean. May be null.
     * @return An <CODE>ObjectInstance</CODE>, containing the
     * <CODE>ObjectName</CODE> and the Java class name of the newly
     * registered MBean.  If the contained <code>ObjectName</code>
     * is <code>n</code>, the contained Java class name is
     * <code>{@link #getMBeanInfo getMBeanInfo(n)}.getClassName()</code>.
     * @throws InstanceAlreadyExistsException The MBean is already
     *                                        under the control of the MBean server.
     * @throws MBeanRegistrationException     The
     *                                        <CODE>preRegister</CODE> (<CODE>MBeanRegistration</CODE>
     *                                        interface) method of the MBean has thrown an exception. The
     *                                        MBean will not be registered.
     * @throws RuntimeMBeanException          If the <CODE>postRegister</CODE>
     *                                        (<CODE>MBeanRegistration</CODE> interface) method of the MBean throws a
     *                                        <CODE>RuntimeException</CODE>, the <CODE>registerMBean</CODE> method will
     *                                        throw a <CODE>RuntimeMBeanException</CODE>, although the MBean
     *                                        registration succeeded. In such a case, the MBean will be actually
     *                                        registered even though the <CODE>registerMBean</CODE> method
     *                                        threw an exception.  Note that <CODE>RuntimeMBeanException</CODE> can
     *                                        also be thrown by <CODE>preRegister</CODE>, in which case the MBean
     *                                        will not be registered.
     * @throws RuntimeErrorException          If the <CODE>postRegister</CODE>
     *                                        (<CODE>MBeanRegistration</CODE> interface) method of the MBean throws an
     *                                        <CODE>Error</CODE>, the <CODE>registerMBean</CODE> method will
     *                                        throw a <CODE>RuntimeErrorException</CODE>, although the MBean
     *                                        registration succeeded. In such a case, the MBean will be actually
     *                                        registered even though the <CODE>registerMBean</CODE> method
     *                                        threw an exception.  Note that <CODE>RuntimeErrorException</CODE> can
     *                                        also be thrown by <CODE>preRegister</CODE>, in which case the MBean
     *                                        will not be registered.
     * @throws NotCompliantMBeanException     This object is not a JMX
     *                                        compliant MBean
     * @throws RuntimeOperationsException     Wraps a
     *                                        <CODE>java.lang.IllegalArgumentException</CODE>: The object
     *                                        passed in parameter is null or no object name is specified.
     * @see MBeanRegistration
     */
    @Override
    public ObjectInstance registerMBean(Object object, ObjectName name) throws InstanceAlreadyExistsException, MBeanRegistrationException, NotCompliantMBeanException {
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * <p>If this method successfully unregisters an MBean, a notification
     * is sent as described <a href="#notif">above</a>.</p>
     *
     * @param name
     * @throws RuntimeOperationsException {@inheritDoc}
     * @throws RuntimeMBeanException      {@inheritDoc}
     * @throws RuntimeErrorException      {@inheritDoc}
     */
    @Override
    public void unregisterMBean(ObjectName name) throws InstanceNotFoundException, MBeanRegistrationException {

    }

    @Override
    public ObjectInstance getObjectInstance(ObjectName name) throws InstanceNotFoundException {
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @param name
     * @param query
     * @throws RuntimeOperationsException {@inheritDoc}
     */
    @Override
    public Set<ObjectInstance> queryMBeans(ObjectName name, QueryExp query) {
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @param name
     * @param query
     * @throws RuntimeOperationsException {@inheritDoc}
     */
    @Override
    public Set<ObjectName> queryNames(ObjectName name, QueryExp query) {
        return null;
    }

    /**
     * @param name
     * @throws RuntimeOperationsException {@inheritDoc}
     */
    @Override
    public boolean isRegistered(ObjectName name) {
        return false;
    }

    /**
     * Returns the number of MBeans registered in the MBean server.
     *
     * @return the number of registered MBeans, wrapped in an Integer.
     * If the caller's permissions are restricted, this number may
     * be greater than the number of MBeans the caller can access.
     */
    @Override
    public Integer getMBeanCount() {
        return null;
    }

    /**
     * @param name
     * @param attribute
     * @throws RuntimeOperationsException {@inheritDoc}
     */
    @Override
    public Object getAttribute(ObjectName name, String attribute) throws MBeanException, AttributeNotFoundException, InstanceNotFoundException, ReflectionException {
        return null;
    }

    /**
     * @param name
     * @param attributes
     * @throws RuntimeOperationsException {@inheritDoc}
     */
    @Override
    public AttributeList getAttributes(ObjectName name, String[] attributes) throws InstanceNotFoundException, ReflectionException {
        return null;
    }

    /**
     * @param name
     * @param attribute
     * @throws RuntimeOperationsException {@inheritDoc}
     */
    @Override
    public void setAttribute(ObjectName name, Attribute attribute) throws InstanceNotFoundException, AttributeNotFoundException, InvalidAttributeValueException, MBeanException, ReflectionException {

    }

    /**
     * @param name
     * @param attributes
     * @throws RuntimeOperationsException {@inheritDoc}
     */
    @Override
    public AttributeList setAttributes(ObjectName name, AttributeList attributes) throws InstanceNotFoundException, ReflectionException {
        return null;
    }

    @Override
    public Object invoke(ObjectName name, String operationName, Object[] params, String[] signature) throws InstanceNotFoundException, MBeanException, ReflectionException {
        return null;
    }

    @Override
    public String getDefaultDomain() {
        return null;
    }

    @Override
    public String[] getDomains() {
        return new String[0];
    }

    /**
     * {@inheritDoc}
     * If the source of the notification
     * is a reference to an MBean object, the MBean server will replace it
     * by that MBean's ObjectName.  Otherwise the source is unchanged.
     *
     * @param name
     * @param listener
     * @param filter
     * @param handback
     */
    @Override
    public void addNotificationListener(ObjectName name, NotificationListener listener, NotificationFilter filter, Object handback) throws InstanceNotFoundException {

    }

    /**
     * {@inheritDoc}
     *
     * @param name
     * @param listener
     * @param filter
     * @param handback
     * @throws RuntimeOperationsException {@inheritDoc}
     */
    @Override
    public void addNotificationListener(ObjectName name, ObjectName listener, NotificationFilter filter, Object handback) throws InstanceNotFoundException {

    }

    @Override
    public void removeNotificationListener(ObjectName name, ObjectName listener) throws InstanceNotFoundException, ListenerNotFoundException {

    }

    @Override
    public void removeNotificationListener(ObjectName name, ObjectName listener, NotificationFilter filter, Object handback) throws InstanceNotFoundException, ListenerNotFoundException {

    }

    @Override
    public void removeNotificationListener(ObjectName name, NotificationListener listener) throws InstanceNotFoundException, ListenerNotFoundException {

    }

    @Override
    public void removeNotificationListener(ObjectName name, NotificationListener listener, NotificationFilter filter, Object handback) throws InstanceNotFoundException, ListenerNotFoundException {

    }

    @Override
    public MBeanInfo getMBeanInfo(ObjectName name) throws InstanceNotFoundException, IntrospectionException, ReflectionException {
        return null;
    }

    @Override
    public boolean isInstanceOf(ObjectName name, String className) throws InstanceNotFoundException {
        return false;
    }

    /**
     * <p>Instantiates an object using the list of all class loaders
     * registered in the MBean server's {@link
     * ClassLoaderRepository Class Loader
     * Repository}.  The object's class should have a public
     * constructor.  This method returns a reference to the newly
     * created object.  The newly created object is not registered in
     * the MBean server.</p>
     *
     * <p>This method is equivalent to {@link
     * #instantiate(String, Object[], String[])
     * instantiate(className, (Object[]) null, (String[]) null)}.</p>
     *
     * @param className The class name of the object to be instantiated.
     * @return The newly instantiated object.
     * @throws ReflectionException        Wraps a
     *                                    <CODE>java.lang.ClassNotFoundException</CODE> or the
     *                                    <CODE>java.lang.Exception</CODE> that occurred when trying to
     *                                    invoke the object's constructor.
     * @throws MBeanException             The constructor of the object has
     *                                    thrown an exception
     * @throws RuntimeOperationsException Wraps a
     *                                    <CODE>java.lang.IllegalArgumentException</CODE>: The className
     *                                    passed in parameter is null.
     */
    @Override
    public Object instantiate(String className) throws ReflectionException, MBeanException {
        return null;
    }

    /**
     * <p>Instantiates an object using the class Loader specified by its
     * <CODE>ObjectName</CODE>.  If the loader name is null, the
     * ClassLoader that loaded the MBean Server will be used.  The
     * object's class should have a public constructor.  This method
     * returns a reference to the newly created object.  The newly
     * created object is not registered in the MBean server.</p>
     *
     * <p>This method is equivalent to {@link
     * #instantiate(String, ObjectName, Object[], String[])
     * instantiate(className, loaderName, (Object[]) null, (String[])
     * null)}.</p>
     *
     * @param className  The class name of the MBean to be instantiated.
     * @param loaderName The object name of the class loader to be used.
     * @return The newly instantiated object.
     * @throws ReflectionException        Wraps a
     *                                    <CODE>java.lang.ClassNotFoundException</CODE> or the
     *                                    <CODE>java.lang.Exception</CODE> that occurred when trying to
     *                                    invoke the object's constructor.
     * @throws MBeanException             The constructor of the object has
     *                                    thrown an exception.
     * @throws InstanceNotFoundException  The specified class loader
     *                                    is not registered in the MBeanServer.
     * @throws RuntimeOperationsException Wraps a
     *                                    <CODE>java.lang.IllegalArgumentException</CODE>: The className
     *                                    passed in parameter is null.
     */
    @Override
    public Object instantiate(String className, ObjectName loaderName) throws ReflectionException, MBeanException, InstanceNotFoundException {
        return null;
    }

    /**
     * <p>Instantiates an object using the list of all class loaders
     * registered in the MBean server {@link
     * ClassLoaderRepository Class Loader
     * Repository}.  The object's class should have a public
     * constructor.  The call returns a reference to the newly created
     * object.  The newly created object is not registered in the
     * MBean server.</p>
     *
     * @param className The class name of the object to be instantiated.
     * @param params    An array containing the parameters of the
     *                  constructor to be invoked.
     * @param signature An array containing the signature of the
     *                  constructor to be invoked.
     * @return The newly instantiated object.
     * @throws ReflectionException        Wraps a
     *                                    <CODE>java.lang.ClassNotFoundException</CODE> or the
     *                                    <CODE>java.lang.Exception</CODE> that occurred when trying to
     *                                    invoke the object's constructor.
     * @throws MBeanException             The constructor of the object has
     *                                    thrown an exception
     * @throws RuntimeOperationsException Wraps a
     *                                    <CODE>java.lang.IllegalArgumentException</CODE>: The className
     *                                    passed in parameter is null.
     */
    @Override
    public Object instantiate(String className, Object[] params, String[] signature) throws ReflectionException, MBeanException {
        return null;
    }

    /**
     * <p>Instantiates an object. The class loader to be used is
     * identified by its object name. If the object name of the loader
     * is null, the ClassLoader that loaded the MBean server will be
     * used.  The object's class should have a public constructor.
     * The call returns a reference to the newly created object.  The
     * newly created object is not registered in the MBean server.</p>
     *
     * @param className  The class name of the object to be instantiated.
     * @param loaderName The object name of the class loader to be used.
     * @param params     An array containing the parameters of the
     *                   constructor to be invoked.
     * @param signature  An array containing the signature of the
     *                   constructor to be invoked.
     * @return The newly instantiated object.
     * @throws ReflectionException        Wraps a <CODE>java.lang.ClassNotFoundException</CODE> or the <CODE>java.lang.Exception</CODE> that
     *                                    occurred when trying to invoke the object's constructor.
     * @throws MBeanException             The constructor of the object has
     *                                    thrown an exception
     * @throws InstanceNotFoundException  The specified class loader
     *                                    is not registered in the MBean server.
     * @throws RuntimeOperationsException Wraps a
     *                                    <CODE>java.lang.IllegalArgumentException</CODE>: The className
     *                                    passed in parameter is null.
     */
    @Override
    public Object instantiate(String className, ObjectName loaderName, Object[] params, String[] signature) throws ReflectionException, MBeanException, InstanceNotFoundException {
        return null;
    }

    /**
     * <p>De-serializes a byte array in the context of the class loader
     * of an MBean.</p>
     *
     * @param name The name of the MBean whose class loader should be
     *             used for the de-serialization.
     * @param data The byte array to be de-sererialized.
     * @return The de-serialized object stream.
     * @throws InstanceNotFoundException The MBean specified is not
     *                                   found.
     * @throws OperationsException       Any of the usual Input/Output
     *                                   related exceptions.
     * @deprecated Use {@link #getClassLoaderFor getClassLoaderFor} to
     * obtain the appropriate class loader for deserialization.
     */
    @Override
    public ObjectInputStream deserialize(ObjectName name, byte[] data) throws InstanceNotFoundException, OperationsException {
        return null;
    }

    /**
     * <p>De-serializes a byte array in the context of a given MBean
     * class loader.  The class loader is found by loading the class
     * <code>className</code> through the {@link
     * ClassLoaderRepository Class Loader
     * Repository}.  The resultant class's class loader is the one to
     * use.
     *
     * @param className The name of the class whose class loader should be
     *                  used for the de-serialization.
     * @param data      The byte array to be de-sererialized.
     * @return The de-serialized object stream.
     * @throws OperationsException Any of the usual Input/Output
     *                             related exceptions.
     * @throws ReflectionException The specified class could not be
     *                             loaded by the class loader repository
     * @deprecated Use {@link #getClassLoaderRepository} to obtain the
     * class loader repository and use it to deserialize.
     */
    @Override
    public ObjectInputStream deserialize(String className, byte[] data) throws OperationsException, ReflectionException {
        return null;
    }

    /**
     * <p>De-serializes a byte array in the context of a given MBean
     * class loader.  The class loader is the one that loaded the
     * class with name "className".  The name of the class loader to
     * be used for loading the specified class is specified.  If null,
     * the MBean Server's class loader will be used.</p>
     *
     * @param className  The name of the class whose class loader should be
     *                   used for the de-serialization.
     * @param loaderName The name of the class loader to be used for
     *                   loading the specified class.  If null, the MBean Server's class
     *                   loader will be used.
     * @param data       The byte array to be de-sererialized.
     * @return The de-serialized object stream.
     * @throws InstanceNotFoundException The specified class loader
     *                                   MBean is not found.
     * @throws OperationsException       Any of the usual Input/Output
     *                                   related exceptions.
     * @throws ReflectionException       The specified class could not be
     *                                   loaded by the specified class loader.
     * @deprecated Use {@link #getClassLoader getClassLoader} to obtain
     * the class loader for deserialization.
     */
    @Override
    public ObjectInputStream deserialize(String className, ObjectName loaderName, byte[] data) throws InstanceNotFoundException, OperationsException, ReflectionException {
        return null;
    }

    /**
     * <p>Return the {@link ClassLoader} that was used for
     * loading the class of the named MBean.</p>
     *
     * @param mbeanName The ObjectName of the MBean.
     * @return The ClassLoader used for that MBean.  If <var>l</var>
     * is the MBean's actual ClassLoader, and <var>r</var> is the
     * returned value, then either:
     *
     * <ul>
     * <li><var>r</var> is identical to <var>l</var>; or
     * <li>the result of <var>r</var>{@link
     * ClassLoader#loadClass(String) .loadClass(<var>s</var>)} is the
     * same as <var>l</var>{@link ClassLoader#loadClass(String)
     * .loadClass(<var>s</var>)} for any string <var>s</var>.
     * </ul>
     * <p>
     * What this means is that the ClassLoader may be wrapped in
     * another ClassLoader for security or other reasons.
     * @throws InstanceNotFoundException if the named MBean is not found.
     */
    @Override
    public ClassLoader getClassLoaderFor(ObjectName mbeanName) throws InstanceNotFoundException {
        return null;
    }

    /**
     * <p>Return the named {@link ClassLoader}.</p>
     *
     * @param loaderName The ObjectName of the ClassLoader.  May be
     *                   null, in which case the MBean server's own ClassLoader is
     *                   returned.
     * @return The named ClassLoader.  If <var>l</var> is the actual
     * ClassLoader with that name, and <var>r</var> is the returned
     * value, then either:
     *
     * <ul>
     * <li><var>r</var> is identical to <var>l</var>; or
     * <li>the result of <var>r</var>{@link
     * ClassLoader#loadClass(String) .loadClass(<var>s</var>)} is the
     * same as <var>l</var>{@link ClassLoader#loadClass(String)
     * .loadClass(<var>s</var>)} for any string <var>s</var>.
     * </ul>
     * <p>
     * What this means is that the ClassLoader may be wrapped in
     * another ClassLoader for security or other reasons.
     * @throws InstanceNotFoundException if the named ClassLoader is
     *                                   not found.
     */
    @Override
    public ClassLoader getClassLoader(ObjectName loaderName) throws InstanceNotFoundException {
        return null;
    }

    /**
     * <p>Return the ClassLoaderRepository for this MBeanServer.
     *
     * @return The ClassLoaderRepository for this MBeanServer.
     */
    @Override
    public ClassLoaderRepository getClassLoaderRepository() {
        return null;
    }
}
