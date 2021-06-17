package com.tp.comerciotp.presentation.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.tp.comerciotp.core.Resource
import com.tp.comerciotp.domain.auth.CloseSessionRepo
import kotlinx.coroutines.Dispatchers

class CloseSessionViewModel(private val repo : CloseSessionRepo ) : ViewModel(){

    fun singOut() = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        if(repo.singOut()){
            try{
                emit(Resource.Success(true))
            }catch (e : Exception){
                emit(Resource.Failure(e))
            }
        }
    }
}

class CloseSessionViewModelFactory(private val repo: CloseSessionRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CloseSessionViewModel(repo) as T
    }

}