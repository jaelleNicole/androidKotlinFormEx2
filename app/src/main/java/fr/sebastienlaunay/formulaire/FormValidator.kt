package fr.sebastienlaunay.formulaire

import android.view.View

class FormValidator {

    private val fields = ArrayList<UField>()
    private val validators = ArrayList<() -> Boolean>()
    private var button: View? = null
    private var listener: View.OnClickListener? = null

    var isValid: Boolean = false
        set(valid) {
            field = valid
            button?.isEnabled = valid
        }

    fun addField(field: UField): FormValidator {
        fields.add(field)
        field.setOnUpdateListener(object : OnUpdateListener {
            override fun onUpdated() {
                update()
            }
        })
        update()
        return this
    }

    fun addValidator(validator: () -> Boolean): FormValidator {
        validators.add(validator)
        update()
        return this
    }

    fun validateWith(button: View): FormValidator {
        this.button = button
        this.button?.setOnClickListener { listener?.onClick(this.button) }
        update()
        return this
    }

    fun validate() {
        update()
    }

    private fun update() {
        val fieldsValidity = fields.map { it.isValid() }.fold(true, { f1, f2 -> f1 && f2 })
        val validatorsValidity = validators.map { it.invoke() }.fold(true, { f1, f2 -> f1 && f2 })
        isValid = (fieldsValidity && validatorsValidity)
    }

    interface OnUpdateListener {
        fun onUpdated()
    }

}
