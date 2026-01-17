import React, { useState, useEffect } from 'react';
import { BeautyProduct } from '../types/BeautyProduct';
import './ProductForm.css';

/**
 * ProductForm Component Props
 */
interface ProductFormProps {
  product?: BeautyProduct | null;
  onSubmit: (product: BeautyProduct) => void;
  onCancel: () => void;
}

/**
 * ProductForm Component
 * Reusable form for creating and updating products
 */
const ProductForm: React.FC<ProductFormProps> = ({ product, onSubmit, onCancel }) => {
  const [formData, setFormData] = useState<BeautyProduct>({
    name: '',
    brand: '',
    category: 'Skincare',
    price: 0,
    shade: '',
    skinType: 'All',
    description: '',
    inStock: true,
  });

  const [errors, setErrors] = useState<Record<string, string>>({});

  // Populate form when editing
  useEffect(() => {
    if (product) {
      setFormData(product);
    }
  }, [product]);

  const handleChange = (
    e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement | HTMLSelectElement>
  ) => {
    const { name, value, type } = e.target;
    
    setFormData((prev) => ({
      ...prev,
      [name]: type === 'checkbox' 
        ? (e.target as HTMLInputElement).checked 
        : type === 'number' 
        ? parseFloat(value) || 0 
        : value,
    }));

    // Clear error for this field
    if (errors[name]) {
      setErrors((prev) => {
        const newErrors = { ...prev };
        delete newErrors[name];
        return newErrors;
      });
    }
  };

  const validate = (): boolean => {
    const newErrors: Record<string, string> = {};

    if (!formData.name.trim()) {
      newErrors.name = 'Product name is required';
    } else if (formData.name.length < 2 || formData.name.length > 100) {
      newErrors.name = 'Name must be between 2 and 100 characters';
    }

    if (!formData.brand.trim()) {
      newErrors.brand = 'Brand name is required';
    } else if (formData.brand.length < 2 || formData.brand.length > 50) {
      newErrors.brand = 'Brand must be between 2 and 50 characters';
    }

    if (formData.price <= 0) {
      newErrors.price = 'Price must be positive';
    }

    if (!formData.description.trim()) {
      newErrors.description = 'Description is required';
    } else if (formData.description.length < 10 || formData.description.length > 500) {
      newErrors.description = 'Description must be between 10 and 500 characters';
    }

    setErrors(newErrors);
    return Object.keys(newErrors).length === 0;
  };

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    
    if (validate()) {
      onSubmit(formData);
    }
  };

  const categories = ['Skincare', 'Makeup', 'Haircare', 'Fragrance', 'Tools'];
  const skinTypes = ['All', 'Dry', 'Oily', 'Combination', 'Sensitive'];

  return (
    <div className="form-overlay" onClick={onCancel}>
      <div className="form-container" onClick={(e) => e.stopPropagation()}>
        <h2 className="form-title">
          {product ? '✏️ Edit Product' : '✨ Add New Product'}
        </h2>

        <form onSubmit={handleSubmit} className="product-form">
          <div className="form-row">
            <div className="form-group">
              <label htmlFor="name">Product Name *</label>
              <input
                type="text"
                id="name"
                name="name"
                value={formData.name}
                onChange={handleChange}
                className={errors.name ? 'error' : ''}
                placeholder="e.g., Rose Glow Serum"
              />
              {errors.name && <span className="error-message">{errors.name}</span>}
            </div>

            <div className="form-group">
              <label htmlFor="brand">Brand *</label>
              <input
                type="text"
                id="brand"
                name="brand"
                value={formData.brand}
                onChange={handleChange}
                className={errors.brand ? 'error' : ''}
                placeholder="e.g., Lumineux"
              />
              {errors.brand && <span className="error-message">{errors.brand}</span>}
            </div>
          </div>

          <div className="form-row">
            <div className="form-group">
              <label htmlFor="category">Category *</label>
              <select
                id="category"
                name="category"
                value={formData.category}
                onChange={handleChange}
              >
                {categories.map((cat) => (
                  <option key={cat} value={cat}>
                    {cat}
                  </option>
                ))}
              </select>
            </div>

            <div className="form-group">
              <label htmlFor="price">Price ($) *</label>
              <input
                type="number"
                id="price"
                name="price"
                value={formData.price}
                onChange={handleChange}
                className={errors.price ? 'error' : ''}
                step="0.01"
                min="0"
                placeholder="0.00"
              />
              {errors.price && <span className="error-message">{errors.price}</span>}
            </div>
          </div>

          <div className="form-row">
            <div className="form-group">
              <label htmlFor="shade">Shade (Optional)</label>
              <input
                type="text"
                id="shade"
                name="shade"
                value={formData.shade || ''}
                onChange={handleChange}
                placeholder="e.g., Dusty Rose"
              />
            </div>

            <div className="form-group">
              <label htmlFor="skinType">Skin Type *</label>
              <select
                id="skinType"
                name="skinType"
                value={formData.skinType}
                onChange={handleChange}
              >
                {skinTypes.map((type) => (
                  <option key={type} value={type}>
                    {type}
                  </option>
                ))}
              </select>
            </div>
          </div>

          <div className="form-group">
            <label htmlFor="description">Description *</label>
            <textarea
              id="description"
              name="description"
              value={formData.description}
              onChange={handleChange}
              className={errors.description ? 'error' : ''}
              rows={4}
              placeholder="Describe the product and its benefits..."
            />
            {errors.description && <span className="error-message">{errors.description}</span>}
          </div>

          <div className="form-group checkbox-group">
            <label className="checkbox-label">
              <input
                type="checkbox"
                name="inStock"
                checked={formData.inStock}
                onChange={handleChange}
              />
              <span>In Stock</span>
            </label>
          </div>

          <div className="form-actions">
            <button type="button" className="btn btn-cancel" onClick={onCancel}>
              Cancel
            </button>
            <button type="submit" className="btn btn-submit">
              {product ? 'Update Product' : 'Create Product'}
            </button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default ProductForm;
