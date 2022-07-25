### 내부클래스

- 클래스 내에 선언된 클래스.
- 서로 긴밀한 관계가 있거나, 외부에 불필요한 클래스를 감춤으로써(캡슐화) 코드의 복잡성을 낮춤.

내부클래스의 종류

- 인스턴스 클래스 : 외부 클래스의 멤버변수 선언위치에 선언, 인스턴스 멤버처럼 다루어짐. 주로 외부클래스의 인스턴스멤버들과 관련된 작업에 사용.
- 스태틱 클래스 : 외부 클래스의 멤버변수 선언위치에 선언, static멤버처럼 다루어짐. 주로외부 클래스의 static멤버(특히 static 메서드)에서 사용될 목적으로 선언.
- 지역 클래스 : 외부 클래스의 메서드나 초기화 블럭 안에 선언. 선언된 영역 내부에서만 사용가능.
- 익명 클래스 : 클래스의 선언과 객체의 생성을 동시에 하는 이름없는 클래스(일회용).

### 내부 클래스의 선언

```java
class Outer {
  int iv = 0;
  static int cv = 0;
  
  void MyMethod() {
    int lv = 0;
  }
}
```

```java
class Outer {
  class InstanceInner {}
  static class StaticInner{}
  
  void myMethod() {
    class LocalInner
  }
}
```

위와 같이 선언된 위치에 따라 인스턴스, 스태틱, 로컬로 나뉜다. 선언 위치에 따라 동일한 유효범위와 접근성을 가진다.

### 내부클래스의 제어자와 접근성

```java
class Outer {
  private int iv = 0;
  protected static int cv = 0;
  
  void myMethod() {
    int lv = 0;
  }
}
```

```java
class Outer {
  private class InstanceInner {}
  protected static class StaticInner {}
  
  void myMethod() {
    class LocalInner {}
  }
}
```

위와 같이 내부 클래스가 외부 클래스의 멤버와 같이 간주되고, 인스턴스 멤버와 static멤버 간의 규칙이 내부 클래스에도 똑같이 적용된다. 내부 클래스도 클래스이기 때문에 abstract, final, private, protected와 같은 접근제어자도 사용이 가능하다.

```java
class Main {
  class InstanceInner {
    int iv = 100;
    static int cv = 100; //ERROR. static변수는 static클래스에서만 사용이 가능.
    final static int CONST = 200; //상수는 사용가능.
  }
  
  static class StaticClass {
    int iv = 200;
    static int cv = 200;
  }
  
  void myMethod() {
    class LocalInner {
      int iv = 300;
      static int cv = 200; //로컬클래스에서도 마찬가지로 상수는 사용가능하지만 static변수를 선언할 시 에러가 발생한다.
      final static int CONSTANT = 300; 
    }
  }
  
  public static void main(String[] args) {
    System.out.println(InstanceInner.CONST);
    System.out.println(StaticInner.cv);
  }
}
```

내부 클래스 중 static클래스만 static멤버를 가질 수 있다. 하지만 final과 static이 동시에 붙은 상수의 경우에는 모든 내부 클래스에서 선언이 가능하다.

```java
class Main {
  class InstanceInner {}
  static class StaticInner {}
  //같은 종류의 멤버간에는 서로 직접 접근이 가능하다.
  InstanceInner iv = new InstanceInner();
  StaticInner iv = new staticInner();
  
  static void staticMethod() {
    //static멤버는 인스턴스멤버에 직접 접근이 불가능하다.
    //InstanceInner iv = new InstanceInner();
    
    //접근하고 싶다면 아래와 같이 외부 클래스를 먼저 생성해야한다.
    Main outer = new Main();
    InstanceInner obj1 = outer.new InstanceInner();
    //static클래스는 접근 할 수 있다.
    StaticInner obj2 = new StaticInner();
  }
  
  void instanceMethod() {
    //인스턴스 메서드에서는 인스턴스 멤버와 static 멤버 둘 다 접근이 가능하다.
  }
  
  void MyMethod() {
    class LocalInner {}
    LocalInner lv = new LocalInner();
  }
}
```

