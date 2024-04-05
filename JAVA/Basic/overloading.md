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



### 가변인자

기존에는 메서드의 매개변수가 고정적이였으나 동적으로 지정할 수 있음.

가변인자는 매개변수 중에서 제일 마지막에 선언해야함.

가변인자를 선언한 메서드를 오버로딩하면 메서드를 호출했을 때 구별되지 못하는 경우가 있음 -> 가변인자를 사용한 메서드는 가능한 오버로딩을 하지 않는 것이 좋음.

```java
//매개변수의 갯수를 다르게 해서 오버로딩
String concatenate(String s1);
String concatenate(String s1, String s2);
String concatenate(String s1, String s2, String s3);
String concatenate(String s1, String s2, String s3, String s4);

//가변인자를 사용해 한줄로 해결.
String concatenate(String... str);  //변수타입... 변수명

//가변인자는 인자가 없어도 되고 배열도 인자가 될 수 있음.
System.out.println(concatenate()); //인자가 없음
System.out.println(concatenate("a"));
System.out.println(concatenate("a", "b"));
System.out.println(concatenate(new String[] {"a", "b"})); //배열
System.out.println(concatenate({"A", "B", "C"})) //불가능.

//인자를 배열로 하면 인자생략이 불가능 -> 길이가 0인 배열, null을 인자로 설정해야함.
```

