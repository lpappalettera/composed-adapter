package nl.lpap.declarativelistsapp.ui.notifications

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import nl.lpap.declarativelistsapp.R
import nl.lpap.declarativelistsapp.databinding.FragmentNotificationsBinding
import nl.lpap.declarativelistsapp.databinding.ItemNotificationBinding
import nl.lpap.declarativelistsapp.ui.ComposedAdapter
import nl.lpap.declarativelistsapp.ui.viewHolderFrom

/**
 * Showcase fragment for showing the usage of the diffUtil in de [ComposedAdapter]
 * - Items can be added by clicking on the first item
 * - Others items are removed by clicking on them
 */
class NotificationsFragment : Fragment(R.layout.fragment_notifications) {

    private lateinit var binding: FragmentNotificationsBinding

    private val viewModel by viewModels<NotificationsViewModel>()

    private val adapter= ComposedAdapter(
            items = emptyList<NotificationItem>(),
            viewHolderCreator = { parent, _ ->
                parent.viewHolderFrom(ItemNotificationBinding::inflate)
            },
            viewHolderBinder = { viewHolder, item, _ ->
                viewHolder.binding.data = item
                viewHolder.itemView.setOnClickListener {
                    if(item.id == 0)
                        viewModel.addNotification()
                    else
                        viewModel.removeNotification(item)
                }
            }
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNotificationsBinding.bind(view)

        binding.list.adapter = adapter

        viewModel.items.observe(viewLifecycleOwner) {
            adapter.update(it) { item -> item.id }
        }
    }
}