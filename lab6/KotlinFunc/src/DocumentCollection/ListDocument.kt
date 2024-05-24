package DocumentCollection

import VehiclePassport.VehiclePassport

class ListDocument(val documents: List<VehiclePassport>): DocumentCollection<VehiclePassport>() {
    override fun searchDoc(doc: VehiclePassport): VehiclePassport? {
        return searchList(doc, documents.size)
    }
    private fun searchList(doc: VehiclePassport,maxIndex:Int,currentIndex:Int=0):VehiclePassport? =
        when{
            (currentIndex >= maxIndex)->null;
            (documents[currentIndex] == doc)->doc;
            else->searchList(doc, maxIndex, currentIndex + 1)
        }
}