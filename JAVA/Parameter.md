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



### 참조형 매개변수(Reference Parameter)

```java
class Data { int x; }

class Eg {
  public static void main(String[] args) {
    Data d = new Data(); // 객체 생성
    d.x= 10; // x = 10
    System.out.println("main_x= "+d.x);
    
    change(d);  //값을 준 것이 아닌 메모리를 줌.-->주소를 받아 읽기와 쓰기 가능.
    System.out.println("main_x= "+d.x);
  }
  
  static void change(Data d) { //참조형 매개변수(Reference parameter).
    d.x = 1000;   
    System.out.println(x);
  }
}
//result
10
1000
1000
```



### 참조형 반환타입

```java
class Data { int x; }

class Eg {
  public static void main(String[] args) {
    Data d = new Data();
    d.x = 10;
    
    Data d2 = copy(d); //copy 메소드의 반환 타입이 참조형이므로 d2도 참조형으로 선언해야 함.
    System.out.println("d.x= "+d.x);
    System.out.println("d2.x= "+d2.x);
    
  }
  //static method : 객체 생성 없이 호출 가능.
  static Data copy(Data d) {   //반환 타입이 참조형 --> return tmp에서 반환 시 객체의 주소를 반환
    Data tmp = new Data();
    
    tmp.x = d.x;
    
    return tmp;        
  }
}
//result
d.x= 10
d2.x= 10

```

