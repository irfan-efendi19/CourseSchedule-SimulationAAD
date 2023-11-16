package com.dicoding.courseschedule.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.dicoding.courseschedule.data.Course
import com.dicoding.courseschedule.data.DataRepository
import com.dicoding.courseschedule.util.QueryType

class HomeViewModel(repository: DataRepository): ViewModel() {

    private val _queryType = MutableLiveData<QueryType>()

    init {
        _queryType.value = QueryType.CURRENT_DAY
    }

    fun setQueryType(queryType: QueryType) {
        _queryType.value = queryType
    }

    private val _course = _queryType.switchMap { queryType ->
        repository.getNearestSchedule(queryType)
    }

    val course: LiveData<Course?> = _course
}
