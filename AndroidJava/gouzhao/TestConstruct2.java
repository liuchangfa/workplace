class Person{
	private String name ;
	private int age ;
	public Person(){
	}
	public Person(String n,int a){
	name = n ;
	age = a ;
	System.out.println("public Person(String n,int a)") ;
	}
	public String talk(){
		return "我是： "+name+"，今年： "+age+"岁" ;
	}
}

public class TestConstruct2{
	public static void main(String[] args){
		Person p = new Person() ;
		System.out.println(p.talk()) ;
	}
}

