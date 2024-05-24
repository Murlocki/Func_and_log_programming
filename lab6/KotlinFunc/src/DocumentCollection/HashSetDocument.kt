package DocumentCollection

import VehiclePassport.VehiclePassport

class HashSetDocument(val documents: HashSet<VehiclePassport>): DocumentCollection<VehiclePassport>() {
    override fun searchDoc(doc: VehiclePassport): VehiclePassport? {
        return documents.find { it == doc }
    }
}