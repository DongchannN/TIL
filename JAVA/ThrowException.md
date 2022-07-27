### 예외 발생시키기

프로그래머는 throw를 사용하여 고의로 예외를 발생시킬 수 있다.

```java
class Main {
  public static void main(String[] args) {
    try {
      Exception e = new Exception("고의로 발생시킴.");
      throw e;
      //한줄로 줄이면 아래와 같다.
      throw new Exception("고의로 발생시켰음.");
    } catch (Exception e) {
      System.out.println("Error Message : " + e.getMessage());
      e.printStackTrace();
    }
    System.out.println("프로그램 정상 종료.");
  }
}
```



```java
class Main {
  public static void main(String[] args) {
    throw new Exception(); //Exception을 고의로 발생시킴.
  }
}
```

위와같은 코드는 에러가 발생하며 컴파일이 완료되지 않는다. 예외처리가 되어야 할 부분에 예외처리가 되어있지 않기 때문이다. Exception클래스들이(RuntimeException클래스들 제외.) 발생할 가능성이 있는 문장들에 대해 예외처리를 해주지 않으면 컴파일조차 되지 않는다.

```java
class Main {
  public static void main(String[] args) {
    throw new RuntimeException(); //RuntimeException을 고의로 발생시킴.
  }
}
```

위와 같은 코드는 예외처리를 하지 않았음에도 성공적으로 컴파일이 되지만 RuntimeException으로 인해 비정상 적으로 종료가 된다.

RuntimeException클래스와 그 자손클래스들은 프로그래머의 실수로 발생하는 것이기 때문에 예외처리를 강제하지 않기 때문이다. 

만약 RuntimeException클래스 들에 속하는 예외가 발생할 가능성이 있는 코드에 예외처리를 필수로 해야한다면 참조변수와 배열이 사용되는 모든 곳에 예외처리를 해주어야 할 것이다.

추가적으로 컴파일러가 예외처리를 확인하지 않는 RuntimeException클래스들은 'unchecked 예외'라고 부르고 예외처리를 확인하는 Exception클래스들은 'checked 예외'라고 부른다.



##### 메서드에 예외 선언하기

try-catch문을 사용하는 것 외에  메서드의 선언부에 throws를 사용하여 메서드에 예외를 선언할 수 있다. throws를 사용해 메서드 내에서 발생할 수 있는 예외를 메서드의 선언부에 적어주면 된다.

```java
void method() throws Exception1, Exception2, Exception3 {
		
}
void method2() throws Exception {
  //예외의 최고조상인 'Exception'을 선언하면 모든 종류의 예외가 발생할 가능성이 있다는 뜻이다.
}
```

위와 같이 메서드의 선언부에 예외를 선언함으로 메서드를 사용하려는 사람이 이 메서드를 사용할 때 어떤 예외들이 처리되어야하는지 알 수 있다.

아래와 같이 JAVA의 api에서 wait메서드를 보면 wait메서드를 사용 할 시 InterruptedException을 처리해줘야 한다는 것을 알 수 있다. 또한 wait메서드를 사용 할 시IllegalMonitorStateException또한 발생할 수 있는데 이는 RuntimeException클래스의 자손이므로 예외처리를 안해도 괜찮아 선언부에는 적지 않은 것이다.

![스크린샷 2022-07-27 오후 1.10.56](/Users/dongchan/Library/Application Support/typora-user-images/스크린샷 2022-07-27 오후 1.10.56.png)



```java
class Main {
  public static void main(String[] args) throws Exception{
    method1();
  }
  
  static void method1() throws Exception{
    method2();
  }
  static void method2() throws Exception{
    throw new Exception(); //예외 발생시킴.
  }
}

//결과
java.lang.Exception
  at Main.method2(...)
  at Main.method1(...)
  at Main.main(...)
```

위의 결과를 보면 3개의 메서드가 호출스택에 있으며 main이 method1을 호출했고 method1은 method2를 호출해 method2에서 예외가 발생 한 것을 알 수 있다.

위의 코드는 method2에서 예외가 발생하였고 예외처리를 하지 않았음으로 method2를 종료하고 method1으로 예외를 넘겨준다. method1에서도 예외처리가 되지 않아 main으로 넘기고 main마찬가지로 예외처리가 없어 비정상적인 종료로 프로그램이 끝난다. 그러므로 어느 한 곳에서는 예외를 처리해주어야한다.



##### finally 블럭

finally 블럭은 try-catch문과 함께 예외의 발생여부에 상관없이 실행되어야할 코드를 포함시킬 수 있다. try-catch 문의 끝에 선택적으로 덧붙일 수 있다.

```java
class Main {
  public static void main(String[] args) {
    method1();
    System.out.println("method1종료 후 main복귀.");
  }
  
  static void method1() {
    try {
      System.out.println("method1 호출.");
      return;
    } catch(Exception e) {
      e.printStackTrace();
    } finally {
      System.out.println("finally블럭 실행.");
    }
  }
}

//결과
method1이 호출.
finally블럭 실행.
method1종료 후 main복귀.
```

try블럭에서 return문이 실행된다 하더라도 finally문이 실행된 후에 메서드가 종료되는 것을 볼 수 있다. 마찬가지로 catch블럭에서 return문이 실행되어도 finally문은 실행된다.

##### 자동 자원 반환(try-with-resources). JDK1.7부터

```java
try {
  fis = new FileInputStream("score.dat");
  dis = new DataInputStream(fis);
} catch(IOException ie) {
  ie.printStackTrace();
} finally {
  dis.close(); //dis가 언제나 닫히도록 finally블럭에 넣음.
}
```

위의 코드는 DataInputStream이 언제나 닫히도록 finally블럭 안에 넣었다. 하지만 close가 예외를 발생시킬 수 있어서 아래 코드와 같이 finally안에 다시 try-catch문을 작성해야한다.

```java
try {
  fis = new FileInputStream("score.dat");
  dis = new DataInputStream(fis);
} catch(IOException ie) {
  ie.printStackTrace();
} finally {
  try {
    if(dis != null) dis.close();
  } catch(IOException ie) {
    ie.printStackTrace();
  }
}
```

이렇게 finally 안에 try-catch문을 넣으면 코드가 복잡해져 보기에 좋지 않다. 또한 try블럭과 finally블럭에서 모두 예외가 발생하면 try블럭의 예외는 무시가 된다. 그래서 아래와 같이 try-with-resources문이 등장하였다

```java
try (FileInputStream fis = new FileInputStream("score.dat");
     DataInputStream dis = new DataInputStream(fis);) {
  while(true) {
    score = dis.readInt();
    System.out.println(score);
    sum += score;
  }
} catch (EOFException e) {
  System.out.println("점수의 총합 : " + sum);
} catch (IOException ie) {
  ie.printStackTrace();
}
```



