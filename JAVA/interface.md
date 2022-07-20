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



### 인터페이스의 장점

역할 : 두 대상(객체) 간의 '연결, 대화, 소통'을 돕는 중간역할을 한다.

- 선언(설계, 껍데기)와 구현(알맹이)을 분리시킬 수 있다.

- User(A)가 Provider(B)를 사용할 때 인터페이스를 사용하면 Provider를 바꾸어도 A의 코드는 바꿀 필요가 없다.(느슨한 결합.)  --> 개발 시간을 단축할 수 있다, 변경에 유리한 설계가 가능하다.

- 표준화가 가능하다.( JDBC )
- 서로 관계없는 클래스들을 관계를 맺어줄 수 있다.

```java
class A {
  public void methodA(B b) {
    b.methodB();  //B클래스 메서드 호출.
  }
}

class B {
  public void methodB() {
    System.out.println("METHOD B");
  }
}

public class InterfaceTest {
  public static void main(String[] args) {
    A a = new A();
    a.methodA(new B());  //A가 B를 사용.(A가 B에 의존한다.)
  }
}
```

A와 B가 관계가 있는 경우 A가 C를 사용 할 시 A와 InterfaceTest 두 곳을 변경해야함.



```java
class A {
  public void methodA(I i) {
    i.methodB();  //인터페이스를 통하도록 함.  ->  class B와 관계가 없어짐.
  }
}

interface I { void methodB(); }

class B implements I {
	public void methodB() {
    System.out.println("METHOD B");
  }
}

public class interfaceTest {
  public static void main(String[] args) {
    A a = new A();
    a.methodA(new B());
  }
}
```

A는 B와 관계가 없고 인터페이스 I를 통하기 때문에 B를 C로 변경해도 interfaceTest는 변경해도 A는 변경할 필요가 없음.



인터페이스의 장점 중 하나인 서로 관계없는 클래스들을 관계 맺어주는 방법을 알아보자.

예를 들어 Unit이라는 클래스를 상속받은 GroundUnit과 AirUnit이 있고 GroundUnit을 상속받은 Marine, SCV, Tank 그리고 AirUnit을 상속받은 Dropship이 있다고 생각을 해보자. 우리는 기계인 SCV, Tank, Dropship을 고칠 수 있는 메서드를 만들고 싶다. 그렇다면 어떤 방법이 있을까?



```java
//방법1. 오버로딩
void repair(SCV s) {
  //내용 생략.
}
void repair(Tank t) {
  //내용 생략.
}
void repair(Dropship ds) {
  //내용 생략.
}
```

위와 같이 코드를 작성하면 수리가 가능한 유닛의 갯수만큼 메서드를 오버로딩해야하고 수리도 쉽지 않을 것이다.



```java
//방법 2. 부모 클래스 이용
void repair(GroundUnit gu) {
  //내용 생략.
}
```

위와 같은 코드를 작성하면 수리가 불가능한 Marine이 repair메서드에 들어갈 수 있고 수리가 필요한 Dropship유닛이 수리를 못하게 되는 문제점이 발생한다.



```java
//방법 3. 인터페이스 사용.
interface Repairable { }

class SCV extends GroundUnit implements Repairable {
  //내용생략.
}

class Tank extends GroundUnit implements Repairable {
  //내용생략.
}

class Dropship extends AirUnit implements Repairable {
  //내용생략.
}


//메서드
void repair(Repairable r) {
  if(r instanceof Unit) {
    Unit u = (Unit) r;
    while(u.hitPoint != u.MAX_HP) {
      u.hitPoint++;
    }
  }
}
```

위와 같이 Repairable이라는 인터페이스를 만들어 수리를 할 수 있는 클래스에 이 인터페이스를 구현하게 되면 관계가 없는 클래스에 공통점이 생겨 효율적으로 repair메서드를 작성 할 수 있다.



```java
//자세한 코드
public class Main {
  public static void main(String[] args) {
    Tank tank = new Tank();
  }
}

interface Repairable {}

class Unit {
  int hitPoint; //HP
  final int MAX_HP;
  Unit(int hp) { //생성자
    MAX_HP = hp
  }
}

class GroundUnit extends Unit {
  GoundUnit(int hp) {
    super(hp);
  }
}

class AirUnit extends Unit {
  AirUnit(int hp) {
    super(hp);
  }
}
//수리가 가능한 것에는 Repairable구현.
class Tank extends GroundUnit implements Repairable {
  Tank() {
    super(150);  //MAX_HP = 150으로 설정.
    hitPoint = MAX_HP;
  }
  
  public String toString() {
    return "Tank";
  }
}

class SCV extends GroundUnit implements Repairable {
  SCV() {
    super(60);  //MAX_HP = 150으로 설정.
    hitPoint = MAX_HP;
  }
  
  public String toString() {
    return "SCV";
  }
  
  void repair(Repairable r) {  //SCV가 다른 유닛들을 수리할 수 있게한다.
  if(r instanceof Unit) {
    Unit u = (Unit) r;
    while(u.hitPoint != u.MAX_HP) {
      u.hitPoint++;
      }
    System.out.println( u.toString + " 수리 완료.")
    }
  }
  
}
//수리가 불가능 한 것에는 Repairable 구현하지 않는다.
class Marine extends GroundUnit {  
  Marine() {
    super(40);  //MAX_HP = 150으로 설정.
    hitPoint = MAX_HP;
  }
  
  public String toString() {
    return "Marine";
  }
}

class Dropship extends AirUnit implements Repairable {
  Dropship() {
    super(125);  //MAX_HP = 150으로 설정.
    hitPoint = MAX_HP;
  }
  
  public String toString() {
    return "Dropship";
  }
}
```





### 디폴트 메서드와 static메서드

인터페이스에 어떠한 메서드(추상 메서드)를 추가할 때 그 인터페이스를 구현한 모든 클래스들이 그 추상메서드를 구현해야함 -> 변경이 많음. -> 디폴트 메서드로 해결. ( 충돌문제가 생길 수 있음. )

디폴트 메서드 기존의 메서드와 충돌할 때의 해결책( 그냥 직접 오버라이딩. )

1. 여러 인터페이스의 디폴트 메서드 간의 충돌 : 인터페이스를 구현한 클래스에서 디폴트메서드를 오버라이딩. 
2. 디폴트 메서드와 조상 클래스의 메서드 간의 충돌 : 조상 클래스의 메서드가 상속되고 디폴트 메서드는 무시됨.