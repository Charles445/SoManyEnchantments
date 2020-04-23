package com.Shultrea.Rin.Utility_Sector;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class TaggedList<E, T> extends ArrayList<E> {
    
    Map<E, Set<T>> tags = new HashMap();
    
    public boolean add (E e, T tag) {
        
        if (!tags.containsKey(e))
            tags.put(e, new HashSet<T>());
        
        tags.get(e).add(tag);
        return super.add(e);
    }
    
    public boolean add (E e, Collection<? extends T> taglst) {
        
        if (!tags.containsKey(e))
            tags.put(e, new HashSet<T>());
        
        tags.get(e).addAll(taglst);       
        return super.add(e);
    }
    
    public Set<T> getTags (E e) {
        
        Set<T> ret = tags.get(e);
        
        if (ret == null && this.contains(e)) {
            
            tags.put(e, new HashSet<T>());
            ret = tags.get(e);
        }
        
        return ret;
    }
    
    public Set<T> getTags (int index) {
        
        return this.getTags(this.get(index));
    }
    
    public void addTag (E e, T tag) {
        
        if (this.contains(e) && !tags.containsKey(e))
            tags.put(e, new HashSet<T>());
            
        tags.get(e).add(tag);
    }
    
    public void addTag (int index, T tag) {
        
        this.addTag(this.get(index), tag);
    }
    
    public void removeTag (E e, T tag) {
        
        if (this.contains(e) && !tags.containsKey(e))
            tags.put(e, new HashSet<T>());
            
        tags.get(e).remove(tag);
    }
    
    public void removeTag (int index, T tag) {
        
        this.removeTag(this.get(index), tag);
    }
    
    public Set<E> getEntries (T tag) {
        
        Set<E> ret = new HashSet();
        for (Entry<E, Set<T>> s : tags.entrySet())
            if (s.getValue().contains(tag))
                ret.add(s.getKey());
        
        return ret;
    }
    
    public void removeEntries (T tag) {
        
        for (E e : this.getEntries(tag))
            this.remove(e);
    }
    
    public String getTagsAsString (E e) {
        
        String ret = "";
        for (T s : tags.get(e))
            ret += s.toString() + ",";
            
        if (ret.length() > 0)
            ret = ret.substring(0, ret.length() - 1);
            
        return ret;
    }
    
    @Override
    public void clear () {
        
        tags.clear();
        super.clear();
    }
    
    @Override
    public E set (int index, E element) {
        
        tags.remove(this.get(index));
        return super.set(index, element);
    }
    
    @Override
    public E remove (int index) {
        
        tags.remove(this.get(index));
        return super.remove(index);
    }
    
    @Override
    public boolean remove (Object o) {
        
        tags.remove(o);
        return super.remove(o);
    }
    
    @Override
    public boolean removeAll (Collection<?> c) {
        
        for (Object o : c)
            tags.remove(o);
            
        return super.removeAll(c);
    }
    
    @Override
    protected void removeRange (int fromIndex, int toIndex) {
        
        for (int i = fromIndex; i < toIndex; i++)
            tags.remove(this.get(i));
            
        super.removeRange(fromIndex, toIndex);
    }
    
    @Override
    public boolean retainAll (Collection<?> c) {
        
        for (E e : tags.keySet())
            if (!c.contains(e))
                tags.remove(e);
                
        return super.retainAll(c);
    }
}