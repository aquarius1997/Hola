<!DOCTYPE html>
<html lang="en">
<head>
    <title>Hola!</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.js"></script>
    <script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
    <!--Font-->
    <link href="https://fonts.googleapis.com/css2?family=Raleway&display=swap" rel="stylesheet">
    <!--CSS-->
    <link rel="stylesheet" href="/resources/mydiary/assets/css/mydiaries.css?ver=5" />

</head>
<body>
<div id="wrapper">
    <div id="main">
        <!-- 왼쪽 네비게이션 위치를 뺌-->
        <div class="inner">

            <!-- header -->
            <header id="header">
                <a href="/my-diaries/<c:out value="${memberId}"/>">Hola!</a>
                <ul>
                    <li><a href="#">Mypage</a></li>
                </ul>
            </header>