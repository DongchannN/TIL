### 제어자(modifier)

- 클래스와 클래스 멤버(멤버 변수, 메서드)에 부가적인 의미 부여

접근 제어자 : public, protected, (default), private

그 외 : static, final, abstract, native, transient, synchronized, volatile, strictfp

- 하나의 대상에 여러 제어자를 같이 사용 가능(접근제어자는 하나만 사용 가능.)

```java
public class ModifierTest {
  public static final int WIDTH = 200;  //관례적으로 접근제어자를 제일 앞에.
  
  public static void main(String[] args) {
    System.out.println(WIDTH);
  }
}
```





### static

- 클래스의, 공통적인

```java
class StaticTest {
  static int width = 200  //static변수 : 모든 인스턴스에 공통적으로 사용되는 변수
  static int height = 120;
  
  static {
    // static변수의 복잡한 초기화 수행 ( 클래스 초기화 블럭 )
  }
  
  static int max(int a, int b) { //static 메서드 : 인스턴스를 생성하지 않고도 호출 가능.
    return a > b ? a : b;
  }
}
```





### final

- 마지막의, 변경될 수 없는

```java
final class FinalTest { //클래스 : 변경될 수 없는 클래스, 확장 불가, 다른 클래스의 조상이 될 수 없음.
  final int MAX_SIZE = 10; //변경할 수 없는 상수.
  
  final void getMaxSize() { //변경할 수 없는 메서드, 오버라이딩을 통해 재정의 될 수 없다.
    final int LV = MAX_SIZE;
    return MAX_SIZE;
  }
}
```



### abstract

- 추상의, 미완성의
- 추상 클래스는 인스턴스 생성 불가. ( AbstractTest a = new AbstractTest(); -> ERROR.)
- 추상 클래스를 상속받아 완전한 클래스를 만든 후에 객체 생성 가능.

```java
abstract class AbstractTest { //클래스 : 클래스 내에 추상메서드가 선언되어 있음을 의미.
  abstract void move();  //메서드 : 구현부가 없는 메서드를 추상 메서드라고 함.
}
```





### 접근 제어자(Access Modifier)

4개 중에 하나만 사용 가능

public > protected > (default) > private

class 앞에는 public, (default) 두가지 종류만 붙일 수 있고, 멤버에는 네가지 종류 다 가능함.

- private : 같은 클래스 내에서만 접근이 가능함
- (default) : 같은 패키지 내에서만 접근이 가능함.
- protected : 같은 패키지 내에서, 그리고 다른 패키지의 자손클래스에서 접근이 가능함.
- public : 접근 제한이 전혀 없음

|  제어자   | 같은 클래스 | 같은 패키지 | 자손 클래스 | 전체 |
| :-------: | :---------: | :---------: | :---------: | :--: |
|  public   |      O      |      O      |      O      |  O   |
| protected |      O      |      O      |      O      |      |
| (default) |      O      |      O      |             |      |
|  private  |      O      |             |             |      |



```java
package pkg1;

public class PubParent {  //소스파일 하나에 하나의 public class 가능.
  private int prv;
  int dft;
  protected int prt;
  public int pub;
  
  public void printMembers() {
    System.out.println(prv); //OK.
    System.out.println(dft); //OK.
    System.out.println(prt); //OK.
    System.out.println(pub); //OK.
  }
}

class ModifierTest1 {
  public static void main(String[] args) {
    
    PubParent pp = new PubParent();
    
    //System.out.println(pp.prv);  //ERROR. -> 다른 클래스
    System.out.println(pp.dft);  //OK.  
    System.out.println(pp.prt);  //OK.
    System.out.println(pp.pub);  //OK.
  }
} 
```

```java
package pkg2;

import pkg1.PubParent;

class ChildClass extends PubParent {
  public void PrintMembers() {
    //System.out.println(prv);  //ERROR
    //System.out.println(dft);  //ERROR -> 다른 패키지의 자손 클래스
    System.out.println(prt);    //OK.
    System.out.println(pub);    //OK.
  }
}

public class ModifierTest2 {
  public static void main(String[] args) {
    PubParent pp = new PubParent();
    //System.out.println(pp.prv); //ERROR
    //System.out.println(pp.dft); //ERROR
    //System.out.println(pp.prt); //ERROR  -> 다른 패키지
    System.out.println(pp.pub);   //OK.
  }
}
```





### 캡슐화와 접근 제어자

접근제어자를 사용하는 이유 -> 외부로부터 데이터를 보호하기 위해서.

외부에는 불필요한, 내부적으로만 사용되는, 부분을 감추기 위하여.

```java
class Time {
  //시간에는 범위 제한이 있음.
  private int hour;
  private int minute;
  private int second;
  
  //public인 메서드를 통하여 같은 클래스의 private변수에 접근.
  public void setHour(int hour) {
    if (isNotValidHour(hour)) return; //범위가 넘어가면 메서드 바로 종료(hour값이 바뀌지 않음.)
    this.hour = hour;
  }
  
  //매개변수로 넘겨진 hour가 유효한지 확인.(내부에서만 사용 -> private)
  private boolean isNotValidHour(int hour) {
    return hour < 0 || hour > 23;
  }
  
  public int getHour() { return hour; }
  
}

public class TimeTest {
  public static void main(String[] args) {
    Time t = new Time();
    
    t.setHour(21); //범위 내의 값으로 변경
    System.out.println(t.getHour());
    
    t.setHour(100); //범위 밖의 값.
    System.out.println(t.getHour()); //값이 변경되지 않음.
  }
}
```

