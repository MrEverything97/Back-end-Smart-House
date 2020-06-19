package com.cg.smart_house.dto.apartment;

import com.cg.smart_house.dto.address.AddressDtoResponse;
import com.cg.smart_house.dto.category.CategoryDtoResponse;
import com.cg.smart_house.dto.host.HostDtoResponse;
import com.cg.smart_house.dto.picture.PictureDtoResponse;
import com.cg.smart_house.dto.roomtype.RoomTypeDtoResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ApartmentDtoResponse {
    private Long id;
    private String name;
    private Integer bathroom;
    private Integer bedroom;
    private Integer priceByDate;
    private String description;
    private List<PictureDtoResponse> pictureResponses;
    private List<CategoryDtoResponse> categoryDtoResponses;
    private AddressDtoResponse addressResponse;
    private HostDtoResponse hostDtoResponse;
    private List<RoomTypeDtoResponse> roomTypeResponses;
}
