package com.finalprojectrest.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RestEBookDto {
    String name;
    String author;
    String category;
    Integer year;
    String language;
    String downloadUrl;
}
