### 메서드

: 특정 작업을 수행하는 일련의 문장들을 하나로 묶은 것

메서드를 사용하는 이유.

1. 재사용성 
2. 중복된 코드를 제거 -> 관리가 쉬워짐
3. 프로그램의 구조화.

```java
///중복된 코드 예시
public static void main(String[] args) {
  Scanner scanner = new Scanner(System.in);
  int arrLength1 = scanner.nextInt();
  int[] arr1 = new int[arrLength];
  
  int arrLength2 = scanner.nextInt();
  int[] arr2 = new int[arrLength];
  
  for(int i = 0; i < arrLength; i++) {
    arr1[i] = scanner.nextInt();
  }
  
  for(int i = 0; i < arrLength; i++) {
    arr2[i] = scanner.nextInt();
  }
}

//중복된 코드 제거
public static void main(String[] args) {
  Scanner scanner = new Scanner(System.in);
  int arr1Length = scanner.nextInt();
  int arr2Length = scanner.nextInt();
  
  int[] arr1 = new int[arr1Length];
  int[] arr2 = new int[arr2Length];
  inputArr(arr1);
  inputArr(arr2);
}

static void inputArr(int[] arr) {
  for(int i = 0; i < arrLength; i++) {
    arr[i] = scanner.nextInt();
  }
}
```



```java
static void printArr(int[] numArr){  //method name
  for(int i = 0;i<10;i++)
    System.out.printf("%d", numArr[i]);
  System.out.println();
}

public static void main(String args[]){
  for(int i =0; i<10; i++)
    numArr[i] = (int)(Math.radom()*10);
  
  printArr(numArr);  //using method
}
```



- 메서드 선언

```java
int add(int x, int y){   //가장 앞의 int : 반환 타입  |  ()괄호 안 : 매개변수   |  add : 메서드 이름 
  int result = x + y;
  return result;
}
```

​	출력은 0개 또는 1개.(출력이 없을 시 반환 타입은 void)

​	출력이 많아야 할 때 --> 하나의 객체로 묶어서 출력

​	메서드의 이름은 주로 동사로 작성, 메서드의 기능을 쉽게 알 수 있는 이름을 지어야함.



- 메서드 호출

```java
printHello(); // void printHello() 호출

int result = add(3,5);   //매게변수 3, 5  int add(int x, int y)호출, result에 저장
```



### return 문

- 실행 중인 method 종료, 호출한 곳으로 복귀.

- 메서드 마지막에도 작성 필수 BUT 반환타입이 void일 때 생략 가능(컴파일러가 자동으로 추가.)

```java
int max(int a, int b) {
  if(a>b)
    return a;       //return문을 작성해도 a < b이면 return문이 없음 -->ERROR.
}
```



메서드의 구현부를 작성 시 매개변수의 값이 적절한 것인지 가장 먼저 확인해야함 --> 적절하지 않을 시 바로 메서드를 빠져나와야함.

```java
float divide(int x, int y) {
  //나누는 수가 0 이면 에러 -> 미리 0인지 확인 후 0이면 메서드 종료.
  if(y == 0) {
    System.out.println("0으로 나눌 수 없습니다.");
    return -1; //return문을 통해 메서드 종료.
  }
  
  return x / (float)y;
}
```



### 호출 스택(call stack)

- Stack : 순서대로 넣고 뺄 수 있음.
- Call Stack : 메서드 수행에 필요한 메모리가 제공되는 공간(메서드 호출 시 호출스택에 메모리 할당(PUT), 종료시 해제(PUSH)). 아래에 있는 메서드가 위에 메서드를 호출, 맨 위의 메서드만 실행.

```java
class Eg{
    public static void main(String[] args) {
    System.out.println("HELLO");
    //call stack : []->[main]->[main,println]->[main]->[]
  }
}
```



