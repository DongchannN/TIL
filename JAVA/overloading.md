### Overloading

한 클래스 안에 같은 이름의 메서드를 여러 개 정의. EX) println

매개변수는 다르지만 같은 의미의 기능 수행.

- 조건

  1.메서드 이름이 같아야함.

  2.매개변수의 갯수 or 타입이 달라야함.

  3.반환 타입은 영향이 없음.

  조건 성립 X --> ERROR

```java
//Overloading Example
long add(int a, long b) { return a+b; }
long add(long a, int b) { return a+b; }
//add(3.3) --> 둘 중 어느 것을 사용 할지 모호 (ERROR)
```

