package com.example.vkmessenger.ui.friendsonline

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vkmessenger.VkRepository
import com.example.vkmessenger.local.Friend
import com.example.vkmessenger.network.Result
import kotlinx.coroutines.launch

class FriendsOnlineViewModel(private val vkRepository: VkRepository) :
    ViewModel() {

    private val _friendsOnline = MutableLiveData<List<Friend>>()
    val friendsOnline: LiveData<List<Friend>> = _friendsOnline

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    val loading = MutableLiveData(false)

    fun refreshFriendsOnline() {
        getFriendsOnline()
    }

    private fun getFriendsOnline() {
        viewModelScope.launch {
            when (val onlineIdsResult = vkRepository.getOnlineFriendsIds()) {
                is Result.Success -> {
                    _friendsOnline.value = vkRepository.getOnlineFriends(onlineIdsResult.data)
                    loading.value = true
                }
                is Result.Error -> {
                    _message.value = onlineIdsResult.getString()
                    _friendsOnline.value = emptyList()
                }
            }
        }
    }

    fun updateFriend(friend: Friend) {
        viewModelScope.launch {
            vkRepository.updateFriend(friend)
        }
    }
}