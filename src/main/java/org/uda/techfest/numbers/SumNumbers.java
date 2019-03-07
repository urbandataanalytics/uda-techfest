package org.uda.techfest.numbers;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.TextIO;


public class SumNumbers {

    static void SumNumbers(){
        Pipeline pipeline = Pipeline.create();
        pipeline.apply("Read File", TextIO.read().from("/files/techfest/numbers.txt"))
                .apply("Write output", TextIO.write().to("exit"));
        pipeline.run().waitUntilFinish();
    }

    public static void main(String args[]){
        SumNumbers();
    }
}
