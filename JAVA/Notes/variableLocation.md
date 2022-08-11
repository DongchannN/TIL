### 선언 위치에 따른 변수의 종류

```java
class Variables
{
  int iv;    //instance variable
  static int cv;  //class variable(static variable, public variable)
  
  void method()
  {
    int lv = 0;  //local variable
  }
}
```

- 클래스 변수(class variable) : 클래스 영억안에서 클래스가 메모리에 올라갈 때 생성(클래스가 필요할 때) --> 객체생성 필요 없음.(아무때나 사용 가능). 위와 같은 클래스에서는 Variables.cv와 같이 사용.

​	CPU  <--->  메모리(RAM)  <---> 하드

- 인스턴스 변수(instance variable) : 클래스 영역안에서 인스턴스가 생성되었을 때 --> 객체생성 필요

​	*객체는 인스턴스 변수들이 모인 것* 

- 지역변수(local variable) : 메서드 안에서 유효, 메서드 종료시 자동 제거



### 클래스 변수와 인스턴스 변수의 차이

EX) 트럼프 카드 : 카드 객체들의 모임 

​							숫자, 무늬 : 개별적인 속성 (instance variable)

​							폭, 높이 : 공통적인 속성(class variable)



```java
//클래스 선언
class Card{
  String kind; //instance variable
  int number;
  
  static int width = 100; //class variable
  static int height = 150;
}
```

```java
///클래스 이용
Card c1 = new Card();
Card c2 = new Card();
c1.kind = "HEART"; //iv
c1.number = 5;

c2.kind = "SPADE";
c2.number = 3;

"""  //width and height is both class variable so c1 and c2 both width and height changed
c1.width = 200;   //cv but not recommanded(인스턴스 변수로 오해받을 수 있음.)
c1.height = 300;
"""

//recommanded
Card.width = 200; //cv how usually use. 
Card.height = 300;
```

