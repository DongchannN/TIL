### 배열 복사

- 얕은복사 : 복사된 배열이나 원본배열이 변경될 때 값이 같이 변경.
- 깊은복사 : 복사된 배열이나 원본배열이 변경될 때 서로 간의 값은 바뀌지 않음.

Deep Copy

```java 
int[] a = {1, 2, 3, 4};
int[] b = a.clone();
int[] c = Arrays.copyOf(a, a.length);
int[] d = Arrays.copuOfRange(a, 1, 3); //시작과 끝 설정 가능.

//System.arraycopy() -> 원하는 부분만 복사 가능, 메모리 자원 낭비 예방, 실행 빠름.
//System.arraycopy(src, srcPos, dest, destPos, length);
//src : 원본 배열, srcPos : 원본배열 복사 시작위치, dest : 복사 배열, destPos : 복사배열의 복사 시작위치, length : 복사할 요수 갯수.

int[] scopy = new int[4];

System.arraycopy(a, 0, b, 0, a.length);
```



Shallow Copy

```java
int[] a = {1, 2, 3, 4};
int[] b = a;
```

