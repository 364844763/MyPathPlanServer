package bean;

/**
 * Created by wang on 2016/5/5.
 */
public class Relation {
    String start;
    String end;

    public Relation(String start, String end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Relation)) return false;

        Relation that = (Relation) o;

        if (!end .equals(that.end) ) return false;
        if (!start .equals(that.start)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = start.hashCode();
        result = 31 * result + end.hashCode();
        return result;
    }
}
