
package csci235;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public class HashTableSet<T> implements Set<T>
{

    private List<T>[] buckets;
    private int size;

    public HashTableSet(int numBuckets) {
        size = 0;
        buckets = createBucketArray( numBuckets );
    }


    // Construct an array of buckets of neededSize, and initialize it.

    private List<T> [] createBucketArray( int neededSize )
    {
        if( neededSize < 1 )
            throw new IllegalArgumentException( "nrbuckets must be greater than zero" );

        // Seems that this problem is unfixable:

        @SuppressWarnings("unchecked" )

        List<T>[] arr = new List[ neededSize ];

        for( int i = 0; i != neededSize; ++ i )
            arr[i] = new ArrayList<T>( );

        return arr;
    }

    private void rehash( int newNrBuckets ) {
        List<T>[] newBuckets = createBucketArray(newNrBuckets);

        for (List<T> bucket : buckets) {
            for (T t : bucket) {
                int index = abs(t.hashCode()) % newBuckets.length;
                for (int j = 0; j < newBuckets.length; j++) {
                    if (j == index) {
                        newBuckets[j].add(t);
                    }
                }
            }
        }
        buckets = newBuckets;
    }

    public boolean add(T value) {
        if(contains(value)) {
            return false;
        }

        int index = abs(value.hashCode()) % buckets.length;
        for(int i = 0; i < buckets.length; i++) {
            if(i == index) {
                buckets[i].add(value);
            }
        }
        size++;

        if(size > buckets.length * 4) {
            rehash(buckets.length * 4);
        }

        return true;
    }

    public boolean contains(T t) {
        int index = abs(t.hashCode()) % buckets.length;
        for(int i = 0; i < buckets.length; i++) {
            if(i == index) {
                for(T element : buckets[index]) {
                    if(element.equals(t)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public int getSize() {
        return size;
    }

    public void clear() {
        buckets = createBucketArray(4);
        size = 0;
    }

    public List<T> toList() {
        List<T> list = new ArrayList<>();
        for (List<T> bucket : buckets) {
            list.addAll(bucket);
        }
        return list;
    }

    public String toString() {

        StringBuilder res = new StringBuilder("{");
        int count = 0;
        for (List<T> bucket : buckets) {
            for (T t : bucket) {
                res.append(t);
                count++;
                if (count < size) {
                    res.append(", ");
                }
            }
        }
        res.append("}");
        return res.toString();
    }
}


