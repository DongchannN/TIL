### Thread

##### 쓰레드의 실행 제어

아래의 사진은 쓰레드의 스케줄링과 관련된 메서드이다.

![스크린샷 2022-08-14 오후 4.33.32](/Users/dongchan/Library/Application Support/typora-user-images/스크린샷 2022-08-14 오후 4.33.32.png)

아래의 사진은 쓰레드의 상태에 관한 설명이다.

![스크린샷 2022-08-14 오후 4.34.15](/Users/dongchan/Library/Application Support/typora-user-images/스크린샷 2022-08-14 오후 4.34.15.png)

![스크린샷 2022-08-14 오후 4.35.39](/Users/dongchan/Library/Application Support/typora-user-images/스크린샷 2022-08-14 오후 4.35.39.png)

위 사진은 쓰레드의 생성부터 소멸까지의 모든 과정을 그린 것이다.

1. 쓰레드를 생성하고 start()를 호출하면 쓰레드는 실행 대기열에 저장되어 있다. 대기열은 큐(queue)와 같은 구조로 먼저 실행 대기열에 들어온 쓰레드가 먼저 실행된다.
2. 실행 대기 상태에 있다 자신의 차례가 되면 실행된다.
3. 주어진 실행 상태가 다 되거나 yield()를 만나면 다시 실행 대기열로 들어간다.
4. 4번 위에 쓰여져 있는 메서드들을 만나면 일지정지 상태가 된다. I/O block은 입출력 작업에서 발생하는 지연상태에다(ex. 사용자의 입력을 기다리는 경우.)
5. 지정된 일시정지시간이 다되거나 5번위의 메서드들을 만나면 다시 실행 대기열로 들어간다.
6. 실행을 모두 마치거나 stop()이 호출되면 쓰레드는 소멸된다.



- sleep(long millis, int nanos) - 일정시간동안 쓰레드를 멈추게 함.

```java
//쓰레드를 0.0015초 동안 멈추게 하는 방법.
try {
  Thread.sleep(1, 500000); //millis - 1000분의 1초, nanos - 10억분의 1초.
} catch(InterruptedException e) { }
```

하지만 t1에 sleep()메서드를 호출했음에도 아래의 코드를 보면 t1의 작업이 먼저 종료 되고 t2의 작업이 종료되는 것을 알 수 있다.

```java
public class JavaPlayGround {
    public static void main(String[] args) {
        Thread1 t1 = new Thread1();
        Thread2 t2 = new Thread2();

        t1.start();
        t2.start();

        try {
            t1.sleep(3000);
        } catch (InterruptedException e) { }

        System.out.println("<main done.>");
    }
}

class Thread1 extends Thread {
    public void run() {
        for (int i = 0; i < 300; i++) {
            System.out.print("1");
        }
        System.out.print("<1 done.>");
    }
}

class Thread2 extends Thread {
    public void run() {
        for (int i = 0; i < 300; i++) {
            System.out.print("2");
        }
        System.out.print("<2 done.>");
    }
}
```

![스크린샷 2022-08-14 오후 11.36.58](/Users/dongchan/Library/Application Support/typora-user-images/스크린샷 2022-08-14 오후 11.36.58.png)

그 이유는 sleep은 현재 실행 중인 쓰레드에 대해 작동하기 때문에 t1.sleep(2000)과 같이 호출했어도 실제로 영향을 받는 것은 main쓰레드이기 때문이다. 그래서 sleep()은 static으로 선언되어 있으며 참조변수를 이용해 호출하기보다 Thread.sleep(2000)과 같이 호출해야한다.



- interrupt()와 interrupted() - 쓰레드의 작업을 취소한다.

큰 파일을 다운로드 받을 때와 같이 시간이 너무 오래걸리면 중간에 다운로드를 포기하고 취소할 수 있어야 한다. 이와 같은 상황일 때 진행 중인 쓰레드의 작업이 끝나기 전 작업을 멈추라고 요청하기 위해 interrupt()가 쓰인다.

interrupt()는 쓰레드의 interrupted상태를 바꾸는 것일 뿐이고, interrupted()는 쓰레드에 대해 interrupt()가 호출되었는지 알려준다. 호출되었으면 true, 그렇지 않다면 false를 반환한다.

isInterrupted()라는 메서드 또한 쓰레드의 interrupted상태를 반환하지만 interrupted()와는 다르게 interrupted상태를 false로 초기화해주지 않는다.

```java
Thread th = new Thread();
th.start();

th.interrupt();

class MyThread extends Thread {
  public void run() {
    //interrupted()의 값이 false일때만 반복, !interrupted()는 그 때 true.
    while(!interrupted()) {
      //내용생략
    }
  }
}
```



쓰레드가 sleep(), wait(), join()에 의해 "일시정지 상태"에 있을 경우 해당 쓰레드에 대해 interrupt()를 호출하면 InterruptedException이 발생하고 쓰레드는 "실행 대기상태"로 바뀐다.

아래의 코드는 카운트 다운을 실행하고 사용자의 입력이 들어오면 카운트다운을 멈추는 코드이다.

```java
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
            for (long x = 0; x < 2500000000L; x++);
        }
        System.out.println("Count Done.");
    }
}
```

위의 예제에서는 반복문으로 시간을 지연시켰다. 하지만 sleep()으로 시간을 지연시키면 어떻게 될까.

```java
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
            } catch (InterruptedException e) { }
        }
        System.out.println("Count Done.");
    }
}
```

이 코드를 실행시켜보면 반복문을 sleep()으로만 변경해줬음에도 입력한 후에 계속 카운트다운이 종료되지 않고있다. 

그 이유는 Thread.sleep()에 의해 쓰레드가 잠시 멈춰있을 때 interrupt()를 호출하면 InterruptedException이 발생하기 때문이다. 그리고 InterruptedException이 발생하면 쓰레드의 interrupted상태는 false로 자동초기화가 된다. 이와 같은 경우에는 catch구문에 interrupt()를 추가로 넣어줘 interrupted상태를 다시 true로 바꾸어 주면 된다.