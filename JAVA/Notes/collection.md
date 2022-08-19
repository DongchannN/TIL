### Collections Framework

- List : 순서가 있는 데이터의 집합, 데이터 중복 허용 (ArrayList, LinkedList, Stack, Vector 등)
- Set : 순서를 유지하지 않는 데이터 집합, 데이터 중복 허용X (HashSet, TreeSet 등)
- Map : key와 value의 쌍으로 이러우짐, 순서 유지 X, value만 데이터 중복 허용, 기존에 저장된 데이터와 중복된 키와 값을 저장하면 기존의 값은 없어지고 갱신이 된다. (HashMap, TreeMap, Hashtable, Properties 등)

![스크린샷 2022-08-19 오후 4.10.36](/Users/dongchan/Library/Application Support/typora-user-images/스크린샷 2022-08-19 오후 4.10.36.png)

위는 컬렉션 프레임웍의 상속계층도이다.



![스크린샷 2022-08-19 오후 4.14.06](/Users/dongchan/Library/Application Support/typora-user-images/스크린샷 2022-08-19 오후 4.14.06.png)

위는 Collection인터페이스의 메서드이다.(자손들로는 List, Set이 있음.)

![스크린샷 2022-08-19 오후 4.15.05](/Users/dongchan/Library/Application Support/typora-user-images/스크린샷 2022-08-19 오후 4.15.05.png)

위는 List인터페이스의 메서드이다. Collection인터페이스로부터 상속받은 것은 당연히 쓸 수 있어 제외하였다.

![스크린샷 2022-08-19 오후 4.17.25](/Users/dongchan/Library/Application Support/typora-user-images/스크린샷 2022-08-19 오후 4.17.25.png)

위는 Map인터페이스의 메서드이다.



- Map,Entry인터페이스

Map인터페이스의 내부 인터페이스이다. Map에 저장되는 key-value쌍을 다루기 위해 내부적으로 Entry인터페이를 정의했다. Map인터페이스를 구현하는 클래스에서는 Map.Entry인터페이스도 함께 구현해야한다.

```java
public interface Map {
  
  interface Entry {
    //Entry의 key객체를 반환한다.
    Object getKey();
    //Entry의 value객체를 반환한다.
    Object getValue();
    //Entry의 value객체를 지정된 객체로 바꾼다.
    Object setValue();
    //동일한 Entry인지 비교한다.
    boolean equals(Object o);
    //Entry의 해시코드를 반환한다.
    int hashCode();
  }
}
```



##### ArrayList

List인터페이스를 구현해 데이터의 저장순서가 유지되고 중복을 허용한다. 컬렉션 프레임웍에서 가장 많이 사용되는 컬렉션 클래스이다.

```java
public class ArrayList extends AbstractList
  implements List, RandomAccess, Cloneable, java.io.Serializable {
  ...
  transient Object[] elementData; //Object배열
  ...
}
```

위 ArrayList의 일부인 코드를 보면 이름이 elementData인 Object배열을 멤버변수로 선언하고 있다. 선언 타입이 Object(모든 객체의 최고조상)이기 때문에 모든 종류의 객체를 담을 수 있다. 또한 배열에 더 이상 저장할 공간이 없으면 보다 큰 새로운 배열을 생성해서 기존의 배열에 저장된 내용을 새로운 배열로 복사한 다음 저장한다.



```java
import java.util.*;


public class JavaPlayGround {
    public static void main(String[] args) {
        Vector v = new Vector(5); // 크기가 5인 벡터 생성.

        v.add("1");
        v.add("2");
        v.add("3");

        print(v);

        v.trimToSize(); //빈공간을 없앤다.
        System.out.println("after trimToSize");
        print(v);

        v.ensureCapacity(6); //capacity가 최소한 6이 되게한다.
        System.out.println("After ensureCapacity");
        print(v);

        v.setSize(7); //사이즈가 6이 되게한다. 여기서는 capacity가 부족하므로 2배 늘려 12로 만든다.
        System.out.println("After setSize");
        print(v);

        v.clear();
        System.out.println("After Clear");
        print(v);
    }

    public static void print(Vector vector) {
        System.out.println(vector);
        System.out.println("size : " + vector.size());
        System.out.println("capacity : " + vector.capacity());
    }
}
```

위의 벡터를 이용한 배열은 데이터를 읽어오고 저장하는데 효율이 좋지만 용량을 변경해야할 때에는 새로운 배열을 생성한 후 기존의 배열로부터 복사를 하는 방식을 가지고 있어 효율이 떨어진다는 단점이 있다. ArrayList또한 마찬가지이다 그러므로 처음 생성시 데이터의 개수를 잘 고려해 넉넉히 생성하는 것이 좋다.