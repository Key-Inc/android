package com.keyinc.keymono.domain.usecase.validation

import com.keyinc.keymono.domain.entity.ValidationResult

class ValidateFullNameUseCase : BaseValidationUseCase {

    private companion object {
        const val MIN_NAME_LENGTH = 2
        const val MAX_NAME_LENGTH = 50
    }

    override fun invoke(validationProperty: String, errorId: Int?): ValidationResult {
        return if (validationProperty.isNotBlank()
            && validationProperty.length in MIN_NAME_LENGTH..MAX_NAME_LENGTH
        ) {
            ValidationResult(successful = true, errorId = null)
        } else {
            ValidationResult(successful = false, errorId = errorId)
        }
    }

}