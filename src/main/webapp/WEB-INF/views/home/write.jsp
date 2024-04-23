<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>All page</title>

    <%@ include file="../include/static-head.jsp" %>

    <!-- header css -->
    <!-- <link rel="stylesheet" href="/assets/css/header.css" > -->
    <link rel="stylesheet" href="/assets/css/write.css" >
    <link rel="stylesheet" href="/assets/css/snb.css" >


</head>
<body>

  


  <!-- 사이드바 -->
  <%@ include file="../include/snb.jsp" %>

  <div class="wrapper">
    <section id="wrap" class="form-container">

      <h1>글쓰기</h1>

      <form action="/home/write" method="post" enctype="multipart/form-data">

        <!-- 카테고리 선택 영역 -->
        <select class="form-select" name="category" aria-label="Default select example">
          <option selected>게시판을 선택해 주세요</option>
          <option value="game">게임 게시판</option>
          <option value="market">중고 거래 게시판</option>
          <option value="movie">영화 게시판</option>
          <option value="trip">여행 게시판</option>
        </select>

          <!-- 제목 입력 영역 -->
          <div class="mb-3">
            <!-- <label for="exampleFormControlInput1" class="form-label"></label> -->
            <input type="text" name="title" class="form-control" id="exampleFormControlInput1" placeholder="제목을 입력해 주세요">
          </div>
          
          <br>

          <!-- 이미지 업로드 영역 -->
          <div class="mb-3">
            <!-- <label for="formFile" class="form-label">Default file input example</label> -->
            <input class="form-control" type="file" name="file" id="formFile" accept="image/*">
            <div class="upload-box">파일 첨부</div>
          </div>
            
          
          <!-- 내용 입력 영역 -->
          <div class="mb-3">
            <!-- <label for="exampleFormControlTextarea1" class="form-label"></label> -->
            <textarea class="form-control" name="content" id="exampleFormControlTextarea1" rows="20" placeholder="내용을 입력하세요"></textarea>
          </div>
          
          <!-- 게시물 쓰기 버튼 -->
          <div class="buttons">
              <button class="list-btn" type="button" onclick="window.location.href='/home/all'">목록</button>
              <button type="submit">글쓰기</button>
          </div>

          <!-- 공개 설정, 태그 입력 등 기타 선택 영역 -->


      </form>


    </section>
  </div>


  </div>

    <script>

      const $box = document.querySelector('.upload-box');
      const $input = document.getElementById('img-input');
      $box.onclick = e => {
        $input.click();
      };


    </script>



</body>

</html>