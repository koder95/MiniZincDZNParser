package pl.koder95.dznp.lang.lib;

import pl.koder95.dznp.lang.*;

import java.util.ArrayList;

public class Array3DFunction extends ReturningFunction {

    public Array3DFunction(String name, Namespace parameters) {
        super(name, parameters);
    }

    public Array3DFunction(String name) {
        super(name);
    }

    @Override
    public Object call(Var[] params) {
        if (params.length != 4) return null;
        Var last = params[3];
        if (last instanceof ArrayVar) {
            ArrayVar arrayVar = (ArrayVar) last;
            if (!(params[0] instanceof IntVar)) return null;
            if (!(params[1] instanceof IntVar)) return null;
            if (!(params[2] instanceof IntVar)) return null;
            return call((IntVar) params[0], (IntVar) params[1], (IntVar) params[2], arrayVar);
        }
        return null;
    }

    public Object[][][] call(IntVar param1, IntVar param2, IntVar param3, ArrayVar array) {
        return getArray3D(param1.getValue(), param2.getValue(), param3.getValue(), array.getValue());
    }

    private Object[][][] getArray3D(int layerFirstLength, int layerSecondLength, int layerThirdLength, ArrayList value) {
        Object[][][] array3d = new Object[layerFirstLength][][];
        for (int f = 0; f < array3d.length; f++) {
            Object[][] array2d = new Object[layerSecondLength][];
            for (int s = 0; f < array2d.length; s++) {
                Object[] array1d = new Object[layerThirdLength];
                for (int t = 0; t < array1d.length; t++) {
                    array1d[t] = value.get(t + s*layerSecondLength + f*layerFirstLength);
                }
                array2d[s] = array1d;
            }
            array3d[f] = array2d;
        }
        return array3d;
    }
}
