# MongoDB Configuration Guide

## Database Information

**Database**: MongoDB Atlas (Cloud)  
**Connection String**: `mongodb+srv://rrumi202047_db_user:F79kZOTSaVflAx9Q@cluster0.jix1djv.mongodb.net/beauty_with_rumi_db`  
**Database Name**: `beauty_with_rumi_db`  
**Collection**: `beauty_products`

## Key Changes from H2 to MongoDB

### 1. Dependencies (pom.xml)
- ❌ Removed: `spring-boot-starter-data-jpa`
- ❌ Removed: `h2` database
- ✅ Added: `spring-boot-starter-data-mongodb`

### 2. Entity Annotations (BeautyProduct.java)
- Changed from JPA to MongoDB annotations:
  - `@Entity` → `@Document(collection = "beauty_products")`
  - `@Id` with `@GeneratedValue` → `@Id` (MongoDB auto-generates)
  - ID type: `Long` → `String` (MongoDB uses String IDs)
  - Removed `@Column` annotations (not needed in MongoDB)
  - Removed `@Table` annotation

### 3. Repository (BeautyProductRepository.java)
- Changed from: `extends JpaRepository<BeautyProduct, Long>`
- Changed to: `extends MongoRepository<BeautyProduct, String>`
- Same method names work with Spring Data MongoDB

### 4. Service & Controller
- Changed ID type from `Long` to `String` throughout
- All method signatures updated
- Business logic remains the same

### 5. Data Initialization
- ❌ Removed: `data.sql` (SQL doesn't work with MongoDB)
- ✅ Added: `DataInitializer.java` - CommandLineRunner that:
  - Checks if database is empty
  - Inserts 15 sample products if needed
  - Runs automatically on startup

### 6. Frontend Changes
- Updated TypeScript interface: `id?: number` → `id?: string`
- Updated API client method signatures
- All component props updated to use `string` for IDs

## Application Properties

```properties
# MongoDB Configuration
spring.data.mongodb.uri=mongodb+srv://rrumi202047_db_user:F79kZOTSaVflAx9Q@cluster0.jix1djv.mongodb.net/beauty_with_rumi_db?retryWrites=true&w=majority
spring.data.mongodb.database=beauty_with_rumi_db

# Logging (optional - for debugging)
logging.level.org.springframework.data.mongodb.core.MongoTemplate=DEBUG
```

## Benefits of MongoDB

1. **Cloud-Based**: No local database setup required
2. **Scalable**: Easy to scale as data grows
3. **Flexible Schema**: Can add fields without migrations
4. **JSON-like**: Documents map naturally to Java objects
5. **Persistent**: Data persists between restarts (unlike H2 in-memory)

## Data Persistence

Unlike H2 (in-memory), MongoDB Atlas stores data persistently:
- Data survives application restarts
- Can access data from multiple instances
- Production-ready database solution

## Sample Document Structure

```json
{
  "_id": "65a1b2c3d4e5f6g7h8i9j0k1",
  "name": "Rose Glow Serum",
  "brand": "Lumineux",
  "category": "Skincare",
  "price": 45.99,
  "shade": null,
  "skinType": "All",
  "description": "Hydrating rose-infused serum...",
  "inStock": true,
  "_class": "com.beautywithrumi.entity.BeautyProduct"
}
```

## Accessing MongoDB Atlas

You can view your data using:

1. **MongoDB Compass** (GUI):
   - Download: https://www.mongodb.com/products/compass
   - Connect with the connection string
   - View and edit documents visually

2. **MongoDB Atlas Web UI**:
   - Login to: https://cloud.mongodb.com
   - Navigate to your cluster
   - Browse Collections → beauty_with_rumi_db → beauty_products

3. **MongoDB Shell**:
   ```bash
   mongosh "mongodb+srv://cluster0.jix1djv.mongodb.net/beauty_with_rumi_db" --username rrumi202047_db_user
   ```

## Common Operations

### Check Connection
The application logs will show:
```
✨ Initialized MongoDB with 15 beautiful products! ✨
```
Or if data already exists:
```
✨ MongoDB already contains data. Skipping initialization. ✨
```

### Clear All Data
If you want to reset the database:
1. Stop the application
2. Use MongoDB Compass or Atlas UI to delete all documents
3. Restart the application (will re-initialize with sample data)

### Add More Sample Data
Edit `DataInitializer.java` and add more products to the list.

## Error Handling

Common issues:

1. **Connection Timeout**:
   - Check internet connection
   - Verify MongoDB Atlas cluster is running
   - Check firewall settings

2. **Authentication Failed**:
   - Verify username/password in connection string
   - Check MongoDB Atlas user permissions

3. **Database Access Denied**:
   - Ensure your IP is whitelisted in MongoDB Atlas
   - Or add 0.0.0.0/0 to allow all IPs (for development)

## Security Note

⚠️ **Important**: The connection string contains credentials. In production:
- Store credentials in environment variables
- Use `${MONGO_USERNAME}` and `${MONGO_PASSWORD}` in properties
- Never commit credentials to Git
- Use MongoDB Atlas IP whitelisting

Example production setup:
```properties
spring.data.mongodb.uri=mongodb+srv://${MONGO_USERNAME}:${MONGO_PASSWORD}@cluster0.jix1djv.mongodb.net/${MONGO_DATABASE}
```

## Testing

All CRUD operations work the same:
- Create: POST /api/products
- Read: GET /api/products, GET /api/products/{id}
- Update: PUT /api/products/{id}
- Patch: PATCH /api/products/{id}
- Delete: DELETE /api/products/{id}

The only difference is ID format (String instead of Long).

---

**Your MongoDB Atlas database is now fully integrated! 🎉**
