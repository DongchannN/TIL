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

- 클래스 변수(class variable) : 클래스 영억안에서 클래스가 메모리에 올라갈 때 생성(클래스가 필요할 때) --> 객체생성 필요 없음.(아무때나 사용 가능)

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
  
  static int width; //class variable
  static int height:
}
```

```java
///클래스 이용
Card c = new Card();
c.kind = "HEART"; //iv
c.number = 5;

c.width = 200;   //cv but not commanded
c.height = 300;

Card.width = 200; //cv how usually use. 
Card.height = 300;
```

