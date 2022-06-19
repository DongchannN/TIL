### Dynamic Programming

큰 문제를 작은 문제로 나누어 해결

작은 문제의 해결 방식은 큰 문제의 해결방식과 동일

### Top-down

- 하향식, 주로 재귀함수 호출 이용, 메모리를 많이 점유함.
- 큰 문제를 해결하기 위해 작은 문제를 호출.
- 메모이제이션 기법 사용 ( 전에 계산한 값을 메모리에 저장해 동일한 계산의 반복 수행 제거)

```java
int fibo(int n) {
  if (n == 0) return 0;
  if (n == 1) return 1;
  
  if (dp[n] != -1) return dp[n];  //이미 계산한 적이 있는 경우.
  dp[n] = fibo(n-1) + fibo(n-2);
  return dp[n];
}
```



### Bottom-up

- 상향식, 주로 반복문 이용. 메모리 사용량이 상대적으로 적음
- 가장 작은 문제들부터 답을 찾아가 마지막에 전체 문제의 답을 구함

```java
int fibonacci(int n) {
   dp[0] = 0, dp[1] = 1;
   for (int i = 2; i <= n; i++)
       dp[i] = dp[i - 1] + dp[i - 2];
}
```





도움 : https://velog.io/@kimdukbae/다이나믹-프로그래밍-Dynamic-Programming