import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        BST<Integer, Integer> bst = new BST<>();
        bst.put(3,3);
        bst.put(1,1);
        bst.put(0,4310);
        bst.put(2,2);
        bst.put(6,13);
        bst.put(5,5);
//        bst.put(7,6);
        bst.delete(5);
        bst.delete(6);
        bst.delete(3);

//        System.out.println(bst.get(4));
        for(var a : bst) {
            System.out.println(a.getKey() + " vafda " + a.getValue());
        }
    }
}
