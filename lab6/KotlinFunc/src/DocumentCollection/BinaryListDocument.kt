package DocumentCollection

import VehiclePassport.VehiclePassport

class BinaryListDocument(val documents: List<VehiclePassport>): DocumentCollection<VehiclePassport>() {
    override fun searchDoc(doc: VehiclePassport): VehiclePassport? {
        return searchBinaryList(doc,0,documents.size)
    }

    private fun processOneBinarySearch(doc:VehiclePassport,low: Int, high: Int): VehiclePassport? {
        val mid = low + (high - low) / 2
        val curDoc = documents[mid]
        val compareRes = doc.compareTo(curDoc)

        when (compareRes) {
            0 -> return curDoc
            1 -> return searchBinaryList(doc, mid + 1, high)
            else -> return searchBinaryList(doc, low, mid - 1)
        }
    }

    private fun searchBinaryList(doc:VehiclePassport,low: Int, high: Int): VehiclePassport? =
        when {
            (low > high) -> null;
            else -> processOneBinarySearch(doc,low,high)
            }
}