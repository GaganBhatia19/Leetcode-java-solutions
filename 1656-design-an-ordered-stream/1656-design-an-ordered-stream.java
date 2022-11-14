class OrderedStream {
    private List<String> stream;
    private int ptr, prevPtr;
    public OrderedStream(int n) {
        stream = new ArrayList<>();
        for(int i=0; i<n; i++) stream.add("");
        ptr = prevPtr = 0;
    }
    
    public List<String> insert(int idKey, String value) {
        idKey--;
        stream.set(idKey, value);
        List<String> list = new ArrayList<>();
        if(idKey == ptr) {
            while(ptr<stream.size() && !stream.get(ptr).equals("")) {
                list.add(stream.get(ptr++));
            }
        }
        return list;
    }
    
}

/**
 * Your OrderedStream object will be instantiated and called as such:
 * OrderedStream obj = new OrderedStream(n);
 * List<String> param_1 = obj.insert(idKey,value);
 */