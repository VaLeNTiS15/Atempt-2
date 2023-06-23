package com.example.definition.presentation.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.definition.data.cloud.model.Word
import com.example.definition.databinding.FragmentFavoriteBinding
import com.example.definition.utils.Utils
import kotlin.properties.Delegates

class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<FavoriteViewModel>()

    private var isNoWords by Delegates.observable(true) { _, _, newValue ->
        binding.noWordTextView.visibility = if (newValue) View.VISIBLE else View.GONE
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.favoriteRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.favoriteRecyclerView.addItemDecoration(
            DividerItemDecoration(binding.favoriteRecyclerView.context, DividerItemDecoration.VERTICAL)
        )
        viewModel.observe(requireContext(), requireActivity())
        {
            val listOfWords = mutableListOf<Word>()
            it.forEach { wordDb ->
                listOfWords.add(Utils.convertStringIntoWord(wordDb.wordString))
            }
            isNoWords = listOfWords.isEmpty()
            val adapter = FavoriteAdapter(listOfWords, findNavController())
            binding.favoriteRecyclerView.adapter = adapter
        }

    }

}