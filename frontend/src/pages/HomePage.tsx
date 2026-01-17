import React, { useState, useEffect } from 'react';
import { BeautyProduct } from '../types/BeautyProduct';
import { productsApi } from '../api/productsApi';
import ProductCard from '../components/ProductCard';
import ProductForm from '../components/ProductForm';
import './HomePage.css';

/**
 * HomePage Component
 * Main page displaying all products with CRUD operations
 */
const HomePage: React.FC = () => {
  const [products, setProducts] = useState<BeautyProduct[]>([]);
  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<string>('');
  const [showForm, setShowForm] = useState<boolean>(false);
  const [editingProduct, setEditingProduct] = useState<BeautyProduct | null>(null);
  const [successMessage, setSuccessMessage] = useState<string>('');

  // Fetch all products on component mount
  useEffect(() => {
    fetchProducts();
  }, []);

  const fetchProducts = async () => {
    try {
      setLoading(true);
      setError('');
      const data = await productsApi.getAllProducts();
      setProducts(data);
    } catch (err) {
      setError('Failed to load products. Please make sure the backend is running.');
      console.error('Error fetching products:', err);
    } finally {
      setLoading(false);
    }
  };

  const handleCreateProduct = async (product: BeautyProduct) => {
    try {
      await productsApi.createProduct(product);
      await fetchProducts();
      setShowForm(false);
      showSuccess('Product created successfully! ✨');
    } catch (err) {
      setError('Failed to create product. Please try again.');
      console.error('Error creating product:', err);
    }
  };

  const handleUpdateProduct = async (product: BeautyProduct) => {
    try {
      if (product.id) {
        await productsApi.updateProduct(product.id, product);
        await fetchProducts();
        setShowForm(false);
        setEditingProduct(null);
        showSuccess('Product updated successfully! ✨');
      }
    } catch (err) {
      setError('Failed to update product. Please try again.');
      console.error('Error updating product:', err);
    }
  };

  const handleDeleteProduct = async (id: string) => {
    try {
      await productsApi.deleteProduct(id);
      await fetchProducts();
      showSuccess('Product deleted successfully! 🗑️');
    } catch (err) {
      setError('Failed to delete product. Please try again.');
      console.error('Error deleting product:', err);
    }
  };

  const handleToggleStock = async (id: string, currentStock: boolean) => {
    try {
      // Use PATCH to partially update only the stock status
      await productsApi.partialUpdateProduct(id, { inStock: !currentStock });
      await fetchProducts();
      showSuccess(`Stock status updated! ${!currentStock ? '✓' : '✗'}`);
    } catch (err) {
      setError('Failed to update stock status. Please try again.');
      console.error('Error toggling stock:', err);
    }
  };

  const handleEdit = (product: BeautyProduct) => {
    setEditingProduct(product);
    setShowForm(true);
  };

  const handleCloseForm = () => {
    setShowForm(false);
    setEditingProduct(null);
  };

  const handleOpenCreateForm = () => {
    setEditingProduct(null);
    setShowForm(true);
  };

  const showSuccess = (message: string) => {
    setSuccessMessage(message);
    setTimeout(() => setSuccessMessage(''), 3000);
  };

  if (loading) {
    return (
      <div className="loading-container">
        <div className="loading-spinner"></div>
        <p>Loading beautiful products... ✨</p>
      </div>
    );
  }

  return (
    <div className="home-page">
      {/* Success Message */}
      {successMessage && (
        <div className="success-message">
          {successMessage}
        </div>
      )}

      {/* Error Message */}
      {error && (
        <div className="error-message-banner">
          {error}
          <button onClick={() => setError('')}>✕</button>
        </div>
      )}

      {/* Action Bar */}
      <div className="action-bar">
        <div className="products-count">
          <span className="count">{products.length}</span>
          <span className="label">Beautiful Products</span>
        </div>
        <button className="btn-add-product" onClick={handleOpenCreateForm}>
          ✨ Add New Product
        </button>
      </div>

      {/* Products Grid */}
      {products.length === 0 ? (
        <div className="empty-state">
          <h2>No products yet! 💄</h2>
          <p>Start by adding your first beauty product.</p>
          <button className="btn-add-product" onClick={handleOpenCreateForm}>
            ✨ Add Your First Product
          </button>
        </div>
      ) : (
        <div className="products-grid">
          {products.map((product) => (
            <ProductCard
              key={product.id}
              product={product}
              onEdit={handleEdit}
              onDelete={handleDeleteProduct}
              onToggleStock={handleToggleStock}
            />
          ))}
        </div>
      )}

      {/* Product Form Modal */}
      {showForm && (
        <ProductForm
          product={editingProduct}
          onSubmit={editingProduct ? handleUpdateProduct : handleCreateProduct}
          onCancel={handleCloseForm}
        />
      )}
    </div>
  );
};

export default HomePage;
