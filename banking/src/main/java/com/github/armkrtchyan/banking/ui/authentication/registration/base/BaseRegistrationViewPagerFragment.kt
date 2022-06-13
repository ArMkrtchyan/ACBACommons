package com.github.armkrtchyan.banking.ui.authentication.registration.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.viewpager2.widget.ViewPager2
import com.github.armkrtchyan.banking.R
import com.github.armkrtchyan.common.base.BaseViewModel
import com.github.armkrtchyan.common.base.BaseViewModelFragment
import com.github.armkrtchyan.common.shared.extensions.getByResourceId
import com.github.armkrtchyan.common.widgets.CommonToolbar

abstract class BaseRegistrationViewPagerFragment<VIEWMODEL : BaseViewModel, REQUESTMODEL> : BaseViewModelFragment<VIEWMODEL>(), View.OnClickListener {
    abstract val mRequestModel: REQUESTMODEL
    abstract val mTitle: String
    abstract val mViewPagerFragments: List<BaseRegistrationFragment<*, *>>
    abstract fun register()
    private val mToolbar by lazy { CommonToolbar(requireContext()) }
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
        else register()
    }

    private fun onPageChangeListener(): ViewPager2.OnPageChangeCallback {
        return object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position > 0) {
                    mToolbar.navigationIcon = requireContext().getByResourceId(R.drawable.ic_back)
                    mToolbar.setNavigationOnClickListener {
                        navigatePreviousPage()
                    }
                } else {
                    mToolbar.navigationIcon = null
                    mToolbar.setNavigationOnClickListener(null)
                }
                mViewPagerFragments[position].mTitle?.let {
                    mToolbar.title = it
                } ?: run {
                    mToolbar.title = mTitle
                }
            }
        }
    }

    fun navigatePreviousPage(): Boolean {
        val isCanGoBack = mViewPager.currentItem != 0
        if (mViewPager.currentItem != 0)
            mViewPager.currentItem--
        return isCanGoBack
    }
}