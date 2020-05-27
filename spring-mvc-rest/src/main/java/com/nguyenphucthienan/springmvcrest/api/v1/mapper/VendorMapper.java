package com.nguyenphucthienan.springmvcrest.api.v1.mapper;

import com.nguyenphucthienan.springmvcrest.api.v1.model.VendorDTO;
import com.nguyenphucthienan.springmvcrest.domain.Vendor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VendorMapper {

    VendorMapper INSTANCE = Mappers.getMapper(VendorMapper.class);

    VendorDTO vendorToVendorDTO(Vendor vendor);

    Vendor vendorDTOtoVendor(VendorDTO vendorDTO);
}
