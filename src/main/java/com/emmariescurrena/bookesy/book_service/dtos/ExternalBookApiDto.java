package com.emmariescurrena.bookesy.book_service.dtos;

import java.util.List;

import lombok.Data;

@Data
public abstract class ExternalBookApiDto {
    
    public abstract String getId();
    public abstract String getTitle();
    public abstract String getDescription();
    public abstract List<String> getGenres();
    public abstract List<String> getAuthorsIds();
    public abstract Integer getCoverId();
    public abstract Double getAverageRating();
    public abstract Integer getRatingCount();

}
