package com.tum.thebot.webapp.utils;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class StreamUtils {

    public static <T> Stream<T> toStream(Iterator<T> iterable) {
        return StreamSupport.stream( Spliterators.spliteratorUnknownSize( iterable
                , Spliterator.ORDERED),false) ;

    }
}
