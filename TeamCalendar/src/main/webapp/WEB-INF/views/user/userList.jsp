<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">

<title>全体 掲示板 Page</title>
<script src="resources/js/jquery-3.3.1.js"></script>
<script>

		$(function() {
			$(".insertMember").on('click', saveId);
		});

		function saveId(){
			console.log("saveID실행");
			var email = $(this).attr("data-val");
			
			$.ajax({
				method : 'get'
				,url : 'saveId'
				,data : {'email' : email}
				,success : showId
				,error : function(resp) {
					console.log(resp);
				}
			});

		}

		function showId(resp){
			var show = '<table>';
			$.each(resp,function(index, item) {
			show += '<td class="name" style="width:200px;">' + item.name + '</td>';
			show += '<td class="email" style="width:70%;">'	+ item.email + '</td>';
			show += '<td style="width:100px;"><button type="button" class="delBtn" email-sno="'+item.email+'">削除</button></td>';
			show += '</tr>';
							
							});
			show += '</table>';
			$("#print_invite").empty();
			$("#print_invite").html(show);
			$(".delBtn").on('click', idDelete);
		}

		//会員をリストから削除
		function idDelete(){
			
			var email = $(this).attr("email-sno");
			$.ajax({
				method : 'get'
				,url : 'updateId'
				,data : {'email' : email}
				,success : showId
				,error : function(resp) {
					console.log(resp);
				}
			});

		}
		
		function pagingFormSubmit(currentPage) {					
			var form = document.getElementById('pagingForm');				
			var page = document.getElementById('page');				
			page.value = currentPage;				
			form.submit();
		}
		
		
		
		
