### super

객체 자신을 가리키는 참조변수. 인스턴스 메서드(생성자) 내에서만 존재(static 메서드 내에서 사용 불가.)

조상의 멤버를 자신의 멤버와 구별할 때 사용.

```java
class Example {
  public static void main(String args[]) {
    Child c = new Child();
    c.method();
  }
}
class Parent { int x = 10; }
class Child extends Parent {
  int x = 20;         //만약 이부분이 없으면 x, this.x, super.x 모두 10.
  
  void method() {
    System.out.println(x);       //20
    System.out.println(this.x);  //20
    System.out.println(super.x); //10
  }
}
```





### super()

조상의 생성자를 호출 할 때 사용.

조상의 멤버는 조상의 생성자를 호출해서 초기화

```java
class Point {
  int x,y;
 
  Point(int x, int y) {
    this.x = x;
    this.y = y;
  }
}

//아래와 같이 하면 안됌.
class Point3D extends Point {
  int z;
  
  Point3D(int x, int y, int z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }
}
//조상 멤버는 조상이 자신의 멤버는 자신이 초기화 해야 함.
class Point3D extends Point {
  int z;
  
  Point3D(int x, int y, int z) {
    super(x, y); // 조상클래스의 생성자 Point(int x, int y) 를 호출
    this.z = z;  // 본인 건 본인이 초기화.
  }
}
```



모든 생성자는 첫 줄에 다른 생성자(this, super)를 호출 해 줘야 함.