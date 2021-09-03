package dev.patika.homework04.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {

    @ApiModelProperty(example = "1")
    private long id;

    @ApiModelProperty(example = "Computer Science")
    private String name;

    @ApiModelProperty(example = "CS102")
    private String courseCode;

    @ApiModelProperty(example = "6")
    private int credit;

}

