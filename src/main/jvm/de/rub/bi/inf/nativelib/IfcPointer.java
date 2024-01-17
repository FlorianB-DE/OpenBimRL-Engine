package de.rub.bi.inf.nativelib;

import java.nio.charset.StandardCharsets;

import com.sun.jna.Memory;
import com.sun.jna.Pointer;

public class IfcPointer extends Pointer {

    public IfcPointer(long peer) {
        super(peer);
    }

    public IfcPointer(Pointer pointer) {
        super(Pointer.nativeValue(pointer));
    }

    public boolean isNull() {
        return peer == 0;
    }

    @Override
    public String toString() {
        final var len = 255;
        final var outString = new Memory(len);
        outString.clear();
        final var valid = FunctionsNative.getInstance().ifc_object_to_string(this, outString, len);
        return valid ? outString.getString(0, StandardCharsets.US_ASCII.name()) : "null";
    }    
}
