package controller.checkValidation;

import commons.exceptions.BusinessException;
import model.entity.FacilityProfile;

import static commons.ExceptionMessages.FacilityProfile_isNot_compeleted;

public class FacilityProfileValidation {
    public static void validate(String loanName, String interestRate) throws BusinessException {
        if (loanName.equals("") || interestRate.equals("")) {
            throw new BusinessException(FacilityProfile_isNot_compeleted);
        } else {
            FacilityProfile facilityProfile = new FacilityProfile(loanName, interestRate);

        }
    }
}
