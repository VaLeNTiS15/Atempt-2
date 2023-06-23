package com.example.definition.presentation.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.definition.data.cloud.core.ApiResult
import com.example.definition.databinding.FragmentSearchBinding
import com.example.definition.presentation.main.MainFragmentDirections
import com.example.definition.utils.Utils

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<SearchViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.definitionButton.setOnClickListener {
            if (isWordValid(binding.wordEditText.text.toString())) {
                binding.progressBar.visibility = View.VISIBLE
                viewModel.getDefinition(binding.wordEditText.text.toString().trim())
            } else {
                showToast("Word is not valid!")
            }
        }

        viewModel.apiResultLiveData.observe(viewLifecycleOwner) { result ->
            binding.progressBar.visibility = View.GONE

            if (result is ApiResult.Success) {
                val wordString = Utils.convertWordIntoString(result.word)
                findNavController().navigate(MainFragmentDirections.actionMainFragmentToDefinitionFragment(wordString))
            } else {
                showToast("Unable to find definition!")
            }
        }

    }

    private fun isWordValid(word: String): Boolean {
        return word.trim().isNotEmpty()
    }

    private fun showToast(text: String) {
        Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
    }

}