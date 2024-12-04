package ru.mtuci.antivirus.controllers;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mtuci.antivirus.entities.requests.LicenseUpdateRequest;
import ru.mtuci.antivirus.entities.Ticket;
import ru.mtuci.antivirus.services.AuthenticationService;
import ru.mtuci.antivirus.services.LicenseService;


@RestController
@RequestMapping("/license")
public class LicenseUpdateController {

    private final LicenseService licenseService;

    @Autowired
    public LicenseUpdateController(LicenseService licenseService) {
        this.licenseService = licenseService;
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateLicense(@Valid @RequestBody LicenseUpdateRequest updateRequest){
        try {

            Ticket ticket = licenseService.updateExistentLicense(updateRequest.getLicenseCode(), updateRequest.getLogin());

            return ResponseEntity.ok("License update successful, Ticket:\n" + ticket.getBody());
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
