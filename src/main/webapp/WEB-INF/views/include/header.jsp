<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- header start-->

<header>
    <div class="inner-header">
        <h1 class="logo">
            <a href="/home/main">
                <img src="/assets/img/logo.png" alt="로고이미지">
            </a>
        </h1>

        <!-- 프로필 사진 -->
        <div class="profile-box">
            <c:if test="${login == null || login.profile == null}">
                <img src="/assets/img/jjanggu.jpg" alt="프사">
            </c:if>

            <c:if test="${login != null && login.profile != null}">
                <c:choose>
                    <c:when test="${login.loginMethod == COMMON}">
                        <img src="/display${login.profile}" alt="프사">
                    </c:when>
                    <c:otherwise>
                        <img src="${login.profile}" alt="프사">
                    </c:otherwise>
                </c:choose>
            </c:if>
        </div>


        <h2 class="intro-text">
            Welcome ${sessionScope.login == null ? '' : login.name}
        </h2>
        <a href="#" class="menu-open">
            <span class="menu-txt">MENU</span>
            <span class="lnr lnr-menu"></span>
        </a>
    </div>

    <nav class="gnb">
        <a href="#" class="close">
            <button type="button">X</button>
        </a>
        <ul>
            <li><a href="/">Home</a></li>
            <li><a href="/home/main">Posts</a></li>
            <li><a href="/home/all">Diary</a></li>
            <li><a href="#">Gallery</a></li>
            <li><a href="#">Chat</a></li>
            <li><a href="#">Market</a></li>

            <c:if test="${login == null}">
                <li><a href="/users/sign-up">Sign Up</a></li>
                <li><a href="/users/sign-in">Sign In</a></li>
            </c:if>

            <c:if test="${sessionScope.login != null}">
                <li><a href="/users/mypage">My Page</a></li>
                <li><a href="/users/sign-out">Sign Out</a></li>
            </c:if>

        </ul>
    </nav>

</header>
<!-- Header END -->

<script>
    const $profileBox = document.querySelector('.profile-box');

    $profileBox.onclick = e => {
        location.href = '/display/download${login.profile}';
    };

    //버튼의 요소 노드 취득
    const menuBtn = document.querySelector('header .menu-open');
    const closeBtn = document.querySelector('.gnb .close');

    const gnb = document.querySelector('.gnb');

    //클릭 이벤트 생성
    menuBtn.addEventListener('click', () => {
        gnb.classList.add('on');
    });

    closeBtn.addEventListener('click', () => {
        gnb.classList.remove('on');
    });
</script>