/**
 * TypeScript interface for BeautyProduct
 * Mirrors the backend DTO for type safety
 */
export interface BeautyProduct {
  id?: string;
  name: string;
  brand: string;
  category: string;
  price: number;
  shade?: string;
  skinType: string;
  description: string;
  inStock: boolean;
}

/**
 * Error response type from backend
 */
export interface ErrorResponse {
  timestamp: string;
  status: number;
  error: string;
  message: string;
  path: string;
  validationErrors?: Record<string, string>;
}
