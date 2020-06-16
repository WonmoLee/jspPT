<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <title>반응형 웹연습</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
</head>
<body>
<div>
	
</div>
<br>
<br>
<div class="container">
    <div class="row">
        <div class="col-sm-3">
        	<h1>팀목록</h1>
        	<table class="table table-dark table-striped">
		    <thead>
		      <tr>
		        <th>번호</th>
		        <th>팀명</th>
		      </tr>
		    </thead>
		    <tbody id="sc__tbody1">
		    <c:forEach var="scTeam" items="${scTeams}">
		      <tr id="scTeam-${scTeam.id}">
		        <td>${scTeam.id}</td>
		        <td style="cursor:pointer" onclick="playerList('${scTeam.teamname}')" >${scTeam.teamname}</td>
		      </tr>
		      </c:forEach>
		    </tbody>
		  </table>
        
        </div>
        <div class="col-sm-4">
        	<h1>선수목록</h1>
        	<table class="table table-dark table-striped">
		    <thead>
		      <tr>
		        <th>선수 번호</th>
		        <th>선수 이름</th>
		      </tr>
		    </thead>
		    <tbody id="scplayer__list">
		    </tbody>
		  </table>
        
        </div>
        <div class="col-sm-5">
        	<h1>선수 상세정보</h1>
        	<table class="table table-dark table-striped">
		    <thead>
		      <tr>
		        <th>선수 등번호</th>
		        <th>선수 이름</th>
		        <th>선수 포지션</th>
		      </tr>
		    </thead>
		    <tbody id="scplayer__info">
		    </tbody>
		  </table>
        
        </div>
    </div>
</div>
</body>
<script>


	function playerList(teamname) {
		console.log("playerList실행");
		$.ajax({

			type: "GET",
			url: "/practice2/scplayers?cmd=playerList&teamName=" + teamname,
			contentType: "application/x-www-form-urlencoded; charset=UTF-8",
			dataType: "json"
			
		}).done((result)=>{
			console.log(result);
				$("#scplayer__list").empty();
				for (let scplayer of result) {
					var string =
						"<tr>\r\n" +
						"	<td>" +scplayer.back_num+"</td>\r\n" +
						"	<td onclick=\"playerInfo('"+scplayer.back_num+"','"+teamname+"' )\"style=\"cursor: pointer;\">"+scplayer.name+"</td>\r\n" +
						"</tr>"
						
						$("#scplayer__list").append(string);
				}
				
				$("#scplayer__info").empty();
				
		}).fail((error)=>{
			alert("업데이트 실패");
		});
	}

	function playerInfo(back_num, teamname) {
		console.log("selectPrice실행");
		$.ajax({

			type: "GET",
			url: "/practice2/scplayers?cmd=playerInfo&teamName=" + teamname + "&back_num=" + back_num,
			contentType: "application/x-www-form-urlencoded; charset=UTF-8", 
			dataType: "json"
			
		}).done((result)=>{
			console.log(result);
				$("#scplayer__info").empty();
				//포이치 버전 1
// 				for ( var scplayerin of result) {
// 					var json = 
// 						"<tr>\r\n" +
// 						"	<td>"+scplayerin.back_num+"</td>\r\n" +
// 						"	<td>"+scplayerin.name+"</td>\r\n" +
// 						"	<td>"+scplayerin.position+"</td>\r\n" +
// 						"</tr>"
						
// 						$("#scplayer__info").append(json);
// 				}
				
				//포이치 버전 2 (ECMA 6버전임)
				result.forEach(scplayerin =>{
				
				var json = 
						"<tr>\r\n" +
						"	<td>"+scplayerin.back_num+"</td>\r\n" +
						"	<td>"+scplayerin.name+"</td>\r\n" +
						"	<td>"+scplayerin.position+"</td>\r\n" +
						"</tr>"
					
						$("#scplayer__info").append(json);
				
				});
				
		}).fail((error)=>{
			alert("오류발생");
		});
	}

</script>
</html>