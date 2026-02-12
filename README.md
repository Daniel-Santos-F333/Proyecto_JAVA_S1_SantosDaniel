# 📱 TecnoStore - Sistema de Gestión de Inventario
Sistema de escritorio desarrollado en Java para la gestión de dispositivos móviles, clientes y marcas, utilizando una arquitectura MVC y persistencia en MySQL.

# 🚀 Características
Gestión de Celulares: Registro, listado y control de stock crítico.

Gestión de Clientes: Registro persistente con manejo de transacciones SQL.

Gestión de Marcas: Catálogo dinámico con integridad referencial.

Reportes: Generación de archivos planos (.txt) para stock bajo.

Robustez: Validación de entradas para evitar cierres inesperados.

# 🛠️ Tecnologías Utilizadas
Lenguaje: Java 17+

Base de Datos: MySQL

Arquitectura: MVC (Modelo-Vista-Controlador)

Control de Versiones: Git (Conventional Commits)

Conexión: Verificar las credenciales en CONTROLADOR/Conexion.java.

Librerías: Asegurarse de tener el mysql-connector-java.jar en el classpath.


# 📂 Estructura del Proyecto
MODELO: Clases de entidad (POJOs) y lógica de herencia.

VISTA: Interfaces de consola y validación de usuario.

CONTROLADOR: Lógica de negocio, gestión de archivos y JDBC.