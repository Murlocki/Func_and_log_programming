package DocumentCollection

import VehiclePassport.VehiclePassport
import java.util.*

class TreeSetDocument(val documents: TreeSet<VehiclePassport>): DocumentCollection<VehiclePassport>() {
    override fun searchDoc(doc: VehiclePassport): VehiclePassport? {
            return documents.find { it == doc }
    }
}