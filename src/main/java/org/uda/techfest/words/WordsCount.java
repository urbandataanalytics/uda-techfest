package org.uda.techfest.words;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.transforms.*;
import org.apache.beam.sdk.values.KV;
import org.apache.beam.sdk.values.TypeDescriptors;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import java.util.Arrays;


public class WordsCount {

    static class SplitLines extends DoFn<String, Iterable<String>> {

        @ProcessElement
        public void process(ProcessContext context){
            String words = context.element();
            context.output(Arrays.asList(words.split("[^\\p{L}]+")));
        }

    }

    static class TopWords implements Comparator<KV<String,Long>> , Serializable {

        @Override
        public int compare(KV<String,Long> k1, KV<String,Long> k2){
            if(k1.getValue() > k2.getValue()) return 1;
            else if(k1.getValue() == k2.getValue()) return 0;
            else return -1;
        }
    }

    static void WordsCount(){
        Pipeline p = Pipeline.create();
        p.apply("Read file", TextIO.read().from("/files/techfest/text.txt"))
         .apply("Split lines into words", ParDo.of(new SplitLines()))
         .apply("Flatten", Flatten.iterables())
         .apply("Normalize", MapElements.into(TypeDescriptors.strings())
                 .via((String s) -> s.trim().toLowerCase()))
         .apply("Remove words", Filter.by((String s) -> s.length() > 5))

         .apply("String to maps", MapElements.into(TypeDescriptors.kvs(TypeDescriptors.strings(), TypeDescriptors.longs())).via((String s) -> KV.of(s, 1l)))
         .apply("Sum the values", Sum.longsPerKey())
                //Map and Sum could be replaced by
                //.apply(Count.perElement())
         .apply("Find the Top", Top.of(10, new TopWords()))
         .apply("Flat values", Flatten.iterables())
         .apply("Convert to string", MapElements.into(TypeDescriptors.strings()).via((KV<String,Long> s) -> s.toString()))

        .apply("Write File", TextIO.write().to("wordsCount"));
        p.run().waitUntilFinish();
    }

    public static void main(String args[]){
        WordsCount();
    }
}
