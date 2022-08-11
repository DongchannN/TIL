import javax.swing.*;

public class JavaPlayGround {
    public static void main(String[] args) {
//        Runnable r = new ImpleThread();
//        Thread t = new Thread(r);
//
//
//        t.run();
        String input = JOptionPane.showInputDialog("input");
        System.out.println(input);

        for (int i = 0; i < 10; i++) {
            System.out.println(i);
            try {
                Thread.sleep(1000);
            } catch (Exception e) { }
        }
    }
}

class ImpleThread implements Runnable {

    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName());
        }
    }
}
