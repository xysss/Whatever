package com.xysss.whatever.project.list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import com.xysss.core.view.base.BaseAndroidViewModel
import com.xysss.model.pojo.QueryArticle
import com.xysss.model.room.entity.Article
import com.xysss.whatever.project.ProjectRepository
import dagger.hilt.android.scopes.FragmentScoped

/**
 * Author:bysd-2
 * Time:2021/3/1711:23
 */
@FragmentScoped
class ProjectListViewModel @ViewModelInject constructor(
    private val projectRepository: ProjectRepository
) : BaseAndroidViewModel<List<Article>, Article, QueryArticle>() {

    override fun getData(page: QueryArticle): LiveData<Result<List<Article>>> {
        return projectRepository.getProject(page)
    }

}