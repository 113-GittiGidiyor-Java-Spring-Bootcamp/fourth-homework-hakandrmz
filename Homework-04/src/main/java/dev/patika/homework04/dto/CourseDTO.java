package dev.patika.homework04.dto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {

    @ApiModelProperty(hidden = true)
    private long id;

    @ApiModelProperty(example = "Computer Science")
    private String courseName;

    @ApiModelProperty(example = "CS101")
    private String courseCode;

    @ApiModelProperty(example = "6")
    private int credit;

}

