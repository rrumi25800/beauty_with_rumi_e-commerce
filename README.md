# Beauty With Rumi - Full-Stack E-Commerce Application

## 📖 Overview

**Beauty With Rumi** is a complete full-stack CRUD application for managing beauty products. It features a modern, girly UI with pink and lavender themes, built with industry-standard technologies.

### 🛠️ Tech Stack

**Backend:**
- Spring Boot 3.2.1 (Java 17)
- Spring Data MongoDB
- MongoDB Atlas (Cloud Database)
- Maven
- Bean Validation
- Lombok

**Frontend:**
- React 18 with TypeScript
- Vite (Build Tool)
- React Router DOM
- Axios
- CSS3 (Custom Styling)

### ✨ Features

- ✅ **Full CRUD Operations**: Create, Read, Update, Delete products
- ✅ **RESTful API**: Well-structured REST endpoints
- ✅ **OOP Principles**: Layered architecture, interfaces, encapsulation
- ✅ **Validation**: Client and server-side validation
- ✅ **Error Handling**: Global exception handling with meaningful messages
- ✅ **Type Safety**: TypeScript for compile-time type checking
- ✅ **Responsive Design**: Mobile-friendly UI
- ✅ **PATCH Support**: Partial updates (e.g., toggle stock status)
- ✅ **CORS Enabled**: Frontend-backend integration

---

## 🚀 Quick Start

### Prerequisites

Before running the application, ensure you have:

1. **Java 17 or later** installed
   ```bash
   java -version
   ```

2. **Maven 3.6+** installed
   ```bash
   mvn -version
   ```

3. **Node.js 18+** and **npm** installed
   ```bash
   node -v
   npm -v
   ```

---

## 📦 Installation & Running

### Step 1: Clone/Navigate to Project

```bash
cd /Users/abubakarsiddik/Desktop/Projects/beauty_with_rumi_e-commerce
```

### Step 2: Run Backend (Spring Boot)

1. **Navigate to backend directory:**
   ```bash
   cd backend
   ```

2. **Build the project:**
   ```bash
   mvn clean install
   ```

3. **Run the application:**
   ```bash
   mvn spring-boot:run
   ```

   Or alternatively:
   ```bash
   java -jar target/beauty-with-rumi-backend-1.0.0.jar
   ```

4. **Verify backend is running:**
   - Backend runs on: `http://localhost:8080`
   - API endpoint: `http://localhost:8080/api/products`
   - MongoDB Atlas connection is established automatically
   - Check console logs for "Initialized MongoDB with 15 beautiful products!" message

### Step 3: Run Frontend (React + Vite)

1. **Open a NEW terminal and navigate to frontend directory:**
   ```bash
   cd /Users/abubakarsiddik/Desktop/Projects/beauty_with_rumi_e-commerce/frontend
   ```

2. **Install dependencies:**
   ```bash
   npm install
   ```

3. **Start development server:**
   ```bash
   npm run dev
   ```

4. **Access the application:**
   - Frontend runs on: `http://localhost:5173`
   - Should automatically open in your browser

---

## 🎯 Using the Application

### Main Features:

1. **View Products**
   - All products are displayed in a beautiful card grid
   - Shows name, brand, category, price, shade, skin type, description, and stock status

2. **Create Product**
   - Click "✨ Add New Product" button
   - Fill in the form with product details
   - Click "Create Product"
   - Uses: `POST /api/products`

3. **Edit Product**
   - Click "✏️ Edit" on any product card
   - Modify the details in the form
   - Click "Update Product"
   - Uses: `PUT /api/products/{id}`

4. **Toggle Stock Status**
   - Click the 📦 button on any product card
   - Quickly toggle between "In Stock" and "Out of Stock"
   - Uses: `PATCH /api/products/{id}` (demonstrates partial update)

5. **Delete Product**
   - Click "🗑️ Delete" on any product card
   - Confirm the deletion
   - Uses: `DELETE /api/products/{id}`

---

## 📁 Project Structure

