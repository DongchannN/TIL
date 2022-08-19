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
