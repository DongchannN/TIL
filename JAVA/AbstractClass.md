### 추상 클래스

미완성 설계도. 미완성 메서드를 갖고 있는 클래스

꼭 필요하지만 자손마다 다게 구현될 것으로 예상되는 경우 사용.

```java
abstract class Player {       // 추상 클래스.
  abstract void play(int pos);// 추상 메서드(구현부가 없음.) 주석으로 어떤 기능을 수행할 것인이 작성하는 것이 좋음.
  abstract void stop();       // 끝에 ;(세미콜론)은 붙여야 함.
}
```

다른 클래스 작성에 도움을 주기 위한 것. -> 인스턴스 생성 불가.

```java
Player p = new Player(); //ERROR.
```



상속을 통하여 추상 메서드를 완성해야 인스턴스를 생성 할 수 있다.

```java
class AudioPlayer extends Player {  //AudioPlayer는 완성된 설계도.
  void play(int pos) {  }
  void stop() {  }
}

abstract class AbstractPlayer extends Player {  //추상메서드가 하나 남았으므로 앞에 abstract를 꼭 붙여야 함.
  void play(int pos) {   }  //추상 메서드를 하나만 구현.
}

public class Main {
  public static void main(String[] args) {
    AudioPlayer ap = new AudioPlayer(); //인스턴스 생성 가능.
    Player p = new AudioPlayer(); //조상 클래스로 참조변수 설정 가능.
  }
}
```



추상 메서드는 구현부가 없지만 호출 할 수 있다. 인스턴스 메서드는 객체 생성을 한 후 사용할 수 있는데 객체 생성을 할 때에는 이미 구현부가 존재하므로 호출 할 수 있는 것이다.

```java
abstract class Player {
  boolean pause;   //지금 상태를 저장하기 위한 변수
  int currentPos;  //현재 play되고 있는 위치 저장하기 위한 변수
  
  Player() {  //생성자.
    pause = false;
    currentPos = 0;
  }
  //지정된 포지션에서 재생을 시작하는 기능 수행 작성.
  abstract void play(int pos);  //추상메서드
  //재생을 즉시 멈추는 기능 수행 작성.
  abstract void stop();         //추상메서드
  
  void play() {
    play(currentPos);          //추상 메서드를 호출하고 있음 -> 가능.(상속을 통해 자손이 완성할 수 있기 때문.)
  }
}
```

위 코드의 play메서드는 추상 메서드이지만 호출 가능하다. 상속을 통해 자손이 구현부를 완성하고 자손 객체를 생성해여 play메서드를 호출 할 수 있다.



```java
abstract class Player { //추상 클래스
  abstract void play(int pos);
  abstract void stop();
}

class AudioPlayer {
  void play(int pos) {
    System.out.println("채널 : "+ pos);
  }
  void stop() {
    System.out.println("재생을 멈춥니다.");
  }
}
public class Main {
  public static void main(String[] main) {
    Player p = new AudioPlayer();   //다형성. Player의 메서드들이 구현이 안되어있어도 실제 인스턴스는 AudioPlayer.
    p.play(100);
    p.stop();
  }
}
```





### 추상 클래스의 작성

여러 클래스에 공콩적으로 사용될 수 있는 추상 클래스를 바로 작성하거나 기존 클래스의 공통 부분을 뽑아 추상 클래스를 만든다.

추상적인 코드는 구체적인 코드보다 변경에 유리하다. 그래서 코드를 추상적으로 작성하고 자손들의 구체적인 코드로 용도에 맞게 유연하게 변경시키는 것이다.

```java
public class Main {
  public static void main(String[] args) {
    Unit[] group = {new Marine(), new Tank(), new Dropship()}; //객체배열
    
    for(int i = 0; i < 3; i++) {
      group[i].move(100, 200);
    }
    
    Object[] group2 = new Object[3];
    group2[0] = new Marine();
    group2[1] = new Tank();
    group2[2] = new Dropship();
    
    group2.move(100, 200); //ERROR.(Object에는 move라는 메서드가 없음)
  }
}

abstract class Unit {
  int x, y;
  abstract void move(int x, int y);
  void stop() {/*현재 위치에 정지*/};
}

class Marine extends Unit {
  void move(int x, int y) {
    System.out.println("Marine[x : "+ x + ",y : " + y + "]");
  }
  void stimPack() {/*use stimPack*/}
}

class Tank extends Unit {
  void move(int x, int y) {
    System.out.println("Tank[x : "+ x + ",y : " + y + "]");
  }
  void changeMode() {/*AttackMode and BasicMode*/}
}

class Dropship extends Unit {
  void move(int x, int y) {
    System.out.println("Dropship[x : "+ x + ",y : " + y + "]");
  }
  void drop() {/*drop units*/}
}
```

