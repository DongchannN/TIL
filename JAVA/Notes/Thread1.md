### Thread

##### process and thread

프로세스 : 프로그램을 실행하면 그 프로그램은 실행에 필요한 자원을 할당받아 프로세스가 된다.

쓰레드 : 프로세스가 공장이라면 쓰레드는 일꾼이라고 비유할 수 있다. 프로세스의 자원 (데이터와 메모리 등)을 받아 쓰레드가 작업을 수행한다. 모든 프로세스는 하나 이상의 쓰레드를 가지고 있으며 둘 이상의 쓰레드를 가진 프로세스를 '멀티쓰레드 프로세스'라고 한다.

멀티쓰레드에는 장단점이 있는데 장점은 CPU의 사용률을 향상시키고, 자원을 보다 효율적으로 사용 할 수 있다. 그리고 작업이 분리되어 코드가 간결해지며 사용자에 대한 응답성이 향상된다. 하지만 여러 쓰레드가 같은 자원을 가지고 작업을 하기 때문에 '동기화'나 '교착상태'와 같은 문제들을 고려해서 프로그래밍을 해야한다.

만약 카카오톡이 싱글쓰레드로 작성되어있으면 채팅을 하면서 파일을 다운로드 받는 것이 불가능하고 다운로드를 받는 중에는 채팅이 불가능 할 것이다.

##### 쓰레드의 구현, 실행

쓰레드를 구현하는 방법은 두가지가 있다. 첫번째로는 Thread클래스를 상속받는 것이고 두번째는 Runnable인터페이스를 구현하는 것이다. 일반적으로는 Runnable인터페이스를 구현한다. Thread클래스를 상속받을 시 다른 클래스를 상속받을 수 없기 때문이다.

```java
//Thread 클래스를 상속받는 방법.
class MyThread extends Thread {
  public void run() { }
}

//Runnable인터페이스를 구현하는 방법(일반적으로 많이 쓰임)
class MyThread implements Runnable {
  public void run() { }
}
```

위의 코드를 보면 오로지 run()만 정의되어 있다. 쓰레드를 구현한다는 것은 작업하고자하는 내용을 run()의 구현부를 작성하는 것이다.



```java
class Main {
  public static void main(String[] args) {
    ExtendedThread t1 = new ExtendedThread();
    
    Runnable r = new ImplementsThread();
    Thread t2 = new Thread(r); //생성자 Thread(Runnable Target)
    
    t1.start();
    t2.start();
  }
}

class ExtendedThread extends Thread {
  public void run() {
    for(int i = 0; i < 5; i++) {
      System.out.println(getName()); //조상인 Thread의 getName() 호출
    }
  }
}

class ImplementsThread implements Runnable {
  public void run() {
    for(int i = 0; i < 5; i++) {
      //Thread.currentThread() - 현재 실행중인 Thread를 반환한다.
      System.out.println(Thread.currentThread().getName());
    }
  } 
}
```

위 코드를 보면 상속받았을 때와 인터페이스를 구현했을 경우의 인스턴스 생성방법이 다르다.

Runnable인터페이스를 구현한 경우 인터페이스를 구현한 클래스의 인스턴스를 생성한 후 이 인스턴스를 Thread클래스의 생성자의 매개변수로 제공해야한다. 그리고 run을 호출하면 Thread클래스의 run을 오버리이딩 하지 않고도 run을 호출할 수 있다.

Thread 클래스를 상속받은 경우 자손클래스에서 Thread클래스의 메서드를 직접호출할수 있다. 하지만  인터페이스를 구현하면 구현된 메서드가 run()밖에 없기 때문에 위의 코드처럼 Thread클래스의 static메서드인 currentThread메서드를 호출해 쓰레드에 대한 참조를 얻어야한다.

```java
static Thread currentThread(); //현재 실행중인 쓰레드의 참조를 반환한다.
String getName(); //쓰레드의 이름을 반환한다.
```



