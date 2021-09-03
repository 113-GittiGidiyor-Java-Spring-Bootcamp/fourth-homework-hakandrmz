package dev.patika.homework04.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {

    @ApiModelProperty(hidden = true)
    private long id;

    @ApiModelProperty(example = "Hakan")
    private String name;

    @ApiModelProperty(example = "2001-01-13")
    private LocalDate birthDate;

    @ApiModelProperty(example = "Bursa/Türkiye")
    private String address;

    @ApiModelProperty(example = "F")
    private String gender;

}
