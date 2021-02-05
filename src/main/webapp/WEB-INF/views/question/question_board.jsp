<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/board.css">

	<section>
		<div class="container">
			<div class="row">
				<div class="notice-title">
					<h2>고객센터</h2>
					<hr>
				</div>
	
				<div class="notice-wrap">
					<form action="boardList">
						<div class="search-wrap">
							<button type="submit" class="btn btn-info search-btn">검색</button>
	
							<input type="text" class="form-control search-input"> <select
								class="form-control search-select">
								<option>제목</option>
								<option>내용</option>
								<option>작성자</option>
							</select>
						</div>
	
					</form>
					<div class="notice-table">
						<table class="board-table table table-bordered table table-hover">
							<thead>
								<tr>
									<th>번호</th>
									<th>제목</th>
									<th>작성자</th>
									<th>등록일</th>
									<th>조회수</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>1</td>
									<td><a href="#">공지사항1</a></td>
									<td>관리자</td>
									<td>2021-01-12</td>
									<td>555</td>
								</tr>
								<tr>
									<td>1</td>
									<td><a href="#">공지사항1</a></td>
									<td>관리자</td>
									<td>2021-01-12</td>
									<td>555</td>
								</tr>
								<tr>
									<td>1</td>
									<td><a href="#">공지사항1</a></td>
									<td>관리자</td>
									<td>2021-01-12</td>
									<td>555</td>
								</tr>
								<tr>
									<td>1</td>
									<td><a href="#">공지사항1</a></td>
									<td>관리자</td>
									<td>2021-01-12</td>
									<td>555</td>
								</tr>
								<tr>
									<td>1</td>
									<td><a href="#">공지사항1</a></td>
									<td>관리자</td>
									<td>2021-01-12</td>
									<td>555</td>
								</tr>
								<tr>
									<td>1</td>
									<td><a href="#">공지사항1</a></td>
									<td>관리자</td>
									<td>2021-01-12</td>
									<td>555</td>
								</tr>
								<tr>
									<td>1</td>
									<td><a href="#">공지사항1</a></td>
									<td>관리자</td>
									<td>2021-01-12</td>
									<td>555</td>
								</tr>
								<tr>
									<td>1</td>
									<td><a href="#">공지사항1</a></td>
									<td>관리자</td>
									<td>2021-01-12</td>
									<td>555</td>
								</tr>
								<tr>
									<td>1</td>
									<td><a href="#">공지사항1</a></td>
									<td>관리자</td>
									<td>2021-01-12</td>
									<td>555</td>
								</tr>
								<tr>
									<td>1</td>
									<td><a href="#">공지사항1</a></td>
									<td>관리자</td>
									<td>2021-01-12</td>
									<td>555</td>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="write-button">
						<button type="button" class="btn btn-info btn-sm">게시글작성</button>
					</div>
	
					<div class="paging">
						<ul class="pagination">
							<li><a href="#">이전</a></li>
							<li class="active"><a href="#">1</a></li>
							<li><a href="#">2</a></li>
							<li><a href="#">3</a></li>
							<li><a href="#">다음</a></li>
						</ul>
					</div>
				</div>
	
			</div>
		</div>
	</section>