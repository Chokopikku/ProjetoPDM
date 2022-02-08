package com.g19.projetopdm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.g19.projetopdm.data.ProgramDatabase
import com.g19.projetopdm.data.user.User
import com.g19.projetopdm.data.user.UserDao
import com.g19.projetopdm.data.user.UserViewModel
import com.g19.projetopdm.data.vehicle.Vehicle
import com.g19.projetopdm.data.vehicle.VehicleViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var scooterCard : CardView
    private lateinit var bicycleCard : CardView
    private lateinit var carCard : CardView
    private lateinit var bikeCard : CardView
    private lateinit var balanceText : TextView
    private lateinit var welcomeText : TextView
    private lateinit var historyButton : CardView
    private lateinit var tableButton : CardView
    lateinit var userViewModel : UserViewModel
    var balance : Float = 3.0f
    private var uid : Int = 0
    private lateinit var addFunds : CardView
    private lateinit var balanceMenu : CardView
    private lateinit var addFundsButton : CardView

    private lateinit var vehicleViewModel: VehicleViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide();
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        welcomeText = findViewById(R.id.welcomeText)
        balanceText = findViewById(R.id.balanceText)
        historyButton = findViewById(R.id.historyCard)
        historyButton.setOnClickListener { goToHistory() }
        tableButton = findViewById(R.id.tableCard)
        tableButton.setOnClickListener { goToTable() }

        carCard = findViewById(R.id.carCard)
        carCard.setOnClickListener{ openMap("0") }
        bicycleCard = findViewById(R.id.bicycleCard)
        bicycleCard.setOnClickListener{ openMap("1") }
        scooterCard = findViewById(R.id.scooterCard)
        scooterCard.setOnClickListener { openMap("2") }
        bikeCard = findViewById(R.id.bikeCard)
        bikeCard.setOnClickListener { openMap("3") }

        val extras = intent.extras
        if (extras != null) {
            uid = extras.getInt("uid")
        }

        checkBalance()

        addFunds = findViewById(R.id.addFunds)
        addFunds.setOnClickListener { openBalanceMenu() }
        balanceMenu = findViewById(R.id.addFundsMenu)
        addFundsButton = findViewById(R.id.addFundsButton)
        addFundsButton.setOnClickListener { addFunds() }
    }

    fun addData() {
        val vehicle1= Vehicle(0,"Carro",    "35Kw/h",    1    ,"Y","Tesla",    true)
        val vehicle2= Vehicle(1,"Trotinete",    "5Kw/h",    1    ,"Essencial","Xiaomi",    true)
        val vehicle3= Vehicle(2,"Mota",    "15Kw/h",    1    ,"Cruzer","Moti",    true)
        val vehicle4= Vehicle(3,"bicicleta",    "0Kw/h",    2    ,"City","Rodinhas",    true)
        val vehicle5= Vehicle(4,"Carro",    "35Kw/h",    1    ,"Y","Tesla",    true)
        val vehicle6= Vehicle(5,"Trotinete",    "5Kw/h",    2    ,"Essencial","Xiaomi",    true)
        val vehicle7= Vehicle(6,"Mota",    "15Kw/h",    3    ,"Cruzer","Moti",    true)
        val vehicle8= Vehicle(7,"bicicleta",    "0Kw/h",    4    ,"City","Rodinhas",    true)
        val vehicle9= Vehicle(8,"Carro",    "35Kw/h",    5    ,"Y","Tesla",    true)
        val vehicle10= Vehicle(9,"Trotinete",    "5Kw/h",    2    ,"Essencial","Xiaomi",    true)
        val vehicle11= Vehicle(10,"Mota",    "15Kw/h",    2    ,"Cruzer","Moti",    true)
        val vehicle12= Vehicle(11,"bicicleta",    "0Kw/h",    1    ,"City","Rodinhas",    true)
        val vehicle13= Vehicle(13,"Trotinete",    "5Kw/h",    8    ,"Essencial","Xiaomi",    true)
        val vehicle14= Vehicle(14,"Mota",    "15Kw/h",    1    ,"Cruzer","Moti",    true)
        val vehicle15= Vehicle(15,"bicicleta",    "0Kw/h",    1    ,"City","Rodinhas",    true)
        val vehicle16= Vehicle(16,"Carro",    "35Kw/h",    2    ,"Y","Tesla",    true)
        val vehicle17= Vehicle(17,"Trotinete",    "5Kw/h",    5    ,"Essencial","Xiaomi",    true)
        val vehicle18= Vehicle(18,"Mota",    "15Kw/h",    6    ,"Cruzer","Moti",    true)
        val vehicle19= Vehicle(19,"bicicleta",    "0Kw/h",    5    ,"City","Rodinhas",    true)
        val vehicle20= Vehicle(20,"Carro",    "35Kw/h",    6    ,"Y","Tesla",    true)
        val vehicle21= Vehicle(21,"Trotinete",    "5Kw/h",    6    ,"Essencial","Xiaomi",    true)
        val vehicle22= Vehicle(22,"Mota",    "15Kw/h",    6    ,"Cruzer","Moti",    true)
        val vehicle23= Vehicle(23,"bicicleta",    "0Kw/h",    3    ,"City","Rodinhas",    true)
        val vehicle24= Vehicle(24,"Carro",    "35Kw/h",    2    ,"Y","Tesla",    true)
        val vehicle25= Vehicle(25,"Trotinete",    "5Kw/h",    3    ,"Essencial","Xiaomi",    true)

        vehicleViewModel = ViewModelProvider(this).get(VehicleViewModel::class.java)
        vehicleViewModel.addVehicle(vehicle1)
        vehicleViewModel.addVehicle(vehicle2)
        vehicleViewModel.addVehicle(vehicle3)
        vehicleViewModel.addVehicle(vehicle4)
        vehicleViewModel.addVehicle(vehicle5)
        vehicleViewModel.addVehicle(vehicle6)
        vehicleViewModel.addVehicle(vehicle7)
        vehicleViewModel.addVehicle(vehicle8)
        vehicleViewModel.addVehicle(vehicle9)
        vehicleViewModel.addVehicle(vehicle10)
        vehicleViewModel.addVehicle(vehicle11)
        vehicleViewModel.addVehicle(vehicle12)
        vehicleViewModel.addVehicle(vehicle13)
        vehicleViewModel.addVehicle(vehicle14)
        vehicleViewModel.addVehicle(vehicle15)
        vehicleViewModel.addVehicle(vehicle16)
        vehicleViewModel.addVehicle(vehicle17)
        vehicleViewModel.addVehicle(vehicle18)
        vehicleViewModel.addVehicle(vehicle19)
        vehicleViewModel.addVehicle(vehicle20)
        vehicleViewModel.addVehicle(vehicle21)
        vehicleViewModel.addVehicle(vehicle22)
        vehicleViewModel.addVehicle(vehicle23)
    }

    private fun goToHistory(){
        val intent = Intent(this, HistoryActivity::class.java)
        intent.putExtra("uid", uid)
        startActivity(intent)
        finish()
    }

    private fun goToTable(){
        val intent = Intent(this, PriceTableActivity::class.java)
        intent.putExtra("uid", uid)
        startActivity(intent)
        finish()
    }

    private fun checkBalance(){
        lifecycleScope.launch(Dispatchers.IO) {
            val programDatabase: ProgramDatabase = ProgramDatabase.getDatabase(applicationContext)
            val userDao: UserDao = programDatabase.userDao()
            val user: User = userDao.getUser(uid)

            balance = user.balance
            balanceText.text = balance.toString() + "€"
            welcomeText.text = "Bem-vindo " + (user.name)
        }
    }

    private fun openMap(typeId: String){
        val intent = Intent(this, VehicleMapActivity::class.java)
        intent.putExtra("uid", uid)
        intent.putExtra("filter", typeId)
        startActivity(intent)
        finish()
    }

    private fun openBalanceMenu(){
        balanceMenu.isVisible = true
    }

    private fun addFunds(){
        val fundsInput: Float = (findViewById<EditText>(R.id.fundsInput).text.toString()).toFloat()

        lifecycleScope.launch(Dispatchers.IO) {
            val programDatabase: ProgramDatabase = ProgramDatabase.getDatabase(applicationContext)
            val userDao: UserDao = programDatabase.userDao()
            balance = userDao.getUser(uid).balance
            balance += fundsInput
            balanceText.text = balance.toString() + "€"
            userViewModel.updateBalance(uid, balance)
        }

        balanceMenu.isVisible = false
    }
}