package uz.oybekdev.expandablelistview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import uz.oybekdev.expandablelistview.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val groupList = mutableListOf<String>()
        val childMap = mutableMapOf<String,List<String>>()
        repeat(5){groupId->
            groupList.add("Group $groupId")
            val childNumber = Random.nextInt(10)
            val tempList = mutableListOf<String>()
            repeat(childNumber){childId->
                tempList.add("Child $childId in Group $groupId")

            }
            childMap["Group $groupId"] = tempList
        }

        val adapter = CustomAdapter(this,groupList,childMap)
        binding.expandableListView.setAdapter(adapter)

        binding.expandableListView.setOnChildClickListener{ _, _, groupP, childP, _ ->
            val groupName = groupList[groupP]
            val childName = childMap[groupName]!![childP]
            Toast.makeText(this,childName,Toast.LENGTH_SHORT).show()
            true
        }

    }
}