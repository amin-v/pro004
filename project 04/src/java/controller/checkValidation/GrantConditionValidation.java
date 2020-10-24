package controller.checkValidation;

import commons.exceptions.BusinessException;
import model.entity.GrantCondition;
import model.service.GrantConditionalCRUD;

import java.util.List;

import static commons.ExceptionMessages.*;

public class GrantConditionValidation {
    public static boolean validate(GrantCondition grantCondition) throws BusinessException {
        if (grantCondition.getMinDuration() >= grantCondition.getMaxDuration()) {
            throw new BusinessException(table_lesstime);
        }
        if (grantCondition.getMinAmount().compareTo(grantCondition.getMaxAmount()) > 0) {
            throw new BusinessException(table_overTime);
        }

        List<GrantCondition> findGrantConditional = GrantConditionalCRUD.finByName(grantCondition.getName());
        if (findGrantConditional == null) {
            throw new BusinessException(repetetive_Condition);
        }

        return true;
    }
}
