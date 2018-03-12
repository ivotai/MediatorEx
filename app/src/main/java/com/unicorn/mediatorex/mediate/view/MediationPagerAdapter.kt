package com.unicorn.mediatorex.mediate.view

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

class MediationPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    companion object {

        val titles = listOf("待接受","待接受","待接受","待接受")

    }

    override fun getItem(position: Int) = MediationFrag()

    override fun getCount()= titles.size

    override fun getPageTitle(position: Int)= titles[position]
}