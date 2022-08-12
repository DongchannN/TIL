### Thread

##### 쓰레드의 우선순위

시각적인 부분이나 사용자에게 빠르게 반응해야하는 작업을 하는 쓰레드는 우선순위를 부여해야한다. 예를 들어 카카오톡과 같은 경우 채팅 내용을 전송하는 쓰레드는 파일을 전송하거나 내려받는 쓰레드보다 우선순위가 높아야 사용자가 불편함 없이 앱을 이용할 수 있을 것이다. 이렇게 쓰레드는 특정 쓰레드가 더 많은 작업시간을 가지도록 우선순위를 정할 수 있다. 

```java
void setPriority(int new Priority) //쓰레드의 우선순위를 지정한 값으로 변경한다.
int getPriority()  //쓰레드의 우선순위 반환

public static final int MAX_PRIORITY = 10 //최대 우선순위
public static final int MAX_PRIORITY = 1  //최소 우선순위
public static final int MAX_PRIORITY = 5  //보통 우선순위
```

쓰레드가 가질 수 있는 우선순위는 1부터 10까지이며 값이 높을 수록 우선순위가 높아진다. 또한 쓰레드의 우선순위는 쓰레드를 생성한 쓰레드로부터 상속받는 것이다 (EX. main메서드의 우선순위는 5이다. 그러므로 main메서드에서 생성하는 쓰레드의 우선순위 또한 5가 된다.)

```java
class Main {
  public static void main(String[] args) {
    Thread1 t1 = new Thread1();
    Thread2 t2 = new Thread2();
    
    t2.setPriority(7);
    
    t1.start(); //우선순위 5
    t2.start(); //우선순위 7
  }
}

class Thread1 extends Thread {
  public void run() {
    for(int i = 0; i < 300; i++) {
      System.out.println("-");
      for(int x=0; x < 10000000; x++); //시간 지연을 위한 반복문.
    }
  }
}

class Thread2 extends Thread {
  public void run() {
    for(int i = 0; i < 300; i++) {
      System.out.println("|");
    }
    for(int x=0; x < 10000000; x++);
  }
}
```

위와 같은 경우 우선순위가 높은 t2 쓰레드가 먼저 작업이 끝나는 것을 알 수 있다.

##### 쓰레드 그룹

