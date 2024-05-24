import java.time.LocalDate
import java.util.Date

class VehiclePassport(val series:Int, val number:Int, val issueDate: LocalDate) {
    override fun toString(): String {
        return "Серия:$series,Номер:$number,Дата выдачи:$issueDate"
    }

    override fun equals(other: Any?): Boolean =
        when{
            this===other->true;
            other !is VehiclePassport->false;
            else-> (compareBySeries(this,other)==0)&& (compareByNumber(this,other) == 0)
    }

    companion object{
        fun compareBySeries(p1:VehiclePassport,p2:VehiclePassport):Int = p1.series.compareTo(p2.series)
        fun compareByNumber(p1:VehiclePassport,p2:VehiclePassport):Int = p1.number.compareTo(p2.number)

    }
}