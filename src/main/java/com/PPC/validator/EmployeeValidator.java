package com.PPC.validator;

import com.PPC.constants.ErrorConstant;
import com.PPC.entity.Employee;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;
import java.util.function.Function;

public interface EmployeeValidator extends Function<Employee, ErrorConstant> {

    static EmployeeValidator isInputValid(){
        return employee -> Objects.isNull(employee) ? ErrorConstant.BAD_REQUEST_INVALID_DATA:null;
    }

    static EmployeeValidator isFirstNameValid(){
        return employee -> StringUtils.isBlank(employee.getFirstName()) ? ErrorConstant.BAD_REQUEST_INVALID_FIRST_NAME : null;
    }

    static EmployeeValidator isDesignationValid(){
        return employee -> StringUtils.isBlank(employee.getDesignation()) ? ErrorConstant.BAD_REQUEST_INVALID_DESIGNATION : null;
    }

    default EmployeeValidator and(EmployeeValidator other){
        return employee -> {
            ErrorConstant errorConstant = this.apply(employee);
            return Objects.isNull(errorConstant) ? other.apply(employee) : errorConstant;
        };
    }

}
