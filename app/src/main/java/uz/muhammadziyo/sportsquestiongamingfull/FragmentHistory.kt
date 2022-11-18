package uz.muhammadziyo.sportsquestiongamingfull

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.muhammadziyo.sportsquestiongamingfull.adapter.RvAdapter
import uz.muhammadziyo.sportsquestiongamingfull.databinding.FragmentHistoryBinding
import uz.muhammadziyo.sportsquestiongamingfull.kesh.MySharedPreferences
import uz.muhammadziyo.sportsquestiongamingfull.models.Game


class FragmentHistory : Fragment() {

    private lateinit var list:ArrayList<Game>
    private lateinit var binding:FragmentHistoryBinding
    private lateinit var adapter : RvAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentHistoryBinding.inflate(layoutInflater)

        list = ArrayList()
        MySharedPreferences.init(binding.root.context)
        list.addAll(MySharedPreferences.list)
        adapter = RvAdapter(list)
        binding.rv.adapter = adapter
        return binding.root
    }


}