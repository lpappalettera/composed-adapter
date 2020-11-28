package nl.lpap.declarativelistsapp.ui.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import nl.lpap.declarativelistsapp.R.layout.fragment_home
import nl.lpap.declarativelistsapp.databinding.FragmentHomeBinding
import nl.lpap.declarativelistsapp.databinding.FragmentHomeBinding.bind
import nl.lpap.declarativelistsapp.databinding.ItemEvenBinding
import nl.lpap.declarativelistsapp.databinding.ItemOddBinding
import nl.lpap.declarativelistsapp.ui.ComposedAdapter
import nl.lpap.declarativelistsapp.ui.viewHolderFrom

/**
 * Showcase fragment for showing how to use the [ComposedAdapter] with multiple viewTypes
 */
class HomeFragment : Fragment(fragment_home) {

    private lateinit var binding: FragmentHomeBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = bind(view)

        binding.list.apply {
            adapter = ComposedAdapter(
                items = (1..100).toList(),
                viewHolderCreator = { parent, viewType ->
                    when (viewType) {
                        0 -> parent.viewHolderFrom(ItemEvenBinding::inflate)
                        1 -> parent.viewHolderFrom(ItemOddBinding::inflate)
                        else -> throw IllegalArgumentException("Invalid viewType")
                    }
                },
                viewHolderBinder = { viewHolder, item, _ ->
                    when (viewHolder.binding) {
                        is ItemEvenBinding -> viewHolder.binding.apply {
                            label.text = item.toString()
                            viewHolder.itemView.setOnClickListener { onItemClicked(item) }
                        }
                        is ItemOddBinding -> viewHolder.binding.apply {
                            label.text = item.toString()
                            viewHolder.itemView.setOnClickListener { onItemClicked(item) }
                        }
                    }
                },
                viewTypeFunction = { it % 2 }
            )
        }

    }

    private fun onItemClicked(item: Int) {
        Toast.makeText(
            activity, "Item $item was clicked",
            Toast.LENGTH_SHORT
        ).show()
    }

}




