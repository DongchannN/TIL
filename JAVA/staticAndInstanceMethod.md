### Static method(Class method) and Instance Method

인스턴스 변수를 사용하나 못하나 차이.

- Instance Method

  인스턴스 생성 후 '참조변수.메서드이름()'으로 호출.

  인스턴스 맴버 관련된 작업

  인스턴스 변수 메서드 내에서 사용 가능

- Static Method

  객체 생성 없이 '클래스이름.메서드이름()'으로 호출.

  인스턴스 맴버와 무관

  인스턴스 변수 메서드 내 사용 불가

```java
class MyMath {
  long a, b;  //인스턴스 변수(instance variable)
  //instance method
  long add() {            
    return a + b;  //instance variable
  }
  //class method(static method) : No need instance variable.
  static long add(long a, long b) { //a,b : local variable
    return a + b;                   //a,b : local variable
  }
}
```

```java
class MyMathTest {
  public static void main(String[] args) {
    System.out.println(MyMath.add(200L, 100L));  //Static Method, 객체 생성 X.
    
    MyMath mm = new MyMath();                    //Instance Method, 객체 생성 필요.
    mm.a = 200L;
    mm.b = 100L;
    System.out.println(mm.add());
  }
}
```



### Static을 붙여야 하는 경우

- 공통된 속성에 static을 붙임
- 인스턴스 맴버를 사용하지 않는 메소드에 붙임



```java
class TestClass2 {
  int iv;                //instance variable : 객체 생성 후 사용 가능
  static int cv;         //class variable : 언제나 사용 가능.
  
  void instanceMethod() {
    System.out.println(iv);
    System.out.println(cv);
  }
  
  static void staticMethod() {
    System.out.println(iv);   //ERROR : Can not use instance variable
    System.out.println(cv);
  }
}
```

```java
class TestClass {
  void instanceMethod() {}
  static void staticMethod() {}
 
  
  void instanceMethod2() {
    instanceMethod();
    staicMethod();
  }
  
  static void staticMethod2() {
    instanceMethod();       //ERROR : Same reason that can't use instance variable.
    staticMethod();
  }
}
```

