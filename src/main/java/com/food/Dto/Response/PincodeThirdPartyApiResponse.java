package com.food.Dto.Response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class PincodeThirdPartyApiResponse {
    private String Message;
    private String Status;
    private List<PostOffice> PostOffice;

}
@Setter
@Getter
@NoArgsConstructor

class PostOffice {
    private String Name;
    private String Description;
    private String BranchType;
    private String DeliveryStatus;
    private String Circle;
    private String District;
    private String Division;
    private String Region;
    private String State;
    private String Country;
}