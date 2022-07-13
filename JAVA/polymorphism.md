### 다형성(polymorphism)

- 여러가지 형태를 가질 수 있는 능력
- 조상 타입 참조 변수로 자손 타입 객체를 다루는 것

```java
class Tv {
  boolean power;
  int channel;
  
  void power() { power =! power; }
  void channelUp() { ++channel; }
  void channelDown() { --channel; }
  
}

class SmartTv extends Tv {
  String text;
  void caption() {}
}

class Main {
  public static void main(String[] args) {
    
    //참조변수 타입과 인스턴스의 타입이 일치하는 경우
    Tv tv = new Tv();
    SmartTv stv = new SmartTv();
    
    //조상타입 참조변수로 자손 타입 인스턴스 참조.
    Tv t = new SmartTv(); //Tv의 다섯 멤버만 사용 가능, SmartTv의 두 멤버 사용 불가능.
    
    //자손 타입의 참조변수로 조상 타입의 객체를 가리킬 수 없음.
    //SmartTv smtv = new Tv();  -> ERROR.
  }
}
```





### 참조변수의 형변환

- 사용할 수 있는 멤버의 갯수를 조절하는 것.
- 조상, 자손 관계의 참조변수는 서로 형변환 가능.

```java
class Car {
  String color;
  int door;
  
  void drive() {
    System.out.println("Drive!!");
  }
  void stop() {
    System.out.println("Stop!!!");
  }
}

class FireEngine extends Car {
  void water() {
    System.out.println("Water!");
  }
}

class Ambulance extends Car {
  void siren() {
    System.out.println("Siren!!");
  }
}

class Main {
  public static void main(String[] args) {
    FireEngine f = new FireEngine(); //멤버 총 5개
    
    //f, c, f2 모두 같은 주소를 가리킴.
    //조상인 Car타입으로 형변환(생략가능.) 
    Car c = (Car) f; //멤버 4개만 다룰 수 있음.
    
    f.water();
    //c.water(); -> ERROR.
    
    //자손인 FireEngine타입으로 형변환(생략불가).
    FireEngine f2 = (FireEngine) c; //멤버 5개 다룰 수 있음.
    
    //Ambulance a = (Ambulance)f; //ERROR. FireEngine 과 Ambulance는 상속관계가 아님.
  }
}
```

