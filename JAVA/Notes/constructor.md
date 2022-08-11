### 생성자(Constructor)

생성자는 인스턴스가 생성될 때마다 호출되는 인스턴스 초기화 메서드.

인스턴스 변수의 초기화 작업, 인스턴스 생성 시에 실행되어야 할 작업을 위해서 주로 사용.

생성자도 오버로딩이 가능해 하나의 클래스에 여러개의 생성자가 존재할 수 있음.

```java
Card c = new Card();
//1. 연산자 new에 의해서 메모리에 Card클래스의 인스턴스가 생성됨.
//2. 생성자 Card()가 호출되어 수행됨.
//3. 연산자 new의 결과로, 생성된 Card인스턴스의 주소가 반환되어 참조변수 c에 저장.
```



```java
Time t = new Time();
t.hour = 12;      //iv 초기화
t.minute = 24;
t.second = 42;

Time t = new Time(12, 24, 42);  //생성자 호출 by 생성자 추가
```

- 생성자의 이름은 클래스 이름과 같아야함.
- 리턴값이 없음(void를 붙이지 않음 항상 반환값이 없기 때문에)
- 모든 클래스는 반드시 생성자를 가져야 함.





### 기본 생성자

- 매개변수가 없는 생성자
- 생성자가 하나도 없을 때만 컴파일러가 자동으로 추가.



```java
class Data_1 {
  int value;
  
  Data_2() {}      //기본 생성자가 컴파일 시 자동적으로 추가 (생성자가 없기 때문)
}

class Data_2 {
  int value;
  
  Data_2() {}     // 기본 생성자 생략 불가능 밑에 매개변수가 있는 생성자가 있음.
  
  Data_2(int value) {
    value = x;
  }
}

class Ex {
  public static void main(String[] args) {
    Data_1 d1 = new Data();
    Data_2 d2 = new Data();  //Data_2() {} 생략 시 에러.
  }
}
```



### 매개변수가 있는 생성자

```java
class Car {
  String color;
  String gearType;
  int door;
  
  Car() {}  //기본 생성자
  Car(String c, String g, int d) {  // 매개변수가 있는 생성자
    color = c;
    gearType = g;
    door = d;
  }
}
```

```java
Class Ex {
  public static void main(String[] args) {
    //매개변수가 있는 생성자를 이용해 간단히 초기화 가능.
    Car c = new Car("white", "auto", 4); 
    
    /* 생략가능
    Car c = new Car();
    c.color = "white";
    c.gearType = "auto";
    c.door = 4;
    */
  }
}
```





### 생성자 this(), 참조변수 this

생성자에서 다른 생성자 호출할 때 사용

```java
class Car {
  String color;
  String gearType;
  int door;
  
  Car() {  //기본 생성자, 매개변수가 없기에 디폴트값을 초기화 해야함(밑에 있는 생성자를 통하여 초기화 했음.)
    this("white", "auto", 4)  //this 는 첫 줄에 사용해야만 함.
  }
  
  Car(String color) {
    this(color, "auto", 4);  //Car(color, "auto", 4); -> 같은 클래스 안 생성자끼리는 this 사용
  }
  
  Car(String color, String gearType, int door) {
    this.color = color;
    this.gearType = gearType;
    this.door = door;
  }
}
```



### 참조변수  this

생성자 this()와 무관.

객체 자신을 가리키는 참조변수.

인스턴스 메서드 (생성자 포함) 에서 사용가능

지역변수와 인스턴스 변수 구분할 때 사용. 같은 이름의 매개변수가 있을 때 구분짓기 위해 사용.

```java
//Car(String c, String g, int d) {
//  color = c;
//  gearType = g;
//  door = d;
///}

Car(String color, String gearType, int door) {
  //this.color는 지역변수 color 인스턴스 변수
  this.color = color;
  this.gearType = gearType;
  this.door = door;
}
```



### 생성자를 이용한 인스턴스의 복사

```java
class Car {
  String color;
  String gearType;
  int door;
  
  Car() {
    this("white", "auto", 4);
  }
  
  Car(Car c) {
    /*
    color = c.color;
    geraType = c.gearType;
    door = c.door;
    */
    this(c.color, c.gearType, c.door);
  }
  
  Car(String color, String gearType, int door) {
    this.color = color;
    this.gearType = gearType;
    this.door = door;
  }
}

class CarTest {
  public static void main(String[] args) {
    Car c1 = new Car();
    Car c2 = new Car(c1); //c1의 복사본 c2생성.
    
    c1.door = 2; //c1의 인스턴스 변수 door의 값을 2로 변경.
    
    System.out.println(c2.door); //4. 독립적인 주소를 가지므로 영향 X.
  }
}
```



