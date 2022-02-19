package com.project.my.homeworks.hw7.q7;

public class TwoThreads {
    static Thread laurel, hardy;

    public static void main(String[] args) {
        laurel = new Thread(() -> {
            System.out.println("A"); // this is the first line to print
            try {
                hardy.sleep(1000);// this code sleeps current thread and then, second thread will be started.
            } catch (Exception e) {
                System.out.println("B"); // this line never be printed bcz no exception will be thrown!
            }
            System.out.println("C"); // after 1 seconds, this thread continues and prints this line as the last result.
        });
        hardy = new Thread(() -> {
            System.out.println("D"); // after sleeping the first thread, this line will be printed by second thread.
            try {
                laurel.wait(); // current thread is not owner so it throws an exception
            } catch (Exception e) {
                System.out.println("E"); // by throwing exception, this line will be printed
            }
            System.out.println("F"); //printing this line doesn't take too long, so it will be printed before the first thread waking up!
        });
        laurel.start(); // the first thread with high probability will be started before the second one
        hardy.start(); // by assuming starting this thread after the first one, the output will be permanent at each run.

        /*According to comments I wrote, the output is:
        * A
        * D
        * E
        * F
        * C
        * */
    }
}
