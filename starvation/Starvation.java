package Lesson11.starvation;

public class Starvation extends Thread{
    static int count = 1;
    @Override
    public void run() {

        System.out.println(count + "thread started");
        System.out.println(count + "completed");
        count++;
    }

    public static void main(String[] args) {

        System.out.println("started");

        Starvation thread1 = new Starvation();
        thread1.setPriority(7);
        Starvation thread2 = new Starvation();
        thread2.setPriority(6);
        Starvation thread3 = new Starvation();
        thread3.setPriority(5);
        Starvation thread4 = new Starvation();
        thread4.setPriority(4);

        thread1.run();
        thread2.run();
        thread3.run();

        //this ll wait because of its priority
        thread4.run();

        System.out.println("completed");
    }
}
