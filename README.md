
# Serviteca - Sistema de Gestión para Taller Automotriz

Este proyecto consiste en un sistema completo que combina un **frontend** desarrollado en React y un **backend** en Spring Boot. Juntos, permiten gestionar vehículos, clientes, órdenes de servicio, revisiones y autopartes en un taller automotriz.

---

## 📦 Repositorios

### Frontend - [serviteca-front](https://github.com/Yusun16/serviteca-front)

- **Tecnologías principales**: React, Bootstrap, Axios.
- **Características**:
  - Listado y creación de órdenes de servicio.
  - Selectores dinámicos para clientes, vehículos y servicios.
  - Funcionalidades de búsqueda avanzada.
  - Gestión visual de revisiones, imágenes, y más.

### Backend - [cw-front-backend](https://github.com/Yusun16/cw-front-backend)

- **Tecnologías principales**: Spring Boot, MySQL.
- **Características**:
  - API REST para gestionar vehículos, clientes, órdenes, servicios y revisiones.
  - Validación de datos y manejo de errores estándar.
  - Persistencia de datos en MySQL.
  - Gestión de imágenes y otros recursos adjuntos.

---

## 🚀 Cómo funcionan juntos

El sistema está diseñado para que el **frontend** interactúe con el **backend** a través de APIs REST. Aquí tienes un flujo básico:

1. **Frontend**:
   - Permite al usuario interactuar con la interfaz, como registrar órdenes o revisar vehículos.
   - Envía solicitudes HTTP al backend utilizando Axios.

2. **Backend**:
   - Recibe las solicitudes del frontend.
   - Procesa la lógica de negocio y realiza operaciones en la base de datos.
   - Devuelve las respuestas al frontend.

Ejemplo de flujo:  
Un usuario registra una nueva orden desde el frontend → El frontend envía los datos al endpoint `/api/ordenes` del backend → El backend almacena la orden en la base de datos y devuelve una confirmación al frontend.

---

## 📂 Estructura del proyecto

### Frontend (`serviteca-front`)

```plaintext
src/                 # Código fuente principal
├── components/      # Componentes reutilizables
├── pages/           # Páginas principales del sistema
├── services/        # Conexión con el backend mediante Axios
├── styles/          # Estilos (CSS/SCSS)
├── utils/           # Funciones y helpers auxiliares
public/              # Recursos públicos (favicon, imágenes estáticas, etc.)
package.json         # Dependencias y scripts del proyecto
README.md            # Documentación del frontend
```

### Backend (`cw-front-backend`)

```plaintext
src/
├── main/
│   ├── java/        # Código fuente del backend
│   │   ├── controllers/ # Controladores REST
│   │   ├── services/    # Servicios de lógica empresarial
│   │   ├── models/      # Entidades de la base de datos
│   │   ├── repositories/# Interfaces para acceso a datos
│   │   └── dto/         # Objetos de transferencia de datos (DTO)
│   ├── resources/       # Configuraciones (application.properties)
├── test/               # Pruebas unitarias e integración
pom.xml                 # Configuración de Maven
README.md               # Documentación del backend
```

---

## 🛠️ Instalación y configuración

### Backend

1. Clona el repositorio:
   ```bash
   git clone https://github.com/Yusun16/cw-front-backend.git
   cd cw-front-backend
   ```

2. Configura la base de datos MySQL en el archivo `application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/serviteca
   spring.datasource.username=tu_usuario
   spring.datasource.password=tu_contraseña
   ```

3. Construye y ejecuta el proyecto:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

4. El backend estará disponible en `http://localhost:8080`.

---

### Frontend

1. Clona el repositorio:
   ```bash
   git clone https://github.com/Yusun16/serviteca-front.git
   cd serviteca-front
   ```

2. Instala las dependencias:
   ```bash
   npm install
   ```

3. Configura el archivo `.env` para conectar al backend:
   ```env
   REACT_APP_API_URL=http://localhost:8080
   ```

4. Inicia el servidor de desarrollo:
   ```bash
   npm start
   ```

5. El frontend estará disponible en `http://localhost:3000`.

---

## 📄 Endpoints destacados

Aquí algunos endpoints clave del backend:

- **GET /api/ordenes**  
  Devuelve todas las órdenes de servicio.

- **POST /api/ordenes**  
  Registra una nueva orden de servicio.

- **GET /api/vehiculos/{id}**  
  Obtiene los detalles de un vehículo por su ID.

- **POST /api/revisiones**  
  Registra la revisión inicial de un vehículo.

Para más detalles, consulta el archivo `application.properties` del backend.

---

## 📄 Scripts disponibles

### Backend

- `mvn spring-boot:run`: Inicia el servidor backend.
- `mvn test`: Ejecuta pruebas unitarias.

### Frontend

- `npm start`: Inicia el servidor de desarrollo.
- `npm build`: Genera la versión de producción.

---

## 📄 Licencia

Este proyecto está bajo la licencia MIT. Consulta el archivo `LICENSE` para más información.

---

## Contacto

- **GitHub**: [Yusun16](https://github.com/Yusun16)
- **Email**: yusunguairabryan@gmail.com

---

¡Gracias por usar Serviteca! 🚗🔧
