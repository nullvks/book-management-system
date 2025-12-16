package com.nullvks.bookmanagementsystem.dto;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {
    private long id;

    @NotBlank(message = "Title cannot be blank")
    @Size(min = 2, max = 20, message = "Title must be between 2 and 20 character")
    private String title;

    @Size(min = 2, message = "Author name must be min 2 characters")
    private String authorName;

    private String createdBy;
}
