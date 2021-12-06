package advent.of.code.day_06

class Lanternfish(
    var daysToReproduce: Int = DAYS_TO_REPRODUCE_FOR_NEW_BORN
) {
    fun age1Day(): Lanternfish? {
        daysToReproduce -= 1
        if(daysToReproduce < 0) {
            val newBorn = Lanternfish()
            daysToReproduce = DAYS_TO_REPRODUCE_AFTER_REPRODUCING
            return newBorn
        }

        return null
    }


    override fun toString(): String {
        return daysToReproduce.toString()
    }

    companion object {
        const val DAYS_TO_REPRODUCE_FOR_NEW_BORN = 8
        const val DAYS_TO_REPRODUCE_AFTER_REPRODUCING = 6
    }
}
