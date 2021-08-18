package com.nicootech.nytimes2.util;

import android.util.Log;

import com.nicootech.nytimes2.models.Docs;

import java.util.List;

public class Testing {

    public static void printArticles(String tag, List<Docs> list){
        for(Docs doc: list){
            Log.d(tag, "printRecipes: " + doc.getSnippet());
        }
    }
}
