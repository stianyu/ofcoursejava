package my.pattern.factory;

public class FactoryTest {

	public static void main(String[] args) {
		ProductA a =(ProductA) Factory.createProduct(ProductName.ProductAName);
		ProductB b =(ProductB) Factory.createProduct(ProductName.ProductBName);
		a.spiderman();
		b.superman();
	}

}