</script>						
</head>
<body id="page-top">

  <!-- Page Wrapper -->
  <div id="wrapper">

    <!-- Sidebar -->
    <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

      <!-- Sidebar - Brand -->
      <a class="sidebar-brand d-flex align-items-center justify-content-center" href="#" style="cursor: auto;">
        <div class="sidebar-brand-icon rotate-n-15">
          <i class="far fa-laugh-squint"></i>
        </div>
        <div class="sidebar-brand-text mx-3" style="font-size: 20px;">チーム 掲示板</div>
      </a>

      <!-- Divider -->
      <hr class="sidebar-divider my-0">

      <!-- Nav Item - Dashboard -->
      <li class="nav-item">
        <a class="nav-link" href="goGroupBoard">
          <i class="fas fa-book"></i>
          <span style="font-size: 15px;">掲示板 ホーム(Team${sessionScope.groupBoardNum})</span></a>
      </li>

      <!-- Divider -->
      <hr class="sidebar-divider">

      <!-- Heading -->
      <div class="sidebar-heading">
        MENU
      </div>

      <!-- Nav Item - Pages Collapse Menu -->
      <li class="nav-item active" id="go_groupBoard">
        <a class="nav-link collapsed" href="groupboard_list">
          <i class="fas fa-fw fa-folder"></i>
          <span>資料共有掲示板</span>
        </a>
      </li>

      <!-- Nav Item - Charts -->
      <li class="nav-item">
        <a class="nav-link" href="thesisWrite">
          <i class="fas fa-book-open"></i>
          <span>論文作成</span></a>
      </li>

      <!-- Nav Item - Tables -->
      <li class="nav-item">
        <a class="nav-link" href="goGroupBoardCalendar">
          <i class="fas fa-calendar-alt"></i>
          <span>スケジュール</span></a>
      </li>

      <!-- Divider -->
      <hr class="sidebar-divider d-none d-md-block">
    </ul>
    <!-- End of Sidebar -->

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

      <!-- Main Content -->
      <div id="content">

          <!-- Topbar Navbar -->
          <ul class="navbar-nav ml-auto">
          <li class="nav-item dropdown no-arrow mx-1">
							<div class="dropdown-toggle" data-toggle="dropdown"
								class="dropdown" id="chatForm">
								<a style="font-size: 25px; color: white;"> <i
									class="fas fa-comments" style="color: gray;"></i>
								</a>
							</div>
						</li>
						<li style="width:10px;"></li>
            <li class="nav-item dropdown no-arrow mx-1">
							<div class="card-body" id="print_invite"></div>
						</li>
           <li class="nav-item dropdown no-arrow mx-1"><a href="../"
							style="font-size: 25px; color: white;"><i class="fas fa-home"
								style="color: gray;"></i></a></li>
						<div class="topbar-divider d-none d-sm-block"></div>
						<li class="nav-item dropdown no-arrow"><span
							class="mr-2 d-none d-lg-inline text-gray-600 small"
							style="font-size: 20px;">${sessionScope.loginName} 様</span></li>
    
              	</i>
              </a>   
    			
            </li>

          </ul>

        </nav>
        <!-- End of Topbar -->

					<div class="container-fluid">
						<div class="d-sm-flex align-items-center justify-content-between mb-4">
						<h1 class="h3 mb-0 text-gray-800" style="font-weight: bold;">社員帳票</h1>
						<button type="button" id="writeBoard" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm">
							<i class="fas fa-pencil-alt"></i> 投稿する
						</button>
					</div>
					<hr class="sidebar-divider d-none d-md-block">

						<div class="card shadow mb-4">
							<div class="card-body">
								<div class="table-responsive" id="editor1">
									<table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
											<tr>
												<th style="width:100px;">追加</th>
												<th style="width:100px;">email</th>
												<th style="width:180px">name</th>
												<th style="width:100px;">division</th>
												<th style="width:180px;">indate</th>
											</tr>
											<c:if test="${userList == null}">
											<tr><td colspan="5" style="font-size:20px;text-align:center;">登録された文がありません。</td></tr>
											</c:if>
										<c:if test="${userList != null}">
											
											<c:forEach var="board" items="${userList}" varStatus="status">
												<tr>
													<td style="style="color: blue; cursor: pointer;">
													<div class="insertMember" style="color: blue; cursor: pointer;" data-val="${board.email}">
													追加</a>
													</div>										
													</td>
													<td style="text-align:center; vertical-align:middle;">${board.email}</td>
													<td class="id" style="text-align:center; vertical-align:middle;">${board.name}</td>
													<td class="groupBoardHitcount" style="text-align:center; vertical-align:middle;">${board.division}</td>
													<td class="groupBoardDate" style="text-align:center; vertical-align:middle;">${board.indate}</td>
												</tr>			
											</c:forEach>
											</c:if>
									</table>
									<form　class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search" action="groupboard_list" method="get">
											<div class="input-group">
											 <div class="form-group">
										      <select name="keyField" class="form-control">
										        <option value="groupBoardTitle" selected>題目</option>
												<option value="groupBoardId">著者</option>
												<option value="groupBoardContent">内容</option>
										      </select>
										      </div>
										      <div style="width:10px;"></div>
												<input type="text" class="form-control bg-light border-0 small"
													placeholder="Search for..." aria-label="Search"
													aria-describedby="basic-addon2" name="keyWord" id="keyWord" value="${keyWord}">
												<div class="input-group-append">
													<button type="submit">
														ボタン
													</button>
												</div>
											</div>
										</form>
									<div id="paging">
									<nav aria-label="Page navigation example">
									  <ul class="pagination justify-content-center">
									    <li class="page-item">
									      <a class="page-link" href="groupboard_list?page=${navi.currentPage - 1}" aria-label="Previous">
									        <span aria-hidden="true">&laquo;</span>
									        <span class="sr-only">Previous</span>
									      </a>
									    </li>
									    
									    <c:forEach var="page" begin="${navi.startPageGroup}" end="${navi.endPageGroup}">

											<c:if test="${navi.currentPage == page}">
												<li class="page-item"><a class="page-link" href="groupboard_list?page=${page}">${page}</a></li>
											</c:if>


											<c:if test="${navi.currentPage != page}">
												 <li class="page-item"><a class="page-link" href="groupboard_list?page=${page}">${page}</a></li>
											</c:if>

										</c:forEach>
									    <li class="page-item">
									      <a class="page-link" href="groupboard_list?page=${navi.currentPage+1}" aria-label="Next">
									        <span aria-hidden="true">&raquo;</span>
									        <span class="sr-only">Next</span>
									      </a>
									    </li>
									  </ul>
									</nav>
									</div>
									
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

</body>