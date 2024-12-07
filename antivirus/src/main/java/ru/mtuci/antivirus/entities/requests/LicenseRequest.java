package ru.mtuci.antivirus.entities.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LicenseRequest {
    @NotNull
    private Long productId;
    @NotNull
    private Long ownerId;
    @NotNull
    private Long licenseTypeId;

    private String description;

    private Integer deviceCount;
    @NotNull
    private Integer duration;

}
