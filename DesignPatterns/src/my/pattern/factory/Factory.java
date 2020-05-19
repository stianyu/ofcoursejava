package my.pattern.factory;
public class Factory {
	public static Product createProduct(ProductName name) {
		Product product = null;
		switch (name) {
			case ProductAName: {
				product = new ProductA();
				break;
			}
			case ProductBName: {
				product = new ProductB();
				break;
			}
		}
		return product;
	}
}

enum ProductName {
	ProductAName, ProductBName
}

interface Product {
	public void sell();
	public void callBack();
}


class ProductA implements Product {
	String name = "Parker";

	ProductA() {

	}

	public ProductA(String name) {
		this.name = name;
	}

	@Override
	public void sell() {
		System.out.println(name + "被卖了");
	}

	@Override
	public void callBack() {
		System.out.println(name + "被召回了");
	}

	public void spiderman() {
		System.out.println(name + "是超级工厂造的英雄蜘蛛侠的名字,可以飞檐走壁, 吐丝结网");
	}
}

class ProductB implements Product {
	String name = "Kal";

	public ProductB(String name) {
		this.name = name;
	}

	public ProductB() {

	}

	@Override
	public void sell() {
		System.out.println(name + "被卖了");
	}

	@Override
	public void callBack() {
		System.out.println(name + "被召回了");
	}

	public void superman() {
		System.out.println(name + "是超级工厂造的英雄超人的名字,他很强壮可以飞");
	}
}