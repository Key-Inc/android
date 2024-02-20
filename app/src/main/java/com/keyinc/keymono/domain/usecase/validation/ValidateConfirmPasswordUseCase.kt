package com.keyinc.keymono.domain.usecase.validation

import com.keyinc.keymono.domain.entity.ValidationResult

class ValidateConfirmPasswordUseCase {

    operator fun invoke(validationProperty: String, password: String, errorId: Int?): ValidationResult {
        if (validationProperty != password) {
            return ValidationResult(successful = false, errorId = errorId)
        }
        return ValidationResult(successful = true, errorId = null)
    }

}