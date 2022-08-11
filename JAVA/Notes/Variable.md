### Type of Variable

- Primitive type(기본형)

  boolean, char, byte, short, int, long, float, double -> only 8.

  실제 값을 저장.

- Reference type(참조형)

  Else (String, System, ...)

  어떤 값이 저장되어있는 주소를 값으로 가짐.

  ```java
  Date today; //declare Reference type variable
  today = new Date(); // save the object's id in today
  ```

  

### Variable and Constant.

- 01 Variable

  ```java
  int x = 100; //can be change
  int x = 200;
  ```

  

- 02 Constant

  ```java
  //상수의 이름은 모두 대문자로 하는 것이 관례
  //여러단어로 이루어지면 _로 구분 (ex. MAX_VALUE)
  final int MAX = 300; //can not change.
  MAX = 400; //ERROR
  
  final int MIN; //ERROR 선언과 동시에 초기화 해야함.
  ```

- 03 Literal 

  여기서의 상수(Constant)는 저장하면 변경 불가능한 저장공간의 의미.

  우리가 알고있는 값들에 관한 상수는 의미의 혼돈을 방지하기 위해 리터럴(Literal)이라고 부름.

  

- 04 Use

  ```java
  boolean power = true;
  char ch = 'A';
  String str = "ABC";
  String str1 = "A";
  byte b = 128; // ERROR (byte range -128~127)
  long l = 10_000_000_000L; // range is out of int --> put L behind
  float f = 3.14f; //d can be omitted but f is not
  double d = 3.14d; //double is more wide range than float.
  ```

  - Range of variable > literal : OK

  - Range of variable < literal : ERROR

    

- 05 Char and String

  ```java
  char ch = 'A';
  char ch = 'AB'; // ERROR
  String s = "ABC"; //String is a Class
  String s1 = "AB"; 
  String s2 = new String("AB"); //how the Class works
  ```

  String + Any type ==> String

  example

  ```java
  int x = 1;
  int y = 2;
  String a = "1";
  System.out.println(x+y+a); //"31"
  System.out.println(a+x+y); // "112"
  ```

  