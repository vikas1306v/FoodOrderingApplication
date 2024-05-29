package com.food.Services;

import com.food.Dto.Request.CreatePincodeRequestDto;
import com.food.Dto.Response.GenericResponseBean;
import com.food.Dto.Response.PincodeThirdPartyApiResponse;
import com.food.Entities.Pincode;
import com.food.Exception.FoodOrderingMainException;
import com.food.Repository.PincodeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class PincodeService
{
    private final PincodeRepository pincodeRepository;
    private RestTemplate restTemplate;
    public ResponseEntity<GenericResponseBean<?>> makePincodeActive(String pincodeNumber)
    {
        try{
            Pincode pincode = pincodeRepository.findByPincodeNumber(pincodeNumber).orElseThrow(() -> new FoodOrderingMainException("No such Pincode Exist"));
            pincode.setIsPincodeNumberActive(true);
            pincodeRepository.save(pincode);
            return ResponseEntity.status(HttpStatus.OK).body(GenericResponseBean.builder()
                    .message("Pincode Is Live Now").status(true).build());
        }catch (Exception e) {
            throw new FoodOrderingMainException("Something Went Wrong While making pincode live");
        }
    }
    @Transactional
    public ResponseEntity<GenericResponseBean<?>> createPincode(CreatePincodeRequestDto createPincodeRequestDto) {
        if(createPincodeRequestDto.getPincodeNumber()==null||createPincodeRequestDto.getPincodeNumber().isEmpty())
        {
            throw new FoodOrderingMainException("Pincode Number Should not be Empty");
        }
        if(createPincodeRequestDto.getPincodeNumber().length()!=6)
        {
            throw new FoodOrderingMainException("Pincode Number Should be of 6 digits");
        }
        //validate if pincode already exist
        if(pincodeRepository.findByPincodeNumber(createPincodeRequestDto.getPincodeNumber()).isPresent())
        {
            throw new FoodOrderingMainException("Pincode Already Exist");
        }
        //validate is given pinocde is valid or not 
        ResponseEntity<PincodeThirdPartyApiResponse[]> forEntity = restTemplate.getForEntity("https://api.postalpincode.in/pincode/" + createPincodeRequestDto.getPincodeNumber(), PincodeThirdPartyApiResponse[].class);
        PincodeThirdPartyApiResponse[] body = forEntity.getBody();
        if(body[0].getStatus()=="Error")
        {

        }

        try{
            Pincode p=new Pincode();
            p.setIsPincodeNumberActive(false);
            p.setPincodeNumber(createPincodeRequestDto.getPincodeNumber());
            pincodeRepository.save(p);
            return ResponseEntity.status(HttpStatus.OK).body(GenericResponseBean.builder()
                    .message("Pincode is Successfully created").status(true).build());
        }catch (Exception e){
            throw new FoodOrderingMainException("Something Went Wrong While creating pincode");
        }
    }

    public ResponseEntity<GenericResponseBean<?>> isPincodeDeliverable(String pincodeNumber) {
        boolean ans=checkForPincodeDeliverable(pincodeNumber);
        if(ans) {
            return ResponseEntity.status(HttpStatus.OK).body(GenericResponseBean.builder()
                    .message("Pincode is Deliverable").status(true).build());
        }
        else {
            return ResponseEntity.status(HttpStatus.OK).body(GenericResponseBean.builder()
                    .message("Pincode is Not Deliverable").status(false).build());
        }

    }
    public boolean checkForPincodeDeliverable(String pincodeNumber)
    {
        Pincode pincode = pincodeRepository.findByPincodeNumber(pincodeNumber).orElse(null);
        return pincode != null && pincode.getIsPincodeNumberActive();
    }
}
