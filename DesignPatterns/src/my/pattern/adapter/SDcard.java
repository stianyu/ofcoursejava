package my.pattern.adapter;
/*
 * 这个三个接口不允许修改，可以自己写实现类进行测试
 */
interface SDCard {
	// 读取SD卡方法
	String readSD();

	// 写入SD卡功能
	void writeSD(String msg);

}

interface TFCard {
	// 读取TF卡方法
	String readTF();

	// 写入TF卡功能
	void writeTF(String msg);
}


interface Computer {
	//电脑只有SD卡读卡器，只能读取SD卡
	String readSD(SDCard sdCard);
}

/*
 * 写一个TF转SD卡的适配器类（SDAdapterTF），要求电脑(Computer)可以通过适配器(SDAdapterTF)读取TF卡(TFCard)
 */
class SD implements SDCard {
	String name = "SD";

	@Override
	public String readSD() {
		return name;
	}

	@Override
	public void writeSD(String msg) {
		this.name = msg;
	}

}

class TF implements TFCard {
	String name = "TF";

	@Override
	public String readTF() {
		return name;
	}

	@Override
	public void writeTF(String msg) {
		this.name = msg;
	}

}

class SDAdapterTF implements SDCard{
	TF myTF;

	public SDAdapterTF(TF tf) {
		this.myTF = tf;
	}

	@Override
	public String readSD() {
		return myTF.readTF();
	}

	@Override
	public void writeSD(String msg) {
		myTF.writeTF(msg);

	}

}

class MyComputer implements Computer {
	String name = "Computer";

	public MyComputer() {

	}

	public MyComputer(String name) {
		this.name = name;
	}

	@Override
	public String readSD(SDCard sdCard) {
		return sdCard.readSD();
	}
}