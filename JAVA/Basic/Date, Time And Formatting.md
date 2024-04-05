### Calendar

Calendar는 추상클래스이기 때문에 객체생성을 직접하지 못한다. -> 메서드를 통하여 완전히 구현된 클래스의 인스턴스를 얻어야한다.

Calendar 클래스를 완전히 구현한 클래스는 그래고리력과 버즈히스트가 있는데 태국을 제외한 나라는 그레고리력을 사용하므로 GregorianCalendar만 사용하면 된다.

```java
Calendar cal = new Calenddar(); //ERROR.

//Calendar클래스를 구현한 클래스의 인스턴스를 반환.
Calendar cal = Calendar.getInstance();

//그래고리력 클래스의 인스턴스를 바로 생성.
Calendar cal = new GregorianCalendar();
```

다른 종류의 캘린더를 사용하는 국가나 새로운 역법이 추가되면 세번째 코드의 경우 변경을 해야하지만 두번째 경우는 시스템의 국가와 지역설정을 확인해서 반환하므로 코드를 수정하지 않아도 된다.



### Date

Calendar가 추가되며 Date는 잘 사용되지 않지만 Date를 필요로 하는 메서드들이 있어 서로 변환할 일이 생긴다.

```java
// Calendar -> Date.
Calendar cal = Calendar.getInstance();

Date d = new Date(cal.getTimeInMillis());

// Date -> Calendar.
Date d = new Date();

Calendar cal = Calendar.getInstance();
cal.setTime(d);
```



```java
import java.util.*;

class Main {
  public static void main(String[] args) {
    //현재의 날짜, 시간으로 설정.
    Calendar today = Calendar.getInstance();
    
    System.out.println("Year : " + today.get(Calendar.YEAR));
    System.out.println("Month-1 : " + today.get(Calendar.MONTH));
    System.out.println("Week of Year : " + today.get(Calendar.WEEK_OF_YEAR));
    System.out.println("Week of Month : " + today.get(Calendar.WEEK_OF_MONTH));
    System.out.println("Day of Month : " + today.get(Calendar.DAY_OF_MONTH));
    System.out.println("Hour(0-11) : " + today.get(Calendar.HOUR));
    System.out.println("Hour(0-23) : " + today.get(Calendar.HOUR_OF_DAY));
    System.out.println("Minute : " + today.get(Calendar.MINUTE));
  }
}
```

getInstance로 얻은 인스턴스는 기본적으로 현재 시스템의 날짜와 시간에 대한 정보를 담고있다. 원하는 날짜나 시간으로 사용하려면 set, 원하는 값을 가져오려면 get을 이용하면 된다.