<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/board.css">
	
	
	<section>
		<div class="container">
			<div class="board-detail">
				<div class="board-title">
					<p>질문내용</p>
	
				</div>
				<div class="detail-body">
					<div class="box">
						<div class="first">
							<span><input type="text" placeholder="첫번쨰 글"></span> <strong>num
								: 1</strong>
						</div>
						<div class="second">
							<span>조회수<strong>23</strong></span> <span>작성일:<strong>2021-01-19</strong></span>
							<span>작성자:<strong>홍길동</strong></span>
						</div>
						<div class="third">
							<div class="content">내용</div>
							<div class="content-detail">
								<textarea>동해물과 백두산이 마르고 닳도록</textarea>
							</div>
						</div>
	
					</div>
					<div class="button">
						<button type="button" class="btn btn-danger btn-lage disabled">삭제</button>
						<button type="button" class="btn btn-success btn-large disabled">수정</button>
						<button type="button" class="btn btn-info btn-large">목록</button>
	
					</div>
	
				</div>
				<div class="detail-reply">
					<div class="reply-box">
						<span><textarea placeholder="답변작성공간"></textarea></span>
						<button type="button" class="reply-btn btn btn-info">등록</button>
					</div>
				</div>
				<div class="reply-answer">
					<div class="answer-box">
						<div class="reply-first">
							<span>관리자아이디</span> <span>2021-01-19</span> <span>16:23</span>
						</div>
						<div class="reply-second">질문에 대한 답변 내용은 ~~~~입니다</div>
					</div>
				</div>
	
			</div>
		</div>
	</section>
	
	
	
	
	<div class="end"></div>