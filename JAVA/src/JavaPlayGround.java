public class JavaPlayGround implements Runnable {
    static boolean autoSave = false;
    public static void main(String[] args) {
        Thread t = new Thread(new JavaPlayGround());
        t.setDaemon(true);
        t.start();

        for (int i = 0; i <= 10; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) { }
            System.out.println(i);

            if(i == 4)
                autoSave = true;
        }
        System.out.println("terminate program.");
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(3 * 1000);
            } catch (InterruptedException e) { }

            if(autoSave) {
                autoSave();
            }
        }
    }

    public void autoSave() {
        System.out.println("auto saved.");
    }
}
