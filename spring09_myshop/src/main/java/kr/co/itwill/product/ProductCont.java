package kr.co.itwill.product;

import java.io.File;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/product")
public class ProductCont {

	public ProductCont() {
		System.out.println("-----ProductCont()객체 생성됨");
	}//end
	
	@Autowired
	private ProductDAO productDao;
	
	//결과확인 http://localhost:9095
	//또는 http://localhost:9095/product/list
	
	@RequestMapping("/list")
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("product/list");
		mav.addObject("list", productDao.list());
		return mav;
	}//list() end
	
	@GetMapping("/write")
	public String write() {
		return "product/write";
	}//write() end
	
	
	@PostMapping("/insert")
	public String insert(@RequestParam Map<String, Object> map,
						 @RequestParam MultipartFile img,
						 HttpServletRequest req) {
		//매개변수가 Map이면 name이 key로 저장된다
		//예)<input type="text" name="product_name">
		//System.out.println(map);
		//System.out.println(map.get("product_name"));
		//System.out.println(map.get("price"));
		//System.out.println(map.get("description"));
		
		//주의사항 : 파일업로드 할 때 리네임 되지 않음
		//업로드된 파일은 /storage 폴더에 저장
		
		String filename = "-";
		long filesize = 0;
		if(img != null && !img.isEmpty()) { //파일이 존재한다면
			filename = img.getOriginalFilename();
			filesize = img.getSize();
			try {
				ServletContext application = req.getSession().getServletContext();
				String path = application.getRealPath("/storage"); //실제 물리적인 경로
				img.transferTo(new File(path + "\\" + filename)); //파일 저장
			} catch (Exception e) {
				System.out.println(e);
			}//try end
		}//if end
		
		map.put("filename", filename);
		map.put("filesize", filesize);
		
		productDao.insert(map);
		
		return "redirect:/product/list";
		
	}//insert() end
	
	
	@GetMapping("/search")
	public ModelAndView search(@RequestParam(defaultValue = "") String product_name) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("product/list");
		mav.addObject("list", productDao.search(product_name));
		mav.addObject("product_name", product_name); //검색어
		return mav;
	}//search() end
	
	
	/*@GetMapping("/detail")
	public ModelAndView detail(@RequestParam int product_code) {
		
	}//detail() end*/

	
	/*
		@GetMapping
		@PostMapping
		@PutMapping
		@DeleteMapping
		
		전송방식 : method = get | post | put | delete
	*/
	/*
		@RequestParam
		http://localhost:9095?aaa=bbb&ccc=ddd
	 
	 	@PathVariable
	 	http://localhost:9095/bbb/ddd
	*/
	/*
		//http://localhost:9095/product/detail?product_code=22
		@GetMapping("/detail")
		public ModelAndView detail(@RequestParam int product_code) {}//detail() end
	*/
	//http://localhost:9095/product/detail/5
	@GetMapping("/detail/{product_code}")
	public ModelAndView detail(@PathVariable int product_code) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("product/detail");
		mav.addObject("product", productDao.detail(product_code));
		return mav;
	}//detail() end
	
	
	@PostMapping("/update")
	public String update(@RequestParam Map<String, Object> map,
						 @RequestParam MultipartFile img,
						 HttpServletRequest req) {
		//매개변수가 Map이면 name이 key로 저장된다
		//예)<input type="text" name="product_name">
		//System.out.println(map);
		//System.out.println(map.get("product_name"));
		//System.out.println(map.get("price"));
		//System.out.println(map.get("description"));
		
		//주의사항 : 파일업로드 할 때 리네임 되지 않음
		//업로드된 파일은 /storage 폴더에 저장
		
		String filename = "-";
		long filesize = 0;
		if(img != null && !img.isEmpty()) { //파일이 존재한다면
			filename = img.getOriginalFilename();
			filesize = img.getSize();
			try {
				ServletContext application = req.getSession().getServletContext();
				String path = application.getRealPath("/storage"); //실제 물리적인 경로
				img.transferTo(new File(path + "\\" + filename)); //파일 저장
			} catch (Exception e) {
				System.out.println(e);
			}//try end
		}//if end
		
		map.put("filename", filename);
		map.put("filesize", filesize);
		
		productDao.update(map);
		
		return "redirect:/product/list";
		
	}//update() end
	
	@PostMapping("/delete")
	public String delete(@RequestParam Map<String, Object> map,
			 		   @RequestParam MultipartFile img,
			 		   HttpServletRequest req) {
		productDao.delete(map);
		
		return "redirect:/product/list";
	}//delete() end
	
}//class end
