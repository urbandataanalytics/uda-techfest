package org.uda.techfest.numbers;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.TextIO;


public class SumNumbers {

    static void SumNumbers(){
        Pipeline pipeline = Pipeline.create();
        //Your code
        pipeline.run().waitUntilFinish();
    }

    public static void main(String args[]){
        SumNumbers();
    }
}