인스턴스 멤버는 같은 클래스에 있는 인스턴스 멤버와 static멤버 모두 직접 호출이 가능하지만  stiatc멤버는 인스턴스 멤버를 직접 호출할 수 없는 것과 같이 클래스 또한 마찬가지이다.



```java
class Main {
  private int outerIv = 0;
  static int outerCv = 0;
  
  class InstanceInner {
    int iiv = outerIv;//외부 클래스의 private멤버도 접근 할 수 있다.
    int iiv2 = outerCv;
  }
  static class StaticInner {
    //스태틱 클래스는 외부 클래스의 인스턴스 멤버에 접근 할 수 없다.
    //int siv = outerIv;
    
    static int scv = outerCv;
  }
  
  void myMethod() {
    int lv = 0;
    final int LV = 0;
    
    class LocalInner {
      int liv = outerIv;
      int liv2 = outerCv;
      
      int liv3 = lv; //JDK1.8이하는 에러.
      int liv4 = LV; // 상수 접근 가능
    }
  }
}
```

지역 클래스는 외부 클래스의 인스턴스 멤버와 static멤버를 모두 사용 할 수 있고 지역 클래스가 포함된 메서드에 정의된 지역변수도 사용 할 수 있다.

단, final이 붙은 지역변수만 사용 할 수 있다. -> 메서드가 수행을 마쳐서 지역변수가 소멸된 시점에도 지역클래스의 인스턴스가 소멸된 지역변수를 참조하려는 경우가 발생할 수도 있기 때문이다.

JDK1.8부터 지역 클래스에서 접근하는 지역 변수 앞에 final을 컴파일러가 자동으로 붙여준다. -> 해당 변수의 값이 바뀌는 문장이 있으면 컴파일 에러가 발생한다.



```java
class Outer {
  class InstanceInner {
    int iv = 100;
  }
  
  static class StaticInner {
    static int cv = 300;
    int iv = 200;
  }
  
  void myMethod() {
    class LocalInner {
      int iv = 400;
    }
  }
}

class Main {
  public static void main(String[] args) {
    Outer oc = new Outer(); //인스턴스클래스의 인스턴스를 생성하려면 외부클래스의 인스턴스를 먼저 생성해야한다.
    Outer.InstanceInner ii = oc.new InstanceInner();
    
    System.out.println("ii.lv : "+ ii.lv); //출력  ii.lv : 100
    System.out.println("Outer.StaticInner.cv : "+Outer.StaticInner.cv);
    //출력   Outer.StaticInner.cv : 300
    
    //static내부 클래승의 인스턴스는 바로 생성할 수 있다.
    Outer.StaticInner si = new Outer.StaticInner();
    System.out.println("si.iv : "+si.iv) //출력  si.iv : 200;
  }
}
```

외부 클래스가 아닌 다른 클래스에서 내부 클래스에서 내부 클래스를 생성하고 내부 클래스의 멤버에 접근하는 예제다. 컴파일 시 Outer$InstanceInner.class와 같이 생성된다.

로컬 클래스의 경우 다른 메서드에 같은 이름이 존재할 수 있으므로 내부클래스이름 앞에 숫자가 붙는다.

```java
class Outer {
  int value = 10;
  
  class Inner {
    int value = 20;
    
    void method1() {
      int value = 30;
      System.out.println("value : "+value); //30
      System.out.println("this.value : "+this.value);//20
      System.out.println("Outer.this.value : "+Outer.this.value);//10
    }
  }
}

class Main {
  public static void main(String[] args) {
    //Inner의 method1을 다른 클래스에서 쓰기 위해 
    //Outer객체 선언 후 그 객체를 통한 Inner객체 선언
    Outer outer = new Outer();
    Outer.Inner inner = outer.new Inner();
    inner.method1();
  }
}
```



