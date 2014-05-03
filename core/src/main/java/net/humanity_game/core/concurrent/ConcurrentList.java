package net.humanity_game.core.concurrent;

import java.util.*;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ConcurrentList<T> implements List<T> {
    private final List<T> list;
    private final ReadWriteLock lock;

    public ConcurrentList() {
        this.list = new ArrayList<>();
        this.lock = new ReentrantReadWriteLock();
    }

    public ConcurrentList(Collection<T> base) {
        this();
        this.addAll(base);
    }

    @Override
    public int size() {
        this.lock.readLock().lock();

        int size = -1;

        try {
            size = this.list.size();
        } finally {
            this.lock.readLock().unlock();
        }

        return size;
    }

    @Override
    public boolean isEmpty() {
        boolean empty = true;
        this.lock.readLock().lock();
        try {
            empty = this.list.isEmpty();
        } finally {
            this.lock.readLock().unlock();
        }
        return empty;
    }

    @Override
    public boolean contains(Object o) {
        boolean contains = false;
        this.lock.readLock().lock();
        try {
            contains = this.list.contains(o);
        } finally {
            this.lock.readLock().unlock();
        }
        return contains;
    }

    @Override
    public Iterator<T> iterator() {
        this.lock.readLock().lock();
        Iterator<T> it = null;

        try {
            it = new ArrayList<T>(this.list).iterator();
        } finally {
            this.lock.readLock().unlock();
        }

        return it;
    }

    @Override
    public Object[] toArray() {
        Object[] arr = null;
        this.lock.readLock().lock();
        try {
            arr = this.list.toArray();
        } finally {
            this.lock.readLock().unlock();
        }
        return arr;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean add(T t) {
        this.lock.writeLock().lock();
        boolean add = false;
        try {
            add = this.list.add(t);
        } finally {
            this.lock.writeLock().unlock();
        }
        return add;
    }

    @Override
    public boolean remove(Object o) {
        this.lock.writeLock().lock();
        boolean remove = false;
        try {
            remove = this.list.remove(o);
        } finally {
            this.lock.writeLock().unlock();
        }
        return remove;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        this.lock.readLock().lock();
        boolean contains = false;
        try {
            contains = this.list.containsAll(c);
        } finally {
            this.lock.readLock().unlock();
        }
        return contains;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        this.lock.writeLock().lock();
        boolean add = false;
        try {
            this.list.addAll(c);
        } finally {
            this.lock.writeLock().unlock();
        }
        return add;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        this.lock.writeLock().lock();
        boolean add = false;
        try {
            this.list.addAll(index, c);
        } finally {
            this.lock.writeLock().unlock();
        }
        return add;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean removed = false;
        this.lock.writeLock().lock();
        try {
            removed = this.list.removeAll(c);
        } finally {
            this.lock.writeLock().unlock();
        }

        return removed;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        this.lock.writeLock().lock();
        try {
            this.list.clear();
        } finally {
            this.lock.writeLock().unlock();
        }
    }

    @Override
    public T get(int index) {
        T get = null;
        this.lock.readLock().lock();
        try {
            get = this.list.get(index);
        } finally {
            this.lock.readLock().unlock();
        }

        return get;
    }

    @Override
    public T set(int index, T element) {
        T set = null;
        this.lock.writeLock().lock();
        try {
            set = this.list.set(index, element);
        } finally {
            this.lock.writeLock().unlock();
        }
        return set;
    }

    @Override
    public void add(int index, T element) {
        this.lock.writeLock().lock();
        this.list.add(index, element);
        this.lock.writeLock().unlock();
    }

    @Override
    public T remove(int index) {
        T remove = null;
        this.lock.writeLock().lock();
        try {
            remove = this.list.remove(index);
        } finally {
            this.lock.writeLock().unlock();
        }

        return remove;
    }

    @Override
    public int indexOf(Object o) {
        int index = -1;
        this.lock.readLock().lock();
        try {
            index = this.list.indexOf(o);
        } finally {
            this.lock.readLock().unlock();
        }
        return index;
    }

    @Override
    public int lastIndexOf(Object o) {
        int index = -1;
        this.lock.readLock().lock();
        try {
            index = this.list.lastIndexOf(o);
        } finally {
            this.lock.readLock().unlock();
        }
        return index;
    }

    @Override
    public ListIterator<T> listIterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        List<T> sub = null;
        this.lock.readLock().lock();
        try {
            sub = this.list.subList(fromIndex, toIndex);
        } finally {
            this.lock.readLock().unlock();
        }
        return sub;
    }
}
