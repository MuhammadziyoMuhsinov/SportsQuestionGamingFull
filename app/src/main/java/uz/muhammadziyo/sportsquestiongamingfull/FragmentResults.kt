package uz.muhammadziyo.sportsquestiongamingfull

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.muhammadziyo.sportsquestiongamingfull.databinding.FragmentResultsBinding


class FragmentResults : Fragment() {

    private lateinit var binding:FragmentResultsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentResultsBinding.inflate(layoutInflater)



        return binding.root

    }


}