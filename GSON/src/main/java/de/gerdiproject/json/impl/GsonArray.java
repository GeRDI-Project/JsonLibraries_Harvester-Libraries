package de.gerdiproject.json.impl;


import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import de.gerdiproject.json.IJsonArray;
import de.gerdiproject.json.IJsonObject;


/**
 * This is a wrapper for the GSON implementation of JSON array. It wraps a
 * {@link JsonArray} and provides convenience functions.
 *
 * @author Robin Weiss
 *
 */
public class GsonArray implements IJsonArray
{
    protected JsonArray wrappedArray;


    /**
     * Simple constructor that initializes the wrapped JsonArray as an empty
     * {@link JsonArray}.
     */
    public GsonArray()
    {
        wrappedArray = new JsonArray();
    }


    /**
     * Constructor that requires an existing {@link JsonArray} to be specified.
     *
     * @param wrappedArray
     *            a {@link JsonArray} that will be wrapped by this class
     */
    public GsonArray(JsonArray wrappedArray)
    {
        this.wrappedArray = wrappedArray;
    }


    @Override
    public String toJsonString()
    {
        return wrappedArray.toString();
    }


    @Override
    public boolean isEmpty()
    {
        return wrappedArray.size() == 0;
    }


    @Override
    public boolean addAll(Collection<? extends Object> collection)
    {
        collection.forEach((Object o) -> add(o));
        return true;
    }


    @Override
    public boolean contains(Object obj)
    {
        return indexOf(obj) != -1;
    }


    private int indexOf(Object requestedObj)
    {
        int size = wrappedArray.size();

        for (int i = 0; i < size; i++) {
            if (get(i).equals(requestedObj))
                return i;
        }

        return -1;
    }


    @Override
    public boolean containsAll(Collection<?> collection)
    {
        boolean hasAll = true;

        for (Object obj : collection) {

            if (!contains(obj)) {
                hasAll = false;
                break;
            }
        }
        return hasAll;
    }


    @Override
    public Iterator<Object> iterator()
    {
        return new JsonIterator();
    }


    @Override
    public boolean remove(Object obj)
    {
        int index = indexOf(obj);

        if (index != -1) {
            wrappedArray.remove(index);
            return true;
        }

        return false;
    }


    @Override
    public boolean removeAll(Collection<?> collection)
    {
        boolean removedAll = true;

        for (Object obj : collection) {
            int index = indexOf(obj);

            if (index != -1)
                wrappedArray.remove(index);
            else
                removedAll = false;
        }
        return removedAll;
    }


    @Override
    public boolean retainAll(Collection<?> collection)
    {
        int size = wrappedArray.size();

        for (int i = 0; i < size; i++) {
            Object obj = get(i);

            if (!collection.contains(obj))
                wrappedArray.remove(i);
        }

        return true;
    }


    @Override
    public Object[] toArray()
    {
        final Object[] arr = new Object[wrappedArray.size()];

        for (int i = 0; i < arr.length; i++)
            arr[i] = get(i);

        return arr;
    }


    @SuppressWarnings("unchecked")
    @Override
    public <T> T[] toArray(T[] dest)
    {
        int size = size();

        // increase destination array size, if it is too small
        if (dest.length < size)
            dest = Arrays.copyOf(dest, size);

        try {
            // copy elements to the destination array
            for (int i = 0; i < size; i++)
                dest[i] = (T) get(i);

        } catch (ClassCastException ex) {
            // if any element is not of the specified type, throw this exception
            throw new ArrayStoreException();
        }

        // fill up the destination array with null, if it is bigger than the
        // source
        for (int i = size; i < dest.length; i++)
            dest[i] = null;

        return dest;
    }


    @Override
    public int size()
    {
        return wrappedArray.size();
    }


    @Override
    public boolean isNull(int index) throws ArrayIndexOutOfBoundsException
    {
        JsonElement ele = wrappedArray.get(index);
        return ele == null || ele.isJsonNull();
    }


    @Override
    public boolean isNonEmptyValue(int index) throws ArrayIndexOutOfBoundsException
    {
        JsonElement ele = wrappedArray.get(index);

        return ele != null
               && !ele.isJsonNull()
               && (!ele.isJsonArray() || ((JsonArray) ele).size() > 0)
               && (!ele.isJsonObject() || ((JsonObject) ele).size() > 0);
    }


    @Override
    public int getInt(int index) throws NullPointerException, ClassCastException, ArrayIndexOutOfBoundsException
    {
        return wrappedArray.get(index).getAsInt();
    }


    @Override
    public int getInt(int index, int defaultValue) throws ArrayIndexOutOfBoundsException
    {
        JsonElement ele = wrappedArray.get(index);
        if (ele != null
            && ele.isJsonPrimitive()
            && ele.getAsJsonPrimitive().isNumber())
            return ele.getAsInt();
        return defaultValue;
    }


    @Override
    public double getDouble(int index) throws NullPointerException, ClassCastException, ArrayIndexOutOfBoundsException
    {
        return wrappedArray.get(index).getAsDouble();
    }


    @Override
    public double getDouble(int index, double defaultValue) throws ArrayIndexOutOfBoundsException
    {
        JsonElement ele = wrappedArray.get(index);
        if (ele != null
            && ele.isJsonPrimitive()
            && ele.getAsJsonPrimitive().isNumber())
            return ele.getAsDouble();
        return defaultValue;
    }


