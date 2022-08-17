import javax.swing.JOptionPane;

public class JavaPlayGround {
    public static void main(String[] args) {
        Thread1 t1 = new Thread1();
        t1.start();

        String input = JOptionPane.showInputDialog("input message");
        System.out.println("input : " + input);
        t1.interrupt();
        System.out.println("t1's Interrupted : " + t1.isInterrupted());

    }
}

class Thread1 extends Thread{
    public void run() {
        int i = 10;
        while (i != 0 && !isInterrupted()) {
            System.out.println(i--);
            try {
                Thread.sleep(1000);
                Thread.suspend();
            } catch (InterruptedException e) { }
        }
        System.out.println("Count Done.");
    }

}
