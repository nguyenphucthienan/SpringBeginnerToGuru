package com.nguyenphucthienan.springmvcrest.service;

import com.nguyenphucthienan.springmvcrest.api.v1.mapper.VendorMapper;
import com.nguyenphucthienan.springmvcrest.api.v1.model.VendorDTO;
import com.nguyenphucthienan.springmvcrest.domain.Vendor;
import com.nguyenphucthienan.springmvcrest.exception.ResourceNotFoundException;
import com.nguyenphucthienan.springmvcrest.repository.VendorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

public class VendorServiceImplTest {

    private static final String NAME_1 = "Vendor 1";
    private static final long ID_1 = 1L;
    private static final String NAME_2 = "Vendor 2";
    private static final long ID_2 = 1L;

    @Mock
    private VendorRepository vendorRepository;

    private VendorService vendorService;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        vendorService = new VendorServiceImpl(vendorRepository, VendorMapper.INSTANCE);
    }

    @Test
    public void getVendors() {
        List<Vendor> vendors = Arrays.asList(getVendor1(), getVendor2());
        given(vendorRepository.findAll()).willReturn(vendors);

        List<VendorDTO> vendorDTOs = vendorService.getVendors();

        then(vendorRepository).should(times(1)).findAll();
        assertThat(vendorDTOs.size(), is(equalTo(2)));
    }

    @Test
    public void getVendorById() {
        Vendor vendor = getVendor1();
        given(vendorRepository.findById(anyLong())).willReturn(Optional.of(vendor));

        VendorDTO vendorDTO = vendorService.getVendorById(1L);

        then(vendorRepository).should(times(1)).findById(anyLong());
        assertThat(vendorDTO.getName(), is(equalTo(NAME_1)));
    }

    @Test
    public void getVendorByIdNotFound() {
        assertThrows(ResourceNotFoundException.class, () -> {
            given(vendorRepository.findById(anyLong())).willReturn(Optional.empty());
            VendorDTO vendorDTO = vendorService.getVendorById(1L);
            then(vendorRepository).should(times(1)).findById(anyLong());
        });
    }

    @Test
    public void saveVendor() {
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(NAME_1);

        Vendor vendor = getVendor1();
        given(vendorRepository.save(any(Vendor.class))).willReturn(vendor);

        VendorDTO savedVendorDTO = vendorService.saveVendor(vendorDTO);

        then(vendorRepository).should().save(any(Vendor.class));
        assertThat(savedVendorDTO.getVendorUrl(), containsString("1"));
    }

    @Test
    public void saveVendorTest() {
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(NAME_1);

        Vendor vendor = getVendor1();
        given(vendorRepository.save(any(Vendor.class))).willReturn(vendor);

        VendorDTO savedVendorDTO = vendorService.saveVendor(ID_1, vendorDTO);

        then(vendorRepository).should().save(any(Vendor.class));
        assertThat(savedVendorDTO.getVendorUrl(), containsString("1"));
    }

    @Test
    public void patchVendor() {
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(NAME_1);

        Vendor vendor = getVendor1();

        given(vendorRepository.findById(anyLong())).willReturn(Optional.of(vendor));
        given(vendorRepository.save(any(Vendor.class))).willReturn(vendor);

        VendorDTO savedVendorDTO = vendorService.patchVendor(ID_1, vendorDTO);

        then(vendorRepository).should().save(any(Vendor.class));
        then(vendorRepository).should(times(1)).findById(anyLong());
        assertThat(savedVendorDTO.getVendorUrl(), containsString("1"));
    }

    @Test
    public void deleteVendorById() {
        vendorService.deleteVendorById(1L);
        then(vendorRepository).should().deleteById(anyLong());
    }

    private Vendor getVendor1() {
        Vendor vendor = new Vendor();
        vendor.setName(NAME_1);
        vendor.setId(ID_1);
        return vendor;
    }

    private Vendor getVendor2() {
        Vendor vendor = new Vendor();
        vendor.setName(NAME_2);
        vendor.setId(ID_2);
        return vendor;
    }
}
