package com.medolia.demo.jdk.jvm;

import org.openjdk.jol.info.ClassLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * 使用 jol 打印对象内存布局
 *
 * @author lb@li@trip.com
 * @date 2021/7/21
 */
@SuppressWarnings("all")
public class MonitorTest {
    public static void main(String[] args) throws InterruptedException {
        // bulkRebiasedTest();
        lockUpgrade();
    }

    static Thread thread1, thread2,t3;

    /**
     * 偏向锁批量重定向
     * @throws InterruptedException
     */
    static void bulkRebiasedTest() throws InterruptedException {

        TimeUnit.SECONDS.sleep(5);

        List<Object> list = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            list.add(new Object());
        }

        thread1 = new Thread(() -> {
            for (int i = 0; i < list.size(); i++) {
                synchronized (list.get(i)) {
                }
            }
            LockSupport.unpark(thread2);
        });
        thread2 = new Thread(() -> {
            LockSupport.park();
            for (int i = 0; i < list.size(); i++) {
                Object o = list.get(i);
                synchronized (o) {
                    if (i == 18 || i == 19) {
                        System.out.println("THREAD-2 Object"+(i+1)+":"+ClassLayout.parseInstance(o).toPrintable());
                    }
                }
            }
            LockSupport.unpark(t3);
        });
        t3 = new Thread(() -> {
            LockSupport.park();
            for (int i = 0; i < list.size(); i++) {
                Object o = list.get(i);
                synchronized (o) {
                    System.out.println("THREAD-3 Object"+(i+1)+":"+ClassLayout.parseInstance(o).toPrintable());
                }
            }
        });

        thread1.start();
        thread2.start();
        t3.start();
        t3.join();
        System.out.println("New: "+ClassLayout.parseInstance(new Object()).toPrintable());
    }

    /**
     * 轻量锁(00)升级为重量锁(10)
     * @throws InterruptedException
     */
    static void lockUpgrade() throws InterruptedException {
        User user = new User();
        System.out.println("--MAIN--:" + ClassLayout.parseInstance(user).toPrintable());
        Thread thread1 = new Thread(() -> {
            synchronized (user) {
                System.out.println("--THREAD1--:" + ClassLayout.parseInstance(user).toPrintable());
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread thread2 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (user) {
                System.out.println("--THREAD2--:" + ClassLayout.parseInstance(user).toPrintable());
            }
        });

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        TimeUnit.SECONDS.sleep(3);
        System.out.println(ClassLayout.parseInstance(user).toPrintable());
    }

    static void test2() throws InterruptedException {
        User user=new User();
        synchronized (user){
            System.out.println(ClassLayout.parseInstance(user).toPrintable());
        }
        Thread thread = new Thread(() -> {
            synchronized (user) {
                System.out.println("--THREAD--:"+ClassLayout.parseInstance(user).toPrintable());
            }
        });
        thread.start();
        thread.join();
        System.out.println("--END--:"+ClassLayout.parseInstance(user).toPrintable());
    }

    /**
     * 查看对象内存布局，注意 jvm 使用大端序，例：
     *  <p>
     *  com.medolia.javaLab.jvm.Person object internals:
     *  OFFSET  SIZE               TYPE DESCRIPTION                               VALUE
     *  0     4                    (object header)                           01 87 28 1d (00000001 10000111 00101000 00011101) (489195265)
     *  4     4                    (object header)                           5b 00 00 00 (01011011 00000000 00000000 00000000) (91)
     *  8     4                    (object header)                           43 c1 00 f8 (01000011 11000001 00000000 11111000) (-134168253)
     *  <p>
     *  此为一个实例的对象头内存结构，MarkWord 部分应为 00000000 00000000 00000000 01011011 00011101 00101000 10000111 00000001
     *  即 25 bit unused (0000000000000000000000000) + 31 bit hashcode (1011011000111010010100010000111 十进制为1528637575) + 1 bit unused (0)
     *  + 4 bit 分代年龄 (0000 未进入过老年代) + 1 bit 偏向锁位 (0 无偏向) + 2 bit 锁标志位 (01 无锁态)
     *  对象指针为(jdk 默认使用指针压缩) 11111000 00000000 11000001 01000011
     */
    static void test1() {
        /*Object obj = new Object();
        Object[] arr = new Object[Integer.MAX_VALUE / 10];
        Person person = new Person("15468", 17, 865147669);

        Person user = new Person();
        System.out.println(ClassLayout.parseInstance(user).toPrintable());
        System.out.println(user.hashCode());
        System.out.println(ClassLayout.parseInstance(user).toPrintable());*/

        /*System.out.println(VM.current().details());
        System.out.println("-------------------------------------");
        System.out.println(ClassLayout.parseInstance(arr).toPrintable());*/
        // System.out.println(ClassLayout.parseInstance(person).toPrintable());

        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>(16);
        System.out.println(ClassLayout.parseInstance(map).toPrintable());

        // System.out.println(ClassLayout.parseClass(Person.class).toPrintable());
    }
}

class User {
    String id;
    int age;
    static long phone;

    public User() {
    }

    public void setId(String id) {
        this.id = id;
    }

    public User(String id, int age) {
        this.id = id;
        this.age = age;
    }
}
