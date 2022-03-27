### 메서드

: 같은 작원 단위의 문장들을 묶어놓은 것

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



- 메서드 호출

```java
printHello(); // void printHello() 호출

int result = add(3,5);   //매게변수 3, 5  int add(int x, int y)호출, result에 저장
```



