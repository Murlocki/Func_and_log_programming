import java.time.LocalDate
import java.util.*

class VehiclePassport(val series:Int, val number:Int, val issueDate: LocalDate):Comparable<VehiclePassport> {

    init {
        require(seriesRegex.matches(this.series.toString())) { "Invalid series: ${this.series}" }
        require(numberRegex.matches(this.number.toString())) { "Invalid number: ${this.number}" }
    }

    override fun toString(): String {
        return "Серия:TK $series,Номер:$number,Дата выдачи:$issueDate"
    }

    override fun compareTo(other: VehiclePassport): Int {
        return this.issueDate.compareTo(other.issueDate)
    }

    override fun hashCode(): Int {
        return Objects.hash(series, number)
    }

    override fun equals(other: Any?): Boolean =
        when{
            this===other->true;
            other !is VehiclePassport->false;
            else-> (compareBySeries(this,other)==0)&& (compareByNumber(this,other) == 0)
    }



    companion object{

        private val seriesRegex =   "^\\d{2}$".toRegex()
        private val numberRegex =   "^\\d{6}$".toRegex()

        fun compareBySeries(p1:VehiclePassport,p2:VehiclePassport):Int = p1.series.compareTo(p2.series)
        fun compareByNumber(p1:VehiclePassport,p2:VehiclePassport):Int = p1.number.compareTo(p2.number)

        fun compareBySeriesAndNumber(p1: VehiclePassport,p2: VehiclePassport):Int{
            val seriesComp = compareBySeries(p1,p2)
            if(seriesComp!=0){
                return seriesComp
            }
            return compareByNumber(p1,p2)
        }
    }
}