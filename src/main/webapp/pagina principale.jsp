
<%@taglib prefix="tg" uri="/WEB-INF/tag1.tld" %>
<html>

<head>

    <title> Buciano </title>

</head>

<body>
    <tg:query
            sql="SELECT nome,cognome FROM studente"
            dsn="test"
            user="root"
            password=""
            fields="nome,cognome"
    />

</body>

</html>