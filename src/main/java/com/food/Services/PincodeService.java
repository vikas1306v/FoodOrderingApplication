package com.food.Services;

import com.food.Dto.Request.CreatePincodeRequestDto;
import com.food.Dto.Response.GenericResponseBean;
import com.food.Entities.Pincode;
import com.food.Exception.FoodOrderingMainException;
import com.food.Repository.PincodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PincodeService
{
    private final PincodeRepository pincodeRepository;
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

    public ResponseEntity<GenericResponseBean<?>> createPincode(CreatePincodeRequestDto createPincodeRequestDto) {
        try{
            Pincode p=new Pincode();
            p.setIsPincodeNumberActive(false);
            p.setPincodeNumber(createPincodeRequestDto.getPincodeNumber());
            return ResponseEntity.status(HttpStatus.OK).body(GenericResponseBean.builder()
                    .message("Pincode is Successfully created").status(true).build());
        }catch (Exception e){
            throw new FoodOrderingMainException("Something Went Wrong While creating pincode");
        }
    }

    public ResponseEntity<GenericResponseBean<?>> isPincodeDeliverable(String pincodeNumber) {
        try{
            Pincode pincode = pincodeRepository.findByPincodeNumber(pincodeNumber).orElseThrow(() -> new FoodOrderingMainException("This Pincode is not Deliverable"));
            if(pincode!=null&&pincode.getIsPincodeNumberActive())
            {
                return ResponseEntity.status(HttpStatus.OK).body(
                        GenericResponseBean.builder().message("This Pincode Is Deliverable").status(true)
                                .build()
                );
            }else{
                throw new FoodOrderingMainException(" Service UnAvailable To This Pincode");
            }

        }catch (Exception e)
        {
            throw new FoodOrderingMainException("Something Went Wrong While Checking Is Pincode Deliverable");
        }
    }
}
