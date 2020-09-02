package my.answer.multithread;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DeleteList {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        Iterator<Integer> iterator = list.iterator();
        synchronized (iterator) {
            while (iterator.hasNext()) {
                int temp = iterator.next();
                if (temp == 1) {
                    iterator.remove();
                }
            }
        }
//        for (Integer integer : list) {
//            if (integer == 1)
//                list.remove(integer);
//        }
        System.out.println(list);
    }
}
