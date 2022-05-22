### return 문

- 실행 중 method 종료, 호출한 곳으로 복귀.

- 메서드 마지막에도 작성 필수 BUT 반환타입이 void일 때 생략 가능(컴파일러가 자동으로 추가.)

```java
int max(int a, int b) {
  if(a>b)
    return a;       //return문을 작성해도 a < b이면 return문이 없음 -->ERROR.
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



