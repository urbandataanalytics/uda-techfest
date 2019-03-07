package org.uda.techfest.numbers;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.transforms.Count;
import org.apache.beam.sdk.transforms.Filter;
import org.apache.beam.sdk.transforms.MapElements;
import org.apache.beam.sdk.values.TypeDescriptors;

public class SumNumbers {

    static boolean isPrime(Integer number){
        boolean flag = true;
        for(int i = 2; i <= number/2; ++i)
        {
            // condition for nonprime number
            if(number % i == 0)
            {
                flag = false;
                break;
            }
        }
        return flag;
    }

    static void SumNumbers(){
        Pipeline pipeline = Pipeline.create();
        pipeline.apply("Read File", TextIO.read().from("/files/techfest/numbers.txt"))
                .apply("Convert Strings to Integer", MapElements.into(TypeDescriptors.integers()).via((String s)-> Integer.valueOf(s)))
                .apply("Filter prime numbers", Filter.by((Integer i) -> isPrime(i)))
                .apply(Count.globally())
                .apply("Convert Long to String", MapElements.into(TypeDescriptors.strings()).via((Long i) -> String.valueOf(i)))
                .apply("Write output", TextIO.write().to("exit"));
        pipeline.run().waitUntilFinish();
    }

    public static void main(String args[]){
        SumNumbers();
    }
}
