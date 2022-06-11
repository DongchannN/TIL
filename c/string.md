### 문자열 선언

```c
char str[] = "Hello";      //변수를 바꾸지 못함.
char *str = "Hello";       //str을 바꿀 수 있음.

//문자열 배열

char str[3][20];
char *str[3];
```

String.h

```c
char *str1 = "Hello";
char *str2 = "World";
int n = strlen(str);    //n = 5. 문자열 길이 반환.
strcpy(str1, str2);     //str2에 str1 복사.
strcat(str1, str2);     //str1 에 str2 연장.
strcmp(str1, str2);     //str1 과 str2 크기 비교.
```

