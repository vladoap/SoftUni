package com.example.pathfinder.model.binding;

import jakarta.validation.constraints.Size;

public class CommentBindingModel {

    private String textContent;

    @Size(min = 10, message = "The comment must be at least 10 characters.")
    public String getTextContent() {
        return textContent;
    }

    public CommentBindingModel setTextContent(String textContent) {
        this.textContent = textContent;
        return this;
    }
}
