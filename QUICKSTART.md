# Quick Run Instructions

## Backend (Spring Boot with MongoDB)

```bash
cd backend
mvn clean install
mvn spring-boot:run
```

Backend runs on: http://localhost:8080

**Note**: Uses MongoDB Atlas cloud database - no local database setup needed!

## Frontend (React + TypeScript + Vite)

```bash
cd frontend
npm install
npm run dev
```

Frontend runs on: http://localhost:5173

## Important Notes

1. Start the backend FIRST, then the frontend
2. Make sure ports 8080 and 5173 are available
3. Backend connects to MongoDB Atlas (cloud database)
4. On first run, 15 beauty products will be automatically loaded
5. Data persists between restarts (MongoDB cloud storage)
6. Internet connection required for database access

## Testing the Application

- View all products on home page
- Click "✨ Add New Product" to create
- Click "✏️ Edit" to update a product
- Click "📦" to toggle stock status (demonstrates PATCH)
- Click "🗑️ Delete" to remove a product

Enjoy! ✨
