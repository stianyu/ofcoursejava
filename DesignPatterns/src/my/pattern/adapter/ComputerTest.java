package my.pattern.adapter;

public class ComputerTest {

	public static void main(String[] args) {
		MyComputer myComputer = new MyComputer("Computer of STY");
		SD mysd = new SD();
		TF mytf = new TF();
		SDAdapterTF sdAdapterTF = new SDAdapterTF(mytf);
		//直接读SD卡
		System.out.println(myComputer.readSD(mysd));
		//用适配器读TF卡
		System.out.println(myComputer.readSD(sdAdapterTF));	
	}

}
