package com.github.armkrtchyan.banking.ui.authentication.registration

import com.github.armkrtchyan.banking.databinding.FragmentPhoneNumberBinding
import com.github.armkrtchyan.banking.ui.authentication.registration.base.BaseRegistrationFragment
import com.github.armkrtchyan.common.shared.Inflater
import com.github.armkrtchyan.common.widgets.CommonButton
import org.koin.androidx.viewmodel.ext.android.getViewModel

class PhoneNumberFragment : BaseRegistrationFragment<FragmentPhoneNumberBinding, PhoneNumberViewModel>() {
    companion object {
        fun newInstance() = PhoneNumberFragment()
    }

    override val mNextButton: CommonButton
        get() = mBinding.validate
    override val inflate: Inflater<FragmentPhoneNumberBinding>
        get() = FragmentPhoneNumberBinding::inflate
    override val mViewModel: PhoneNumberViewModel
        get() = getViewModel()

}