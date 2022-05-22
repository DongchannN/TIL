### 기본형 매개변수(Primitive Parameter)

- 기본형 매개변수 : read only.
- 참조형 매개변수 : can read and write.

```java
class Data { int x; }

class Eg {
  public static void main(String[] args) {
    Data d = new Data(); // 객체 생성
    d.x= 10; // x = 10
    System.out.println("main_x= "+d.x);
    
    change(d.x);
    System.out.println("main_x= "+d.x);
  }
  
  static void change(int x) { //기본형 매개변수(Primitive parameter).
    x = 1000;    //지역변수 x(main 함수의 x와 이름은 같지만 저장위치가 다름.)
    System.out.println(x);
  }
}

//result
main_x= 10
1000
main_x= 10
```



### 참조형 매개변수

```java
class Data { int x; }

class Eg {
  public static void main(String[] args) {
    Data d = new Data(); // 객체 생성
    d.x= 10; // x = 10
    System.out.println("main_x= "+d.x);
    
    change(d);
    System.out.println("main_x= "+d.x);
  }
  
  static void change(Data d) { //참조형 매개변수(Primitive parameter).
    d.x = 1000;   
    System.out.println(x);
  }
}
```