### 변수의 초기화

지역변수 -> 사용 전 수동 초기화 해야함.(에러 방지)

인스턴스 변수, 클래스 변수 -> 자동초기화 됨.

```java
class Init {
  int x;
  int y = x;
  void method() {
    int i;
    int j = i; //에러. 지역변수는 자동초기화가 되지 않음.
  }
}
```



1. 명시적 초기화   //간단 초기화

   변수를 선언과 동시에 초기화 하는 것

   ```java
   class Car {
     int door = 4; //기본형 변수의 초기화
     Engine e = new Engine(); //참조형 변수의 초기화
   }
   ```

   

2. 초기화 블럭  //복잡초기화 

   인스턴스 초기화 블럭 : {}

   ```java
   class Car {
     String color;
     String gearType;
     int count;
     int serialNo;
     Car() {
       count++;
       serialNo = count;
       color = "white";
       gearType = "auto";
     }
     
     Car(String color, String gearType) {
       count++;
       serialNo = count;
       this.color = color;
       this.gearType = gearType;
     }
   }
   ```

   ```java
   class Car {
     String color;
     String gearType;
     int count;
     int serialNo;
     { //인스턴스 초기화 블럭, 코드의 중복을 제거.
       count++;
       serialNo = count;
     }
     Car() {
       color = "white";
       gearType = "auto";
     }
     
     Car(String color, String gearType) {
       this.color = color;
       this.gearType = gearType;
     }
   }
   ```

   

   클래스 초기화 블럭 : static {}

   클래스가 메모리에 처음 로딩될 때 한번만 수행.

   ```java
   class ClassBlock {
     //클래스 초기화 블럭
     static {
       System.out.println("static {}");
     }
     //인스턴스 초기화 블럭
     {
       System.out.println("{}");
     }
     
     public ClassBlock() {
       System.out.println("생성자");
     }
     
     public static void main(String[] args) {
       System.out.println("ClassBlock b1 = new ClassBlock();");
       ClassBlock b1 = new ClassBlock();
       System.out.println("ClassBlock b2 = new ClassBlock();");
       ClassBlock b2 = new ClassBlock();
     }
   }
   
   /*
   출력
   static {}                            //클래스가 메모리에 로딩될 때 가장 먼저 한번 수행.
   ClassBlock b1 = new ClassBlock();   //main메서드 수행
   {}
   생성자
   ClassBlock b2 = new ClassBlock();
   생성자
   */
   ```

   

3. 생성자 (iv 초기화)  //복잡초기화

   ```java
   Car(String color, String gearType, int door) {
     this.color = color;
     this.gearType = gearType;
     this.door = door;
   }
   ```

   

```java
class StaticBlockTest {
  static int[] arr = new int[10]; //명시적 초기화 (간단초기화)
  
  static { //클래스 초기화 블럭 - 난수로 채우기.
    for(int i =0; i<arr.length; i++) {
      arr[i] = (int) (Math.random()*10)+1;
    }
}
```



### 맴버변수의 초기화

클래스 변수 초기화 시점 : 클래스가 처음으로 로딩 될 때 단 한번

인스턴스 변수 초기화 시점 : 인스턴스가 생성될 때 마다

순서

클래스 변수 : 기본값 -> 명시적 초기화 -> 클래스 초기화 블럭 

인스턴스 변수 : 기본값 -> 명시적 초기화 -> 인스턴스 초기화 블럭 -> 생성자



```java
class Product {
  static int count = 0; //클래스 변수 처음 한번 메모리에 가장먼저 올라감.
  int serialNo;
  
  { //인스턴스가 생성 될 때마다 실행
    ++count;
    serialNo = count;
  }
}

class ProductTest {
  public static void main(String[] args) {
    Product p1 = new Product();
    Product p2 = new Product();
    Product p3 = new Product();
    
    System.out.println(p1.serialNo); //1
    System.out.println(p2.serialNo); //2
    System.out.println(p3.serialNo); //3
    System.out.println(Product.count); //3
  }
}
```



