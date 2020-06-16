package fr.isen.bertrand.valentin.droidpizza

import android.app.TimePickerDialog
import android.content.Intent
import android.content.res.Resources
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_commande.*


class Commande : AppCompatActivity() {


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_commande)

        val button_choisir_heure = findViewById(R.id.choicehl) as Button
        val button_valider_commande = findViewById(R.id.validercommande) as Button
        val button_retour = findViewById(R.id.imageButton) as ImageButton
        val mPickTimeBtn = findViewById<Button>(R.id.choicehl)
        val textView = findViewById<TextView>(R.id.heureLivraison)
        val res: Resources = resources
        val pizzaList = res.getStringArray(R.array.pizzas)
        val pizza = choixPizza.getSelectedItem().toString()
        var heure_changee = 0


// bouton pour aller voir le résumé de la commande
        button_valider_commande.setOnClickListener {
            val intent = Intent(this, ConfirmationCommande::class.java)
            val envoi_pizza = choixPizza.getSelectedItem().toString()
            if (envoi_pizza == pizza){
                val toast = Toast.makeText(applicationContext, "Pizza invalide", Toast.LENGTH_LONG)
                toast.show()
            }
            else if (heure_changee == 0) {
                val toast = Toast.makeText(applicationContext, "Heure invalide", Toast.LENGTH_LONG)
                toast.show()
            }
            else {
                startActivity(intent)
            }


        }

// bouton de retour a la page d'inscription
        button_retour.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

// Definition du choix de la pizza avec un Spinner
        choixPizza.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, pizzaList)
        choixPizza.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

            }
        }


// Definition de l'heure de livraison avec un TimePicker
        mPickTimeBtn.setOnClickListener {
            val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)
                textView.text = SimpleDateFormat("HH:mm").format(cal.time)
            }
            TimePickerDialog(
                this,
                timeSetListener,
                cal.get(Calendar.HOUR_OF_DAY),
                cal.get(Calendar.MINUTE),
                true
            ).show()
            heure_changee = 1

        }
    }
}