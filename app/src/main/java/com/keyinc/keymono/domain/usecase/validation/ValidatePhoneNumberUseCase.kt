package com.keyinc.keymono.domain.usecase.validation

import com.keyinc.keymono.domain.entity.ValidationResult

class ValidatePhoneNumberUseCase : BaseValidationUseCase {

    private companion object {
        const val PHONE_NUMBER_LENGTH = 10
    }

    override fun invoke(validationProperty: String, errorId: Int?): ValidationResult {
        return if (validationProperty.isNotBlank()
            && validationProperty.length == PHONE_NUMBER_LENGTH
            && validationProperty.matches(Regex("[0-9]+"))
        ) {
            ValidationResult(successful = true, errorId = null)
        } else {
            ValidationResult(successful = false, errorId = errorId)
        }
    }
}
