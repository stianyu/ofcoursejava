package my.answer.generic;

public class NumberContainer<E extends Number> extends Container<E> implements Comparable<NumberContainer<?>>{
	
	@Override
	public boolean equals(Object object) {
		if(this == object) {
			return true;
		}
		if(object instanceof NumberContainer<?>) {
			NumberContainer<?> anotherO = (NumberContainer<?>) object;
			if(this.getElement() == null && anotherO.getElement() == null) {
				return true;
			}
			if(this.getElement() != null && anotherO.getElement() != null && this.getElement().doubleValue() == anotherO.getElement().doubleValue()){
				return true;
			}
		}
		return false;	
	}
   
	@Override
	public int compareTo(NumberContainer<?> o) {
		if(this.getElement().doubleValue() < o.getElement().doubleValue()) {
			return -1;
		}
		else if (this.getElement().doubleValue() == o.getElement().doubleValue()) {
			return 0;
		}
		else {
			return 1;
		}
	}
	
	@Override
	public String toString() {
		return this.getElement().toString();
	}

	public static void main(String[] args) {
		NumberContainer<Integer> a = new NumberContainer<>();
		a.setElement(32);
		System.out.println(a);
		NumberContainer<Float> a2 = new NumberContainer<>();
		a2.setElement(new Float(32.2));
		System.out.println(a2);	
		NumberContainer<Integer> a3 = new NumberContainer<>();
		a3.setElement(34);
		System.out.println(a.equals(a2));
		System.out.println(a.compareTo(a2));
	}




}
