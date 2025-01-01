package com.jn.ecommerce.exception;

public class ResourceNotFoundException extends RuntimeException {
    String resourceName;
    String field;
    String fieldName;
    Long fieldId;

    public ResourceNotFoundException() {}

    public ResourceNotFoundException(String resourceName, String field, String fieldName) {
        super(String.format("Resource %s not found for field %s and fieldName %s", resourceName, field, fieldName));
        this.resourceName = resourceName;
        this.field = field;
        this.fieldName = fieldName;

    }

    public ResourceNotFoundException(String resourceName, String field, Long fieldId) {
        super(String.format("Resource %s not found for field %s and fieldId %s", resourceName, field, field));
        this.resourceName = resourceName;
        this.field = field;
        this.fieldId = fieldId;
    }

}
