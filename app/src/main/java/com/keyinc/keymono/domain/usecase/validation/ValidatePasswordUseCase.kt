package com.keyinc.keymono.domain.usecase.validation

import com.keyinc.keymono.domain.entity.ValidationResult

class ValidatePasswordUseCase : BaseValidationUseCase {

    override operator fun invoke(validationProperty: String, errorId: Int?): ValidationResult {

        if (validationProperty.isBlank() || validationProperty.length < 8) {
            return ValidationResult(successful = false, errorId = errorId)
        }
        return ValidationResult(successful = true, errorId = null)
    }

}