package org.uda.techfest.numbers;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.transforms.MapElements;
import org.apache.beam.sdk.values.TypeDescriptors;


public class SumNumbers {

    static void SumNumbers(){
        Pipeline pipeline = Pipeline.create();
        pipeline.apply("Read File", TextIO.read().from("/files/techfest/numbers.txt"))
                .apply("Convert Strings to Integer", MapElements.into(TypeDescriptors.integers()).via((String s)-> Integer.valueOf(s)))
                .apply("Convert Integer to String", MapElements.into(TypeDescriptors.strings()).via((Integer i) -> String.valueOf(i)))
                .apply("Write output", TextIO.write().to("exit"));
        pipeline.run().waitUntilFinish();
    }

    public static void main(String args[]){
        SumNumbers();
    }
}
