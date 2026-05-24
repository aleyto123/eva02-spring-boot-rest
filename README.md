# 🚀 Sistema de Gestión de Pedidos - Evaluación N° 02

Este proyecto consiste en el desarrollo de una aplicación Web Backend segura utilizando **Spring Boot 4.0.6** y **Java 25**, aplicando una arquitectura en capas rigurosa, persistencia de datos relacionales, validaciones, manejo global de excepciones, Programación Orientada a Aspectos (AOP) y control de acceso mediante Spring Security con Autenticación Básica.

---

## 📋 Integrante
* **Nombre:** Rony Bellido
* **Correo:** rony.bellido@tecsup.edu.pe
* **Institución:** Tecsup

---

## 🛠️ Tecnologías y Versiones Utilizadas
* **Backend:** Spring Boot 4.0.6 & Java JDK 25
* **Persistencia:** Spring Data JPA & Hibernate
* **Base de Datos:** MySQL (Driver 9.7.0)
* **Seguridad:** Spring Security (Cifrado BCrypt)
* **Herramientas:** Lombok, AspectJ (AOP), Maven, IntelliJ IDEA, Postman

---

## 🗄️ I. Arquitectura de Base de Datos y Relaciones

El sistema gestiona de manera relacional una base de datos denominada `evaluacion_springboot_relaciones`. Las entidades mapeadas y sus relaciones son:

* **Cliente (1 : N) Pedido:** Un cliente puede registrar múltiples pedidos en el tiempo.
* **Pedido (1 : N) DetallePedido:** Un pedido contiene múltiples líneas de detalle de productos.
* **Producto (1 : N) DetallePedido:** Un producto puede figurar en diferentes detalles de pedidos.
* **Categoría (1 : N) Producto:** Los productos pertenecen a una categoría específica.
* **AuditoríaLog:** Tabla independiente encargada de registrar las trazas lógicas de las operaciones del sistema mediante AOP.

*(El script oficial de creación `script.sql` se encuentra adjunto en la raíz de este repositorio).*

---

## 🛡️ II. Seguridad y Restricciones de Acceso (Spring Security)

Se ha implementado **Basic Authentication** protegiendo los endpoints del API Rest y aplicando un control estricto de acceso basado en roles con contraseñas seguras encriptadas mediante `BCryptPasswordEncoder`.

### Usuarios Iniciales de Prueba
| Usuario | Password | Rol | Permisos lógicos |
| :--- | :--- | :--- | :--- |
| `admin` | `admin123` | `ROLE_ADMIN` | Acceso TOTAL (Lectura, Escritura, Modificación y Eliminación) |
| `user` | `user123` | `ROLE_USER` | Acceso LIMITADO (Únicamente consultas `GET`) |

### Matriz de Permisos por Endpoint
* **Peticiones `GET` (`/clientes`, `/productos`, `/pedidos`, `/categorias`):** Permitido para roles `USER` y `ADMIN`.
* **Peticiones `POST`, `PUT`, `DELETE`:** Permitido **únicamente** para el rol `ADMIN`. Cualquier intento por parte de un usuario con rol `USER` retornará un código de estado http `403 Forbidden` (Acceso Denegado).

---

## 🎯 III. Programación Orientada a Aspectos (AOP)

Para desacoplar las responsabilidades transversales del negocio, se configuró el motor de Aspectos interceptando las ejecuciones de la capa `service`:

1. **`AuditoriaAspect`:** Captura las operaciones de escritura y las registra automáticamente en la base de datos dentro de la tabla `auditoria_log`, guardando la acción, el método ejecutado, el usuario autenticado y la fecha exacta.
2. **`LoggingAspect`:** Registra trazas detalladas en la consola de IntelliJ al inicio (`@Before`) y al finalizar exitosamente (`@After`) cada método del servicio.
3. **`ErrorAspect`:** Intercepta cualquier excepción o fallo no controlado ocurrido en la lógica de negocio (`@AfterThrowing`) y escribe el error detallado en los logs del servidor para auditoría técnica.

---

## 🔄 IV. Lógica de Negocio Crítica: Control de Stock

Se programó una validación transaccional automatizada al momento de registrar un pedido:
* Al recibir una petición `POST` en `/pedidos`, el sistema valida de manera unitaria si hay stock suficiente de cada producto solicitado.
* Si el stock es apto, se procesa la transacción disminuyendo de forma automática la cantidad comprada del inventario en la tabla `producto`.
* En caso de no contar con unidades suficientes, se dispara una excepción controlada interrumpiendo la persistencia y protegiendo la integridad de la base de datos.

---

## 🚀 V. Instrucciones para Ejecutar el Proyecto

1. **Clonar el repositorio:**
   ```bash
   git clone [https://github.com/aleyto123/eva02-spring-boot-rest.git](https://github.com/aleyto123/eva02-spring-boot-rest.git)