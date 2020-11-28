package nl.lpap.declarativelistsapp.ui.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NotificationsViewModel : ViewModel() {

    private var counter = 0

    private val _items = MutableLiveData<List<NotificationItem>>().apply {
        value = listOf(NotificationItem(0, "Add notification"))
    }
    val items: LiveData<List<NotificationItem>> = _items

    fun addNotification() {
        counter++
        val items = _items.value?.toMutableList() ?: mutableListOf()
        items.add(NotificationItem(counter, "Notification: $counter"))
        _items.value = items
    }

    fun removeNotification(item: NotificationItem) {
        val items = _items.value?.toMutableList() ?: mutableListOf()
        items.remove(item)
        _items.value = items
    }
}