package com.finalprojectrest.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RestBookDto {
    String name;
    String image;
    String category;
    String author;
    String language;
    Integer year;
    String publishing;
}
