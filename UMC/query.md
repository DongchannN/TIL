# SQL 문법
## DDL (Data Definition Language)
- CREATE : 새로운 DB 테이블, 뷰, 인덱스, 저장 프로시저 만들기
```sql
//기본 문법
CREATE table 테이블이름(
	컬럼명 데이터타입 조건,
	컬럼명 데이터타입 조건,
	컬럼명 데이터타입 조건
);
```
```sql
//예제
CREATE table members(
	memberIndex		BIGINT			NOT NULL	AUTO_INCREMENT,
	member_name 	VARCHAR(45) 	NOT NULL,
	member_phone	VARCHAR(25)		NOT NULL,
	status			VARCHAR(2)		NOT NULL,
	PRIMARY KEY(memberIndex)
	//[CONSTRAINT 제약조건이름 제약조건]으로 제약조건에 이름을 설정할 수 있다.
	//ex. CONTSTRAINT members_pk PRIMARY KEY(memberIndex)
);
```

- DROP : 이미 존재하는 DB 테이블, 뷰, 인덱스, 저장프로시저 삭제
```sql
//DB 삭제
DROP DATABASE 데이터베이스이름

//TABLE 삭제
DROP TABLE 테이블이름
```

- ALTER : 이미 존재하는 DB개체에 대한 변경
```sql
//컬럼 추가(ADD)
ALTER TABLE 테이블이름 ADD COLUMN 컬럼이름 varchar(45) NOT NULL;

//컬럼 변경(MODIFY)
ALTER TABLE 테이블이름 MODIFY COLUMN 컬럼이름 varchar(30) NULL;

//컬럼 이름까지 변경(CHANGE)
ALTER TABLE 테이블이름 CHANGE COLUMN 컬럼이름, 새컬럼이름 varchar(20) NULL;

//테이블이름 변경(RENAME)
ALTER TABLE 테이블이름 RENAME 새테이블이름;
```

- TRUNCATE : 테이블안의 모든 데이터를 제거 (한번 삭제시 돌이킬 수 없음)
```sql
TRUNCATE TABLE members;
```

## DML (Data Manipulation Language)
- SELECT : 검색
```sql
//테이블의 모든 컬럼을 읽음.
SELECT * from members;
//특정 열 읽기.
SELECT member_name, member_phone FROM members;

//where조건문 -> AND, OR, NOT과 같이 조합 가능.
//문자열
SELECT * FROM members WHERE member_name='홍길동';
//숫자
SELECT * FROM members WHERE memberIndex=4;
```
- INSERT : 삽입
```sql
//기본 -> 컬럼순으로 입력.
INSERT INTO members
	VALUE (1, '홍길동', '01012345678', 'A'),
	VALUE (2, '홍길순', '01043218765', 'A'),
	VALUE (3, '고길동', '01009091212', 'A');

//컬럼지정
INSERT INTO members(member_name, member_phone)
	VALUE ('고길순', '01077761234');

//다른 테이블에서 값 가져오기
INSERT into memberDetails (name)
	SELECT member_name
	FROM members
	WHERE member_name <> '';
```

- UPDATE : 수정
```sql
//테이블에 있는 열 전체 변경
UPDATE members SET status = 'B';

//조건문 붙이기
UPDATE member SET status = 'B' WHERE status is null;
```

- DELETE : 삭제
```sql
DELETE FROM members WHERE status = 'D';
```

## DCL (Data Control Language)
- GRANT : 특정 DB사용자에게 특정 작업에 대한 수행 권한을 부여
```sql
//GRANT 권한리스트 ON 객체명 TO 사용자;
GRANT SELECT, INSERT ON members TO defaultuser;
```
- REVOKE : 특정 DB사용자의 특정 작업에 대한 수행 권한 박탈


### DB유저에게 GRANT, REVOKE를 이용해 설정할 수 있는 권한

- CONNECT : DB 또는 SCHEMA에 연결하는 권한
- SELECT :  DB에서 DATA를 검색할 수 있는 권한
- INSERT : DB에 DATA를 삽입할 수 있는 권한
- UPDATE : DB에 DATA를 수정할 수 있는 권한
- DELETE : DB에 DATA를 삭제할 수 있는 권한
- USAGE : SCHEMA 또는 함수와 같은 DB개체를 사용할 수 있는 권한


## TCL (Transaction Control Language)
- COMMIT : 트랙잭션 처리가 정상적으로 종료되어 수행한 변경 내용을 DB에 반영

- ROLLBACK : 트랜잭션 처리가 비정상적으로 종료되어 DB의 일관성이 깨졌을 때 트랜잭션이 한 모든 변경작업 취소

- SAVEPOINT : 현재 트랜잭션 작게 분할, 저장된 포인트는 ‘ROLLBACK TO SAVEPOINT’문을 통해 지정한 곳까지 롤백 가능

**1. COMMIT 이후 ROLLBACK은 이미 DB에 DATA가 반영이 되었기 때문에 롤백 X.**

**2. COMMIT 이후 SAVEPOINT는 모두 없어짐.**

