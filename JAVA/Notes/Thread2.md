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

우리는 서로관련된 문서를 파일안에 넣어서 관리한다. 이와 마찬가지로 서로 관련된 쓰레드들을 쓰레드 그룹으로 묶어서 관리할 수 있다. 또 폴더 안에 폴더가 들어갈 수 있는 것처럼 쓰레드 그룹 안에도 다른 쓰레드 그룹을 포함시킬 수 있다. 

쓰레드 그룹은 보안상의 이유로 도입된 개념이다. 자신이 속한 쓰레드 그룹이나 하위 쓰레드 그룹은 변경할 수 있지만 다른 쓰레드 그룹의 쓰레드를 변경할 수는 없다.

아래는 주요 생성자와 메서드들이다.

![스크린샷 2022-08-13 오후 4.34.06](/Users/dongchan/Library/Application Support/typora-user-images/스크린샷 2022-08-13 오후 4.34.06.png)



```java
Thread(ThreadGroup group, String name)
Thread(ThreadGroup group, Runnable target)
Thread(ThreadGroup group, Runnable target, String name);
Thread(ThreadGroup group, Runnable target, String name, long stackSize);
```

쓰레드를 쓰레드 그룹안에 포함시키려면 위와 같은 생성자를 이용해야한다. 또한 모든 쓰레드는 반드시 쓰레드 그룹안에 포함되어있어야한다. 그러므로 위와 같은 생성자를 이용하지 않았으면 기본적으로 자신을 생성한 쓰레드와 같은 쓰레드 그룹에 속하게 된다.

자바 어플리케이션이 실행되면 JVM은 main과 system이라는 쓰레드 그룹을 만들고 JVM운영에 필요한 쓰레드들은 이 그룹에 포함시킨다. 그러므로 우리가 생성하는 모든 쓰레드그룹은 main쓰레드 그룹의 하위 그룹이 되며 생성자로 쓰레드 그룹을 지정하지 않은 것들은 자동적으로 main쓰레드 그룹에 속하게 된다.

```java
//ThreadGroup 관련 메서드들.

ThreadGroup getThreadGroup() //자신이 속한 쓰레드 그룹을 반환함.

//쓰레드 그룹ㅇ의 쓰레드가 처리되지 않은 예외에 의해 실행이 종료되었을 때 JVM이 자동적으로 아래의 메서드를 호출한다
void uncaughtException(Thread t, Throwable e)
```



```java
public class Main {
    public static void main(String[] args) {
        ThreadGroup main = Thread.currentThread().getThreadGroup();
        //grp1, grp2는 자동적으로 main쓰레드 그룹 하위로 들어감.
        ThreadGroup grp1 = new ThreadGroup("Group1");
        ThreadGroup grp2 = new ThreadGroup("Group2");
				
      	//subGrp1이라는 쓰레드 그룹을 grp1안에 넣음. 쓰레드 그룹의 이름은 subGroup1.
        ThreadGroup subGrp1 = new ThreadGroup(grp1, "subGroup1");

        grp1.setMaxPriority(3);

        Runnable r = new Runnable() {

            public void run() {
                try {
                    Thread.sleep(1000); //쓰레드를 1초간 멈추게함
                } catch (InterruptedException e) { }
            }
        };


        new Thread(grp1, r, "th1").start();
        new Thread(subGrp1, r, "th2").start();
        new Thread(grp2, r, "th3").start();

        System.out.println(">>List of ThreadGroup : " + main.getName() +
                ", Active ThreadGroup : " + main.activeGroupCount() +
                ", Active Thread : " + main.activeCount());

        main.list();
    }
}
```

![스크린샷 2022-08-13 오후 5.02.25](/Users/dongchan/Library/Application Support/typora-user-images/스크린샷 2022-08-13 오후 5.02.25.png)

결과를 보면 쓰레드 그룹에 포함된 하위 쓰레드 그룹이나 쓰레드는 들여쓰기를 이용하여 구분하였다.



##### 데몬 쓰레드

데몬 쓰레드가 아닌 쓰레드인 일반 쓰레드의 작업 돕는 보조적인 역할이 데몬쓰레드이다. 일반쓰레드가 종료되면 데몬쓰레드의 역할이 없어지기 떄문에 일반쓰레드가 모두 종료되면 데몬쓰레드는 강제적으로 자동 종료된다. 가비지 컬렉터, 워드프로세서의 자동저장, 화면자동갱신 등의 쓰레드가 데몬쓰레드에 속한다.

데몬쓰레드는 무한루프와 조건문을 이용해서 실행 후 대기하고 있다가 특정 조건이 만족하면 작업을 수행한 후 다시 대기한다.

데몬쓰레드의 생성방법은 일반 쓰레드와 같지만 실행하기 전에 setDaemon(true)를 호출하면 된다. 또한 데몬 쓰레드가 생성한 쓰레드는 자동적으로 데몬 쓰레드가 된다.

```java
boolean isDaemon() //쓰레드가 데몬쓰레드이면 true반환
void setDaemon(boolean on) //쓰레드를 사용자쓰레드 또는 데몬쓰레드로 변환한다.
```



```java
public class JavaPlayGround implements Runnable {
    static boolean autoSave = false;
    public static void main(String[] args) {
        Thread t = new Thread(new JavaPlayGround());
        t.setDaemon(true); //데몬 쓰레드로 지정.
        t.start();

        for (int i = 0; i <= 10; i++) {
            try {
                Thread.sleep(1000); //1초 지연.
            } catch (InterruptedException e) { }
            System.out.println(i);
						//i가 4일 떄 autoSave의 값을 true로 변겅.
            if(i == 4) 
                autoSave = true;
        }
        System.out.println("terminate program.");
    }

    public void run() {
        while (true) { //계속 무한 루프를 돌고 있음.
            try {
                Thread.sleep(3 * 1000); //3초 지연.
            } catch (InterruptedException e) { }

            if(autoSave) { //autoSave가 참이면 autoSave() 호출.
                autoSave();
            }
        }
    }

    public void autoSave() {
        System.out.println("auto saved.");
    }
}

```

<img src="/Users/dongchan/Library/Application Support/typora-user-images/스크린샷 2022-08-13 오후 5.34.22.png" alt="스크린샷 2022-08-13 오후 5.34.22" style="zoom:70%;" />

위의 코드는 3초마다 autoSave값을 확인하며 그 값이 참이면 autoSave()를 호출한다. 만약 데몬쓰레드로 지정하는" t.setDaemon(true); "문장이 없었다면 이 프로그램은 영원히 종료되지 않을 것이다.