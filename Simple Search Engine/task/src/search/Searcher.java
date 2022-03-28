package search;

import java.util.ArrayList;

public class Searcher {
    private SearchingMethod method;

    public void setMethod(SearchingMethod method) {
        this.method = method;
    }

    public void search(ArrayList<String> lines, ArrayList<String> words) {
        method.search(lines, words);
    }
}
