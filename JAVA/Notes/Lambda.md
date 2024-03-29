### 람다식

람다식은 간단히 말해서 메서드를 하나의 식으로 표현한 것이다. 메서드를 람다식으로 표현하면 이름과 반환값이 필요없어지게 된다.

```java
//람다식 표현 방법
(매개변수 선언) -> {
  구현부
}
```

```java
int max(int a, int b) {
  return a > b ? a : b;
}
//람다 1.
(int a, int b) -> {return a > b ? a: b;}
//람다 2.
(int a, int b) -> a > b ? a: b
//람다 3.
(a, b) -> a > b ? a: b
//람다 4.
a -> a * a
//람다 5.
(String name, int i) ->
  System.out.printlnt(name + "=" + i)
```

기본적으로 위의 코드에서 메서드의 이름을 지워 람다식 1과 같이 표현을 할 수 있다.

- 람다식 2에서처럼 return문 대신 식으로만 표할 할 수 있다. 식의 마지막에는 ;를 붙이지 않는다.
- 람다식 3에서와 같이 타입을 생략할 수 있다. (추론이 가능하기 때문이다.) 둘 중 하나만 생략하는 경우는 불가능하다.
- 람다식 4에서와 같이 매개변수가 하나인 경우에는 괄호를 생략할 수 있다. 단 매개변수 타입이 있으면 생략이 불가능하다.
- 람다식 5에서와 같이 문장이 하나인 경우 괄호{}를 생략할 수 있다. 이때 세미콜론;은 붙이지 않는다.

##### 함수형 인터페이스

모든 메서드는 클래스 내에 포함되어있어야한다. 람다식이 메서드와 동등한 것처럼 보이지만 삭실 람다식은 익명 클래스의 객체와 동등하다.

```java
interface MyFunction {
  public abstract int max(int a, int b);
}
```

위를 보면 max() 메서드가 정의된 MyFunction인터페이스가 정의되어있다. 

이 인터페이스를 구현한 익명 클래스의 객체는 아래와 같이 생성할 수 있다.

```java
//객체 생성 및 추상메서드 구현.
MyFunction f = new MyFunction() {
  public int max(int a, int b) {
    return a > b ? a : b;
  }
};
//객체의 메서드 호출.
int big = f.max(3, 5);
```

이제 위의 코드를 람다식으로 바꾸어 보면 아래와 같다.

```java
MyFunction f = (int a, int b) -> a > b ? a : b;
int big = f.max(3,5);
```

이처럼 MyFunction인터페이스를 구현한 익명개체를 람다식으로 대체할 수 있는 이유는 람다는 실제도 익명객쳉이고, MyFunction인터페이스를 구현한 익명객체의 메서드 max()와 람다식의 매개변수의 타입과 개수 그리고 반환값이 일치하기 때문이다.

이렇게 하나의 메서드가 선언된 인터페이스를 정의해 람다식을 다루는 것은 자바의 규칙을 어기지 않으며 자연스럽다. 그래서 인터페이스를 통해 람다식을 다루기로 결정되었으며 람다식을 다루기 위한 인터페이스를 '함수형 인터페이스'라고 부르기로했다.

```java
//아래와 같은 어노테이션을 붙이면
//컴파일러가 함수형 인터페이스를 올바르게 정의해주었는지 확인한다.
@FunctionalInterface
interface MyFuntion {
  
  //함수형 인터페이스에는 오직 하나의 추상메서드만 정의되어있어야한다.
  public abstract int max(int a, int b);
}
```



```java
@FunctionalInterface
interface MyFunction {
    void run();
}
```

위 코드와 같이 함수형 인터페이스가 정의 되어있을 때 아래의 코드와 같이 매개변수가 MyFunction타입이라면 이 메서드를 호출 할 때 람다식을 참조하는 참조변수를 매개변수로 지정해야한다는 것이다.

```java
static void execute(MyFunction f) {
        f.run();
    }

//람다식을 이용해 인터페이스를 완성한 참조변수 생성
MyFunction f = () -> System.out.println("run()"); 
//참조변수를 이용해 메서드 호출
execute(f);
```

위의 두줄로 이루어진 코드를 아래와 같이 참조변수 없이 직접 람다식을 매개변수로 지정할 수도 있다.

```java
execute( () -> System.out.println("run()") );
```



```java
@FunctionalInterface
interface MyFunction {
    void run();
}

public class JavaPlayGround {
    static void execute(MyFunction f) {
        f.run();
    }

    static MyFunction getMyFunction() {
        //MyFunction f = () -> System.out.println("f3.run()");
        //return f;
        //위의 두줄을 아래의 한줄로 줄일 수 있다.
      	return () -> Syste,.out.println("f3.run()");
    }
    public static void main(String[] args) {

        MyFunction f1 = () -> System.out.println("f1.run()");

        MyFunction f2 = new MyFunction() {
            public void run() {
                System.out.println("f2.run()");
            }
        };

        MyFunction f3 = getMyFunction();

        f1.run();
        f2.run();
        f3.run();

        execute(f1);
        execute( ()  -> System.out.println("run()"));
    }

}
```

