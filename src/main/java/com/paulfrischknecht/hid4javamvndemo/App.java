package com.paulfrischknecht.hid4javamvndemo;

import org.hid4java.HidDevice;
import org.hid4java.HidManager;
import org.hid4java.HidServices;
import org.hid4java.HidServicesSpecification;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        // similar to https://github.com/gary-rowe/hid4java/blob/develop/src/test/java/org/hid4java/examples/UsbHidEnumerationExample.java
        // see https://github.com/gary-rowe/hid4java/tree/develop?tab=readme-ov-file#-code-example

        // Configure to use custom specification
        HidServicesSpecification hidServicesSpecification = new HidServicesSpecification();

        // Use the v0.7.0 manual start feature to get immediate attach events
        hidServicesSpecification.setAutoStart(false);

        // Get HID services using custom specification
        HidServices hidServices = HidManager.getHidServices(hidServicesSpecification);
        //hidServices.addHidServicesListener(this);

        // Manually start the services to get attachment event
        hidServices.start();

        // Provide a list of attached devices
        if (hidServices.getAttachedHidDevices().isEmpty()) throw new IllegalStateException("no hid devices found, please attach something we cannot validate if the library works otherwise");
        for (HidDevice hidDevice : hidServices.getAttachedHidDevices()) {
            System.out.println(hidDevice);

            if (!hidDevice.open()) throw new IllegalStateException("failed to open device! do you need to run as sudo?");
            //hidDevice.write(new byte[]{1, 's'}, 1, (byte)63 ); // reportid = '?'
            
            Byte[] read = hidDevice.read(63, 1000);
            System.out.println("read: " + read);
            hidDevice.close();
        }
        System.out.println( "devices enumerated" );

        // requird because apparently hid4java starts some threads, otherwise:
        /*
         * [WARNING] thread Thread[hid4java event worker,5,com.paulfrischknecht.hid4javamvndemo.App] was interrupted but is still alive after waiting at least 15000msecs
         * [WARNING] thread Thread[hid4java event worker,5,com.paulfrischknecht.hid4javamvndemo.App] will linger despite being asked to die via interruption
         * [WARNING] thread Thread[hid4java event worker,5,com.paulfrischknecht.hid4javamvndemo.App] will linger despite being asked to die via interruption
         * [WARNING] thread Thread[hid4java event worker,5,com.paulfrischknecht.hid4javamvndemo.App] will linger despite being asked to die via interruption
         * [WARNING] NOTE: 3 thread(s) did not finish despite being asked to via interruption. This is not a problem with exec:java, it is a problem with the running code. Although not serious, it should be remedied.
         * [WARNING] Couldn't destroy threadgroup org.codehaus.mojo.exec.ExecJavaMojo$IsolatedThreadGroup[name=com.paulfrischknecht.hid4javamvndemo.App,maxpri=10]
         */
        hidServices.shutdown();
        System.exit(0);
            
    }
}
