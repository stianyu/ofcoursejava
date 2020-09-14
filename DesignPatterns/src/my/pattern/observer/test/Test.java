package my.pattern.observer.test;

import my.pattern.observer.JingDongObserver;
import my.pattern.observer.ProductList;
import my.pattern.observer.TaoBaoObserver;

public class Test {

    @org.junit.Test
    public void testObserver() {
        ProductList observable = ProductList.getInstance();
        TaoBaoObserver taoBaoObserver = new TaoBaoObserver();
        JingDongObserver jingDongObserver = new JingDongObserver();
        observable.addObserver(taoBaoObserver);
        observable.addObserver(jingDongObserver);
        observable.addProduct("新增产品1");
    }
}
