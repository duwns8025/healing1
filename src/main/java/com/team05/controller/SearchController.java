package com.team05.controller;

import java.io.File;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpSession;

import org.rosuda.REngine.Rserve.RConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.team05.command.ProductVO;
import com.team05.command.ReservationVO;
import com.team05.command.ReviewVO;
import com.team05.command.Review_imgVO;
import com.team05.command.RoomVO;
import com.team05.command.UserVO;
import com.team05.command.util.Criteria;
import com.team05.command.util.Room_infoVO;
import com.team05.command.util.SearchAreaVO;
import com.team05.command.util.SearchNameVO;
import com.team05.search.service.SearchService;

@Controller
@RequestMapping("search")
public class SearchController {
	
	@Autowired
	SearchService searchService;
	
	@RequestMapping(value = "reservation",method=RequestMethod.POST)
	public String reservation(Room_infoVO infovo,Model model) {
		System.out.println(infovo.toString());
		
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH");
		Date date1 = new Date();
		Date date2 = new Date();
		if(infovo.getCheckin()== null) {
			date1.setHours(infovo.getTime1());
			date2.setHours(infovo.getTime1()+infovo.getTime2());			
		}else {
			int checkin_time=Integer.parseInt(infovo.getCheckin().split(":")[0]);
			int checkout_time=Integer.parseInt(infovo.getCheckout().split(":")[0]);
			date1.setHours(checkin_time);
			date2.setDate(date1.getDate()+1);
			date2.setHours(checkout_time);
		}
		String checkin=format1.format(date1);
		String checkout = format1.format(date2);
		infovo.setCheckin(checkin);
		infovo.setCheckout(checkout);
		
	
		
		String pro_title=searchService.getproductTitle(infovo.getPro_no());
		infovo.setPro_title(pro_title);
		model.addAttribute("infovo", infovo);
		System.out.println(infovo.toString());

		return "search/reservation";
	}
	@RequestMapping("reservationForm")
	public String reservationForm(ReservationVO vo,RedirectAttributes ra) {
		int result =searchService.reservationForm(vo);
		if(result ==1) {
			ra.addFlashAttribute("msg", "예약이 완료되었습니다");
		}else {
			ra.addFlashAttribute("msg", "예약 실패!, 관리자에게 문의하세요");
		}
		return "redirect:/";
	}
	
	@RequestMapping(value = "room_info",method = RequestMethod.GET)
	public String room_info(@RequestParam("pro_no") int pro_no,Model model) {
		ArrayList<RoomVO> roomlist=searchService.getroom(pro_no);
		
		String address=searchService.getaddress(pro_no);
		String protitle=searchService.gettitle(pro_no);
		
		
		model.addAttribute("address", address);
		model.addAttribute("protitle", protitle);
		model.addAttribute("roomlist", roomlist);
		return "search/room_info";
	}
	
	

	@RequestMapping("search_area")
	public String search_area(SearchAreaVO searchvo,Model model) {
		
		ArrayList<ProductVO> productlist=searchService.getlist(searchvo);
		model.addAttribute("productlist", productlist);
		model.addAttribute("searchvo", searchvo);
		
		
		return "search/search_area";
	}

