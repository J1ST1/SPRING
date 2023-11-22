package kr.co.itwill.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/cart")
public class CartCont {

	public CartCont() {
		System.out.println("-----CartCont()객체 생성됨");
	}//end
	
	@Autowired
	CartDAO cartDao;
	
	@PostMapping("/insert")
	public String cartInsert(@ModelAttribute CartDTO cartDto, HttpSession session) {
		//로그인 기능을 구현했다면 session.getAttribute() 활용
		//cartDto.setId(session.getAttribute("s_id"));
		cartDto.setId("j1st"); //여기서는 임시로 "itwill"
		//아이디 바꾸는 곳 ↑
		cartDao.cartInsert(cartDto);
		
		return "redirect:/cart/list"; //장바구니 목록페이지 호출
	}//cartInsert() end
	
	
	@RequestMapping("/list")
	public ModelAndView list(HttpSession session) {
		//로그인 했다면
		//String s_id=session.getAttribute("s_id")
		String s_id="j1st"; //테스트용 임시 아이디 "itwill"
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("cart/list"); // /WEB-INF/views/cart/list.jsp
		mav.addObject("list", cartDao.cartList(s_id));
		return mav;
	}//list() end
	
	
	
}//class end
