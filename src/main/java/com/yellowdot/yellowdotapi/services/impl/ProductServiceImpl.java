package com.yellowdot.yellowdotapi.services.impl;

import com.yellowdot.yellowdotapi.dtos.CreateProductDto;
import com.yellowdot.yellowdotapi.dtos.ProductDto;
import com.yellowdot.yellowdotapi.dtos.UpdateProductDto;
import com.yellowdot.yellowdotapi.dtos.UpdateStatusProductDto;
import com.yellowdot.yellowdotapi.enums.MessagesCode;
import com.yellowdot.yellowdotapi.enums.ProductStatus;
import com.yellowdot.yellowdotapi.exceptions.EntityNotFoundException;
import com.yellowdot.yellowdotapi.mappers.CategoryMapper;
import com.yellowdot.yellowdotapi.mappers.ProductMapper;
import com.yellowdot.yellowdotapi.repositories.CategoryRepository;
import com.yellowdot.yellowdotapi.repositories.ProductRepository;
import com.yellowdot.yellowdotapi.services.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper, CategoryMapper categoryMapper, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.categoryMapper = categoryMapper;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<ProductDto> getAllProducts() {
        return productMapper.listEntityToListDto(productRepository.findAll());
    }

    @Override
    public ProductDto addNewProduct(CreateProductDto dto) throws EntityNotFoundException {
        try {
            var category = categoryRepository.findById(dto.categoryId());
            var product = productMapper.dtoToEntity(dto);
            category.ifPresent(product::setCategory);
            product.setStatus(ProductStatus.ACTIVE);
            return productMapper.entityToDto(productRepository.save(product));
        } catch (NullPointerException exception){
            throw new EntityNotFoundException(MessagesCode.DB001.getMessage(), MessagesCode.DB001.getCode());
        }
    }

    @Override
    public ProductDto updateProduct(UpdateProductDto dto) throws EntityNotFoundException {
        try {
            var productToUpdate = productRepository.findById(dto.productId());
            var category = categoryRepository.findById(dto.categoryId());
            if(productToUpdate.isPresent()){
                productToUpdate.get().setName(dto.name());
                category.ifPresent(value -> productToUpdate.get().setCategory(value));
                productToUpdate.get().setDescription(dto.description());
                productToUpdate.get().setPrice(dto.price());
                return productMapper.entityToDto(productRepository.save(productToUpdate.get()));
            } else {
                throw new EntityNotFoundException(MessagesCode.DB001.getMessage(), MessagesCode.DB001.getCode());
            }
        } catch (NullPointerException exception) {
            throw new NullPointerException(MessagesCode.DB001.getMessage());
        }
    }

    @Override
    public void deleteProduct(Integer productId) throws EntityNotFoundException {
        var productToDelete =  productRepository.findById(productId);
        if(productToDelete.isEmpty()){
            throw new EntityNotFoundException(MessagesCode.DB001.getMessage(), MessagesCode.DB001.getCode());
        }
        productRepository.deleteById(productId);
    }

    @Override
    @Transactional
    public ProductDto updateStatusProduct(UpdateStatusProductDto dto) throws EntityNotFoundException {
        try {
            var productToUpdate = productRepository.findById(dto.productId());
            productToUpdate.ifPresent(value -> value.setStatus(dto.status()));
            return productMapper.entityToDto(productRepository.save(productToUpdate.get()));
        } catch (Exception exception){
            throw new EntityNotFoundException(MessagesCode.DB001.getMessage(), MessagesCode.DB001.getCode());
        }
    }

    @Override
    public List<ProductDto> getAllProductsByCategoryId(Integer categoryId) throws EntityNotFoundException {
        try {
            return productMapper.listEntityToListDto(productRepository.findByCategory(categoryRepository.findById(categoryId).get()));
        } catch (Exception exception){
            throw new EntityNotFoundException(MessagesCode.DB001.getMessage(), MessagesCode.DB001.getCode());
        }
    }

    @Override
    public ProductDto getProductById(Integer categoryId) throws EntityNotFoundException {
        try {
            return productMapper.entityToDto( productRepository.findById(categoryId).get() );
        } catch (Exception exception){
            throw new EntityNotFoundException(MessagesCode.DB001.getMessage(), MessagesCode.DB001.getCode());
        }


    }
}
