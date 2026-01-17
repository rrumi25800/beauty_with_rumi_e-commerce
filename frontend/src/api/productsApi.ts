import axios, { AxiosError } from 'axios';
import { BeautyProduct, ErrorResponse } from '../types/BeautyProduct';

/**
 * Base URL for the backend API
 */
const API_BASE_URL = 'http://localhost:8080/api/products';

/**
 * Axios instance with default configuration
 */
const apiClient = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    'Content-Type': 'application/json',
  },
});

/**
 * API client for Beauty Product operations
 * Provides type-safe methods for CRUD operations
 */
export const productsApi = {
  /**
   * GET all products
   */
  getAllProducts: async (): Promise<BeautyProduct[]> => {
    try {
      const response = await apiClient.get<BeautyProduct[]>('');
      return response.data;
    } catch (error) {
      handleApiError(error);
      throw error;
    }
  },

  /**
   * GET a single product by ID
   */
  getProductById: async (id: string): Promise<BeautyProduct> => {
    try {
      const response = await apiClient.get<BeautyProduct>(`/${id}`);
      return response.data;
    } catch (error) {
      handleApiError(error);
      throw error;
    }
  },

  /**
   * POST - Create a new product
   */
  createProduct: async (product: BeautyProduct): Promise<BeautyProduct> => {
    try {
      const response = await apiClient.post<BeautyProduct>('', product);
      return response.data;
    } catch (error) {
      handleApiError(error);
      throw error;
    }
  },

  /**
   * PUT - Update entire product (full replacement)
   */
  updateProduct: async (id: string, product: BeautyProduct): Promise<BeautyProduct> => {
    try {
      const response = await apiClient.put<BeautyProduct>(`/${id}`, product);
      return response.data;
    } catch (error) {
      handleApiError(error);
      throw error;
    }
  },

  /**
   * PATCH - Partially update product
   */
  partialUpdateProduct: async (
    id: string,
    product: Partial<BeautyProduct>
  ): Promise<BeautyProduct> => {
    try {
      const response = await apiClient.patch<BeautyProduct>(`/${id}`, product);
      return response.data;
    } catch (error) {
      handleApiError(error);
      throw error;
    }
  },

  /**
   * DELETE a product
   */
  deleteProduct: async (id: string): Promise<void> => {
    try {
      await apiClient.delete(`/${id}`);
    } catch (error) {
      handleApiError(error);
      throw error;
    }
  },
};

/**
 * Helper function to handle API errors
 */
const handleApiError = (error: unknown) => {
  if (axios.isAxiosError(error)) {
    const axiosError = error as AxiosError<ErrorResponse>;
    if (axiosError.response) {
      console.error('API Error:', axiosError.response.data);
    } else if (axiosError.request) {
      console.error('No response received from server');
    } else {
      console.error('Error setting up request:', axiosError.message);
    }
  } else {
    console.error('Unexpected error:', error);
  }
};
