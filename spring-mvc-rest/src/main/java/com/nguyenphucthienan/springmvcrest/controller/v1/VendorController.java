package com.nguyenphucthienan.springmvcrest.controller.v1;

import com.nguyenphucthienan.springmvcrest.api.v1.model.VendorDTO;
import com.nguyenphucthienan.springmvcrest.api.v1.model.VendorListDTO;
import com.nguyenphucthienan.springmvcrest.config.SwaggerConfig;
import com.nguyenphucthienan.springmvcrest.service.VendorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Api(tags = {SwaggerConfig.VENDOR_TAG})
@RestController
@RequestMapping(VendorController.BASE_URL)
public class VendorController {

    public static final String BASE_URL = "/api/v1/vendors";

    private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @ApiOperation(value = "Get vendors")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public VendorListDTO getVendors() {
        return new VendorListDTO(vendorService.getVendors());
    }

    @ApiOperation(value = "Get a vendor")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO getVendorById(@PathVariable Long id) {
        return vendorService.getVendorById(id);
    }

    @ApiOperation(value = "Create a vendor")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VendorDTO createVendor(@RequestBody VendorDTO vendorDTO) {
        return vendorService.saveVendor(vendorDTO);
    }

    @ApiOperation(value = "Update a vendor")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO updateVendor(@PathVariable Long id, @RequestBody VendorDTO vendorDTO) {
        return vendorService.saveVendor(id, vendorDTO);
    }

    @ApiOperation(value = "Update a vendor")
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO patchVendor(@PathVariable Long id, @RequestBody VendorDTO vendorDTO){
        return vendorService.patchVendor(id, vendorDTO);
    }

    @ApiOperation(value = "Delete a vendor")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteVendor(@PathVariable Long id) {
        vendorService.deleteVendorById(id);
    }
}
