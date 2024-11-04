# **Microservicios Bancarios**

Este proyecto es una solución basada en microservicios que proporciona una API para gestionar clientes, cuentas bancarias y movimientos, con soporte para mensajería asincrónica utilizando RabbitMQ y persistencia en MySQL.

## Tabla de Contenidos
* Descripción
* Características
* Tecnologías Utilizadas
* Requisitos Previos
* Instrucciones de Instalación
* Uso
* Endpoints de la API
* Pruebas
* Despliegue

## Descripción

Este proyecto es una implementación de un sistema bancario utilizando microservicios. Los servicios permiten gestionar clientes, cuentas y movimientos financieros. La comunicación entre microservicios es manejada de forma asincrónica a través de RabbitMQ.

## Características
* Gestión de Clientes: CRUD completo.
* Gestión de Cuentas: CRUD completo.
* Gestión de Movimientos: CRUD completo, con validación de saldo.
* Generación de reportes de estado de cuenta.
* Soporte para mensajería asincrónica con RabbitMQ.
* Despliegue usando Docker y Docker Compose.

## Tecnologías Utilizadas
* Java 17
* Spring Boot 3.3.5
* Spring Data JPA
* RabbitMQ
* MySQL
* Docker y Docker Compose
* Postman para pruebas de API

## Requisitos Previos

* Docker y Docker Compose instalados.
* Java 17 o superior.
* Gradle instalado.

## Instrucciones de Instalación

1. Clonar el Repositorio:

* git clone https://github.com/rjpuente/microservicios-bancarios.git
* cd microservicios-bancarios

2. Construir la Aplicación:

* ./gradlew bootJar

3. Levantar los Servicios:

* docker-compose up --build

## Uso

1. Accede a la API en http://localhost:3000.
2. Prueba los endpoints usando Postman o cualquier cliente HTTP.

## Endpoints de la API

### Clientes

* GET /clientes: Obtener todos los clientes.
* POST /clientes: Crear un nuevo cliente.
* PUT /clientes/{id}: Actualizar un cliente existente.
* DELETE /clientes/{id}: Eliminar un cliente.

### Cuentas

* GET /cuentas: Obtener todas las cuentas.
* POST /cuentas: Crear una nueva cuenta.
* PUT /cuentas/{id}: Actualizar una cuenta existente.
* DELETE /cuentas/{id}: Eliminar una cuenta.

### Movimientos

* GET /movimientos: Obtener todos los movimientos.
* POST /movimientos: Registrar un nuevo movimiento.

### Reportes

* GET /reportes: Obtener el reporte de estado de cuenta basado en un rango de fechas y cliente.

### Pruebas

#### Pruebas Unitarias: Ejecuta las pruebas unitarias con:

* ./gradlew test

#### Pruebas de Integración: Incluidas en el proyecto, pueden ejecutarse junto con las pruebas unitarias.

## Despliegue

La aplicación se despliega utilizando Docker Compose:

* docker-compose up --build