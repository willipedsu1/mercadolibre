# DNA mercadolibre

Este es un proyecto desarrollado con Springboot MVC, java 17. El cual se conecta a una base de datos MySql en aws para almacenar los registros procesados y posteriormente generar las estadisticas.

para ejecutarlo de manera local, se debe clonar este repositorio y abrir el proyecto en el IDE de su preferencia, se recomienda Intellij idea o Spring tools suite 4.
Posteriormente desacargar y configurar la version 17 o superior de java, creando o modificando las variables de entorno JAVA_HOME.

Asignar la version del Jdk de java al IDE, e iniciar el servidor.

se desplegara el servicio de validacion de DNA MELI en la siguiente ruta: http://localhost:8080/meli_dna por el metodo HTTP POST
se desplegara el servicio de estadisticas en la siguiente ruta: http://localhost:8080/stats por el metodo HTTP GET

Para garantizar la disponibilidad del servicio en fluctuaciones agresivas de tráfico. Se recomienda desplegar el jar en contenedores gestionados por Kubernetes agregando un balanceador de carga que se encargue de distribuir las peticiones e implementar escalado horizontal automatico apoyandose en servicios de la nube. 
