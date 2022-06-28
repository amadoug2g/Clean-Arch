package com.playgroundagc.cleanarch.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.playgroundagc.cleanarch.R
import com.playgroundagc.cleanarch.databinding.FragmentListBinding
import com.playgroundagc.cleanarch.framework.ListViewModel

class ListFragment : Fragment(), ListAction {

    private lateinit var binding: FragmentListBinding

    private val adapter = NotesListAdapter(arrayListOf(), this)

    private val viewModel by activityViewModels<ListViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.listRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.listRecyclerView.adapter = adapter

        binding.addNoteFAB.setOnClickListener { navigateToDetails() }

        observeViewModel()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getNotes()
    }

    override fun onClick(id: Long) {
        navigateToDetails(id)
    }

    private fun observeViewModel() {
        viewModel.notes.observe (this) { list ->
            binding.loadingView.visibility = View.GONE
            binding.listRecyclerView.visibility = View.VISIBLE
            adapter.updateNotes(list.sortedByDescending { it.updateTime })
        }
    }

    private fun navigateToDetails(id: Long = 0L) {
        val action = ListFragmentDirections.listFragmentToNoteFragment(id)
        Navigation.findNavController(binding.listRecyclerView).navigate(action)
    }
}