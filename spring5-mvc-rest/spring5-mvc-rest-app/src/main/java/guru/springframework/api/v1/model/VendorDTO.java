package guru.springframework.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class VendorDTO {

    @ApiModelProperty(value = "Name of the Vendor", required = true)
    private String name;

    @JsonProperty("vendor_url")
    private String vendorUrl;
}
