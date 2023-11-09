package kr.co.mymelon.media;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.UploadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsFileUploadSupport;
import org.springframework.web.servlet.ModelAndView;

import net.utility.UploadSaveManager;

@Controller
public class MediaCont {
	
	//이미 자동으로 만들어진 객체이므로 autowired로 불러옴
	@Autowired
	MediaDAO dao;

	public MediaCont() {
		System.out.println("-----MediaCont() 객체 생성됨");
	}//end
	
	@RequestMapping("/media/list.do")
	public ModelAndView list(int mediagroupno) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("media/list");
		mav.addObject("list", dao.list(mediagroupno));
		mav.addObject("mediagroupno", mediagroupno);
		return mav;
	}//list() end
	
	@GetMapping("/media/create.do")
	public ModelAndView createForm(int mediagroupno) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("media/createForm");
		mav.addObject("mediagroupno", mediagroupno);
		return mav;
	}//createForm() end
	
	@PostMapping("media/create.do")
	public ModelAndView createProc(@ModelAttribute MediaDTO dto, HttpServletRequest req) {
					  //createProc(String title, MultipartFile posterMF, MultipartFile filenameMF)
		ModelAndView mav = new ModelAndView();
		mav.setViewName("media/msgView");
		
		//////////////////////////////////////////
		/*
		MultipartFile posterMF   = dto.getPosterMF();
		MultipartFile filenameMF = dto.getFilenameMF();
		System.out.println(posterMF.getOriginalFilename());
		System.out.println(posterMF.getSize());
		System.out.println(filenameMF.getOriginalFilename());
		System.out.println(filenameMF.getSize());
		*/
		//첨부된 파일 처리
		//-> 실제 파일은 /storage 폴더에 저장
		//-> 저장된 파일 관련 정보는 media 테이블에 저장
		
		//파일 저장 폴더의 실제 물리적인 경로 가져오기
		ServletContext application = req.getServletContext();
		String basePath = application.getRealPath("/storage");
		
		//1) <input type='file' name='posterMF'>
		MultipartFile posterMF = dto.getPosterMF(); //파일 가져오기
		// /storage 폴더에 첨부된 파일 저장하고, 리네임된 파일명 반환
		String poster = UploadSaveManager.saveFileSpring30(posterMF, basePath);
		dto.setPoster(poster); //리네임된 파일명을 dto 객체 담기
		
		//2) <input type='file' name='filenameMF'>
		MultipartFile filenameMF=dto.getFilenameMF();
		String filename=UploadSaveManager.saveFileSpring30(filenameMF, basePath);
		dto.setFilename(filename);
		dto.setFilesize(filenameMF.getSize());
		//////////////////////////////////////////////////////////////

		int cnt = dao.create(dto);
		if(cnt==0) {
			String msg1="<p>음원 등록 실패</p>";
			String img="<img src='../images/fail.png'>";
			String link1="<input type='button' value='다시시도' onclick='javascript:history.back()'>";
			String link2="<input type='button' value='목록으로' onclick='location.href=\"list.do?mediagroupno=" + dto.getMediagroupno() +"\"'>";
		
			mav.addObject("msg1", msg1);
			mav.addObject("img", img);
			mav.addObject("link1", link1);
			mav.addObject("link2", link2);
		
		}else {
			String msg1="<p>음원 등록 성공</p>";
			String img="<img src='../images/sound.png'>";
			String link2="<input type='button' value='목록으로' onclick='location.href=\"list.do?mediagroupno=" + dto.getMediagroupno() +"\"'>";
		
			mav.addObject("msg1", msg1);
			mav.addObject("img", img);
			mav.addObject("link2", link2);
		
		}//if end
		
		return mav;
	}//createProc() end
	
	
	@GetMapping("media/read.do")
	public ModelAndView read(int mediano) {
		ModelAndView mav = new ModelAndView();
		MediaDTO dto = dao.read(mediano);
		if(dto != null) {
			String filename = dto.getFilename(); //파일명 가져오기
			filename = filename.toLowerCase();	 //파일명 전부 소문자로 바꾸기
			if(filename.endsWith(".mp3")) {		 //마지막 문자열이 .mp3인지?
				mav.setViewName("media/readMP3");
			}else if(filename.endsWith(".mp4")) {//마지막 문자열이 .mp4인지?
				mav.setViewName("media/readMP4");
			}//if end
		}//if end
		
		mav.addObject("dto", dto);
		return mav;
		
	}//read() end
	
	
}//class end
