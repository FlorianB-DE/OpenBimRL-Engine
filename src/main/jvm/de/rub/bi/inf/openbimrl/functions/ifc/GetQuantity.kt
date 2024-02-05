package de.rub.bi.inf.openbimrl.functions.ifc

import de.rub.bi.inf.nativelib.IfcPointer
import de.rub.bi.inf.openbimrl.NodeProxy
import de.rub.bi.inf.openbimrl.engine.ifc.IIFCModel
import de.rub.bi.inf.openbimrl.functions.AbstractFunction
import de.rub.bi.inf.openbimrl.functions.NativeFunction

/**
 * Retrieves the quantity value of a specific entity.
 *
 * @author Marcel Stepien (reworked by Florian Becker)
 */
class GetQuantity(nodeProxy: NodeProxy?) : AbstractFunction(nodeProxy) {
    override fun execute(ifcModel:IIFCModel?) {
        val ifcElements = getInputAsCollection(0)
        val quantitySetName = getInput<String>(1)
        val quantityName = getInput<String>(2)

        val result = ifcElements.filterIsInstance<IfcPointer>().filter {
            if (!it.quantities.containsKey(quantitySetName)) return@filter false
            return@filter it.quantities[quantitySetName]!!.containsKey(quantityName)
        }.map {
            it.quantities[quantitySetName]!![quantityName]!!
        }

        setResult(0, result)
    }
}
