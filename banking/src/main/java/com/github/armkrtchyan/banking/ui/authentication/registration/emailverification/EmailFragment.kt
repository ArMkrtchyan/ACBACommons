package com.github.armkrtchyan.banking.ui.authentication.registration.emailverification

import com.github.armkrtchyan.banking.databinding.FragmentPhoneNumberBinding
import com.github.armkrtchyan.banking.ui.authentication.registration.base.BasePagerFragment
import com.github.armkrtchyan.common.shared.Inflater
import com.github.armkrtchyan.common.widgets.CommonButton
import org.koin.androidx.viewmodel.ext.android.getViewModel

class EmailFragment : BasePagerFragment<FragmentPhoneNumberBinding, EmailViewModel>() {
    companion object {
        fun newInstance() = EmailFragment()
    }

    override val mNextButton: CommonButton
        get() = mBinding.validate
    override val inflate: Inflater<FragmentPhoneNumberBinding>
        get() = FragmentPhoneNumberBinding::inflate
    override val mViewModel: EmailViewModel
        get() = getViewModel()
    override val mTitle: String
        get() = "Email verification"
}