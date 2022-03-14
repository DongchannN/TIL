### Variable and Constant.

- 01 Variable

  ```java
  int x = 100; //can be change
  int x = 200;
  ```

  

- 02 Constant

  ```java
  final int MAX = 300; //can not change.
  int MAX = 400; //ERROR
  ```

- 03 Literal 

  value of the Variable or Constant.

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

  