package com.github.armkrtchyan.banking.ui.authentication.registration.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.viewpager2.widget.ViewPager2
import com.github.armkrtchyan.banking.R
import com.github.armkrtchyan.common.base.BaseViewModel
import com.github.armkrtchyan.common.base.BaseViewModelFragment
import com.github.armkrtchyan.common.shared.extensions.getByResourceId
import com.github.armkrtchyan.common.shared.extensions.log
import com.github.armkrtchyan.common.state.State
import com.github.armkrtchyan.common.widgets.CommonToolbar

abstract class BaseRegistrationViewPagerFragment<VIEWMODEL : BaseViewModel, REQUESTMODEL> : BaseViewModelFragment<VIEWMODEL>(), View.OnClickListener {
    abstract val mRequestModel: REQUESTMODEL
    abstract val mTitle: String
    abstract val mLastButtonTitle: String
    abstract val mViewPagerFragments: List<BaseRegistrationFragment<*, *>>
    open val mLiveDataList: List<LiveData<*>> = arrayListOf()
    private val mToolbar by lazy { CommonToolbar(requireContext()) }
    private val mViewPagerAdapter by lazy { PagerAdapter(mViewPagerFragments, parentFragmentManager, lifecycle) }
    private val mViewPager by lazy {
        ViewPager2(requireContext()).apply {
            adapter = mViewPagerAdapter
            offscreenPageLimit = mViewPagerFragments.size
            isUserInputEnabled = false
            if (mLiveDataList.isNotEmpty())
                visibility = View.INVISIBLE
        }
    }

    abstract fun register()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = LinearLayoutCompat(requireContext())
        root.orientation = LinearLayoutCompat.VERTICAL
        root.addView(mToolbar)
        root.addView(mViewPager)
        mActivity.setSupportActionBar(mToolbar)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (mLiveDataList.isNotEmpty())
            commonLiveData().observe(viewLifecycleOwner) {
                if (it) {
                    mViewPager.visibility = View.VISIBLE
                    mViewModel.stateFlow.value = State.Success
                }
            }
    }

    override fun onResume() {
        super.onResume()
        mViewPager.registerOnPageChangeCallback(onPageChangeListener)
    }

    override fun onStop() {
        super.onStop()
        mViewPager.unregisterOnPageChangeCallback(onPageChangeListener)
    }

    override fun onClick(p0: View?) {
        if (!isLastPage())
            mViewPager.currentItem++
        else register()
    }

    private val onPageChangeListener: ViewPager2.OnPageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
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
            mViewPagerFragments[position].mButtonTextStateFlow.value.log("StateFlowTag", "pre: ")
            if (isLastPage())
                mViewPagerFragments[position].mButtonTextStateFlow.value = mLastButtonTitle
            else mViewPagerFragments[position].mButtonTextStateFlow.value = "Next"
            mViewPagerFragments[position].mButtonTextStateFlow.value.log("StateFlowTag", "after: ")
        }
    }

    fun navigatePreviousPage(): Boolean {
        val isCanGoBack = mViewPager.currentItem != 0
        if (mViewPager.currentItem != 0)
            mViewPager.currentItem--
        return isCanGoBack
    }

    private fun isLastPage() = mViewPager.currentItem == mViewPagerFragments.size - 1

    private fun commonLiveData(): LiveData<Boolean> {
        mViewModel.stateFlow.value = State.Loading
        val mLiveData = MutableLiveData<Boolean>()
        var counter = 0
        for (liveData in mLiveDataList) {
            liveData.observe(viewLifecycleOwner) {
                counter++
                if (counter == mLiveDataList.size) mLiveData.postValue(true)
            }
        }
        return mLiveData
    }
}