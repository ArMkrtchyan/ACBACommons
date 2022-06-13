package com.github.armkrtchyan.banking.ui.authentication.registration

import com.github.armkrtchyan.banking.ui.authentication.registration.base.BaseRegistrationFragment
import com.github.armkrtchyan.banking.ui.authentication.registration.base.BaseRegistrationViewPagerFragment
import com.github.armkrtchyan.banking.ui.authentication.registration.emailverification.EmailFragment
import com.github.armkrtchyan.banking.ui.authentication.registration.models.RegistrationRequestModel
import com.github.armkrtchyan.banking.ui.authentication.registration.phoneverification.PhoneNumberFragment
import org.koin.androidx.viewmodel.ext.android.getViewModel

class RegistrationViewPagerFragment : BaseRegistrationViewPagerFragment<RegistrationViewPagerViewModel, RegistrationRequestModel>() {
    override val mRequestModel: RegistrationRequestModel
        get() = RegistrationRequestModel()
    override val mViewModel: RegistrationViewPagerViewModel
        get() = getViewModel()
    override val mTitle: String
        get() = "Registration"
    override val mViewPagerFragments: List<BaseRegistrationFragment<*, *>>
        get() = listOf(PhoneNumberFragment.newInstance(), EmailFragment.newInstance())

    override fun register() {
        mViewModel.register(mRequestModel)
    }
}