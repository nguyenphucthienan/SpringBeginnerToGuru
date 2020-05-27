package com.nguyenphucthienan.springmvcrest.service;

import com.nguyenphucthienan.springmvcrest.api.v1.mapper.VendorMapper;
import com.nguyenphucthienan.springmvcrest.api.v1.model.VendorDTO;
import com.nguyenphucthienan.springmvcrest.controller.v1.VendorController;
import com.nguyenphucthienan.springmvcrest.domain.Vendor;
import com.nguyenphucthienan.springmvcrest.exception.ResourceNotFoundException;
import com.nguyenphucthienan.springmvcrest.repository.VendorRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;
    private final VendorMapper vendorMapper;

    public VendorServiceImpl(VendorRepository vendorRepository, VendorMapper vendorMapper) {
        this.vendorRepository = vendorRepository;
        this.vendorMapper = vendorMapper;
    }

    @Override
    public List<VendorDTO> getVendors() {
        return vendorRepository.findAll()
                .stream()
                .map(vendor -> {
                    VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);
                    vendorDTO.setVendorUrl(getVendorUrl(vendor.getId()));
                    return vendorDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public VendorDTO getVendorById(Long id) {
        Vendor vendor = vendorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vendor ID " + id + " not found"));

        VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);
        vendorDTO.setVendorUrl(getVendorUrl(Objects.requireNonNull(vendor).getId()));
        return vendorDTO;
    }

    @Override
    public VendorDTO saveVendor(VendorDTO vendorDTO) {
        Vendor vendor = vendorMapper.vendorDTOtoVendor(vendorDTO);
        return saveAndReturnVendorDTO(vendor);
    }

    @Override
    public VendorDTO saveVendor(Long id, VendorDTO vendorDTO) {
        Vendor vendor = vendorMapper.vendorDTOtoVendor(vendorDTO);
        vendor.setId(id);
        return saveAndReturnVendorDTO(vendor);
    }

    @Override
    public VendorDTO patchVendor(Long id, VendorDTO vendorDTO) {
        return vendorRepository.findById(id).map(vendor -> {
            if (!StringUtils.isEmpty(vendorDTO.getName())) {
                vendor.setName(vendorDTO.getName());
            }

            VendorDTO returnVendorDTO = vendorMapper.vendorToVendorDTO(vendorRepository.save(vendor));
            returnVendorDTO.setVendorUrl(getVendorUrl(id));
            return returnVendorDTO;
        }).orElseThrow(() -> new ResourceNotFoundException("Vendor ID " + id + " not found"));
    }

    @Override
    public void deleteVendorById(Long id) {
        vendorRepository.deleteById(id);
    }

    private VendorDTO saveAndReturnVendorDTO(Vendor vendor) {
        Vendor savedVendor = vendorRepository.save(vendor);
        VendorDTO returnVendorDTO = vendorMapper.vendorToVendorDTO(savedVendor);
        returnVendorDTO.setVendorUrl(getVendorUrl(savedVendor.getId()));
        return returnVendorDTO;
    }

    private String getVendorUrl(Long id) {
        return VendorController.BASE_URL + "/" + id;
    }
}
