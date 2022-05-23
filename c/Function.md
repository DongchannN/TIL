### 함수

```c
int add(int x, int y) //int : 반환형  add : 함수 이름   (int x,int y) : 인자
{
  int z;
  z = x + y;
  return z;           //z 반환  반환형이 void인 경우  return 불필요
}
```



### 함수 호출

```c
int main()
{
  int c;
  c = add(3,4);  //함수 호출
  return 0;
}
```



### 함수 호출 점검

```c
int add(int x, int y)          //함수 정의를 함수 호출 위치보다 앞에 작성해야함.
{
  int z;
  printf("add start_x = %d y = %d",x, y);
  
  z = x + y;
  printf("add end_z = %d",z);
  
  return z;
}

int main()
{
  int c = 0;
  printf("main start_c = %d",c);
  
  c = add(3,4);  //함수 호출 위치
  printf("main end_c = %d",c);
  
  return 0;
}
```



### 함수 원형 선언

```c
int add(int, int); //함수 원형 선언  ,  변수명 생략 가능

int main(){
  c = add(3,4);
  return 0;
}

int add(int x, int y){  //함수 정의
  int z;
  z= x + y;
  return z;
}
```



### 함수와 변수의 종류

1. 지역변수 : 함수 내에서 선언

   함수의 호출과 동시에 자동적으로 생성, 종료시 자동 소멸.

```c
int add (int x, int y) {
  //c = x + y; main에 c가 정의 되어도 add에 정의가 안되었으므로 오류
  int c;  //main의 c와 add의 c는 다른 변수
  c = x + y;
  return c;
}

int main() {
  int c;
  add(3,4);
}
```

2. 전역변수 : 함수 밖에서 선언

   프로그램 내 어디서든 사용 가능.

   함수의 독립성을 해칠 수 있음.

```c
int c =0;                //전역변수
int add (int x, int y) {
  c = x + y;
  return c;
}

int main() {
  c = 10;  
  printf("%d\n", add(3,4));
  printf("%d", c);
  '''
    7
    7
    '''
}
```

3. 동일한 이름의 전역변수, 지역변수

   지역변수가 우선한다.



### 함수 배열 전달

1. 배열의 개별 원소 전달

   일반 원소와 같음.

2. 배열 전체 전달(1차원)

   형식인자에서 배열 크기를 명시해도 의미 없음.

   배열 크기가 필요한 경우 별도의 함수 인자로 전달.

```c
void print_arr(int x, int n){
  for (int = 0; i < n; i++){
    printf("%d", x[i]);
  }
}
int main() {
  int a[3] = {1,2,3};
  int size = sizeof(a) / sizeof(int);
  print_arr(a, size);
}
```

​	 배열 값 변경의 종속성.



### 라이브러리

​	함수들을 구현해 모아 놓은 것.

1. 표준 라이브러리

   c언어에서 정해놓은 표준 함수들로 구성 : printf(), scanf() 등. --> stdio.h에 원형선언.

2. 라이브러리

   함수의 형태와 기능만 알고 있으면 사용 할 수 있음.

   호출되기 전에 함수의 원형이 선언되어 있어야함.  적절한 헤더파일을 포함시켜야 함.



### 재귀함수

```c
void dec (int x) {
  printf("%d\n", x);
  if (x>1)
    dec(x-1);
}
int main() {
  dec(3);
  return 0;
}
//출력
3
2
1
```

