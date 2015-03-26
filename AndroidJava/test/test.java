class Test {
	private String name;
	private int age;

	private void self() {
		System.out.println("my name is: "+name+",my age is : "+age+" ");
	}

	public void talk(){
		self();
	}
	public void setName(String setname){
		name = setname;
	}
	public void setAge(int setage){
		if(setage > 0)
			age = setage;
	}
	public 	String getName(){
		return name;
	}
	public int setAge(){
		return age;
	}
}

class TestDemo
{
	public static void main(String[] args){
		Test p1 = new Test();
		Test p2 = new Test();
		p1.setName("Herman");
		p1.setAge(22);
		p2.setName("Focus");
		p2.setAge(22);
		p1.talk();
		p2.talk();
	}
}

