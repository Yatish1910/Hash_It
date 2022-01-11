package com.example.hashit

import android.os.Bundle
import android.view.*
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.hashit.databinding.FragmentHomeBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {
    private val classViewModel : ClassViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding
    override fun onResume() {
        super.onResume()
        val hashAlgorithms = resources.getStringArray(R.array.hash_algorithms)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.drop_down_menu, hashAlgorithms)
        binding.autoCompleteTextView.setAdapter(arrayAdapter)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentHomeBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)


        binding.generateButton.setOnClickListener {
            onClicked()
        }
        return binding.root
    }

    private suspend fun animations() {
        binding.titleText.animate().alpha(0f).duration = 400L
        binding.generateButton.animate().alpha(0f).duration = 400L
        binding.textInputLayout.animate()
            .alpha(0f)
            .translationXBy(1200f)
            .duration = 400L
        binding.plainText.animate().alpha(0f).translationXBy(-1200f).duration = 400L

        delay(300)

        binding.backgroundSuccess.animate().alpha(1f).duration = 600L
        binding.backgroundSuccess.animate().rotationBy(360f).duration = 600L
        binding.backgroundSuccess.animate().scaleXBy(1000f).duration = 800L
        binding.backgroundSuccess.animate().scaleYBy(1000f).duration = 800L

        delay(300)

        binding.successView.animate().alpha(1f).duration = 1000L
        delay(1500)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.clear_menu){
            binding.plainText.text.clear()
            snackBar("Cleared")
            return true
        }
        return true
    }

    private fun onClicked() {
        if (binding.plainText.text.isEmpty()) {
            snackBar("Please Enter a Text")
        } else {
            lifecycleScope.launch {

                animations()
                navigateToSuccess(getHashData())
            }
        }
    }

    private fun navigateToSuccess(hash:String) {
        val directions = HomeFragmentDirections.actionHomeFragmentToBlankFragment2(hash)
        findNavController().navigate(directions)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.home_menu, menu)
    }
    private fun getHashData():String{
        val algorithm = binding.autoCompleteTextView.text.toString()
        val plainText = binding.plainText.text.toString()
        return classViewModel.hash(plainText,algorithm)
    }
    private fun snackBar(message: String) {
        Snackbar.make(
            binding.rootLayout,
            message,
            Snackbar.LENGTH_SHORT
        ).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()

    }
}