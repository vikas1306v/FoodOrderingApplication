package com.food.Controller;

import com.food.Dto.Request.CreatePincodeRequestDto;
import com.food.Dto.Response.GenericResponseBean;
import com.food.Services.PincodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pincode")
public class PincodeController
{
    private final PincodeService pincodeService;

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<GenericResponseBean<?>> createPincode(@RequestBody CreatePincodeRequestDto createPincodeRequestDto)
    {
        return pincodeService.createPincode(createPincodeRequestDto);
    }
    @PostMapping("/make/active")
    public ResponseEntity<GenericResponseBean<?>> makePincodeActive(@RequestParam("pincode_number") String pincodeNumber)
    {
        return pincodeService.makePincodeActive(pincodeNumber);
    }

    @GetMapping("/check/deliverable")
    public ResponseEntity<GenericResponseBean<?>> isPincodeDeliverable(@RequestParam("pincode_number") String pincodeNumber)
    {
        return pincodeService.isPincodeDeliverable(pincodeNumber);
    }
}
