package DocumentCollection

import VehiclePassport.VehiclePassport

abstract class DocumentCollection<Doc : VehiclePassport> {
    abstract fun searchDoc(doc: Doc): Doc?

    fun measureSearchTime(doc: Doc): Long {
        val startTime = System.currentTimeMillis()
        searchDoc(doc)
        val endTime = System.currentTimeMillis()
        return endTime - startTime
    }
}