/*
 * Copyright 2015 Skymind,Inc.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package org.nd4j.linalg.ops.transforms;

import org.nd4j.linalg.api.complex.IComplexNumber;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.ops.BaseElementWiseOp;
import org.nd4j.linalg.util.ComplexUtil;

/**
 * Power element wise op
 *
 * @author Adam Gibson
 */
public class Pow extends BaseElementWiseOp {


    private double power;
    private IComplexNumber powComplex;

    public Pow(Integer n) {
        this.power = n;
    }

    public Pow(Double n) {
        this.power = n;
    }

    public Pow(double power, IComplexNumber powComplex) {
        this.power = power;
        this.powComplex = powComplex;
    }

    public Pow(IComplexNumber powComplex) {
        this.powComplex = powComplex;
    }

    public Pow(double power) {
        this.power = power;
    }

    /**
     * The transformation for a given value (a scalar ndarray)
     *
     * @param value the value to applyTransformToOrigin (a scalar ndarray)
     * @param i     the index of the element being acted upon
     * @return the transformed value based on the input
     */
    @Override
    public Object apply(INDArray from, Object value, int i) {
        if (value instanceof IComplexNumber) {
            IComplexNumber n = (IComplexNumber) value;
            return ComplexUtil.pow(n, power);
        }
        double d = (double) value;
        return Math.pow(d, power);
    }
}