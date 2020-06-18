package com.cg.smart_house.dto.apartment;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ApartmentDtoRequest {
    private @NotEmpty String name;
    private Integer bathroom;
    private Integer bedroom;
    private @NotNull Integer priceByDate;
    private String description;
    private List<Long> pictureIds;
    private Long addressId;
    private Long hostId;
    private List<Long> roomTypes;
}
