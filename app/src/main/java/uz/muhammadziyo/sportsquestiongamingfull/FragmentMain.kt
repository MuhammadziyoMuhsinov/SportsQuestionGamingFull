package uz.muhammadziyo.sportsquestiongamingfull

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import uz.muhammadziyo.sportsquestiongamingfull.databinding.FragmentMainBinding
import uz.muhammadziyo.sportsquestiongamingfull.databinding.ItemDialogBinding
import uz.muhammadziyo.sportsquestiongamingfull.databinding.ItemNavviewBinding
import uz.muhammadziyo.sportsquestiongamingfull.kesh.MySharedPreferences
import uz.muhammadziyo.sportsquestiongamingfull.models.Game
import uz.muhammadziyo.sportsquestiongamingfull.models.Mydata
import uz.muhammadziyo.sportsquestiongamingfull.models.Mydata.ball
import uz.muhammadziyo.sportsquestiongamingfull.models.Mydata.correct
import uz.muhammadziyo.sportsquestiongamingfull.models.Mydata.incorrect
import uz.muhammadziyo.sportsquestiongamingfull.models.Question
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class FragmentMain : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private lateinit var list: ArrayList<Question>
    private lateinit var listGame: ArrayList<Game>
    private var position = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentMainBinding.inflate(layoutInflater)


        correct = 0
        incorrect = 0
        list = ArrayList()
        listGame = ArrayList()
        MySharedPreferences.init(binding.root.context)
        listGame.addAll(MySharedPreferences.list)

        loadList()
        loadQuestion()


        binding.cardAnswer1.setOnClickListener {
            chekQuestion(1)
        }
        binding.cardAnswer2.setOnClickListener {
            chekQuestion(2)
        }
        binding.cardAnswer3.setOnClickListener {
            chekQuestion(3)
        }
        binding.cardAnswer4.setOnClickListener {
            chekQuestion(4)
        }


        val itemNavviewBinding = ItemNavviewBinding.inflate(layoutInflater)

        itemNavviewBinding.history.setOnClickListener {
            findNavController().navigate(R.id.fragmentResults)
        }

        itemNavviewBinding.results.setOnClickListener {
            findNavController().navigate(R.id.fragmentHistory)
        }

        binding.navView.addHeaderView(itemNavviewBinding.root)

        binding.menu.setOnClickListener {
            binding.drawer.open()
        }







        return binding.root
    }

    private fun loadQuestion() {
        binding.question.text = list[position].question
        binding.txtAnswer1.text = list[position].answer1
        binding.txtAnswer2.text = list[position].answer2
        binding.txtAnswer3.text = list[position].answer3
        binding.txtAnswer4.text = list[position].answer4
    }

    private fun loadList() {

        list.add(Question("Which country has the most Olympic gold medals in swimming?",
            "China",
            "The USA",
            "The UK",
            "Australia",
            2
        ))

        list.add(Question("Of all the fighting sports below, which sport wasn’t practised by Bruce Lee?",
        "Wushu",
            "Boxing",
            "Jeet Kune Do",
            "Fencing",
            1
            ))

        list.add(Question("Where did the term “billiard” originated from?",
        "Italy",
            "Hungary",
            "Belgium",
            "France",
            4
            ))

        list.add(Question("Which former Belarus international played for Arsenal between 2005 and 2008?",
                "Alexander Hleb",
            "Maksim Romaschenko",
            "Valyantsin Byalkevich",
            "Yuri Zhenov",
            1
        ))

        list.add(Question("David Beckham became president of which newly founded club in 2018?",
        "Bergamo Calcio",
            "Inter Miami",
            "West London Blue",
            "The Potteries",
            2
        ))

        list.add(Question("Which team did Porto beat in the 2004 Champions League final?",
        "Bayern Munich",
            "Deportivo La Coruña",
            "Barcelona",
            "Monaco",
            4
            ))

        list.add(Question("Who is the current top scorer in the UEFA Champions League?",
        "Alan Shearer",
            "Thierry Henry",
            "Cristiano Ronaldo",
            "Robert Lewandowski",
            4
            ))

        list.add(Question("After losing a key player in the first game, which team went onto the semi-finals of Euro 2020?",
        "Denmark",
            "Spain",
            "Wales",
            "England",
            1
            ))

        list.add(Question("In 2011, a 5th tier match in Argentina saw a record number of red cards. How many were given out?",
        "6",
            "11",
            "22",
            "36",
            4
            ))

        list.add(Question("Which footballer holds the record for the highest number of assists in the Premier League?",
        "Cesc Fabregas",
            "Ryan Giggs",
            "Frank Lampard",
            "Paul Scholes",
            2
            ))

//        list.add(Question("Chelsea beat which team 8-0 to secure the 2009-10 Premier League title on the final day of the season?",
//        "Blackburn",
//            "Hull",
//            "Wigan",
//            "Norwich",
//            3
//            ))

    }

    private fun chekQuestion(answer: Int) {
        if (answer == list[position].correctAnswer) {
            if (list.size == position + 1) {
                Mydata.correct+=1
                Mydata.ball += 50

                alertDialog()
            } else {
                position += 1
                Mydata.correct+=1
                Mydata.ball += 50
                binding.txtBall.text = "ball: ${Mydata.ball}"
                Snackbar.make(binding.root, "Correct!", 1000).show()
                loadQuestion()
            }

        } else {
            if (list.size == position + 1) {
                Mydata.incorrect+=1
                alertDialog()
            } else {
             Mydata.incorrect+=1
                Snackbar.make(binding.root, "Wrong!", 1000).show()
                position += 1
                loadQuestion()

            }

        }
    }

    private fun alertDialog() {
        val alertDialog = AlertDialog.Builder(binding.root.context).create()
        val itemDialogBinding = ItemDialogBinding.inflate(LayoutInflater.from(binding.root.context))
        alertDialog.setView(itemDialogBinding.root)
        itemDialogBinding.ball.text = "total ball:  ${Mydata.ball}"
         position = 0

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val myDateObj = LocalDateTime.now()
                val myFormatObj: DateTimeFormatter =
                    DateTimeFormatter.ofPattern("dd-MM-yyyy")
                val formattedDate = myDateObj.format(myFormatObj)

                itemDialogBinding.date.text = formattedDate

                listGame.add(Game(listGame.size,
                    "${Mydata.correct}",
                    "${Mydata.incorrect}",
                    formattedDate))
                MySharedPreferences.list = listGame
                alertDialog.show()
            }
        }


    }

//    private fun alertdialog(){
//        val alertDialog = AlertDialog.Builder(binding.root.context).create()
//        val itemDialogBinding = ItemDialogBinding.inflate(LayoutInflater.from(binding.root.context))
//        itemDialogBinding.correct.text = "correct answer: $correct"
//        itemDialogBinding.incorrect.text = "incorrect answer: $incorrect"
//        itemDialogBinding.btnNewstart.setOnClickListener {
//            position = 0
//            loadQuestion()
//            alertDialog.cancel()
//            Mydata.incorrect=0
//            Mydata.correct=0
//        }
//        alertDialog.setView(itemDialogBinding.root)
//        alertDialog.show()
//    }

}


