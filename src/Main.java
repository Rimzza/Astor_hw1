import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;


public class Main {
    public static void main(String[] args) {






        /*MyArrayList<String> test = new MyArrayList<>();
        test.add("1");
        test.add("10");
        test.add("5");
        test.add("3");
        test.add("15");
        test.add("0");
        test.add("7");
        test.add(8,"123");
        test.sort(new MyComp());
        System.out.println(test);*/

        /*MyArrayList<Integer> test = new MyArrayList<>();
        test.add(10);
        test.add(1);
        test.add(3);
        test.add(6);
        test.add(9);
        test.add(45);
        test.add(100);
        test.add(42);
        test.remove(0);
        test.add(1,12);
        test.set(1,13);
        Integer ew = test.get(3);
        test.sort((a,b) -> a - b);
        System.out.println(test);*/


        MyList<Integer> test = new MyArrayList<>();
        test.add(11);
        test.add(30);
        test.add(12);
        test.add(-1);
        test.add(13);
        test.remove(1);




        MyList<Integer> test2 = new MyLinkedList<>();
        test2.add(0);
        test2.add(1);
        test2.add(3);
        test2.add(5);
        test2.remove(0);
        test2.clear();

        System.out.println(test2);


    }


    static class MyComp implements MyComparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;
        }
    }
}