### 예외 처리

- 컴파일 에러 : 컴파일 시에 발생하는 에러
- 런타임 에러 : 실행 시에 발생되는 에러
- 논리적 에러 : 실행은 되지만 의도한 것과 다르게 작동 하는 것.

자바에서는 실행 시 발생할 수 있는 프로그램 오류를 "에러(ERROR)"와 "예외(EXCEPTION)" 두가지로 구분하였다.

에러는 메모리 부족이나 스택오버플로우와 같이 일단 발생하면 복구할 수 없는 오류이고 예외는 발생하더라도 수습이 가능한 오류이다.

![hierarchy of exception handling](https://static.javatpoint.com/core/images/hierarchy-of-exception-handling.png)

자바는 위와 같이 실행 시 발생할 수 있는 오류를 클래스로 정의하였다.

예외 클래스들을 아래와 같이 두 그룹으로 나누어보겠다

- 1. Exception클래스와 그 자손들(RuntimeException과 자손 제외)
- 2. RuntimeException클래스와 그 자손들

2번과 같은 경우는 주로 프로그래멍의 실수에 의해 잘생될 수 있는 예외들로 자바의 프로그래밍 요소들과 관계가 깊다.

1번과 같은 경우는 주로 외부의 영향으로 발생할 수 있는 것들로서 사용자들의 동ㅇ작에 의해서 발생하는 경우가 많다.(ex. FileNotFoundException, ClassNotFoundException, DataFormatException 등)



##### 예외 처리하기 (try-catch문)

예외의 발생에도 비정상적인 종료를 막고 정상적인 실행상태를 유지하기 위해 예외에 대비한 코드를 작성할 필요가 있음.

```java
//try-catch문 사용방법
try {
  //예외 발생 가능성이 있는 문장을 넣음.
} catch(Exception1 e1) { //괄호 생략 불가능(문장이 하나일지라도.)
  //Exception1이 발생 시 이를 처리하기 위한 문장을 넣음.
} catch(Exception2 e2) {
  //Exception2가 발생 시 이를 처리하기 위한 문장을 넣음.
}

```

위와 같이 하나의 try블럭 다음에 여러 종류의 예러를 처리하기 위한 하나 이상의 catch블럭이 올 수 있다. 예외의 종류와 일치하는 catch블럭이 없으면 예외는 처리되지 않는다.

또한 하나의 메서드 내에 여러개의 try-catch문이 사용 될 수 있고, try블럭 또는 catch블럭에 또 다른 try-catch문이 포함될 수 있다.

```java
class Main {
  public static void main(String[] args) {
    try
  }
}
```



```java
class Main {
  public static void main(String[] args) {
    int number = 100;
    int result = 0;
    
    for(int i = 0; i < 10; i++) {
      result = number / (int) (Math.random() *10); // 0~9까지 랜덤추출
      System.out.println(result)
    }
  }
}
```

위와 같은 코드는 random메서드로 0부터 9까지의 임의의 정수로 number를 나누는데 0이 뽑히게 되면 예외가 발생하여 프로그램이 비정상적으로 종료가 된다. 이떄 우리는 0이 나왔을 때를 대비해 예외처리를 할 필요가 있다.

```java
class Main {
  public static void main(String[] args) {
    int number = 100;
    int result = 0;
    
    for(int i = 0; i < 10; i++) {
      try {
        result = number / (int) (Math.random() *10); // 0~9까지 랜덤추출
      System.out.println(result)
      } catch(ArithmeticException e) {
        //0으로 나누면(ArithmeticException이 발생하면) 실행되는 구문.
        System.out.println("Infinity");
      }
    }
  }
}
```

위와 같은 경우에는 0이 뽑혀도 비정상적으로 프로그램이 종료되지 않고 catch구문을 수행한 뒤 for문의 10번의 수행을 마무리 짓고 정상적으로 프로그램이 종료될 것이다.



##### 예외의 발생과 catch블럭

Try-catch문에서 예외가 발생한 경우에는 발생한 예외에 해당하는 클래스의 인스턴스가 만들어진다. 발생한 예외한 일치하는 catch블럭이 있는지 바로 확인한다. 첫번째 catch블럭부터 내려가며 ()안에 선언된 참조변수의 종류와 생성된 예외클래스의 인스턴스에 instanceof연산자를 이용하며 true가 나올 때까지 계속 검사를 실시한다. 일치하는 블럭을 찾게되면 그 catch블럭에서 문장을 수행하고 전체 try-catch문을 빠져나가 그 다음 문장을 수행한다. true가 하나도 없다면 예외는 처리되지 않는다.

```java
class Main {
  public static void main(String[] args) {
    System.out.println(1);
    System.out.println(2);
    try {
      System.out.println(3);
      System.out.println(0/0); //이 문장에서 바로 catch구문으로 간다.
      System.out.println(4);   //예외가 발생해 이 문장을 실행되지 않는다.
    } catch(ArithmeticException ae) {
      System.out.println("예외발생");
    }
    System.out.println(5);
  }
}
```

위와 같이 예외가 발생하면 예외가 발생한 이후에 있는 try구문은 수행하지 않으므로 try블럭에 포함시킬 코드의 범위를 잘 선택해야한다.

모든 예외 클래스는 Exception클래스의 자손이므로 catch블럭의 괄호에 Exception클래스 타입의 참조변수를 선언해놓으면 어떤 종류의 예외가 나와도 catch블럭에 의하여 처리가 된다.



###### printStackTrace()와 getMessage()

- printStackTrace() : 예외발생 당시의 호출스택에 있었던 메서드의 정보와 예외 메시지를 화면에 출력한다.
- getMessage() : 발생한 예외클래스의 인스턴스에 저장된 메시지를 얻을 수 있다.

```java
class Main {
  public static void main(String[] args) {
    System.out.println(1);
    System.out.println(2);
    try {
      System.out.println(3);
      System.out.println(0/0); //이 문장에서 바로 catch구문으로 간다.
      System.out.println(4);   //예외가 발생해 이 문장을 실행되지 않는다.
    } catch(ArithmeticException ae) {
      ae.printStackTrace();
      //예외 발생 당시의 정보 출력 (아래 주석, 몇번째 줄인지 등)
      //java.lang.ArithmeticException : / by zero ....
      System.out.println("예외메시지 : " + ae.getMessage());
      //예외메시지 : / by zero.
    }
    System.out.println(5);
  }
}
```

위의 결과는 프로그램이 비정상적으로 종료되었을 때의 메시지가 뜨지만 프로그램을 정상적으로 종료되었다.

###### 멀티catch블럭

'|' 기호를 이용하여 하나의 catch 블럭으로 합칠 수 있다. 하지만 기호로 연결된 예외클래스가 조상과 자손관계에 있다면 컴파일 에러가 발생한다.(그냥 조상 클래스의 예외만 써주는 것과 똑같기 때문이다.)

