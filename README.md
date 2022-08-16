# Backend Agricultores/Mayorista 
    Es un proecto enteramente hecho con JAVA y Spring Boot

## Instalación
    1. Debes bajar IntelliJ (Puedes usar otro IDE, pero el proyecto se hizo en este)
    2. Instalar OpenJDK 17 (lo puedes bajar aquí <https://jdk.java.net/java-se-ri/17>
    3. Clonar el repositorio desde GitHub <https://github.com/LuisG-D/commerce.git>
    4. Al abrir el proyecto en IntelliJ puede que no te reconozca algunos archivos, puedes hacer dos cosas.
        4.1 Recargar el proyecto (Click derecho en la carpeta commerce, Rebuild Module 'commerce'
        4.2 Si aun asi no lo reconoce reinicia el IDE
    5. Una vez hecho todo, debes ir a la ruta src/main/java/CommerceApplication una vez dentro del archivo te saldra
        un simbolo de play a la izquierda, le das y ya tendrías la aplicación corriendo.
    6. Una vez la aplicación este corriendo ve a la direccion <http://localhost:8080/swagger-ui/>
        allí veras todos los endpoints que posee el proyecto.


### Posibles Problemas con el envío de correo electronico
    Si por alguna razón se pasa el numero diario de envios de correos por parte de Google debemos hacer lo siguiente:
        1. Irnos a Gmail y crear un nuevo correo   
        2. Una vez ya tengamos el correo listo para usar debemos hacer lo siguiente: 
            2.1 Debemos ir a administrar cuenta de Google / Seguridad
            2.2 En el apartado de Acceso a Google ir a Contraseñas de aplicaciones 
            (debes habilitar la autenticación en dos pasos para ello)
            2.3 Seleccionar Correo electrónico y Computadora con Windows para generar la contraseña
            2.4 Una vez tengas la contraseña copia y no la pierdas
        3. Una vez completado el paso 2 debes irte a commerce/src/main/resources/application.yml
            3.1 Una vez dentro busca el apartado de mail
            3.2 Debes cambiar el username(es el correo sin @)
            3.3 Y la password debemos meter la que obtuvimos en el paso 2.
#### Con esto ya tendriamos la aplicación lista, puedes consultar distintos endpoints de los distintos apartados en el paso 6 de Instalación.

    

# PROYECTO EN CONSTRUCCION