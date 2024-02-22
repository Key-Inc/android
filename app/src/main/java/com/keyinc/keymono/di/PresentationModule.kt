package com.keyinc.keymono.di

import com.keyinc.keymono.domain.repository.AccountRepository
import com.keyinc.keymono.domain.repository.ClassroomRepository
import com.keyinc.keymono.domain.usecase.account.LoginUserUseCase
import com.keyinc.keymono.domain.usecase.classroom.GetClassroomsUseCase
import com.keyinc.keymono.domain.usecase.validation.ValidateConfirmPasswordUseCase
import com.keyinc.keymono.domain.usecase.validation.ValidateEmailUseCase
import com.keyinc.keymono.domain.usecase.validation.ValidatePasswordUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
object PresentationModule {

    @Provides
    fun provideValidatePasswordUseCase() = ValidatePasswordUseCase()

    @Provides
    fun provideValidateEmailUseCase() = ValidateEmailUseCase()

    @Provides
    fun provideValidateConfirmPasswordUseCase() = ValidateConfirmPasswordUseCase()

    @Provides
    fun provideLoginUserUseCase(accountRepository: AccountRepository)
        = LoginUserUseCase(accountRepository)

    @Provides
    fun provideGetClassroomsUseCase(classroomRepository: ClassroomRepository)
        = GetClassroomsUseCase(classroomRepository)

}