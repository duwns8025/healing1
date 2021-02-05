package com.team05.command.util;

import lombok.Data;

@Data //게터, 세터
public class PageVO {
	
	//화면에 페이지네이션을 그리는 클래스
	private int startPage;//페이지네이션 시작번호
	private int endPage; //페이지네이션 끝번호
	private boolean prev; //이전버튼활성화여부
	private boolean next; //다음버튼 활성화여부
	
	private int pageNum;//조회 페이지 번호
	private int amount;//데이터 개수(ex_10개씩보기)
	private int total;//전체게시글 수
	private Criteria cri; //★페이지 기준(번호, 데이터개수)
	
	//PageVO가 생성될 때 cri, total이 반드시 전달되야 하기 때문에 한개로 제한
	public PageVO(Criteria cri, int total) {
		//페이지번호, 데이터개수, 전체게시글 수는 전달되는 값에 따라서 초기화
		this.pageNum = cri.getPageNum();
		this.amount = cri.getAmount();
		this.total = total;
		this.cri = cri;
		
		//끝페이지 계산
		//사용자가 pageNum을 11번 눌렀다 -> 20번, pageNum을 13번 눌렀다 -> 20번
		this.endPage = (int) (Math.ceil(this.pageNum / 10.0) * 10);
		
		//끝페이지를 이용해서 처음페이지계산
		//끝페이지가 20이면 처음페이지는 11, 끝페이지 30이면 처음페이지21
		this.startPage = endPage - 10 + 1;
		
		//진짜 끝번호 계산
		//데이터가 60개 있다고 가정할 때, pageNum 5번 누르면 끝페이지 6번
		//데이터가 112개 있다고 가정할 때, pageNum 11번 누르면 끝페이지번호 12번
		int realEnd = (int) Math.ceil(this.total / (double)this.amount);
		
		//특정조건에서 endPage를 변경
		//ex_123개 게시글일 때
		//11번 클릭 -> endPage = 20, realEnd -> 13
		//12번 클릭 -> endPage -> 10, realEnd -> 13
		if(this.endPage > realEnd) {
			this.endPage = realEnd;
		}
		
		//이전버튼 활성화여부
		//startPage 1, 11, 21, 31이기 때문에 1초과면 활성화
		this.prev = startPage > 1;
		
		//다음버튼 활성화 여부
		//조건 - 진짜 끝번호가 페이지번호보다 큰경우 활성화
		this.next = realEnd > this.endPage;
		
	}
	
	
	
	
	
}





































