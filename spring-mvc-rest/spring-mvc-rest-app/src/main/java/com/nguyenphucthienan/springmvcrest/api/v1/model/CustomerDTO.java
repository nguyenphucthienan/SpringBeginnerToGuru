package com.nguyenphucthienan.springmvcrest.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CustomerDTO {

    @ApiModelProperty(value = "First Name", required = true)
    private String firstName;

    @ApiModelProperty(value = "Last Name", required = true)
    private String lastName;

    @JsonProperty("customer_url")
    private String customerUrl;
}
