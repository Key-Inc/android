package com.keyinc.keymono.domain.usecase.validation

import com.keyinc.keymono.domain.entity.ValidationResult

class ValidatePasswordUseCase : BaseValidationUseCase {

    private companion object {
        const val MIN_PASSWORD_LENGTH = 8
    }

    override operator fun invoke(validationProperty: String, errorId: Int?): ValidationResult {

        if (validationProperty.isBlank() || validationProperty.length < MIN_PASSWORD_LENGTH) {
            return ValidationResult(successful = false, errorId = errorId)
        }
        return ValidationResult(successful = true, errorId = null)
    }

}