    @Override
    public String getString(int index) throws NullPointerException, ClassCastException, ArrayIndexOutOfBoundsException
    {
        return wrappedArray.get(index).getAsString();
    }


    @Override
    public String getString(int index, String defaultValue) throws ArrayIndexOutOfBoundsException
    {
        JsonElement ele = wrappedArray.get(index);
        if (ele != null
            && ele.isJsonPrimitive()
            && ele.getAsJsonPrimitive().isString())
            return ele.getAsString();
        return defaultValue;
    }


    @Override
    public boolean getBoolean(int index)
    throws NullPointerException, ClassCastException, ArrayIndexOutOfBoundsException
    {
        return wrappedArray.get(index).getAsBoolean();
    }


    @Override
    public boolean getBoolean(int index, boolean defaultValue) throws ArrayIndexOutOfBoundsException
    {
        JsonElement ele = wrappedArray.get(index);
        if (ele != null
            && ele.isJsonPrimitive()
            && ele.getAsJsonPrimitive().isBoolean())
            return ele.getAsBoolean();
        return defaultValue;
    }


    @Override
    public IJsonObject getJsonObject(int index) throws ClassCastException, ArrayIndexOutOfBoundsException
    {
        JsonElement ele = wrappedArray.get(index);
        return (ele != null && !ele.isJsonNull()) ? new GsonObject(ele.getAsJsonObject()) : null;
    }


    @Override
    public IJsonObject getJsonObject(int index, IJsonObject defaultValue) throws ArrayIndexOutOfBoundsException
    {
        JsonElement ele = wrappedArray.get(index);
        if (ele != null && ele.isJsonObject())
            return new GsonObject(ele.getAsJsonObject());
        return defaultValue;
    }


    @Override
    public IJsonArray getJsonArray(int index) throws ClassCastException, ArrayIndexOutOfBoundsException
    {
        JsonElement ele = wrappedArray.get(index);
        return (ele != null && !ele.isJsonNull()) ? new GsonArray(ele.getAsJsonArray()) : null;
    }


    @Override
    public IJsonArray getJsonArray(int index, IJsonArray defaultValue) throws ArrayIndexOutOfBoundsException
    {

        JsonElement ele = wrappedArray.get(index);
        if (ele != null && ele.isJsonArray())
            return new GsonArray(ele.getAsJsonArray());
        return defaultValue;
    }


    @Override
    public Object get(int index) throws ArrayIndexOutOfBoundsException
    {
        return GsonUtils.gsonToObject(wrappedArray.get(index));
    }


    @Override
    public Object get(int index, Object defaultValue) throws ArrayIndexOutOfBoundsException
    {
        Object value = GsonUtils.gsonToObject(wrappedArray.get(index));
        return (value != null) ? value : defaultValue;
    }


    @Override
    public Object put(int index, Object value) throws ArrayIndexOutOfBoundsException
    {
        Object oldValue = get(index);

        if (value instanceof GsonObject)
            wrappedArray.set(index, ((GsonObject) value).wrappedObject);

        else if (value instanceof GsonArray)
            wrappedArray.set(index, ((GsonArray) value).wrappedArray);

        else if (value instanceof String)
            wrappedArray.set(index, new JsonPrimitive((String) value));

        else if (value instanceof Number)
            wrappedArray.set(index, new JsonPrimitive((Number) value));

        else if (value instanceof Boolean)
            wrappedArray.set(index, new JsonPrimitive((Boolean) value));

        return oldValue;
    }


    @Override
    public Object putNotNull(int index, Object value) throws ArrayIndexOutOfBoundsException
    {
        if (value != null)
            return put(index, value);
        return null;
    }


    @Override
    public boolean add(Object value)
    {
        if (value instanceof GsonObject)
            wrappedArray.add(((GsonObject) value).wrappedObject);

        else if (value instanceof GsonArray)
            wrappedArray.add(((GsonArray) value).wrappedArray);

        else if (value instanceof String)
            wrappedArray.add((String) value);

        else if (value instanceof Number)
            wrappedArray.add((Number) value);

        else if (value instanceof Boolean)
            wrappedArray.add((Boolean) value);

        return true;
    }


    @Override
    public void addNotNull(Object value)
    {
        if (value != null)
            add(value);
    }


    @Override
    public void addAll(Iterable<?> values)
    {
        values.forEach((Object value) -> add(value));
    }


    @Override
    public void addAllNotNull(Iterable<?> values)
    {
        values.forEach((Object value) -> {
            if (value != null)
            {
                add(value);
            }
        });
    }


    @Override
    public Object remove(int index) throws ArrayIndexOutOfBoundsException
    {
        JsonElement oldValue = wrappedArray.remove(index);
        return GsonUtils.gsonToObject(oldValue);
    }


    @Override
    public void clear()
    {
        int i = wrappedArray.size();
        while (i != 0) {
            wrappedArray.remove(0);
            i--;
        }
    }

    /**
     * Custom iterator class for iterating the Json object.
     *
     * @author Robin Weiss
     *
     */
    protected class JsonIterator implements Iterator<Object>
    {
        int index = 0;
        final int size = size();


        @Override
        final public boolean hasNext()
        {
            return index != size;
        }


        @Override
        final public Object next()
        {
            if (!hasNext())
                throw new NoSuchElementException();
            return get(index++);
        }


        @Override
        public void remove()
        {
            throw new UnsupportedOperationException();
        }
    }

}
