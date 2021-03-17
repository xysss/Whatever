package com.xysss.whatever.official

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.xysss.core.view.custom.FragmentAdapter
import com.xysss.whatever.databinding.FragmentOfficialAccountsBinding
import com.xysss.whatever.official.list.OfficialListFragment
import com.xysss.whatever.project.BaseTabFragment
import dagger.hilt.android.AndroidEntryPoint

/**
 * Author:bysd-2
 * Time:2021/3/1711:19
 */

@AndroidEntryPoint
class OfficialAccountsFragment : BaseTabFragment() {

    private val viewModel by viewModels<OfficialViewModel>()
    private var binding: FragmentOfficialAccountsBinding? = null

    override fun getLayoutView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ): View {
        binding = FragmentOfficialAccountsBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    private lateinit var adapter: FragmentAdapter

    override fun initView() {
        adapter = FragmentAdapter(activity?.supportFragmentManager)
        binding?.apply {
            officialViewPager.adapter = adapter
            officialTabLayout.setupWithViewPager(officialViewPager)
            officialViewPager.addOnPageChangeListener(this@OfficialAccountsFragment)
            officialTabLayout.addOnTabSelectedListener(this@OfficialAccountsFragment)
        }
    }

    override fun initData() {
        startLoading()
        setDataStatus(viewModel.dataLiveData) {
            val nameList = mutableListOf<String>()
            val viewList = mutableListOf<Fragment>()
            it.forEach { project ->
                nameList.add(project.name)
                viewList.add(OfficialListFragment.newInstance(project.id))
            }
            adapter.apply {
                reset(nameList.toTypedArray())
                reset(viewList)
                notifyDataSetChanged()
            }
            binding?.officialViewPager?.currentItem = viewModel.position
        }
        getOfficialTree()
    }

    private fun getOfficialTree() {
        viewModel.getDataList(false)
    }

    override fun onTabPageSelected(position: Int) {
        viewModel.position = position
    }

    companion object {
        @JvmStatic
        fun newInstance() = OfficialAccountsFragment()
    }

}
