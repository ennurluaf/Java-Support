package com.github.ennurluaf;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.Spliterator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JSList<T> extends ArrayList<T> {

    public int limit = Integer.MAX_VALUE;

    public JSList() {
        super();
    }

    public JSList(int capacity) {
        super(capacity);
    }

    @SuppressWarnings("unchecked")
    public JSList(T... elements) {
        super(elements.length);
        this.addAll(Arrays.asList(elements));
    }

    public JSList(List<T> list) {
        super(list);
    }

    public <R> JSList<R> map(Function.Op1<T, R> function) {
        JSList<R> result = new JSList<>();
        for (T item : this) {
            result.add(function.apply(item));
        }
        return result;
    }

    public <R> JSList<R> map(Function.Op2<T, Integer, R> function) {
        JSList<R> result = new JSList<>();
        for (int i = 0; i < this.size(); i++) {
            result.add(function.apply(this.get(i), i));
        }
        return result;
    }

    public JSList<T> filter(Function.Op1<T, Boolean> predicate) {
        JSList<T> result = new JSList<>();
        for (T item : this) {
            if (predicate.apply(item)) {
                result.add(item);
            }
        }
        return result;
    }

    public T find(Function.Op1<T, Boolean> predicate) {
        for (T item : this) {
            if (predicate.apply(item)) {
                return item;
            }
        }
        return null;
    }

    public boolean some(Function.Op1<T, Boolean> predicate) {
        for (T item : this) {
            if (predicate.apply(item)) {
                return true;
            }
        }
        return false;
    }

    public boolean all(Function.Op1<T, Boolean> predicate) {
        for (T item : this) {
            if (!predicate.apply(item)) {
                return false;
            }
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    public void sort() {
        if (this.isEmpty() || !(this.get(0) instanceof Comparable)) {
            throw new IllegalStateException("Elements must implement Comparable or use a Comparator");
        }
        this.sort((Comparator<? super T>) Comparator.naturalOrder());
    }

    @Override
    public void sort(Comparator<? super T> comparator) {
        super.sort(comparator);
    }

    public T reduce(Function.Op2<T, T, T> accumulator) {
        if (this.isEmpty()) {
            throw new NoSuchElementException("Reduce of empty array with no initial value");
        }
        T result = this.get(0);
        for (int i = 1; i < this.size(); i++) {
            result = accumulator.apply(result, this.get(i));
        }
        return result;
    }

    public <U> U reduce(U identity, Function.Op2<U, T, U> accumulator) {
        U result = identity;
        for (T item : this) {
            result = accumulator.apply(result, item);
        }
        return result;
    }

    public <R> JSList<R> flatMap(Function.Op1<T, JSList<R>> mapper) {
        JSList<R> result = new JSList<>();
        for (T item : this) {
            result.addAll(mapper.apply(item));
        }
        return result;
    }

    public JSList<T> slice(int start, int end) {
        if (start < 0) {
            start = 0;
        }
        if (end > this.size()) {
            end = this.size();
        }
        return new JSList<>(this.subList(start, end));
    }

    public JSList<T> slice(int start) {
        return slice(start, this.size());
    }

    public void limit(int limit) {
        this.limit = limit;
    }

    public JSList<T> concat(JSList<T> other) {
        JSList<T> result = new JSList<>(this);
        result.addAll(other);
        return result;
    }

    @SuppressWarnings("unchecked")
    public JSList<T> concat(T... elements) {
        JSList<T> result = new JSList<>(this);
        Collections.addAll(result, elements);
        return result;
    }

    public JSList<T> reverse() {
        JSList<T> result = new JSList<>(this);
        Collections.reverse(result);
        return result;
    }

    public T first() {
        if (this.isEmpty()) {
            return null;
        }
        return this.get(0);
    }

    public T last() {
        if (this.isEmpty()) {
            return null;
        }
        return this.get(this.size() - 1);
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public int size() {
        return super.size();
    }

    @Override
    public T get(int index) {
        return super.get(index);
    }

    @Override
    public boolean add(T item) {
        return super.add(item);
    }

    @Override
    public void add(int index, T item) {
        super.add(index, item);
    }

    @Override
    public boolean remove(Object item) {
        return super.remove(item);
    }

    @Override
    public T remove(int index) {
        return super.remove(index);
    }

    @Override
    public void clear() {
        super.clear();
    }

    @Override
    public boolean contains(Object item) {
        return super.contains(item);
    }

    @Override
    public int indexOf(Object item) {
        return super.indexOf(item);
    }

    @Override
    public int lastIndexOf(Object item) {
        return super.lastIndexOf(item);
    }

    @Override
    public Object[] toArray() {
        return super.toArray();
    }

    @Override
    @SuppressWarnings("SuspiciousToArrayCall")
    public <R> R[] toArray(R[] a) {
        return super.toArray(a);
    }

    @Override
    public Iterator<T> iterator() {
        return super.iterator();
    }

    @Override
    public Spliterator<T> spliterator() {
        return super.spliterator();
    }

    @Override
    public Stream<T> stream() {
        return super.stream();
    }

    @Override
    public Stream<T> parallelStream() {
        return super.parallelStream();
    }

    public void forEach(Function.VoidOp1<? super T> action) {
        super.forEach(action::apply);
    }

    public void forEach(Function.VoidOp2<? super T, Integer> action) {
        for (int i = 0; i < this.size(); i++) {
            action.apply(this.get(i), i);
        }
    }

    public boolean removeIf(Function.Op1<? super T, Boolean> filter) {
        return super.removeIf(filter::apply);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof JSList)) {
            return false;
        }
        JSList<?> JSList = (JSList<?>) o;
        return super.equals(JSList);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @SuppressWarnings("unchecked")
    public static <T> JSList<T> of(T... elements) {
        return new JSList<>(elements);
    }

    public static <T> JSList<T> from(Collection<T> collection) {
        return new JSList<>(new ArrayList<>(collection));
    }

    public static <T> JSList<T> from(T[] array) {
        return new JSList<>(Arrays.asList(array));
    }

    public static <T> JSList<T> empty() {
        return new JSList<>();
    }

    public static <T> JSList<T> ofSize(int size) {
        return new JSList<>(Collections.nCopies(size, null));
    }

    public static <T> JSList<T> ofSize(int size, T defaultValue) {
        JSList<T> array = new JSList<>();
        for (int i = 0; i < size; i++) {
            array.add(defaultValue);
        }
        return array;
    }

    @SuppressWarnings("unchecked")
    public static JSList<Integer> range(int start, int end) {
        return range(start, end, 1);
    }

    @SuppressWarnings("unchecked")
    public static JSList<Integer> range(int start, int end, int step) {
        JSList<Integer> array = new JSList<>();
        for (int i = start; i < end; i += step) {
            array.add(i);
        }
        return array;
    }

    public static void iterate(int range, Function.Op1<Integer, Void> action) {
        for (int i = 0; i < range; i++) {
            action.apply(i);
        }
    }

    public static <T> JSList<T> repeat(T item, int times) {
        JSList<T> array = new JSList<>();
        for (int i = 0; i < times; i++) {
            array.add(item);
        }
        return array;
    }

    public static <T> JSList<T> fromStream(Stream<T> stream) {
        return stream.collect(Collectors.toCollection(JSList::new));
    }

    public static <T> JSList<T> fromIterable(Iterable<T> iterable) {
        JSList<T> array = new JSList<>();
        for (T item : iterable) {
            array.add(item);
        }
        return array;
    }

    public static <T> JSList<T> fromEnumeration(Enumeration<T> enumeration) {
        JSList<T> array = new JSList<>();
        while (enumeration.hasMoreElements()) {
            array.add(enumeration.nextElement());
        }
        return array;
    }

    public static <T> JSList<T> fromIterator(Iterator<T> iterator) {
        JSList<T> array = new JSList<>();
        while (iterator.hasNext()) {
            array.add(iterator.next());
        }
        return array;
    }

    public static <T> JSList<T> fromSpliterator(Spliterator<T> spliterator) {
        JSList<T> array = new JSList<>();
        spliterator.forEachRemaining(array::add);
        return array;
    }

    public static <T> JSList<T> fromCollection(Collection<T> collection) {
        return new JSList<>(new ArrayList<>(collection));
    }

    public static <T> JSList<T> fromArray(T[] array) {
        return new JSList<>(Arrays.asList(array));
    }

    public static <T> JSList<T> fromList(List<T> list) {
        return new JSList<>(list);
    }

    public static <T> JSList<T> fromSet(Set<T> set) {
        return new JSList<>(new ArrayList<>(set));
    }

    public static <T> JSList<T> fromMap(Map<?, T> map) {
        JSList<T> array = new JSList<>();
        for (T value : map.values()) {
            array.add(value);
        }
        return array;
    }

    public static <T> JSList<T> fromOptional(Optional<T> optional) {
        JSList<T> array = new JSList<>();
        optional.ifPresent(array::add);
        return array;
    }

    public static <T> JSList<T> fromOptional(Optional<T> optional, T defaultValue) {
        JSList<T> array = new JSList<>();
        array.add(optional.orElse(defaultValue));
        return array;
    }

    public static <T> JSList<T> fromStream(Stream<T> stream, int limit) {
        return stream.limit(limit).collect(Collectors.toCollection(JSList::new));
    }

}