### Backend Structure
```
backend/
├── src/main/java/com/beautywithrumi/
│   ├── BeautyWithRumiApplication.java    # Main entry point
│   ├── entity/
│   │   └── BeautyProduct.java             # JPA Entity
│   ├── dto/
│   │   └── BeautyProductDTO.java          # Data Transfer Object
│   ├── repository/
│   │   └── BeautyProductRepository.java   # JPA Repository
│   ├── service/
│   │   ├── BeautyProductService.java      # Service Interface
│   │   └── impl/
│   │       └── BeautyProductServiceImpl.java  # Service Implementation
│   ├── controller/
│   │   └── BeautyProductController.java   # REST Controller
│   ├── exception/
│   │   ├── ResourceNotFoundException.java
│   │   ├── ErrorResponse.java
│   │   └── GlobalExceptionHandler.java
│   └── config/
│   │       ├── WebConfig.java                  # CORS Configuration
│   │       └── DataInitializer.java            # MongoDB Data Loader
├── src/main/resources/
│   └── application.properties              # App & MongoDB configuration
└── pom.xml                                 # Maven dependencies
```

### Frontend Structure
```
frontend/
├── src/
│   ├── components/
│   │   ├── Header.tsx                     # App header/logo
│   │   ├── Header.css
│   │   ├── ProductCard.tsx                # Individual product card
│   │   ├── ProductCard.css
│   │   ├── ProductForm.tsx                # Create/Edit form
│   │   └── ProductForm.css
│   ├── pages/
│   │   ├── HomePage.tsx                   # Main page
│   │   └── HomePage.css
│   ├── api/
│   │   └── productsApi.ts                 # API client (Axios)
│   ├── types/
│   │   └── BeautyProduct.ts               # TypeScript interfaces
│   ├── App.tsx                            # Main App component
│   ├── App.css
│   ├── main.tsx                           # Entry point
│   └── index.css                          # Global styles
├── index.html
├── package.json
├── tsconfig.json
└── vite.config.ts
```

---

## 🔌 API Endpoints

### Base URL: `http://localhost:8080/api/products`

| Method | Endpoint | Description | Request Body |
|--------|----------|-------------|--------------|
| GET | `/api/products` | Get all products | None |
| GET | `/api/products/{id}` | Get product by ID | None |
| POST | `/api/products` | Create new product | BeautyProductDTO |
| PUT | `/api/products/{id}` | Update entire product | BeautyProductDTO |
| PATCH | `/api/products/{id}` | Partial update | Partial BeautyProductDTO |
| DELETE | `/api/products/{id}` | Delete product | None |

### Example Request Body (POST/PUT):
```json
{
  "name": "Rose Glow Serum",
  "brand": "Lumineux",
  "category": "Skincare",
  "price": 45.99,
  "shade": null,
  "skinType": "All",
  "description": "Hydrating rose-infused serum that gives your skin a luminous glow.",
  "inStock": true
}
```

### Example PATCH Request:
```json
{
  "inStock": false
}
```

---

## 🎨 OOP Principles Demonstrated

### 1. **Encapsulation**
- Private fields in entities with getters/setters (Lombok)
- Business logic encapsulated in service layer

### 2. **Abstraction**
- `BeautyProductService` interface abstracts implementation
- Repository pattern abstracts data access

### 3. **Inheritance**
- `ResourceNotFoundException` extends `RuntimeException`
- `BeautyProductRepository` extends `JpaRepository`

### 4. **Polymorphism**
- Interface-based service design allows multiple implementations
- Exception handling through polymorphic exception types

### 5. **Single Responsibility Principle**
- Controller: HTTP handling only
- Service: Business logic only
- Repository: Data access only
- DTO: Data transfer only

### 6. **Dependency Injection**
- Constructor injection used throughout
- Spring manages bean lifecycle

---

## 🧪 Testing the Application

### Manual Testing Checklist:

