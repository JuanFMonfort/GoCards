# GoCards
### Manual de Instalacion

### <a href=#Mysql>**1. Mysql**</a>

### <a href=#Tomcat>**2. Tomcat**</a>

### <a href=#Servidor>**3. Instalacion de archivo WAR**</a>

### <a href=#Android>**4. Instalacion de Android**</a>

<dl>
    <dt name=Mysql>1. Mysql</dt>
    <dd>
        <p>La primera parte para la instalacion de el juego GaCards es crear una base de datos y para eso se debe crear una base de datos con el archivo mwb.</p>
        <p>Se instala la base de datos Mysql con la version <a href=https://downloads.mysql.com/archives/community>8.0.18</a></p>
        <p>El usuario creado para el archivo de base de datos es el root.</p>
    </dd>
</dl>

<dl>
    <dt name=Tomcat>2. Tomcat</dt>
    <dd>
        <p>El segundo paso es descargar el servidor de Tomcat que se encargara de procesar las peticiones de los clientes.</p>
        <p>Se descarga la version de Tomcat <a href=https://tomcat.apache.org/download-90.cgi>9.0</a></p>
        <p>Para que la aplicacion de Android te encuentre el servidor se debe cambiar la Ip a 192.168.20.51 y el servidor conficurar para que escuche por el puerto 9090.</p>
    </dd>
</dl>

<dl>
    <dt name=Servidor>3. Instalacion de archivo WAR</dt>
    <dd>
        <p>En ultima parte es configurar en servidor Tomcat para que procese nuestro servidor del juego y empieze a procesar las peticiones de los clientes.</p>
        <p>Se debe pegar el archivo war en la carpeta webapps de nuestra carpeta del servidor.</p>
    </dd>
</dl>

<dl>
    <dt name=Android>4. Instalacion de Android</dt>
    <dd>
        <p>En la instalacion de Android tenemos el archivo APK en el respositorio.</p>
    </dd>
</dl>