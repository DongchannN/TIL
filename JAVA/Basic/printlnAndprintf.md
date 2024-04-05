### println and printf

- println() 의 단점

  - 실수 자릿수 조절 불가

    ```java
    System.out.println(10.0/3); //3.333333...
    ```

     - 10진수로만 출력.

       ```java
       System.out.println(0x1A); // 26
       ```

- printf()f로 출력 형식 지정

  ```java
  System.out.printf("%.2f", 10.0/3); // 3.33
  System.out.printf("%d", 0x1A); // 26
  System.out.printf('%X', 0x1A); // 1A
  System.out.printf("age: %d year: %d\n",24, 2022); //age: 24 year: 2022
  ```

  %b %d %o %x %X %f %e %E %c %s -->지시자