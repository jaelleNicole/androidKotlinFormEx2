package fr.sebastienlaunay.formulaire

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_form.*

class FormActivity : AppCompatActivity() {

    private lateinit var mValidator: FormValidator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)

        /*****************************/
        /* Validation du formulaire  */
        /*****************************/
        mValidator = FormValidator()
            // Ajout vérification s'il y a un tel fixe ou un tel mobile, ou les 2 valides
            .addField(UFieldFirstValidOrSecondValidOrBoth(landLinePhone, mobilePhone))
            // Ajout vérification du mot de passe
            .addField(password)
            // Ajout vérification de la confirmation du mot de passe
            .addField(passwordConfirmation.isIdenticalTo(password))
            // Ajout vérification Année de naissance
            .addField(birthday)
            // Ajout vérification externe : Ici un checkbox
            .addValidator { checkBox.isChecked }
            // On valide la vérification du formulaire par l'activation du bouton btnValidateForm
            .validateWith(btnValidateForm)

        // On valide le formulaire
        mValidator.validate()

        // On a besoin de relancer manuellement la validation du formulaire à chaque changement de l'état du checkbox
        // car c'est une vérification "externe"
        checkBox.setOnClickListener {
            mValidator.validate()
        }


        btnValidateForm.setOnClickListener {
            resultat.setText("Le formulaire est valide !")
        }
    }
}
