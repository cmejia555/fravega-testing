# Challenge QA Automation

Para el desarrollo del proyecto se utilizo: 
 <br>
 <ul>
     <li>Java</li>
     <li>Selenium WebDriver</li>
     <li>JUnit</li>
     <li>Rest Assured</li>
     <li>Allure</li>
 </ul>
 <br>
 
## Ejecución

Para ejecutar los test se debe tener en cuenta los siguientes pasos:
 <br>
 <ol>
    <li>Descargar o clonar el proyecto</li>
    <li>Descargar el <a href="">driver de chrome</a> de acuerdo a su version de navegador y sistema operativo</li>
    <li>Una vez descargado el driver, descomprimir y obtener su ruta absoluta. Por ejemplo: /home/my_user/Downloads/chromedriver</li>
    <li>Pararse dentro del proyecto y ejecutar: 
        <br><strong>./gradlew clean allTest -DdriverPath=/absolute/path/to/chromedriver</strong>
    </li>
    <li>Ejecutar por <strong>unica</strong> vez el siguiente comando: 
        <br><strong>./gradlew allureReport</strong>
    </li>
    <li>Para ver el reporte: <br><strong>./gradlew allureServe</strong></li>
    <li>Si solo se desea ejecutar los tests del frontend:
        <br><strong>./gradlew clean frontendTest -DdriverPath=/absolute/path/to/chromedriver</strong>
        <br>Repetir paso 6
    </li>
    <li>Si solo se desea ejecutar los tests del backend:
            <br><strong>./gradlew clean backendTest</strong>
            <br>Repetir paso 6
     </li>
 </ol>
<br>

<h4>Consideraciones</h4>

Si se encuentra en Windows, a la ruta del driver se debe agregar la extension. Por ejemplo: C:\user\Downloads\chromedriver.exe
<br>
Ademas para ejecutar un comando usar el archivo .bat, por ejemplo: <strong>gradlew.bat clean backendTest</strong>