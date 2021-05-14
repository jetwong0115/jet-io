package org.jet.io.common.utils.pojo.dto;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

@Data
@SuperBuilder
public class PageRequestDTO {

    @Range(min = 1)
    @NotNull
    private Integer page;

    @Range(min = 5, max = 100)
    @NotNull
    private Integer size;

}
