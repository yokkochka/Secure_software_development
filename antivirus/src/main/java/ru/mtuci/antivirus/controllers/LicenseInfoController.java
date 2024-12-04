package ru.mtuci.antivirus.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.mtuci.antivirus.entities.requests.LicenseInfoRequest;
import ru.mtuci.antivirus.entities.Device;
import ru.mtuci.antivirus.entities.License;
import ru.mtuci.antivirus.entities.Ticket;
import ru.mtuci.antivirus.entities.User;
import ru.mtuci.antivirus.services.DeviceService;
import ru.mtuci.antivirus.services.LicenseService;
import ru.mtuci.antivirus.services.UserService;

@RestController
@RequestMapping("/license")
public class LicenseInfoController {

    private final DeviceService deviceService;
    private final LicenseService licenseService;
    private final UserService userService;

    @Autowired
    public LicenseInfoController(DeviceService deviceService, LicenseService licenseService, UserService userService) {
        this.deviceService = deviceService;
        this.licenseService = licenseService;
        this.userService = userService;
    }

    @GetMapping("/info")
    public ResponseEntity<?> getLicenseInfo(@Valid @RequestBody LicenseInfoRequest licenseInfoRequest){
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String login = authentication.getName();
            User user = userService.findUserByLogin(login);

            Device device = deviceService.getDeviceByInfo(licenseInfoRequest.getMacAddress(), user);
            if(device == null){
                throw new IllegalArgumentException("Validation error: Device not found");
            }

            License activeLicense = licenseService.getActiveLicenseForDevice(device, user, licenseInfoRequest.getLicenseCode());

            Ticket ticket = licenseService.generateTicket(activeLicense, device);

            return ResponseEntity.ok("Licenses found. Ticket:\n" + ticket.getBody());

        } catch (Exception e){
            return ResponseEntity.status(500).body("Internal server error: " + e.getMessage());
        }
    }

}
