### 상속 (inheritance)

코드 재사용. 부모 - 자손 관계.

자손은 조상의 모든 멤버를 상속받음. (생성자, 초기화 블럭 제외)

클래스 A, B간의 관계가 A is a B 일 때 주로 상속 사용

- 자바는 단일 상속만을 허용. (충돌 문제를 방지.) -> 상속(비중이 높은 클래스)과 포함 섞기.

- 인터페이스 이용 시 충동 문제를 해결하며 다중 상속 가능.

  

```java
class Parent {
  int age;
}
class Child extends Parent { 
  void play() {
    System.out.println("Play.");
  }
}
```



```java
class Point2D {
  int x;
  int y;
}

class Point3D extends Point2D {  //Point2D의 영향을 받음.
  int z;          //int x,y 는 이미 상속을 받음.
}

class SecondPoint3D { //Point2D의 영향을 받지 않음.
  int x;
  int y;
  int z;
}
```





### 포함(composite)

클래스의 멤버로 참조변수 선언

대부분의 경우 상속보다 포함 사용.

클래스 A, B간의 관계가 A has a B 일 때 주로 포함 사용

```java
class Point2D {
  int x;
  int y;
}

class Circle {
  int x;
  int y;
  int r;
}

class CompositeCircle {
  Point2D c = new Point2D;
  int r;
}
```



```java
class Door {
  int door;  //문의 개수.
}

class Type {
  String type; //차종
}

class Car {
  Door d = new Door();
  Type t = new Type();
  String name;  //차 이름.
}
public class CompositeTest {
  public static void main(String[] args) {
    Car c = new Car();
    c.d.door = 2;
    c.t.type = "Sports";
    c.name = "Huracan";
    
    System.out.println("c.d.door="+c.d.door);
    System.out.println("c.t.type="+c.t.type);
    System.out.println("c.name="+c.name);
  }
}
```



### Object Class

기본적으로 클래스는 자동적으로 오브젝트 클래스를 상속받음.

Object Class : toString(),equals(Object obj), hashCode() 등 11개 메서드 기본 제공.

```java
class Tv extends Object {  //extends Object -> 작성 불필요(컴파일러가 자동적으로 추가)
  
}
class SmartTv extends Tv {
  
}
```

