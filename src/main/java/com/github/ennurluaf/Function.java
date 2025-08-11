package com.github.ennurluaf;

import java.util.Arrays;

public interface Function {
    Object applyArgs(Object... args);

    @FunctionalInterface
    public interface Op0<R> extends Function {
        R apply();

        @SuppressWarnings("unchecked")
        default Object applyArgs(Object... args) {
            if (args.length != 0)
                throw new IllegalArgumentException("Op0 expects 0 arguments");
            return apply();
        }
    }

    @FunctionalInterface
    public interface Op1<T, R> extends Function {
        R apply(T a);

        @SuppressWarnings("unchecked")
        default Object applyArgs(Object... args) {
            if (args.length != 1)
                throw new IllegalArgumentException("Op1 expects 1 argument");
            return apply((T) args[0]);
        }
    }

    @FunctionalInterface
    public interface Op2<T, U, R> extends Function {
        R apply(T a, U b);

        @SuppressWarnings("unchecked")
        default Object applyArgs(Object... args) {
            if (args.length != 2)
                throw new IllegalArgumentException("Op2 expects 2 arguments");
            return apply((T) args[0], (U) args[1]);
        }
    }

    @FunctionalInterface
    public interface Op3<T, U, V, R> extends Function {
        R apply(T a, U b, V c);

        @SuppressWarnings("unchecked")
        default Object applyArgs(Object... args) {
            if (args.length != 3)
                throw new IllegalArgumentException("Op3 expects 3 arguments");
            return apply((T) args[0], (U) args[1], (V) args[2]);
        }
    }

    @FunctionalInterface
    public interface Op4<T, U, V, W, R> extends Function {
        R apply(T a, U b, V c, W d);

        @SuppressWarnings("unchecked")
        default Object applyArgs(Object... args) {
            if (args.length != 4)
                throw new IllegalArgumentException("Op4 expects 4 arguments");
            return apply((T) args[0], (U) args[1], (V) args[2], (W) args[3]);
        }
    }

    @FunctionalInterface
    public interface OpN<T, R> extends Function {
        R apply(T a, Object... rest);

        @SuppressWarnings("unchecked")
        default Object applyArgs(Object... args) {
            if (args.length < 1)
                throw new IllegalArgumentException("OpN expects at least 1 argument");
            return apply((T) args[0], Arrays.copyOfRange(args, 1, args.length));
        }
    }

    public static Object apply(Function function, Object... args) {
        return function.applyArgs(args);
    }

}
