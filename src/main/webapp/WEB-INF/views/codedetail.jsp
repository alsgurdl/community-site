<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>게시판 글쓰기</title>




    <style>


        .form-container {
            width: 500px;
            margin: auto;
            padding: 20px;
            background-color: #000080;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            border-radius: 4px;
            font-size: 18px;
        }

        .form-container h1 {
            font-size: 40px;
            font-weight: 700;
            letter-spacing: 10px;
            text-align: center;
            margin-bottom: 20px;
            color: #ffffff;
        }

        .form-container h2 {
            font-size: 30px;
            color: white;
            text-align: center;
            margin-bottom: 20px;
        }

        label {
            display: block;
            margin-bottom: 5px;
            font-size: 20px;
            color: white;
        }
h2{
    color: white;
}
        #title, #programming {
            font-size: 18px;
            width: 100%;
            padding: 8px;
            box-sizing: border-box;
            border: 2px solid #ffffff;
            border-radius: 8px;
            margin-bottom: 10px;
            background-color: rgba(255, 255, 255, 0.8);
        }

        #content {
            height: 400px;
            font-size: 18px;
            width: 100%;
            padding: 8px;
            box-sizing: border-box;
            border: 2px solid #ffffff;
            border-radius: 8px;
            margin-bottom: 10px;
            background-color: rgba(255, 255, 255, 0.8);
        }

        textarea {
            resize: none;
            height: 200px;
        }

        .buttons {
            display: flex;
            justify-content: flex-end;
            margin-top: 20px;
        }

        button {
            font-size: 20px;
            padding: 10px 20px;
            border: none;
            margin-right: 10px;
            background-color: #4CAF50;
            color: white;
            cursor: pointer;
            border-radius: 4px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            transition: background-color 0.3s;
        }

        button.list-btn {
            background: #e61e8c;
        }

        button:hover {
            background-color: #3d8b40;
        }

        button.list-btn:hover {
            background: #e61e8c93;
        }

        /* 페이지 css */
        /* 페이지 액티브 기능 */
        .pagination .page-item.p-active a {
            background: #333 !important;
            color: #fff !important;
            cursor: default;
            pointer-events: none;
        }

        .pagination .page-item:hover a {
            background: #888 !important;
            color: #fff !important;
        }

        /* 댓글 프로필 */
        .profile-box {
            width: 70px;
            height: 70px;
            border-radius: 50%;
            overflow: hidden;
            margin: 10px auto;
        }

        .profile-box img {
            width: 100%;
        }

        .reply-profile {
            width: 30px;
            height: 30px;
            border-radius: 50%;
            margin-right: 10px;

        }

        
    </style>
</head>
<body>



<div id="wrap" class="form-container">
    
    <h2># 작성일자: ${b.regDate}</h2>
    <input type="text" id="programming" name="programming" value="${b.programming}" readonly>
    
    <label for="title">제목</label>
    <input type="text" id="title" name="title" value="${b.title}" readonly>

    <label for="content">내용</label>
    <div id="content">${b.content}</div>
    <div class="buttons">
        <button class="list-btn" type="button"
                onclick="location.href='/wel/myCode'">
            목록
        </button>
        <button  type="button"
                onclick="location.href='/wel/edit/${b.codeNo}'">
            수정
        </button>
       


    </div>




   
    </div>




</div>

    <script>






    </script>



</body>
</html>