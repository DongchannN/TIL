### 구조체

```c
Struct Car {     //구조체 선언
  int num;
  double gas;
}

int main void() { 
  struct Car car1;  //구조체 변수 선언
  car1.num = 1234;  //변수 엑세스.
  car1.gas = 25.5;
}

//typedef

typedef sturct Car {
  int num;
  double gas;
}Car;          //이름 붙이기.

int main void() {
  Car car1; //붙인 이름 바로 사용 (struct 필요X)
  car1.num = 1241;  //변수 엑세스.
  car1.gas = 23.3;
}
```