package kr.co.mymelon.media;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MediaCont {
	
	//이미 자동으로 만들어진 객체이므로 autowired로 불러옴
	@Autowired
	MediaDAO dao;

	public MediaCont() {
		System.out.println("-----MediaCont() 객체 생성됨");
	}//end
	
}//class end
