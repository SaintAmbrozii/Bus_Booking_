<#ftl encoding="UTF-8">
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Спасибо за вступление в партию! ${name}</title>
    <style>
        #body {
            display: flex;
            flex-direction: column;
            font-family: "Segoe UI", serif;
            align-items: center;
            background-color: #607FF2;
            font-size: 20px;
            height: 100%;
        }

        #div {
            display: block;
            flex-direction: column;
            margin: 10%;
            font-family: "Segoe UI", serif;
            background-color: white;
            padding: 3%;
            border-radius: 20px;
            color: black;
        }

        #a {
            text-decoration: none;
        }
    </style>
</head>
<body>
<div id="body">
    <div id="div">
        <h1 style="text-align: center">Registration</h1>
        <p>Здорова, ${name}!</p>
        <p>Ты внес в список свою почту.Теперь будешь агентом кремля</p>
        <p>Этот год был трудный, но мы выстояли! <br> Владимир Владимирович Путин</p>

    </div>
</div>
</body>
</html>