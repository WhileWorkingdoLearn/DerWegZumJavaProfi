package org.der.weg.zum.java.profi.StreamAPI.PrincipleOfStream;

import java.util.function.Function;

public interface IntermediateSteps<T> {
   TerminalOperations<T> filter();

   CustomStream<T> map(Function<?, ?> function);

   CustomStream<T> flatMaps();

   CustomStream<T> peek();

   CustomStream<T> distinct();

   CustomStream<T> sorted();
}
