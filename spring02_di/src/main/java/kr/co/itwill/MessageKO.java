package kr.co.itwill;

public class MessageKO implements IHello{
	//내 클래스의 부모가 인터페이스 상속(구현) : implements
	
	public MessageKO() {
		System.out.println("MessageKO 호출됨...");
	}//end
	
	@Override
	public void sayHello(String name) {
		System.out.println("안녕하세요" + name + "님");
	}//end
	
}//class end
