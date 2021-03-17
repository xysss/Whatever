package com.xysss.whatever.project

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import com.xysss.core.view.base.BaseAndroidViewModel
import com.xysss.model.room.entity.ProjectClassify
import dagger.hilt.android.scopes.FragmentScoped

/**
 * Author:bysd-2
 * Time:2021/3/1711:24
 */

@FragmentScoped
class ProjectViewModel @ViewModelInject constructor(
    private val projectRepository: ProjectRepository
) :
    BaseAndroidViewModel<List<ProjectClassify>, Unit, Boolean>() {

    var position = 0

    override fun getData(page: Boolean): LiveData<Result<List<ProjectClassify>>> {
        return projectRepository.getProjectTree(page)
    }

}