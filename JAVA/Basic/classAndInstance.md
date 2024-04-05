### 객체지향 언어(object-oriented programming)

4가지 특징

- 캡슐화
- 상속
- 추상화
- 다형성

클래스

- 객체를 정의해 놓은 것, 클래스는 객체를 생성하는데 사용
- 객체는 실제로 존재하는 것(사물, 개념), 객체 = 속성(변수) + 기능(메소드)

```java
//Hello2.java   //소스파일이름 = public class 이름
public class Hello2{}
			 class Hello3{}

//Hello2.java or Hello3.java or Hello4.java
//public class가 없으면 2클래스 모두 소스파일 이름이 될 수 있음
//main함수를 가지고 있는 클래스 이름은 소스파일이름과 같아야 정상적으로 실행 가능
class Hello2{}
class Hello3{}
class Hello4{}
//기본적으로 하나의 소스파일에는 하나의 클래스만 작성 하는 것이 바람직
```

객체, 인스턴스 : 객체는 모든 인스턴스

- 인스턴스 : 특정 클래스로부터 생성된 객체.  클래스 --> 인스턴스(객체)

ex) TV는 객체, TV클래스를 이용하여 TV라는 인스턴스를 만들어 냄.

객체의 생성, 사용

```java
Class Tv {
  String color;  //변수
  boolean power;
  int channel;
  void power() { power = !power; }  //매서드
  void channelUp { channel++;}
  void channelDown { channel--;}
}

Tv t;  //Tv클래스 타입의 참조변수 t선언
t= new Tv(); //Tv인스턴스를 생성한 후, 생성된 Tv인스턴스의 주소를 t에 저장

Tv t1 = new Tv(); //위와 같음
Tv t2 = new Tv();
//객체의 사용
t1.channel = 7;
t1.channelDown();
System.out.println("현재 채널은 " + t1.channel + "입니다.");
//현재 채널은 7입니다.
System.out.println("현재 채널은 " + t2.channel + "입니다.");
//현재 채널은 0입니다
```



객체 배열

- 객체 배열 = 참조변수 배열

```java
Tv t1 = new Tv();
Tv t2 = new Tv();
Tv t3 = new Tv();
//객체 배열
Tv[] tvArr = new Tv[3];
tvArr[0] = new Tv();
tvArr[1] = new Tv();
tvArr[2] = new Tv();
//간단히 표현
Tv[] tvArr = {new Tv(), new Tv(), new Tv()};
//객체의 수가 많을 때
Tv[] tvArr = new Tv[50];
for(int i = 0; i < 50; i++) {
  tvArr[i] = new Tv();
}
```



### 클래스

변수 : 하나의 데이터를 저장할 수 있는 공간

배열 : 같은 종류의 여러 데이터를 하나로 저장할 수 있는 공간

구조체 : 서로 관련된 여러 데이터를 하나로 저장할 수 있는 공간

클래스 : 데이터와 함수의 결함 (구조체 + 함수)

```java
int hour1,hour2,hour3;
int minute1,minute2,minute3;
int second1, second2, second3;
//아래와 같이 사용자 정의로 시간이라는 클래스로 묶을 수 있음, 객체지향적 코드
class Time{
  int hour;
  int minute;
  int second;
}
Time t1 = new Time();
Time t2 = new Time();
Time t3 = new Time();
t1.hour =12;
t1.minute = 34;
t1.second = 56;
```

