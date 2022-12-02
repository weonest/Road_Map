<%--
  Created by IntelliJ IDEA.
  User: WEON
  Date: 2022-12-01
  Time: 오후 9:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <head>
        <meta charset="UTF-8">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400&display=swap" rel="stylesheet">
        <style>*{font-family: 'Noto Sans KR', sans-serif;}

        .info {
            display: flex;
            background: #FFF;
            color: #000;
            text-align: center;
            line-height: 7px;
            border-style: solid;
            border-width: 1px;
            border-color: #f88b3d;
            border-radius: 23px;
            padding: 0px 10px 0px 0px;
        }
        .lf {
            width: 20px;
            height: 20px;
        }
        .left {
            width: 30%;
            width: 30px;
            padding-top: 50%;
            padding-left: 20px;
            background-size: contain;
            border-radius: 30px;
            background-image: url("https://velog.velcdn.com/images/weonest/profile/68fc423b-9199-4047-8eb8-c1d98af92a01/image.png");
            background-repeat: no-repeat;
            background-position: center;
            float: left;
        }
        .right {
            contain: content;
            float: right;
        }
        </style>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
        <title>경기요 - 경기대 맛집리스트</title>
        <script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=dol835p4ve"></script>
        <script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
    </head>
<body>
<div id="map" style="width:100%;height:900px;"></div>

<script>

    var HOME_PATH = window.HOME_PATH || '.'

    var mat = new naver.maps.LatLng(37.3006759, 127.035805),
        map = new naver.maps.Map('map', {
            center: mat,
            zoom: 15
        }),
        marker = new naver.maps.Marker({
            map: map,
            position: mat
        });

    // var contentString = [
    //     '<div class="iw_inner">',
    //     '   <h3 class="h3">  title  </h3>',
    //     '   <p class="con"> 양도 줄었고 기존에 있던 메뉴들이 대부분 사라짐</p>',
    //     '   <p class="star"> 평점 : 4.3</p>',
    //     '</div>'
    // ].join('');
    var infowindow = new naver.maps.InfoWindow({
        content:
            '<div class="info">' +
            // '<img src="turtle.png" width="20" height="20">' +
            '<div class="left lf">' +
            '</div>' +
            '<div class="right"><h3>카타르시스ddd</h3><p>이거 맛좋네ddddddddd</p><p>평점:4.5</p></div>' +
            '</div>',
        // maxWidth: 200,
        backgroundColor: "",
        borderWidth: "",
        disableAnchor: true,
        anchorSize: new naver.maps.Size(15, 2),
        anchorSkew: false,
        anchorColor: "",
        pixelOffset: new naver.maps.Point(0, -20)
    });

    naver.maps.Event.addListener(marker, "click", function(e) {
        if (infowindow.getMap()) {
            infowindow.close();
        } else {
            infowindow.open(map, marker);
        }
    });

</script>
</body>
</html>