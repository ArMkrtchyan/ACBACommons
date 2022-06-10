package com.github.armkrtchyan.banking.ui.authentication.registration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.viewpager2.widget.ViewPager2
import com.github.armkrtchyan.banking.R
import com.github.armkrtchyan.banking.ui.authentication.registration.base.BaseRegistrationFragment
import com.github.armkrtchyan.banking.ui.authentication.registration.base.BaseRegistrationViewPagerFragment
import com.github.armkrtchyan.banking.ui.authentication.registration.base.PagerAdapter
import com.github.armkrtchyan.common.shared.extensions.getByResourceId
import com.github.armkrtchyan.common.widgets.CommonToolbar
import org.koin.androidx.viewmodel.ext.android.getViewModel

class RegistrationViewPagerFragment : BaseRegistrationViewPagerFragment<RegistrationViewPagerViewModel>(), View.OnClickListener {
    override val mViewModel: RegistrationViewPagerViewModel
        get() = getViewModel()
    override val mTitle: String
        get() = "Registration"
    override val mViewPagerFragments: List<BaseRegistrationFragment<*, *>>
        get() = listOf(PhoneNumberFragment.newInstance(), PhoneNumberFragment.newInstance())
    private val mToolbar by lazy {
        CommonToolbar(requireContext()).apply {
            title = mTitle
        }
    }
    private val mViewPagerAdapter by lazy { PagerAdapter(mViewPagerFragments, parentFragmentManager, lifecycle) }
    private val mViewPager by lazy {
        ViewPager2(requireContext()).apply {
            adapter = mViewPagerAdapter
            isUserInputEnabled = false
            registerOnPageChangeCallback(onPageChangeListener())
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = LinearLayoutCompat(requireContext())
        root.orientation = LinearLayoutCompat.VERTICAL
        root.addView(mToolbar)
        root.addView(mViewPager)
        mActivity.setSupportActionBar(mToolbar)
        return root
    }

    override fun onClick(p0: View?) {
        if (mViewPager.currentItem < mViewPagerFragments.size - 1)
            mViewPager.currentItem++
    }

    private fun onPageChangeListener(): ViewPager2.OnPageChangeCallback {
        return object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position > 0) {
                    mToolbar.navigationIcon = requireContext().getByResourceId(R.drawable.ic_back)
                    mToolbar.setNavigationOnClickListener {
                        if (mViewPager.currentItem != 0)
                            mViewPager.currentItem--
                    }
                } else {
                    mToolbar.navigationIcon = null
                    mToolbar.setNavigationOnClickListener(null)
                }
            }
        }
    }
}