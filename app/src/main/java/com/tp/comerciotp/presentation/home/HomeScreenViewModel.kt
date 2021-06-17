package com.tp.comerciotp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.tp.comerciotp.core.Resource
import com.tp.comerciotp.data.model.QRRequest
import com.tp.comerciotp.domain.home.HomeRepo
import kotlinx.coroutines.Dispatchers

class HomeScreenViewModel(private val repo : HomeRepo ) : ViewModel(){

    fun getQR(qrRequest: QRRequest) = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try{
            emit(Resource.Success(repo.getQR(qrRequest)))
        }catch (e: Exception){
            emit(Resource.Failure(e))
        }
    }
}


class HomeScreenViewModelFactory(private val repo : HomeRepo): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeScreenViewModel(repo) as T
    }

}