### 인터페이스

추상 메서드의 집합.

추가적으로 상수, static메서드, default메서드를 가질 수 있다. (부수적인 것.)

iv, 생성자, 인스턴스 메서드는 가질 수 없음

구현된 것이 전혀 없는 설계도. 모든 멤버가 public인 껍데기.

추상 클래스는 클래스이지만 추상메서드를 가지고 있는 클래스, 인터페이스는 추상메서드의 집합.

```java
interface 인터페이스이름 {
  public static final 타입 상수이름 = 값;
  public abstract 메서드이름(매개변수 목록);
}
```

```java
interface PlayingCard {
  //상수
  public static final int SPADE = 4;
  final int DIAMOND = 3; //항상 public static final이므로 생략 가능, 일부만 써도 가능.
  static int HEART = 2;
  int CLOVER = 1;
  
  //추상 메서드
  public abstract String getCardNumber();
  String getCardKind(); //public abstract 생략가능 (항상 인터페이스의 모든 메서드는 puvlic abstract이므로 생략 가능.)
}
```



인터페이스의 조상은 인터페이스만 가능 ( Object가 최고 조상이 아님. )

클래스와 달리 다중 상속이 가능( 추상메서드는 충돌해도 문제가 없기 때문 ), 클래스는 선언부는 같아도 구현부가 다르면 충돌이 일어날 가능성이 있지만 인터페이스의 메서드는 추상메서드이므로 구현부가 없음.

```java
interface Fightable extends Movable, Attackable { }

interface Movable {
  void move(int x, int y);
}

interface Attackable {
  void attack(Unit u);
}
```



### 인터페이스의 구현

인터페이스에 정의된 추상 메서드를 완성하는 것.(미완성 설계도를 완성하는 것.)

```java
class 클래스이름 implements 인터페이스이름 {
  // 인터페이스에 정의된 모든 추상메서드를 구현해야함.
}
```

```java
class Fighter implements Fightable {
  public void move(int x, int y) {/* */}
  public void attack(Unit u) {/* */}
}
```



모두 구현하지 않을경우 class앞에 abstract를 붙여야 함.

```java
abstract class AbFighter implements Fightable {
  public void move(int x, int y) {/* */}
}
```



### 인터페이스를 이용한 다형성

인터페이스 타입이 매개변수이면 그 인터페이스 구현한 클래스의 객체만 들어올 수 있다.

```java
interface Fightalbe {
  public void move(int x, int y);
  
  //매개 변수 타입이 인터페이스.
  public void attack(Fightable f); //Fightalbe인터페이스를 구현한 클래스의 인스턴스만 가능.
}

class Fighter extends Unit implements Fightable {
  public void move(int x, int y) {/* */}
  public void attack(Unit u) {/* */}
}

public class Main {
  public static void main(String[] args) {
    Unit u = new Figther();
    Fightable f = new Fighter(); //Figher 객체의 멤버가 아무리 많아도 쓸 수 있는 멤버는 두개(move, attack).
    
    f.move(100, 200);
    
    Fighter fighter = new Fighter();
    f.attack(fighter); //fighter는 Fightable이라는 인터페이스를 구현한 Figher이라는 클래스의 인스턴스이므로 attack의 매개변수로 들어올 수 있다.
  }
}
```



인터페이스를 메서드의 리턴타입으로 지정할 수 있다.

반환 타입이 인터페이스라는 뜻은 인터페이스를 구현한 클래스의 인스턴스를 반환한다는 뜻.

```java
public class Main {
  public static void main(String[] args){
    
    Fightable method() {  //Fightable이 리턴타입.
      Fighter f = new Fighter(); //Fighter이라는 클래스는 Fightable이라는 인스턴스를 구현한 클래스, f는 Fighter의 인스턴스.
			
      //그러므로 f를 반환할 수 있음.
      return f; // (Fightable) f 형변환
    }
    
    Fightable f = method();  //Fightable f = new Fighter();와 같은 코드.
  }
}
class Fighter extends Unit implements Fightable {
  public void move(int x, int y) {/* */}
  public void attack(Fightable f) {/* */}
}
```





```java
abstract class Unit {
  int x, y;
  abstract void move(int x, int y);
  void stop() {
    System.out.println("STOP.");
  }
}

interface Fightable {
  void move(int x, int y);  //public abstract 생략.
  void attack(Fightable f);
}

class Fighter extends Unit implements Fightalbe {
  //오버라이딩 시 조상보다 접근제어가 범위가 좁으면 안됨.
  public void move(int x, int y) {  //public 붙여야함. 안붙이면 접근제어자가 default.
    System.out.println("이동 (x , y)" + x + " , " + y);
  }
  public void attack(Fightable f) {
    System.out.println(f + "공격");
  }
  
  Fightable getFightable() { // 싸울 수 있는 상대 가져오기.
    Fighter f = new Fighter();
    return f;
  }
}

public class Main {
  public static void main(String[] args) {
    Fighter f = new Fighter();
    f.move(100, 200);
    f.attack(new Fighter());
    
    Unit uf = new Fighter(); //move, stop만  사용 가능.
    
    Fightable ff = new Fighter(); //move, attack 만 사용 가능.
    
    Fightable otherFighter = f.getFightalbe();
  }
}

```

