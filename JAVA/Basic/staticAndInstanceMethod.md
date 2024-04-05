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
    System.out.println(MyMath.add(200L, 100L));  //Static Method, 객체 생성 X.( 클래스이름.메서드이름(매개변수) )
    
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

1. 클래스 설계 시, 멤버 변수 중 모든 인스턴스에 공통으로 사용하는 것에 static 붙임.
2. 클래스 변수(static 변수)는 인스턴스를 생성하지 않아도 사용할 수 있다.
3. 클래스 변수(static 변수)는 인스턴스 변수를 사용 할 수 없다
4. 메서드 내에서 인스턴스 변수를 사용하지 않는다면, static을 붙이는 것을 권장한다.

Ex) Math클래스의 메서드는 작업을 수행하는데 필요한 값들은 모두 매개변수로 받아서 처리 -> 클래스 메서드.

### 클래스 멤버와 인스턴스 멤버간의 참조, 호출



```java
class TestClass2 {
  int iv;                //instance variable : 객체 생성 후 사용 가능
  static int cv;         //class variable : 언제나 사용 가능.
  
  void instanceMethod() {
    System.out.println(iv);
    System.out.println(cv);
  }
  
  static void staticMethod() { //클래스 메서드는 인스턴스 변수를 사용할 수 없음.
    System.out.println(iv);   //ERROR : Can not use instance variable
    System.out.println(cv);
  }
}
```

```java
class TestClass {
  void instanceMethod() {}
  static void staticMethod() {}
 
  
  void instanceMethod2() { //인스턴스 메서드는 객체 생성을 하지 않고도 인스턴스메서드 클래스메서드를 호출할 수 있음.
    instanceMethod();
    staicMethod();
  }
  
  static void staticMethod2() { //클래스 메서드는 인스턴스 메서드를 호출할 수 없다.
    instanceMethod();       //ERROR : Same reason that can't use instance variable.
    staticMethod();
  }
}
```



클래스 메서드에서 인스턴스 변수 및 메서드를 호출 하기 위해서는 객체생성이 필요함.

```java
class MemberCall {
  void instanceMethod() {
    System.out.println("ABC");
  }
  
  int iv = 10;
  static int cv = 100;
  
  MemberCall mc = new MemberCall(); //객체 생성
  
  static int cv2 = mc.iv;  //static int cv2 = iv로 하면 에러.
  
  static void staticMethod() {
    MemberCall c = new MemberCall();
    System.out.println(c.iv); //객체생성 필수.
    c.instanceMethod();
  }
}
```

