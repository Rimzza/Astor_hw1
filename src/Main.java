import java.util.ArrayList;
import java.util.LinkedList;


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


        MyLinkedList<Integer> test = new MyLinkedList<>();

        test.add(11);
        test.add(30);
        test.add(12);
        test.add(-1);
        test.add(13);
        test.add(10000);
        test.set(32, 231);
        test.remove(312);
        test.clear();
        System.out.println(test);
        System.out.println(test.getHead() + " : " + test.getLast());

        test.sort(new MyComp());

        System.out.println(test);

        System.out.println(test.getHead() + " : " + test.getLast());


    }


    static class MyComp implements MyComparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;
        }
    }
}