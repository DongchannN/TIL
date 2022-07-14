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
- 실제 인스턴스가 무엇인지가 중요.

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



```java
Car c = new Car();
FireEngine fe = (FireEngine) c;
//컴파일 에러 나지 않음, 실행시 ClassCastException 에러.
fe.water(); //실제 인스턴스가 Car -> water멤버가 없음.
```





### instanceof 연산자

- 참조변수의 형변환 가능여부 확인에 사용. 가능하면 true반환
- 형변환 전에 반드시 instanceof으로 확인 후 형변환 해야함.

```java
void doWork(Car c) {  //다형성이 되므로 Car, FireEngine, Ambulance(Car의 모든 자손) 참조변수 가능.
  
  if(c instanceof FireEngine) {  //c를 FireEngine으로 형변환을 해도 되는지 확인 (c에 Ambulance가 들어오면 false반환)
    FireEngine fe = (FireEngine) c; //이후 형변환
    fe.water();
  }
  
  else if(c instanceof Ambulance) {
    Ambulance a = (Ambulance) c;
    a.siren;
  }
}
```





### 매개변수의 다형성(장점 1.)

다형성의 장점

- 다형적 매개변수
- 하나의 배열로 여러종류 객체 다루기.

매개변수의 다형성

- 참조형 매개변수는 메서드 호출시, 자신과 같은 타입 또는 자손타입의 인스턴스를 넘겨줄 수 있다.

```java
//부모
class Product {
  int price;
  int bonusPoint;
}
//자손
class Tv extends Product { }
class Computer extends Product { }
class Audio extends Product { }

class Buyer {
  int money = 1000;  //소유금액.
  int bonusPoint = 0;
  
  //오버로딩하는 방법. -> 물건 종류가 많아지면 관리가 힘들어지고 코드가 중복됨.
  void buy(Tv t) { //이 메서드를 가지고 Tv만 살 수 있음
    money -= t.price;
    bonusPoint += t.bonusPoint;
  }
  void buy(Computer c) { //이 메서드를 가지고 Computer만 살 수 있음
    money -= t.price;
    bonusPoint += t.bonusPoint;
  }
  void buy(Audio a) { //이 메서드를 가지고 Audio만 살 수 있음
    money -= t.price;
    bonusPoint += t.bonusPoint;
  }
  
  //다형성 이용.
  void buy(Product p) {
    money -= t.price;
    bonusPoint += t.bonusPoint;
  }
}

class Main {
  public static void main(String[] args) {
    Buyer b = new Buyer();
    
    Product p1 = new Tv();
    Product p2 = new Computer();
    Product p3 = new Audio();
    
    //메서드 하나로 물건을 살 수 있음.
    b.buy(p1);
    b.buy(p2);
    b.buy(p3);
  }
  
}
```

```java
class Product {
  int price;
  int bonusPoint;
  
  Product(int price) {
    this.price = price;
    bonusPoint = (int)(price / 10.0);
  }
}
//자손
class Tv extends Product {
  Tv() {
    //조상클래스의 생성자 Product(int price)호출.
    super(100); //Tv의 가격 100으로 설정.
  }
  
  //Object클래스의 toString() 오버라이딩.
  public String toString() {return "Tv";}
}

class Computer extends Product { 
	Computer() { super(200); }
  
  public String toString() { return "Computer"; }
}
class Audio extends Product { 
	Audio() { super(200); }
  
  public String toString() { return "Audio"; }
}

class Buyer { //구매자
  int money = 1000;  //소유금액.
  int bonusPoint = 0;//보너스포인트.
  
  //다형성 이용.
  void buy(Product p) {
    if(money < p.price) {
      System.out.println("잔액부족");
      return;
    }
    money -= p.price;
    bonusPoint += p.bonusPoint;
    System.out.println(p + " 구매"); //System.out.println(p.toString + " 구매");과 같음.
  }
}

class Main {
  public static void main(String[] args) {
    Buyer b = new Buyer();
    
    Product p1 = new Tv();
    Product p2 = new Computer();
    
    //메서드 하나로 물건을 살 수 있음.
    b.buy(p1);
    b.buy(p2);
    b.buy(new Audio());
    
    System.out.println("잔액(만 원) : " + b.money);
    System.out.println("보너스포인트(점) : " + b.bonusPoint);
  }
  
}
```



### 여러 종류의 객체를 배열로 다루기(장점2.)

- 조상타입의 배열에 자손들의 객체를 담을 수 있다.

```java
Product p1 = new Tv();
Product p2 = new Computer();
Product p3 = new Audio();

//자손 객체를 조상타입의 배열에 담기 -> 관리 쉬워짐.
Product p[] = new Product[3];
p[0] = new Tv();
p[1] = new Computer();
p[2] = new Audio();
```

```java
class Product {
  int price;
  int bonusPoint;
  
  Product(int price) {
    this.price = price;
    bonusPoint = (int)(price / 10.0);
  }
}
//자손
class Tv extends Product {
  Tv() {
    //조상클래스의 생성자 Product(int price)호출.
    super(100); //Tv의 가격 100으로 설정.
  }
  
  //Object클래스의 toString() 오버라이딩.
  public String toString() {return "Tv";}
}

class Computer extends Product { 
	Computer() { super(200); }
  
  public String toString() { return "Computer"; }
}
class Audio extends Product { 
	Audio() { super(200); }
  
  public String toString() { return "Audio"; }
}

class Buyer { //구매자
  int money = 1000;  //소유금액.
  int bonusPoint = 0;//보너스포인트.
  
  //조상타입으로 배열 만들기. (자손 타입 객체 담을 수 있음.)
  Product[] cart = new Product[10]; //구입한 제품을 저장하기 위한 배열.
  
  //다형성 이용.
  void buy(Product p) {
    if(money < p.price) {
      System.out.println("잔액부족");
      return;
    }
    money -= p.price;
    bonusPoint += p.bonusPoint;
    System.out.println(p + " 구매"); //System.out.println(p.toString + " 구매");과 같음.
  }
  
  void whatBuy() {
    int sumPrice = 0;
    String itemList = "";
    
    for(int i = 0; i < cart.length; i++) {
      if(cart[i] == null) break;
      sumPrice += cart[i].price;
      itemList += cart[i] + ", "; //cart[i].toString으로 작성해도 됨.
    }
    System.out.println("총 물품 금액 : " + sumPrice);
    System.out.println("구입한 제품 : " + itemList);
  }
}

class Main {
  public static void main(String[] args) {
    Buyer b = new Buyer();
    
    Product p1 = new Tv();
    Product p2 = new Computer();
    
    //메서드 하나로 물건을 살 수 있음.
    b.buy(p1);
    b.buy(p2);
    b.buy(new Audio());
    
    b.whatBuy();
    System.out.println("잔액(만 원) : " + b.money);
    System.out.println("보너스포인트(점) : " + b.bonusPoint);
  }
  
}
```

