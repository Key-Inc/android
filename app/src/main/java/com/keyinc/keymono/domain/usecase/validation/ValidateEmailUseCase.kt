package com.keyinc.keymono.domain.usecase.validation

import com.keyinc.keymono.domain.entity.ValidationResult

class ValidateEmailUseCase : BaseValidationUseCase {

    private companion object {
        const val emailPattern = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}"
    }


    override operator fun invoke(validationProperty: String, errorId: Int?): ValidationResult {

        if (validationProperty.isBlank() || Regex(emailPattern).matches(validationProperty).not()) {
            return ValidationResult(successful = false, errorId = errorId)
        }
        return ValidationResult(successful = true, errorId = null)
    }

}