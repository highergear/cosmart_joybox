package com.library.cosmart_joybox.book.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
public class SubjectEntry implements Serializable {

    private String name;

    @JsonProperty(value = "work_count")
    private Long workCount;

    private List<BookEntry> works;
}
