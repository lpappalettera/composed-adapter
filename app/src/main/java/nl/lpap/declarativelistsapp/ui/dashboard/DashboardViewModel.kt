package nl.lpap.declarativelistsapp.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DashboardViewModel : ViewModel() {

    private val _items = MutableLiveData<List<DashboardItem>>().apply {
        value = (1..10).map { DashboardItem(it, "Title $it", "Subtitle $it") }.toList()
    }
    val items: LiveData<List<DashboardItem>> = _items

}