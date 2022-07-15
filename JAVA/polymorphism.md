### 다형성(polymorphism)

- 여러가지 형태를 가질 수 있는 능력
- 조상 타입 참조 변수로 자손 타입 객체를 다루는 것



```java
SmartTv st = new SmartTv();    //SmartTv는 Tv의 자손

Tv tv = new SmartTv();
```

둘 다 SmartTv라는 같은 타입의 인스턴스이지만 참조변수가 st는 SmartTv, tv는 Tv로 다름. -> 참조변수 타입에 따라 사용 할 수 있는 멤버의 갯수가 달라짐.

```java
SmartTv st = new Tv();  //컴파일 에러.
```

위와 같은 경우 인스턴스인 Tv보다 참조변수인 SmartTv의 멤버가 더 많으므로 컴파일 에러가 발생함.(쉽게 말하면 인스턴스에 없는 멤버를 사용하려 하기 때문에 에러. 



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
double a = 2.6;
int b = (int) a; // b = 2
```

위의 코드는 기본형 변수의 형변환이다. 참조형 변수도 기본형 변수와 마찬가지로 조상, 자손 관계의 경우 형변환이 가능하다.



```java
//자손타입의 참조변수를 조상타입으로 형변환 하는 경우.(Up casting)
FireEngine fe = new FireEngine();
Car c = (Car) fe;

FireEngine fe2 = new FireEngine();
Car c2 = fe2;  //형변환 생략 가능.

//조상타입의 참조변수를 자손타입의 참조변수로 형변환 하는 경우.(Down casting)
Car c3 = new Car();
Ambulance a = (Ambulance) c3;

//상속받은 두 자손일 경우. -> ERROR.
FireEngine fe3 = new FireEngine();
Ambulance a2 = (Ambulance) fe3; //ERROR.
```

위와 같이 Up casting인 경우 형변환을 생략할 수 있다. 하지만 Down casting인 경우 생략하면 에러가 발생한다. (에러를 잡아주므로 생략가능한 경우에 외우는 것에 크게 신경쓰지 않아도 됨.)

또한 조상, 자식 관계에서만 가능하고 같은 조상에서 나온 다른 두 자손 관계(형제 관계)에서는 형변환이 가능하지 않다.



```java
//부모 클래스
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

//자손 클래스 (소방차, 구급차)
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

형변환은 참조변수의 타입을 변환하는 것이지 실제 인스턴스를 변환하는 것이 아니다.

참조변수의 형변환을 통해서 참조하고 있는 인스턴스의 사용 가능한 멤범의 갯수를 조절하는 것이다.



```java
Car c = new Car();
FireEngine fe = (FireEngine) c; //컴파일 에러 나지 않음, 실행 시 ClassCastException 에러.
```

위 코드는 컴파일 할 때 에러가 난다. 조상타입의 참조변수를 자손타입의 참조변수로 형변환 한 것 처럼 보인다. 하지만 c의 실제 인스턴스는 Car이다. FireEngine이라는 참조변수로 부모인 Car를 참조하는 것은 혀용되지 않는다. (위의 '다형성' 내용.)

```java
Car c = new FireEngine();
FireEngine fe = (FireEngine) c;
```

위와 같이 코드를 고치면 실행 시 에러가 발생하지 않는다.



### instanceof 연산자

- 참조변수의 형변환 가능여부 확인에 사용. 가능하면 true반환 (주로 조건문에 사용.)
- 형변환 전에 반드시 instanceof으로 확인 후 형변환 해야함.
- instanceof의 왼쪽에는 참조변수 오른쪽에는 타입이 위치함 (참조변수 instanceof 타입)

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



```java
class Main {
  public static void main(String[] args) {
    FireEngine fe = new FireEngine();
    
    if(fe instanceof FireEngine) {
      System.out.println("FireEngnie instance.")
    }
    if(fe instanceof Car) {
      System.out.println("Car instance.")
    }
    if(fe instanceof Object) {
      System.out.println("Object instance.")
    }
  }
}
```

위와 같은 코드의 경우 세 if구문 모두 참이므로 세줄을 출력한다.

실제 인스턴스가 FireEngine이여도 Object와 Car클래스는 조상 클래스이므로 FireEngine인스턴스는 Object와 Car인스턴스를 포함하고 있기 때문이다.

실제 인스턴스 뿐만 아니라 조상타입도 instanceof에서 참을 반환하고 참이므로 형변환을 해도 아무 문제가 없다.



### 참조변수와 인스턴스의 연결

조상 클래스에 선언된 멤버변수와 같은 이름의 인스턴스 변수를 자손 클래스에 중복으로 정의 했을 때 조상타입의 참조변수로 자손 인스턴스를 참조하는 경우와 자손타입의 참조변수로 자손 인스턴스를 참조하는 경우 다른 결과가 나온다

메서드의 경우 조상클래스의 메서드를 자손클래스에서 오버라이딩 한 경우에는 항상 실제 인스턴스의 메소드가 호출되지만 멤버변수의 경우 참조변수의 타입에 따라 달라진다.

```java
class Main {
  public static void main(String[] args) {
    Parent p = new Child();
    Child c = new Child();
    
    System.out.println(p.x);  //100출력
    p.method(); //Child 클래스의 Method 실행.
    
    System.out.println(c.x);  //300출력
    c.method(); //당현히 Child 클래스의 Method 실행.
  }
}

class Parent {
  int x = 100;
  
  void method() {
    System.out.println("Parent Method!");
  }
}

class Child extends Parent {
  int x = 300;
  
  void method() {
    System.out.println("Child Method!!!");
    System.out.println("x = " + x);
    System.out.println("super.x = " + super.x);  //super.x = 100 조상 클래스의 x값.
    System.out.println("this.x =" + this.x);  //this.x = 300 Child클래스의 x값.
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

위와 같은 경우 배열의 크기가 지정되어있다. 배열의 크기를 유동적으로 만들고 싶으면 Vector클래스를 사용하면 된다.



```java
class Buyer {
  int money = 1000;
  int bonusPoint = 0;
  
  Vector item = new Vector(); //Vector 객체 생성.
  
  void buy(Product p) {
    if(money < p.price) {
      System.out.println("잔액부족");
      return;
    }
    money -= p.price;
    bonusPoint += p.bonusPoint;
    item.add(p);    //구입한 제품을 Vector에 저장한다.
    System.out.println(p + " 구매");
  }
  
  void refund(Product p) {
    if(item.remove(p)) {
      money += p.price;
      bonusPoint -= p.bonusPoint;
      System.out.println(p + " 반품");
    }else {
      System.out.println("해당 제품 구매내역 없음.");
    }
  }
  
  void summary() {
    int sum = 0;
    String itemList = "";
    
    if(item.isEmpty()) { //item(Vector)이 비어있으면 true 반환.
      System.out.println("구매 목록 없음.");
      return; //메서드 종료.
    }
    
    for(int i = 0; i < item.size(); i++) {
      Product p = (Product) item.get(i);
      sum += p.price;
      itemList += (i=0) "" + p :  ", " + p; //i값이 0이면 'p'추가 아니면 ', p'추가.
    }
    System.out.println("총 금액 : " + sum);
    System.out.println("구입 내역 : " + itemList);
  }
}
```

위는 Vector 클래스를 이용해 작성한 코드이다.

- Vector v = new Vector();  :  벡터 인스턴스 생성(크기 10, 그 이상의 인스턴스가 저장되면 자동으로 크기가 커짐.)
- boolean b = v.add(Object o) : 벡터에 o를 추가한다. 추가에 성공하면 true 실패하면 false를 반환한다.
- boolean b = v.remove(Object o) : 벡터에 저장되어있는 o를 제거한다. 제거에 성공하면 true 실패하면 false를 반환한다.
- boolean b = v.isEmpty() : 벡터가 비어있으면 true, 아니면 false를 반환한다.
- Object o = v.get(int index) : index에 위치한 값을 반환한다.
- int n = v.size(); : 벡터에 저장된 객체의 개수를 반환한다.