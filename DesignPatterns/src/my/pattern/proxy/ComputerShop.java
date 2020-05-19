package my.pattern.proxy;

/**
 * 代理对象
 */
public class ComputerShop implements BuyComputer {
    BuyComputer cf;

    public ComputerShop(BuyComputer cf) {
        this.cf = cf;
    }

    @Override
    public void buyComputer(double money) {
        // 静态代理
        try {
            System.out.print("从工厂提货");
            cf.buyComputer(0.9 * money);
            System.out.println("送一台电脑包");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("提货失败");
        } finally {
            System.out.println("交易完成");
        }
    }

    @Override
    public void afterService() {
        cf.afterService();
    }

}
