package my.pattern.abstractfactory;

public class AbstractFactoryTest {
    public static void main(String[] args) {
        String beer = (new BeerFactory()).creatProduct("Yanjing");
        String coffer = (new CoffeeFactory()).creatProduct("mokai");
        System.out.println("点了一杯yanjing啤酒，给我了一杯" + beer);
        System.out.println("点了一杯mokai咖啡，给我了一杯" + coffer);
    }
}

abstract class AbstractFactory {
    public abstract String creatProduct(String product);
}

class BeerFactory extends AbstractFactory {

    @Override
    public String creatProduct(String product) {
        String result = null;
        switch (product) {
            case "Hanse":
                result = "汉斯";
                break;
            case "Yanjing":
                result = "燕京";
                break;
            default:
                result = "其他啤酒";
                break;
        }
        return result;
    }
}

class  CoffeeFactory extends AbstractFactory {

    @Override
    public String creatProduct(String product) {
        String result = null;
        switch (product) {
            case "lansan":
                result = "蓝山";
                break;
            case "mokai":
                result = "摩卡";
                break;
            default:
                result = "卡布奇诺";
                break;
        }
        return result;
    }
}