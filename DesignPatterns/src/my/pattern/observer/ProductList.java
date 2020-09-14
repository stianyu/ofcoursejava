package my.pattern.observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * 观察者模式：
 * 一个对象（电商接口）会去监听另外一个对象（产品列表），当被监听对象（产品列表）发生变化时，对象（电商接口）就会触发一定的行为，以适应变化的逻辑模式，称为观察者模式
 *
 * 被观察的产品列表
 */
public class ProductList extends Observable {

    private List<String> productList = null; // 产品列表

    private static ProductList instance; // 类唯一实例

    private ProductList() {

    }

    /**
     * 取得唯一实例
     * @return 产品列表唯一实例
     */
    public static ProductList getInstance() {
        if (instance == null) {
            instance = new ProductList();
            instance.productList = new ArrayList<>();
        }
        return instance;
    }

    /**
     * 添加观察者（电商接口）
     * @param observer 管擦者
     */
    public void addProductListObserver(Observer observer) {
        this.addObserver(observer);
    }

    /**
     * 新增产品
     * @param newProduct 新产品
     */
    public void addProduct(String newProduct) {
        productList.add(newProduct);
        System.out.println("产品列表新增了产品：" + newProduct);
        this.setChanged(); // 设置被观察对象发生变化
        this.notifyObservers(newProduct); //把新产品作为参数传给观察者，通知观察者，产品列表新增了产品
    }

}
