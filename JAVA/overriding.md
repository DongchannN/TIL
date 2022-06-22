### overriding

상속받은 조상의 메서드를 자신에 맞게 변형.

```java
class Point {
  int x;
  int y;
  
  String getLocation() {
    return "x :" +x + ",y :" +y;
  }
}

class Point3D extends Point {
  int z;
  String getLocation() { //overriding. -> 내용만 변경 가능.(선언부 변경 불가)
    return "x :" +x + ",y :" +y + ",z :" +z;
  }
}

public class Overriding {
  public static void main(String[] args) {
    Point3D point = new Point3D();
    point.x = 1;
    point.y = 2;
    point.z = 3;
    System.out.println(point.getLoacation())
  }
}
```





```java
class TwoNumber extends Object {  //extends Object는 생략 가능 (컴파일러 자동 추가)
  int x;
  int y;
  
  TwoNumber(int x, int y) {
    this.x = x;
    this.y = y;
  }
  //Object의 메서드 toString을 오버라이딩
  public String toString() {
    return "x :" +x + ",y :" +y;
  }
}

public class Override {
  public static void main(String[] args) {
    TwoNumber twonum = new TwoNumber(3,5);  //More simple.
    
    //TwoNumber twonum = new TwoNumber();
    
    //twonum.x = 2;
    //twonum.y = 5;
    
    //아래 두개 출력 같음. toString 생략가능
    System.out.println(twonum);
    System.out.println(twonum.toString());
  }
}
```





- 오버라이딩 조건

1. 선언부가 조상클래스의 메서드와 일치해야함.
2. 접근 제어자(public, protected, default, private)를 조상 클래스의 메서드보다 좁은 범위로 설정할 수 없음.
3. 예외는 조상 클래스의 메서드보다 많이 선언 할 수 없음.

```java 
class Parent {
  void parentMethond() throws IOException, SQLException { //예외 두개.
    
  }
}
class Child extends Parents {
  void childMethod() throw IOException {  //예외 한개.
    
  }
}
```





- 오버로딩은 오버라이딩과 관계가 없음.

오버로딩 - 상속과 관계 없음. 기존에 없는 새로운 메서드를 정의 하는 것.

오버라이딩 - 상속받은 메서드의 내용을 변경.
