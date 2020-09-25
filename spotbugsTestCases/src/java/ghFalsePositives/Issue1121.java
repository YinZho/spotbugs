package ghFalsePositives;

public class Issue1121 {
    boolean isEmpty(Object obj) {
        return obj == null;
    }

    void fun() {
        Object obj = null;
        if (isEmpty(obj)) {
            System.out.println("Obj is Empty!");
        } else {
            obj.toString(); // FP here
        }
    }
}
