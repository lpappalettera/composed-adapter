package nl.lpap.declarativelistsapp.ui.dashboard

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import nl.lpap.declarativelistsapp.R
import nl.lpap.declarativelistsapp.databinding.FragmentDashboardBinding
import nl.lpap.declarativelistsapp.databinding.ItemDashboardBinding
import nl.lpap.declarativelistsapp.ui.ComposedAdapter
import nl.lpap.declarativelistsapp.ui.viewHolderFrom


/**
 * Showcase fragment for showing the usage of the [ComposedAdapter] in combination with data binding
 */
class DashboardFragment : Fragment(R.layout.fragment_dashboard) {

    private lateinit var binding: FragmentDashboardBinding

    private val viewModel by viewModels<DashboardViewModel>()

    private val adapter= ComposedAdapter(
            items = emptyList<DashboardItem>(),
            viewHolderCreator = { parent, _ ->
                parent.viewHolderFrom(ItemDashboardBinding::inflate)
            },
            viewHolderBinder = { viewHolder, item, _ ->
                viewHolder.binding.data = item
            }
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDashboardBinding.bind(view)

        binding.list.adapter = adapter

        viewModel.items.observe(viewLifecycleOwner) {
            adapter.update(it) { item -> item.id }
        }
    }

}