package kr.co.itwill.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginCont {

	public LoginCont() {
		System.out.println("-----LoginCont() 객체 생성됨");
	}//end
	
	
	//결과확인 http://localhost:9095/login.do GET 방식 요청
	@GetMapping("/login.do")
	public String loginForm() {
		return "login/loginForm";	//  /WEB-INF/views/login/loginForm.jsp
	}//loginForm() end
	
	
	@PostMapping("/login.do")
	public ModelAndView loginProc(@ModelAttribute LoginDTO dto, HttpServletRequest req, HttpServletResponse resp, HttpSession session) {
		
		//String id = req.getParameter("id");
		//String pw = req.getParameter("pw");
		String id = dto.getId();
		String pw = dto.getPw();
		
		ModelAndView mav = new ModelAndView();
		if(id.equals("itwill") && pw.equals("1234")) {
			//로그인 성공했을 때
			mav.setViewName("login/loginResult");
			
			//session 전역변수에 값 올리기
			session.setAttribute("s_id", id);
			session.setAttribute("s_pw", pw);
			
			req.setAttribute("message", "<h3>로그인 성공</h3>");
			
		}else {
			//로그인 실패했을 때
			mav.setViewName("login/msgView");
			req.setAttribute("message", "<p>아이디와 비번이 일치하지 않습니다</p>");
			req.setAttribute("link", "<a href='javascript:history.back()'>[다시시도]</a>");
		}//if end
		
		return mav;
		
	}//loginProc() end
}//class end