	//파일 불러오기 
	@ResponseBody
	@RequestMapping("display/{fileloca}/{filename:.+}")
	public ResponseEntity<byte[]> display(@PathVariable("fileloca") String fileloca,
											@PathVariable("filename") String filename){
		
		//각자 컴퓨터에 맞는 경로로 바꿀 것
		String uploadpath = "/var/upload/"+fileloca;
		File file = new File(uploadpath+"/"+filename);
		
		ResponseEntity<byte[]> result = null;
		try {
			HttpHeaders header = new HttpHeaders();
			header.add("Content-Type", Files.probeContentType(file.toPath()));
			byte[] arr = FileCopyUtils.copyToByteArray(file);
			result = new ResponseEntity<byte[]>(arr,header,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	@RequestMapping("search_room")
	public String search_room(SearchNameVO searchNameVO,Model model) {
		System.out.println(searchNameVO.toString());
		
		ArrayList<ProductVO> productlist=searchService.searchname(searchNameVO);
		model.addAttribute("productlist", productlist);
		model.addAttribute("searchNameVO",searchNameVO);
		return "search/search_room";
	}
	
	@RequestMapping("select_area")
	public String select_area() {
		return "search/select_area";
	}
	
	
	//파일 업로드 
	@ResponseBody
	@RequestMapping("upload")
	public String upload(@RequestParam(value="file",required = false) MultipartFile file,
						@RequestParam("pro_no") int pro_no,
						@RequestParam("score") int score,
						@RequestParam("title") String title,
						@RequestParam("content") String content,HttpSession session) {
		try {
			
			UserVO uservo=(UserVO)session.getAttribute("userVO");
			String writer=uservo.getUserId();
			
			ReviewVO vo = null;
			if(file !=null) {
				
				Date date = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				String fileloca=sdf.format(date);
				
				String uploadpath = "/var/upload/"+fileloca;
				File folder = new File(uploadpath);
				if(!folder.exists()) { 
					folder.mkdir(); 
				}
				
				
				String filerealname=file.getOriginalFilename();
				long size=file.getSize();
				String fileExtension=filerealname.substring(filerealname.lastIndexOf("."),filerealname.length());
				
				UUID uuid=UUID.randomUUID();
				String uuids=uuid.toString().replaceAll("-", "");
				
				String filename = uuids+fileExtension; 
				
				File saveFile = new File(uploadpath+"/"+filename);
				file.transferTo(saveFile); 
				
				vo = new ReviewVO(0,writer,pro_no,score,content,title,uploadpath,filename,filerealname,fileloca,null,null);
					
			}else {
				vo = new ReviewVO(0,writer,pro_no,score,content,title,null,null,null,null,null,null);
			}
			boolean result = searchService.insertReview(vo);
			
			if(result) {
				return "success";
			}else {
				return "fail";
			}
		} catch (NullPointerException e) {
			
			return "fail";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
		
		
	}
	
	@ResponseBody
	@RequestMapping("getreview/{pro_no}/{pageNum}")
	public HashMap<String, Object> getreview(@PathVariable("pro_no") int pro_no,
										@PathVariable("pageNum") int pageNum) {
		System.out.println(pro_no);
		Criteria cri = new Criteria(pageNum,10);
		HashMap<String, Object> map = new HashMap<String, Object>();
		//리뷰개수
		int count=searchService.reviewtotal(pro_no);
		double mean =0.0;
		if(count > 0) {
			//리뷰점수합
			int total=searchService.reviewtotalSum(pro_no);
			mean = (double)total /count;			
		}
		
		
		map.put("count", count);
		map.put("mean",mean);
		map.put("list",searchService.getreview(cri,pro_no));
		return map;
	}
	
	@ResponseBody
	@RequestMapping("reviewcountMean/{pro_no}")
	public HashMap<String, Object> reviewcountMean(@PathVariable("pro_no") int pro_no){
		HashMap<String, Object> map = new HashMap<String, Object>();
		//리뷰개수
		int count=searchService.reviewtotal(pro_no);
		if(count == 0) {		
			map.put("count", 0);
			map.put("mean", 0);
			return map;
		}
		//리뷰점수합
		int total=searchService.reviewtotalSum(pro_no);
		
		double mean = (double)total /count;
		
		map.put("count", count);
		map.put("mean", mean);
		return map;
	}
	
	
	@RequestMapping("byagegroup")
	public String agePopul(Model model) {
		model.addAttribute("twentylist",searchService.twentyProductList());
		model.addAttribute("thirtylist",searchService.thirtyProductList());
		model.addAttribute("fortylist",searchService.fortyProductList());

		return "search/byagegroup";
		
	}
	
	@RequestMapping("trip")
	public String trip(RedirectAttributes ra) {
		ra.addFlashAttribute("msg", "페이지 준비중입니다.");
		return "redirect:/";
	}
	
}
