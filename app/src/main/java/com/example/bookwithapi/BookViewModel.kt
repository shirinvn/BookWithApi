package com.example.bookwithapi

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext






class BookViewModel : ViewModel() {


    val bookList= MutableLiveData<List<BookResponceModel>>()
    val bookCaption= MutableLiveData<List<BookResponceModel>>()
    val postListError= MutableLiveData<String?>()
    val loading= MutableLiveData<Boolean>()

    fun getAllBookSRequest(){
        loading.value= true


        val coroutineExceptionHandler = CoroutineExceptionHandler{_, throwable ->
            throwable.printStackTrace()
        }

        CoroutineScope(Dispatchers.IO + coroutineExceptionHandler).launch {
            val response = ApiService.api.getAll()

            withContext(Dispatchers.Main) {
                if (response.isSuccessful && response.body() != null) {
                    response.body()?.let { allBooks ->
                        bookList.value = allBooks
                        postListError.value = null
                        loading.value = false

                    }
                } else {
                    postListError.value = response.message()
                    loading.value = false
                } } } }

    fun getCaptionReq(title:String){
        loading.value= true
        val coroutineExceptionHandler = CoroutineExceptionHandler{_, throwable ->
            throwable.printStackTrace() }
        CoroutineScope(Dispatchers.IO + coroutineExceptionHandler).launch {


            val response = ApiService.api.getCaptionItem(title  )

            withContext(Dispatchers.Main) {
                if (response.isSuccessful && response.body() != null) {
                    response.body()?.let { Captions ->
                        bookCaption.value = Captions
                        postListError.value = null
                        loading.value = false

                    }
                } else {
                    postListError.value = response.message()
                    loading.value = false
                }


            }

        }
    }

}
