package fr.sebastienlaunay.formulaire

class UFieldFirstValidOrSecondValidOrBoth(private val first: PersoTextInputLayout, private val second: PersoTextInputLayout) : UField {

    private var mUpdateListener: FormValidator.OnUpdateListener? = null

    init {
        val childrenListener: FormValidator.OnUpdateListener = object : FormValidator.OnUpdateListener {
            override fun onUpdated() {
                mUpdateListener?.onUpdated()
            }
        }
        first.setOnUpdateListener(childrenListener)
        second.setOnUpdateListener(childrenListener)
    }

    override fun isValid(): Boolean = first.isValid() && second.isEmpty()
            || first.isEmpty() && second.isValid()
            || first.isValid() && second.isValid()

    override fun setOnUpdateListener(listener: FormValidator.OnUpdateListener) {
        mUpdateListener = listener
    }

}