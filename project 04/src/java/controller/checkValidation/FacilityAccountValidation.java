package controller.checkValidation;

import commons.exceptions.BusinessException;
import model.entity.GrantCondition;
import model.service.GrantConditionalCRUD;

import java.math.BigDecimal;
import java.util.List;

import static commons.ExceptionMessages.time_and_durtion;

public class FacilityAccountValidation {
    public static void validate(double amount, long duration, long loanTypeId) throws BusinessException {
        List<GrantCondition> grantConditionList = GrantConditionalCRUD.findByLoanId(loanTypeId);
        for (GrantCondition grantCondition : grantConditionList) {
            if ((Long.parseLong(String.valueOf(duration)) < grantCondition.getMaxDuration()) && (Long.parseLong(String.valueOf(duration)) > grantCondition.getMinDuration())) {
                if ((new BigDecimal(amount).compareTo(grantCondition.getMaxAmount())) <= 0 && (new BigDecimal(amount).compareTo(grantCondition.getMinAmount()) >= 0)) {
                }
            }
        }
        throw new BusinessException(time_and_durtion);
    }
}
