package kr.co.itwill.calc;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;

//URL에서 요청 명령어를 읽어서 실행해 주는 클래스. 자동으로 객체 생성된다
@Controller
public class CalcController {

	public CalcController() {
		System.out.println("-----CalcController() 객체 생성됨");
	}//end
	
	//URL에서 요청한 명령어를 등록
	//@RequestMapping(value="", method = GET | POST)
	
	
	//결과확인 http://localhost:9095/add.do?no1=3&no2=5
	// /add.do 요청명령어를 get방식으로 요청하면
	@RequestMapping(value = "/add.do", method = RequestMethod.GET)
	public ModelAndView add(HttpServletRequest req) {
		
		//사용자가 요청한 값 가져오기
		int no1 = Integer.parseInt(req.getParameter("no1"));
		int no2 = Integer.parseInt(req.getParameter("no2"));
		int result = no1+no2;
				
		//View페이지로 이동하기 위한 클래스
		ModelAndView mav = new ModelAndView();
		//ApplicationContext.properties환경설정의 prefix와 suffix 참조
		// /WEB-INF/views/calcResult.jsp 뷰페이지로 이동
		mav.setViewName("calcResult");
		
		//전역변수를 활용하여 공유장소에 값 올리기
		req.setAttribute("no1", no1);
		req.setAttribute("no2", no2);
		req.setAttribute("result", result);
		req.setAttribute("message", "<h3>두 수 사이의 합</h3>");
		req.setAttribute("img", "<img src='images/PHOTO1.jpeg'>");
		
		return mav;
		
	}//add() end
	
	
	//결과확인 http://localhost:9095/sub.do?no1=3&no2=5
	//@GetMapping("/sub.do") 아래 명령어와 동일
	@RequestMapping(value = "/sub.do", method = RequestMethod.GET)
	public ModelAndView sub(HttpServletRequest req) {
		
		int no1 = Integer.parseInt(req.getParameter("no1"));
		int no2 = Integer.parseInt(req.getParameter("no2"));
		int result = no1-no2;
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("calcResult");
	
		//request영역에 값 올리기. req.setAttribute()동일한 함수
		req.setAttribute("no1", no1);
		req.setAttribute("no2", no2);
		req.setAttribute("result", result);
		req.setAttribute("message", "<h3>두 수 사이의 차</h3>");
		req.setAttribute("img", "<img src='images/PHOTO2.jpeg'>");
		
		return mav;
		
	}//sub() end
	
	
	//결과확인 http://localhost:9095/mul.do?no1=3&no2=5
	@RequestMapping(value = "/mul.do", method = RequestMethod.GET)
	public ModelAndView mul(HttpServletRequest req) {
		
		int no1 = Integer.parseInt(req.getParameter("no1"));
		int no2 = Integer.parseInt(req.getParameter("no2"));
		int result = no1*no2;
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("calcResult");
	
		//request영역에 값 올리기. req.setAttribute()동일한 함수
		req.setAttribute("no1", no1);
		req.setAttribute("no2", no2);
		req.setAttribute("result", result);
		req.setAttribute("message", "<h3>두 수 사이의 곱</h3>");
		req.setAttribute("img", "<img src='images/PHOTO3.jpeg'>");
		
		return mav;
		
	}//mul() end
	
	
	//결과확인 http://localhost:9095/div.do?no1=3&no2=5
	@RequestMapping(value = "/div.do", method = RequestMethod.GET)
	public ModelAndView div(HttpServletRequest req) {
		
		int no1 = Integer.parseInt(req.getParameter("no1"));
		int no2 = Integer.parseInt(req.getParameter("no2"));
		int result = no1/no2;
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("calcResult");
	
		//request영역에 값 올리기. req.setAttribute()동일한 함수
		req.setAttribute("no1", no1);
		req.setAttribute("no2", no2);
		req.setAttribute("result", result);
		req.setAttribute("message", "<h3>두 수의 나눗셈</h3>");
		req.setAttribute("img", "<img src='images/new4.jpg'>");
		
		return mav;
		
	}//div() end
	
	
	
}//class end
