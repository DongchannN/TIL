### import 문

클래스를 사용할 때 패키지 이름을 생략할 수 있다.

java.lang패키지의 클래스는 import하지 않고 사용 가능.(String, Object, System, Thread 등)

import문은 패키지선언과 클래스선언 사이에 작성해야 함.

import packageName.className;

import packageName.*;  <- package 안의 모든 클래스

```java
class ImportTest {
  java.util.Date today = new java.util.Date();
}
```

```java
import java.util.Date;
//import java.util.*;  -> *은 모든 클래스
class ImportTest {
  Date today = new Date();
}
```



```java
//1.과 2.의 의미는 서로 다름.
//1.
import java.util.*;
import java.text.*;
//2. java패키지의 모든 클래스.(하위 패키지는 포함되지 않음.)
import java.*;
```



```java
import java.sql.*;  //sql과 util패키지 둘 모두 안에 Date클래스가 있음
import java.util.*;

public class SameClass {
  public static void main(String[] args) {
    java.util.Date today = new java.util.Date();//어느 패키지 안의 클래스인지 앞에 패키지명을 붙여줘야함.
  }
}
```





### static import 문

static 멤버를 사용할 때 클래스 이름을 생략할 수 있게 해준다.

```java
import static java.lang.Integer.*;   //Integer클래스의 모든 static메서드.
//import static java.lang.Math.random; //괄호는 안붙임.
import static java.lang.Math.*;
import static java.lang.System.out;  //System.out을 out만으로 참조가능.

class StaticTest {
  public static void main(String[] args) {
    //System.out.println(Math.random());
    out.println(random());
    
    //System.out.println("Math.PI : " + Math.PI);
    out.println("Math PI : " + PI);
  }
}
```