1. ✅ **Start backend** - Verify console shows "Beauty With Rumi Backend is running"
2. ✅ **Start frontend** - Verify page loads with sample products
3. ✅ **View products** - Should see 15 pre-loaded beauty products
4. ✅ **Create product** - Add a new product and verify it appears
5. ✅ **Edit product** - Modify an existing product and verify changes
6. ✅ **Toggle stock** - Click 📦 button and verify stock status changes (PATCH demo)
7. ✅ **Delete product** - Delete a product and verify it's removed
8. ✅ **Validation** - Try submitting form with invalid data (empty fields, negative price)
9. ✅ **Error handling** - Stop backend and verify frontend shows error message

### Testing with cURL:

```bash
# Get all products
curl http://localhost:8080/api/products

# Get product by ID
curl http://localhost:8080/api/products/1

# Create product
curl -X POST http://localhost:8080/api/products \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Test Product",
    "brand": "Test Brand",
    "category": "Makeup",
    "price": 29.99,
    "skinType": "All",
    "description": "This is a test product description.",
    "inStock": true
  }'

# Partial update (PATCH)
curl -X PATCH http://localhost:8080/api/products/1 \
  -H "Content-Type: application/json" \
  -d '{"inStock": false}'

# Delete product
curl -X DELETE http://localhost:8080/api/products/1
```

---

## 🎨 UI/UX Design

### Color Palette:
- **Primary Pink**: `#ff8fab`
- **Soft Pink**: `#ffb3c6`
- **Lavender**: `#e0b0ff`
- **Peach**: `#ffd5cd`
- **Cream**: `#fff5f7`

### Typography:
- **Headings**: Playfair Display (italic, serif)
- **Body**: Poppins (sans-serif)

### Design Features:
- Gradient backgrounds (pink to lavender)
- Rounded corners (border-radius: 12-24px)
- Soft shadows with pink tint
- Smooth transitions and hover effects
- Responsive grid layout

---

## 🐛 Troubleshooting

### Backend Issues:

**Port 8080 already in use:**
```bash
# Find and kill process on port 8080
lsof -ti:8080 | xargs kill -9
```

**Maven build fails:**
```bash
# Clean and rebuild
mvn clean install -U
```

### Frontend Issues:

**Port 5173 already in use:**
```bash
# Kill process on port 5173
lsof -ti:5173 | xargs kill -9
```

**Dependencies won't install:**
```bash
# Clear cache and reinstall
rm -rf node_modules package-lock.json
npm install
```

**CORS errors:**
- Ensure backend is running
- Check WebConfig.java has correct origins
- Clear browser cache

---

## 📝 Notes for Learning

### Backend Highlights:
1. **Layered Architecture**: Clear separation between controller → service → repository
2. **DTO Pattern**: API models (DTO) separate from database models (Entity)
3. **Validation**: Bean Validation annotations on entity and DTO
4. **Exception Handling**: `@RestControllerAdvice` for centralized error handling
5. **CORS**: Configured in `WebConfig` for frontend integration

### Frontend Highlights:
1. **TypeScript**: Strong typing prevents runtime errors
2. **Component Composition**: Reusable, single-responsibility components
3. **State Management**: React hooks (useState, useEffect)
4. **API Separation**: API logic separated in `productsApi.ts`
5. **Form Validation**: Client-side validation with error messages
6. **CRUD Operations**: All operations demonstrated with proper HTTP methods

---

## 🚀 Future Enhancements

Potential improvements for students to implement:

1. **Pagination**: Add pagination to product list
2. **Search/Filter**: Search by name, filter by category/brand
3. **Sorting**: Sort products by price, name, etc.
4. **Image Upload**: Add product images
5. **Authentication**: User login and authorization
6. **Database**: Replace H2 with PostgreSQL/MySQL
7. **Testing**: Add unit and integration tests
8. **Docker**: Containerize the application

---

## 👨‍💻 Developer

Created as a learning project demonstrating full-stack development with:
- OOP principles
- RESTful API design
- Modern React with TypeScript
- Responsive UI design
- CRUD operations

**Made with 💖 and code!**

---

## 📄 License

This project is created for educational purposes.