위의 메인 클래스를 실행해보면 "Thread-0"과 "Thread-1"이 5번씩 호출된다. 쓰레드의 이름을 지정하지 않으면 "Thread-번호"의 형식으로 이름이 정해진다. 이름을 지정하고 싶으면 아래의 생성자나 메서드를 사용할 수 이싸.

```java
Thread(Runnable target, String name)
Thread(String name)
void setName(String name)
```



또한 start()를 호출함으로써 쓰레드를 실행 할 수 있다. 다만 한번 실행이 종료된 쓰레드는 다시 실행할 수 없다.(IllegalThreadStateException 발생).

```java
//아래와 같이 이미 종료된 쓰레드 다시 실행시 예외 발생.
ExtendedThread t1 = new ExtendedThread();
t1.start();
t1.start();

//새로운 쓰레드를 다시 생성해야함.
ExtendedThread t1 = new ExtendedThread();
t1.start();
t1 = new ExtendedThread();
t1.start();
```



##### start()와 run()

앞서 공부한 코드에서 왜 run()이 아닌 start()를 호출했을까? main메서드에서 run()을 호출하는 것은 생성된 쓰레드를 실행시키는 것이 아닌 단순히 클래스에 선언된 메서드를 실행하는 것이다.

start()메서드를 호출하면 새로운 쓰레드가 작업을 실행하는데 필요한 호출스택을 생성해 생성된 호출스택에 run()이 첫번째로 올라간다. 그리고 쓰레드가 종료되면 생성된 호출스택은 소멸된다. 그리고 실행중인 쓰레드가 하나도 없을 대 프로그램은 종료된다.

![스크린샷 2022-08-11 오후 3.22.52](/Users/dongchan/Library/Application Support/typora-user-images/스크린샷 2022-08-11 오후 3.22.52.png)

위 그림은 start()가 아닌 run()을 호출했을 때의 결과다. 우리는 main쓰레드(main도 마찬가지로 쓰레드.)에서 run을 호출하고 싶은 것이 아닌 Thread에서 run을 호출하고 싶으므로 start메서드로 실행해야한다.



##### 싱글쓰레드, 멀티쓰레드

두 작업을 하나의 쓰레드로 처리하는 경우 한 작업을 마친 후 다른 작업을 처리하지만 두개의 쓰레드로 작업하는 경우 짧은 시간동안 두개의 쓰레드가 번갈아가며 작업을 수행해 동시에 두 작업이 처리되는 것과 같이 보인다.

하나의 쓰레드로 두 작업을 하는 경우와 두 쓰레드로 두개의 작업을 하는 경우 시간이 거의 비슷하지만 오히려 쓰레드가 두개일 때 쓰레드간의 작업 전환으로 인해 시간이 더 걸리게 된다. 작업전환(context switching)을 할 때에는 다음에 실행해야할 위치 등의 정보를 저장하고 읽어오는 시간의 소요가 있다.

두개의 쓰레드가 서로 다른 자원을 사용할 경우에는 멀티쓰레드가 효과적이다. 예를들면 사용자로부터 데이터를 입력받는 작업, 네트워크로 파일을 주고받는 작업, 프린터로 파일을 출력하는 작업과 같이 외부기기와 입출력을 필요로하는 경우 등이다

```java
import javax.swing.*;

public class JavaPlayGround {
    public static void main(String[] args) {

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
```

위 코드는 사용자의 입력값을 받으려고 대기하는 동안에는 카운트가 멈춰있고 입력을 받고 출력한 후에 카운트가 작동한다.

하지만 아래의 코드 같은 경우에는 입력값을 받기위해 대기하는 동안에도 쓰레드를 두개로 나누었기에 카운트는 계속 출력된다.

```java
import javax.swing.*;

public class JavaPlayGround {
    public static void main(String[] args) {
        Runnable r = new ImpleThread();
        Thread t = new Thread(r);


        t.start();
        String input = JOptionPane.showInputDialog("input");
        System.out.println(input);

    }
}

class ImpleThread implements Runnable {

    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
            try {
                Thread.sleep(1000);
            } catch (Exception e) { }
        }
    }
}

```

