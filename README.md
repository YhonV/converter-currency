# converter-currency
Este proyecto es un conversor de divisas desarrollado en Java que utiliza una API externa para obtener tasas de cambio en tiempo real.


## Descripción

El Conversor de Divisas es una aplicación de consola que permite a los usuarios:
- Ver una lista de todas las monedas disponibles
- Convertir una cantidad de dinero de una moneda a otra
- Obtener tasas de cambio actualizadas en tiempo real

## Tecnologías Utilizadas

- Java 17
- Maven (para gestión de dependencias)
- Gson (para procesamiento de JSON)
- HttpClient de Java (para realizar peticiones HTTP)
- API de ExchangeRate-API para obtener tasas de cambio

## Configuración del Proyecto

### Prerrequisitos

- JDK 17 o superior
- Maven 3.6 o superior

### Instalación
1. Clona el repositorio:
2. Navega al directorio del proyecto:
3. Compila el proyecto con Maven:

## Uso

Para ejecutar la aplicación, usa el siguiente comando desde el directorio raíz del proyecto:

Sigue las instrucciones en pantalla para:
1. Ver la lista de monedas disponibles
2. Ingresar la cantidad a convertir
3. Seleccionar la moneda de origen
4. Seleccionar la moneda de destino

## Estructura del Proyecto

- `src/main/java/org/example/Main.java`: Punto de entrada de la aplicación
- `src/main/java/models/Converter.java`: Clase principal para la lógica de conversión
- `pom.xml`: Archivo de configuración de Maven

## Dependencias

- Gson 2.10.1: Para el procesamiento de JSON

## Configuración de API

El proyecto utiliza la API gratuita de ExchangeRate-API. La URL de la API está configurada en la clase `Main.java`:

```java
private final static String API_URL = "https://api.exchangerate-api.com/v4/latest/USD";
