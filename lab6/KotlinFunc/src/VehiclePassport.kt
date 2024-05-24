import java.time.LocalDate
import java.util.Date

class VehiclePassport(val series:Int, val number:Int, val issueDate: LocalDate) {
    override fun toString(): String {
        return "Серия:$series,Номер:$number,Дата выдачи:$issueDate"
    }
}