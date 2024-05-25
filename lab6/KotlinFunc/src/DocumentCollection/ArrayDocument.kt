package DocumentCollection

import VehiclePassport.VehiclePassport

class ArrayDocument(val documents:Array<VehiclePassport>): DocumentCollection<VehiclePassport>() {
    override fun searchDoc(doc: VehiclePassport): VehiclePassport? {
        return searchArray(doc,documents.size)
    }


    private fun checkOutOfBounds(currentIndex: Int,maxIndex: Int) = currentIndex>maxIndex

    private tailrec fun searchArray(doc: VehiclePassport, maxIndex:Int, currentIndex:Int = 0):VehiclePassport? =
        when {
            (checkOutOfBounds(currentIndex,maxIndex))->null;
            (documents[currentIndex] == doc)->doc;
            else->searchArray(doc, maxIndex, currentIndex + 1)
        }

    }
