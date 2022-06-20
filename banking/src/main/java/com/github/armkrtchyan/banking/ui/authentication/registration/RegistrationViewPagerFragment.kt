package com.github.armkrtchyan.banking.ui.authentication.registration

import android.os.Bundle
import androidx.lifecycle.LiveData
import com.github.armkrtchyan.banking.ui.authentication.registration.base.BasePagerFragment
import com.github.armkrtchyan.banking.ui.authentication.registration.base.BaseViewPagerFragment
import com.github.armkrtchyan.banking.ui.authentication.registration.emailverification.EmailFragment
import com.github.armkrtchyan.banking.ui.authentication.registration.models.RegistrationRequestModel
import com.github.armkrtchyan.banking.ui.authentication.registration.phoneverification.PhoneNumberFragment
import org.koin.androidx.viewmodel.ext.android.getViewModel

class RegistrationViewPagerFragment : BaseViewPagerFragment<RegistrationViewPagerViewModel, RegistrationRequestModel>() {
    private val fragments = listOf<BasePagerFragment<*, *>>(PhoneNumberFragment.newInstance(), EmailFragment.newInstance())
    override val mRequestModel: RegistrationRequestModel
        get() = RegistrationRequestModel()
    override val mViewModel: RegistrationViewPagerViewModel
        get() = getViewModel()
    override val mTitle: String
        get() = "Registration"
    override val mLastButtonTitle: String
        get() = "Register"
    override val mViewPagerFragments: List<BasePagerFragment<*, *>>
        get() = fragments
    override val mLiveDataList: List<LiveData<*>>
        get() = listOf(mViewModel.firstLiveData, mViewModel.secondLiveData, mViewModel.thirdLiveData, mViewModel.fourthLiveData)

    override fun onLastPageButtonClick() {
        mViewModel.register(mRequestModel)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel.firstRequest()
        mViewModel.secondRequest()
        mViewModel.thirdRequest()
        mViewModel.fourthRequest()
    }
}