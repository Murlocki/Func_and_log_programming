package VehiclePassport

import java.time.LocalDate
import java.util.*

class VehiclePassport(val series:String, val number:String, val issueDate: LocalDate=LocalDate.now()):Comparable<VehiclePassport> {

    init {
        require(seriesRegex.matches(this.series)) { "Invalid series: ${this.series}" }
        require(numberRegex.matches(this.number.toString())) { "Invalid number: ${this.number}" }
    }

    override fun toString(): String {
        return "Серия:$series,Номер:$number,Дата выдачи:$issueDate"
    }


    override fun hashCode(): Int {
        return Objects.hash(series, number,issueDate.toEpochDay())
    }

    override fun equals(other: Any?): Boolean =
        when{
            this===other->true;
            other !is VehiclePassport->false;
            else-> (compareBySeries(this,other)==0) && (compareByNumber(this,other) == 0) && (this.issueDate.compareTo(other.issueDate)==0)
    }

    override fun compareTo(other: VehiclePassport): Int {
        val seriesNumberComparison = compareValuesBy(this, other, { it.series }, { it.number })
        if (seriesNumberComparison != 0) return seriesNumberComparison

        val issueDateComparison = issueDate.compareTo(other.issueDate)
        return issueDateComparison
    }

    companion object{

        private val seriesRegex =   "^\\d{2} Т[АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЬЫЪЭЮЯ]$".toRegex()
        private val numberRegex =   "^\\d{6}$".toRegex()

        fun compareBySeries(p1:VehiclePassport,p2:VehiclePassport):Int = p1.series.compareTo(p2.series)
        fun compareByNumber(p1:VehiclePassport,p2:VehiclePassport):Int = p1.number.compareTo(p2.number)

        fun compareByDate(p1:VehiclePassport,p2: VehiclePassport):Int = p1.issueDate.compareTo(p2.issueDate)

        private fun nullComp(p1: VehiclePassport?,p2: VehiclePassport?):Int =
            when{
                (p1==null)&&(p2==null)->0;
                (p1==null)->-1;
                (p2==null)->1;
                else->2
            }

        fun compareBySeriesAndNumber(p1: VehiclePassport?,p2: VehiclePassport?):Int{
            if(nullComp(p1,p2)!=2) return nullComp(p1,p2)

            val seriesComp = compareBySeries(p1!!,p2!!)
            if(seriesComp!=0){
                return seriesComp
            }
            return compareByNumber(p1,p2)
        }
    }
}