### 배열

선언, 생성, 초기화

```java
int[] arr; //배열의 선언
arr = new int[5]; //배열의 생성 [길이]
//동시에 가능
int[] arr = new int[5];

//원하는 값 저장 방법.
int[] score = new int[5];
score[0] = 10;
score[1] = 40;
score[2] = 20;
score[3] = 50;
score[4] = 70;
//생성과 초기화 동시에
int[] score = new int[] {10, 20, 30, 40, 50};  //길이 생략 가능
int[] score = {10, 20, 30, 40, 50};  //new int[]또한 생략 가능
```



출력

```java
int[] arr = {10, 20, 30, 40, 50};
System.out.println(arr); //배열의 주소가 출력됨(참조변수이기 때문)
//배열요소 출력
System.out.println(Arrays.toString(arr));

//char 배열의 경우 요소 출력 but 구분자는 없음
char[] chArr = {'S', 'T', 'R', 'I', 'N', 'G'};
System.out.println(chArr); // STRING출력
```





### 배열 복사

- 얕은복사 : 복사된 배열이나 원본배열이 변경될 때 값이 같이 변경.
- 깊은복사 : 복사된 배열이나 원본배열이 변경될 때 서로 간의 값은 바뀌지 않음.

Deep Copy

for문으로 복사 가능하지만 비효율적임 -> Sytem.arraycopy() 주로 사용.

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

