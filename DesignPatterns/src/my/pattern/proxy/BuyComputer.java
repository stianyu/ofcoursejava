package my.pattern.proxy;

/**
 * 代理模式
 * 定义一个代理工厂接口
 */
public interface BuyComputer {

    public void buyComputer(double money);

    public void afterService();
}
