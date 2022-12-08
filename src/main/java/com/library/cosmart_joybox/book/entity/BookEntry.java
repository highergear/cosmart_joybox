package com.library.cosmart_joybox.book.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookEntry implements Serializable {

    private String title;

    private List<Author> authors;

    @JsonAlias({"cover_edition_key"})
    private String edition;

    @Data
    @NoArgsConstructor
    static class Author implements Serializable{
        String key;
        String name;
    }
}
