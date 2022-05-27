package id.accurate.pos.ui.insert

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import id.accurate.pos.data.remote.request.RequestUser
import id.accurate.pos.repository.ContentRepository

class InsertUserViewModel(private val contentRepository: ContentRepository) : ViewModel() {

    fun insertUser(requestUser: RequestUser) : LiveData<String> = contentRepository.insertUser(requestUser)

    fun getCities() = contentRepository.getCities()

}