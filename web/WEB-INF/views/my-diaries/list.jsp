<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>



<!DOCTYPE html>
<html lang="en">
<head>
    <title>Hola!</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!--Font-->
    <link href="https://fonts.googleapis.com/css2?family=Raleway&display=swap" rel="stylesheet">

    <style>
        * { margin: 0; padding: 0;}
        body { font-family: sans-serif;}
        ol, li { list-style: none; }
        a { text-decoration: none;
            color:black;
        }
        img { border: 0;}
    </style>
    <style>
        .profileImg img{
            width: 200px;
            height: 200px;
            float: left;
            margin-right:10px;
        }

        /* diaries */
        .diaries {
            display: -moz-flex;
            display: -webkit-flex;
            display: -ms-flex;
            display: flex;
            -moz-flex-wrap: wrap;
            -webkit-flex-wrap: wrap;
            -ms-flex-wrap: wrap;
            flex-wrap: wrap;
            position: relative;
        }
        .diaries article {
            -moz-transition: -moz-transform 0.5s ease, opacity 0.5s ease;
            -webkit-transition: -webkit-transform 0.5s ease, opacity 0.5s ease;
            -ms-transition: -ms-transform 0.5s ease, opacity 0.5s ease;
            transition: transform 0.5s ease, opacity 0.5s ease;
            position: relative;
            width: calc(25% - 20px);

            /*width: calc(33.33333% - 20px);*/
            margin: 10px 10px 0 10px;
        }

        .diaries article > .image {
            -moz-transition: -moz-transform 0.5s ease;
            -webkit-transition: -webkit-transform 0.5s ease;
            -ms-transition: -ms-transform 0.5s ease;
            transition: transform 0.5s ease;
            position: relative;
            display: block;
            width: 100%;
            border-radius: 4px;
            overflow: hidden;
        }

        .diaries article > .image img {
            display: block;
            width: 100%;
        }

        .diaries article > .image:before {
            pointer-events: none;
            -moz-transition: background-color 0.5s ease, opacity 0.5s ease;
            -webkit-transition: background-color 0.5s ease, opacity 0.5s ease;
            -ms-transition: background-color 0.5s ease, opacity 0.5s ease;
            transition: background-color 0.5s ease, opacity 0.5s ease;
            content: '';
            display: block;
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            opacity: 1.0;
            z-index: 1;
            opacity: 0.8;
        }

        .diaries article > .image:after {
            pointer-events: none;
            -moz-transition: opacity 0.5s ease;
            -webkit-transition: opacity 0.5s ease;
            -ms-transition: opacity 0.5s ease;
            transition: opacity 0.5s ease;
            content: '';
            display: block;
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            /* background-image: url("data:image/svg+xml;charset=utf8,%3Csvg xmlns='http://www.w3.org/2000/svg' width='100' height='100' viewBox='0 0 100 100' preserveAspectRatio='none'%3E%3Cstyle%3Eline %7B stroke-width: 0.25px%3B stroke: %23ffffff%3B %7D%3C/style%3E%3Cline x1='0' y1='0' x2='100' y2='100' /%3E%3Cline x1='100' y1='0' x2='0' y2='100' /%3E%3C/svg%3E"); */
            background-position: center;
            background-repeat: no-repeat;
            background-size: 100% 100%;
            opacity: 0.25;
            z-index: 2;
        }


    </style>
    <style>
        #wrapper {
            display: -moz-flex;
            display: -webkit-flex;
            display: -ms-flex;
            display: flex;
            -moz-flex-direction: row-reverse;
            -webkit-flex-direction: row-reverse;
            -ms-flex-direction: row-reverse;
            flex-direction: row-reverse;
            min-height: 100vh;
        }
        #main {
            -moz-flex-grow: 1;
            -webkit-flex-grow: 1;
            -ms-flex-grow: 1;
            flex-grow: 1;
            -moz-flex-shrink: 1;
            -webkit-flex-shrink: 1;
            -ms-flex-shrink: 1;
            flex-shrink: 1;
            width: 100%;
        }
        #main > .inner {
            padding: 50px 30px 10px 50px;
            margin: 0 auto;
            max-width: 110em;
        }
        #header {
            position: static;
            padding: 0 5px 5px 5px;
            border-bottom: solid 5px #f56a6a;
        }
        #header ul {
            float: right;
        }
        #banner {
            margin-top: 20px;
            padding: 0 5px 5px 5px;
            border-bottom: solid 2px #DCDCDC;
        }
        #profileText > h1 {
            padding: 165px 5px 20px 0;
        }
    </style>

</head>
<body>
<div id="wrapper">
    <dif id="main">
        <!-- 왼쪽 네비게이션 위치를 뺌-->
        <div class="inner">

            <!-- header -->
            <header id="header">
                <a href="#">Hola!</a>
                <ul>
                    <li><a href="#">Mypage</a></li>
                </ul>
            </header>

            <!-- Banner -->
            <section id="banner">
                <span class="profileImg"><img src="/resources/mydiary/images/pic10.jpg" alt="그림아직지정못함"></span>
                <span>
                        <header id="profileText">
                            <h1>User Message</h1>
                        </header>
                    </span>
            </section>

            <!-- diaries : 다이어리 사진들 보여주기-->
            <section class="diaries">

                <c:forEach items="${diaryList}" var="diary">
                    <article>
                                <span class="image">
                                    <img src="/resources/mydiary/images/IMG_1417.jpeg" alt=""/>
                                </span>
                    </article>
                </c:forEach>
            </section>
        </div>
    </dif>
</div>

<!-- JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.js"></script>


</body>

</html>


