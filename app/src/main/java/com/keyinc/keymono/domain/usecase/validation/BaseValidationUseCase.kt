package com.keyinc.keymono.domain.usecase.validation

import com.keyinc.keymono.domain.entity.ValidationResult

interface BaseValidationUseCase {

    operator fun invoke(validationProperty: String, errorId: Int?) : ValidationResult

}