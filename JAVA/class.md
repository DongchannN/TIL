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

