package my.pattern.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * 淘宝电商接口实现了Observer
 */
public class TaoBaoObserver implements Observer {

    @Override
    public void update(Observable o, Object product) {
        String newProduct = (String) product;
        System.out.println("发送新产品【" + newProduct + "】同步到淘宝商城");
    }
}
