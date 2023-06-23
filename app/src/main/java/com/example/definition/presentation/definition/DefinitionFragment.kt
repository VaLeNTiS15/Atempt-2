package com.example.definition.presentation.definition

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.definition.R
import com.example.definition.databinding.FragmentDefinitionBinding
import com.example.definition.utils.Utils

class DefinitionFragment : Fragment() {

    private var _binding: FragmentDefinitionBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<DefinitionViewModel>()
    private val args: DefinitionFragmentArgs by navArgs()

    private var isWordFavorite = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDefinitionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.init(requireContext())

        val toast = Toast(requireContext()).apply {
            duration = Toast.LENGTH_SHORT
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val wordString = args.wordString

        if (wordString.isNotEmpty()) {
            viewModel.getWord(wordString)

            binding.favoriteImageView.setOnClickListener {
                if (isWordFavorite) {
                    viewModel.deleteWord(wordString)
                    toast.setText("Removed from favorite!")
                    toast.show()
                } else {
                    viewModel.saveWord(wordString)
                    toast.setText("Added to favorite!")
                    toast.show()
                }
                isWordFavorite = !isWordFavorite
                updateFavoriteImage(binding.favoriteImageView)
            }

            val word = Utils.convertStringIntoWord(wordString)

            binding.titleWordTextView.text = word.word
            binding.transcriptionTextView.text = word.phonetics.last().text

            val adapter = MeaningAdapter(word.meanings)
            binding.recyclerView.adapter = adapter
            binding.recyclerView.addItemDecoration(
                DividerItemDecoration(binding.recyclerView.context, DividerItemDecoration.VERTICAL)
            )
        } else {
            Toast.makeText(requireContext(), "Something went wrong!", Toast.LENGTH_SHORT).show()
        }

        viewModel.wordLiveData.observe(viewLifecycleOwner) {
            if (it == null) {
                isWordFavorite = false
                updateFavoriteImage(binding.favoriteImageView)
            } else {
                isWordFavorite = true
                updateFavoriteImage(binding.favoriteImageView)
            }
        }
    }

    private fun updateFavoriteImage(imageView: ImageView) {
        if (isWordFavorite) {
            imageView.setImageResource(R.drawable.ic_baseline_favorite_24)
        } else {
            imageView.setImageResource(R.drawable.ic_baseline_favorite_border_24)
        }
    }

}