# Beauty With Rumi - API Documentation

## Base URL
```
http://localhost:8080/api/products
```

## Endpoints

### 1. Get All Products
**GET** `/api/products`

**Response:** `200 OK`
```json
[
  {
    "id": 1,
    "name": "Rose Glow Serum",
    "brand": "Lumineux",
    "category": "Skincare",
    "price": 45.99,
    "shade": null,
    "skinType": "All",
    "description": "Hydrating rose-infused serum that gives your skin a luminous glow. Perfect for achieving that dewy, radiant look!",
    "inStock": true
  }
]
```

### 2. Get Product by ID
**GET** `/api/products/{id}`

**Example:** `GET /api/products/1`

**Response:** `200 OK`
```json
{
  "id": 1,
  "name": "Rose Glow Serum",
  "brand": "Lumineux",
  "category": "Skincare",
  "price": 45.99,
  "shade": null,
  "skinType": "All",
  "description": "Hydrating rose-infused serum...",
  "inStock": true
}
```

**Error Response:** `404 Not Found`
```json
{
  "timestamp": "2026-01-18T10:30:00",
  "status": 404,
  "error": "Not Found",
  "message": "Beauty product not found with id: 999",
  "path": "/api/products/999"
}
```

### 3. Create Product
**POST** `/api/products`

**Request Body:**
```json
{
  "name": "Sparkle Eyeshadow",
  "brand": "Glamour Girl",
  "category": "Makeup",
  "price": 32.50,
  "shade": "Rose Gold",
  "skinType": "All",
  "description": "Stunning rose gold eyeshadow with amazing shimmer and long-lasting formula.",
  "inStock": true
}
```

**Response:** `201 Created`
```json
{
  "id": 16,
  "name": "Sparkle Eyeshadow",
  "brand": "Glamour Girl",
  "category": "Makeup",
  "price": 32.50,
  "shade": "Rose Gold",
  "skinType": "All",
  "description": "Stunning rose gold eyeshadow...",
  "inStock": true
}
```

**Validation Error:** `400 Bad Request`
```json
{
  "timestamp": "2026-01-18T10:30:00",
  "status": 400,
  "error": "Validation Failed",
  "message": "Input validation failed. Please check the errors.",
  "path": "/api/products",
  "validationErrors": {
    "name": "Product name is required",
    "price": "Price must be positive"
  }
}
```

### 4. Update Product (Full)
**PUT** `/api/products/{id}`

**Example:** `PUT /api/products/1`

**Request Body:**
```json
{
  "name": "Rose Glow Serum - Updated",
  "brand": "Lumineux Premium",
  "category": "Skincare",
  "price": 49.99,
  "shade": null,
  "skinType": "All",
  "description": "Enhanced formula with even more rose extract!",
  "inStock": true
}
```

**Response:** `200 OK`
```json
{
  "id": 1,
  "name": "Rose Glow Serum - Updated",
  "brand": "Lumineux Premium",
  "category": "Skincare",
  "price": 49.99,
  "shade": null,
  "skinType": "All",
  "description": "Enhanced formula with even more rose extract!",
  "inStock": true
}
```

### 5. Partial Update Product
**PATCH** `/api/products/{id}`

**Example:** `PATCH /api/products/1`

**Request Body (only update specific fields):**
```json
{
  "price": 39.99,
  "inStock": false
}
```

**Response:** `200 OK`
```json
{
  "id": 1,
  "name": "Rose Glow Serum",
  "brand": "Lumineux",
  "category": "Skincare",
  "price": 39.99,
  "shade": null,
  "skinType": "All",
  "description": "Hydrating rose-infused serum...",
  "inStock": false
}
```

**Note:** Only the fields sent in the request body are updated. Other fields remain unchanged.

### 6. Delete Product
**DELETE** `/api/products/{id}`

**Example:** `DELETE /api/products/1`

**Response:** `204 No Content`

**Error Response:** `404 Not Found`
```json
{
  "timestamp": "2026-01-18T10:30:00",
  "status": 404,
  "error": "Not Found",
  "message": "Beauty product not found with id: 1",
  "path": "/api/products/1"
}
```

## Validation Rules

### Product Fields:

| Field | Type | Required | Constraints |
|-------|------|----------|-------------|
| name | String | Yes | 2-100 characters |
| brand | String | Yes | 2-50 characters |
| category | String | Yes | Max 50 characters |
| price | Double | Yes | Must be positive |
| shade | String | No | Max 50 characters |
| skinType | String | Yes | Max 30 characters |
| description | String | Yes | 10-500 characters |
| inStock | Boolean | Yes | true or false |

### Valid Categories:
- Skincare
- Makeup
- Haircare
- Fragrance
- Tools

### Valid Skin Types:
- All
- Dry
- Oily
- Combination
- Sensitive

## Testing with cURL

### Get all products
```bash
curl http://localhost:8080/api/products
```

### Get product by ID
```bash
curl http://localhost:8080/api/products/1
```

### Create product
```bash
curl -X POST http://localhost:8080/api/products \
  -H "Content-Type: application/json" \
  -d '{
    "name": "New Product",
    "brand": "New Brand",
    "category": "Makeup",
    "price": 25.99,
    "skinType": "All",
    "description": "A wonderful new beauty product for you!",
    "inStock": true
  }'
```

### Update product
```bash
curl -X PUT http://localhost:8080/api/products/1 \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Updated Product",
    "brand": "Updated Brand",
    "category": "Skincare",
    "price": 29.99,
    "skinType": "Dry",
    "description": "Updated description for this product.",
    "inStock": true
  }'
```

### Partial update (PATCH)
```bash
curl -X PATCH http://localhost:8080/api/products/1 \
  -H "Content-Type: application/json" \
  -d '{"inStock": false}'
```

### Delete product
```bash
curl -X DELETE http://localhost:8080/api/products/1
```

## Status Codes

| Code | Meaning | When |
|------|---------|------|
| 200 | OK | Successful GET, PUT, PATCH |
| 201 | Created | Successful POST |
| 204 | No Content | Successful DELETE |
| 400 | Bad Request | Validation failed |
| 404 | Not Found | Product not found |
| 500 | Internal Server Error | Server error |

## CORS Configuration

The API allows requests from:
- `http://localhost:5173` (Vite dev server)
- `http://localhost:3000` (Alternative React dev server)

Allowed methods: GET, POST, PUT, PATCH, DELETE, OPTIONS
