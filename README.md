# Hexagonal Architecture - Simple CRUD

## Cấu trúc project

```
src/main/java/com/example/
│
├── domain/                          ← Lõi nghiệp vụ (KHÔNG phụ thuộc gì)
│   ├── model/
│   │   └── Product.java             ← Domain Entity
│   └── port/
│       ├── in/
│       │   └── ProductUseCase.java  ← Input Port (driving)
│       └── out/
│           └── ProductRepository.java ← Output Port (driven)
│
├── application/                     ← Use case implementations
│   └── usecase/
│       └── ProductService.java      ← Implements ProductUseCase
│
└── infrastructure/                  ← Adapters (Framework, DB, HTTP)
    ├── persistence/
    │   ├── entity/
    │   │   └── ProductEntity.java   ← JPA Entity
    │   ├── mapper/
    │   │   └── ProductPersistenceMapper.java
    │   └── repository/
    │       ├── SpringProductRepository.java    ← Spring Data JPA
    │       └── ProductPersistenceAdapter.java  ← Implements ProductRepository
    └── web/
        ├── controller/
        │   └── ProductController.java  ← REST Controller (Driving Adapter)
        ├── dto/
        │   ├── ProductRequest.java
        │   └── ProductResponse.java
        └── mapper/
            └── ProductWebMapper.java
```

## Kiến trúc Hexagonal

```
┌─────────────────────────────────────────────────────────┐
│                     DRIVING SIDE                         │
│  [HTTP Client] → [ProductController]                     │
│                         │                               │
│                    (Input Port)                          │
│                  ProductUseCase                          │
│                         │                               │
│              ┌──────────▼──────────┐                    │
│              │   ProductService    │   APPLICATION       │
│              │   (Use Case Impl)   │                    │
│              └──────────┬──────────┘                    │
│                         │                               │
│                   (Output Port)                         │
│                 ProductRepository                        │
│                         │                               │
│              [ProductPersistenceAdapter]                 │
│                         │                               │
│                    [H2 Database]                        │
│                     DRIVEN SIDE                         │
└─────────────────────────────────────────────────────────┘
```

## Nguyên tắc chính

| Tầng | Trách nhiệm | Phụ thuộc |
|------|-------------|-----------|
| **Domain** | Business logic, entity, port | Không phụ thuộc gì |
| **Application** | Điều phối use case | Domain ports only |
| **Infrastructure** | HTTP, DB, mapping | Domain + Framework |

## Chạy project

```bash
# Build và chạy
mvn spring-boot:run

# Chạy test
mvn test
```

## API Endpoints

| Method | URL | Description |
|--------|-----|-------------|
| POST   | `/api/products` | Tạo product mới |
| GET    | `/api/products` | Lấy tất cả products |
| GET    | `/api/products/{id}` | Lấy product theo ID |
| PUT    | `/api/products/{id}` | Cập nhật product |
| DELETE | `/api/products/{id}` | Xóa product |

## Ví dụ Request

### Tạo product
```http
POST http://localhost:8080/api/products
Content-Type: application/json

{
  "name": "Laptop Gaming",
  "description": "RTX 4080, 32GB RAM",
  "price": 35000000,
  "stock": 5
}
```

### H2 Console
Truy cập: http://localhost:8080/h2-console  
JDBC URL: `jdbc:h2:mem:hexagonaldb`  
Username: `sa` | Password: (để trống)

## Dependencies
- Spring Boot 3.2
- Spring Data JPA
- H2 Database (in-memory)
- Lombok
- MapStruct
- Bean Validation
