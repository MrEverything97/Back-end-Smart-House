package com.cg.smart_house.service;

import com.cg.smart_house.model.Address;

public interface AddressService {
    ServiceResult createAddress(Address address);

    ServiceResult findAll();

    ServiceResult findById(Long id);

    ServiceResult deleteAddress(Long id);

    ServiceResult updateAddress(Address address);
<<<<<<< HEAD
}
=======
}
>>>>>>> 5c756754e611cad23b6af99de1cf0587f25e6bc4
