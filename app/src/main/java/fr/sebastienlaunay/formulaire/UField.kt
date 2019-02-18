package fr.sebastienlaunay.formulaire

interface UField {
    fun isValid() : Boolean
    fun setOnUpdateListener(listener : FormValidator.OnUpdateListener)
}