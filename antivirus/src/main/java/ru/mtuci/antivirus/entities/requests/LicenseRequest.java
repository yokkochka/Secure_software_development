package ru.mtuci.antivirus.entities.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LicenseRequest {

    private Long productId;

    private Long userId;

    private Long licenseTypeId;

    @NotBlank(message = "description cannot be empty.")
    private String description;

    private Integer deviceCount;

    private Integer duration;

}
