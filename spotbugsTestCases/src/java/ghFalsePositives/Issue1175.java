package ghFalsePositives;

import edu.umd.cs.findbugs.annotations.NonNull;

import java.util.Objects;

public class Issue1175 {
    @NonNull
    public static Foo foo1() {
        final Foo foo = Foo.something(); // method not annotated, so could return null
        if (foo == null)
            throw new NullPointerException("Oops.");
        return foo;
    }
    @NonNull
    public static Foo foo2() {
        final Foo foo = Foo.something(); // method not annotated, so could return null
        return Objects.requireNonNull(foo, "Oops.");
    }
}
class Foo{
    static Foo something(){
        return null;
    }
}
