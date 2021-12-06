package com.wongnai.interview.movie;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Component
public class InvertedIndex {
    public static Map<String, List<Long>> iIdex;

    public InvertedIndex(){
        iIdex = new TreeMap<>();
    }

    public void addInvertedIndex(Movie movie){
        String[] s = movie.getName().split(" ");
        for (int i = 0; i < s.length; i++) {
            List<Long> idList = new ArrayList<>();
            idList.add(movie.getId());
            if(iIdex.containsKey(s[i].toLowerCase())){
                idList.addAll(iIdex.get(s[i].toLowerCase()));
            }
            iIdex.put(s[i].toLowerCase(),idList);
        }
    }


}
