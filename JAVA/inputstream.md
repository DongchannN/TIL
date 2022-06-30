### Inputstream

stream이란 단방향의 데이터 흐름.

System.in : System.class의 in이라는 변수.

in은 inputstream의 변수.

```java
InputStream inputstream = System.in;  //inputstream이라는 변수 생성, System.in을 inputstream에 할당.

int num = inputstream.read(); // .read()라는 입력받는 메소드를 통해 입력받고 num에 넣음.

System.out.println(num); //출력해보면 입력한 값과 다름.

//inputstream.read()는 1byte만 읽음 -> 입력이 2byte이상이면 1byte만 읽고 10진수로 인코딩.
```



### Scanner

```java
InputStream inputstream = System.in;
Scanner scanner = new Scanner(inputstream);
//inputstream으로 입력받은 값이 Scanner.class의 public Scanner(InputStream source)의 new InputStreamReader(source)로 이동.
//InputStreamReader는 온전한 문자 형태로 반환하는 중재자 역할.

InputStreamReader sr = new InputStreamReader(inputstream);
//InputStream inputstream = System.in 생략하고 아래와 같이 표현 가능.
InputStreamReader sr = new InputStreamReader(System.in);
```

