# SQL 문법 2
 #sql
## JOIN
하나의 테이블에 원하는 데이터가 없으면 JOIN을 이용해두개의 테이블을 엮어서 원하는 결과를 도출해야한다.

## INNER JOIN
가장 많이 사용하는 JOIN이다. 그냥 JOIN을 호출하면 자동적으로 INNER JOIN으로 인식한다.
Table A 와 B가 있으면 A와 B의 교집합 부분을 연결한다.

```sql
SELECT 컬럼명
FROM 테이븖A INNER JOIN 테이블B
ON 조인조건
WHERE 검색조건   //생략가능
```

## OUTER JOIN
INNER JOIN은 두 테이블에 모두 데이터가 있어지 결과가 나오지만 OUTER JOIN은 한쪽에만 있어도 결과가 나온다. FULL OUTER JOIN, LEFT OUTER JOIN, RIGHT OUTER JOIN이 있다.

```sql
SELECT 컬럼명
FROM 테이블A LEFT OUTER JOIN 테이블B //LEFT RIGHT FULL 선택
ON 조인조건
WHERE 검색조건   //생략가능
```

![](query2/%E1%84%89%E1%85%B3%E1%84%8F%E1%85%B3%E1%84%85%E1%85%B5%E1%86%AB%E1%84%89%E1%85%A3%E1%86%BA%202022-10-26%20%E1%84%8B%E1%85%A9%E1%84%92%E1%85%AE%205.14.11.png)

## CROSS JOIN
한쪽 테이블과 다른 쪽 테이블의 모든 행을 조인시키는 기능이다. CROSS JOIN을 전체 행 개수는 두 테이블의 각 행의 개수를 곱한 수만큼 나온다.

```sql
SELECT *
FROM 테이블A CROSS JOIN 테이블B
```

## SELF JOIN
JOIN은 일반적으로 2개의 테이블을 연결하지만 SELF JOIN은 자기 자신을 참조한다.
따로 명령어가 있는 것이 아닌 OUTER JOIN, INNER JOIN 과 같은 명령어로 자기 자신과 조인하는 것을 SELF JOIN 이라고 한다.

```sql
SELECT 컬럼명
FROM 테이블 별칭A INNERJOIN 테이블 별칭B
ON 조인조건
WHERE 검색조건   //생략가능
```

```sql
//Example

//테이블 생성 및 데이터 삽입
CREATE TABLE company
(
EmployeeId BIGINT  NOT NULL	AUTO_INCREMENT,
EmployeeName VARCHAR(45)	 NOT NULL,
GRADE INT  NOT NULL
)

INSERT INTO company (EmployeeName, Grade) 
VALUES ('DAN', 1)

INSERT INTO company (EmployeeName, Grade) 
VALUES ('HONG', 2)

INSERT INTO company (EmployeeName, Grade) 
VALUES ('LEE', 3)

INSERT INTO company (EmployeeName, Grade) 
VALUES ('KIM', 3)


//SELF JOIN 예제1
SELECT A.EmployeeName, B.EmployeeName AS 'UPPER'
FROM company A INNER JOIN company B
ON A.Grade -1 = B.Grade

//예제2
SELECT A.EmployeeName, B.EmployeeName AS 'UPPER'
FROM company A FULL OUTER JOIN company B
ON A.Grade -1 = B.Grade
```

**예제 1 결과**
![](query2/%E1%84%89%E1%85%B3%E1%84%8F%E1%85%B3%E1%84%85%E1%85%B5%E1%86%AB%E1%84%89%E1%85%A3%E1%86%BA%202022-10-26%20%E1%84%8B%E1%85%A9%E1%84%92%E1%85%AE%2011.20.14.png)

**예제 2 결과**
![](query2/%E1%84%89%E1%85%B3%E1%84%8F%E1%85%B3%E1%84%85%E1%85%B5%E1%86%AB%E1%84%89%E1%85%A3%E1%86%BA%202022-10-26%20%E1%84%8B%E1%85%A9%E1%84%92%E1%85%AE%2011.21.38.png)